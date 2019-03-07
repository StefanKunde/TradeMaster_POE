package gui;

import com.sun.jna.Native;
import config.Config;
import connector.SearchParameter;
import items.CurrencyOffers;
import items.Map;
import items.PoeNinjaPrices;
import items.TradeableBulk;
import listener.*;
import listener.bulkMaps.*;
import listener.currency.*;
import listener.singleMaps.*;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.User32;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class MainFrame extends JDialog implements IHideable {
    private Logger LOG = LoggerFactory.getLogger(MainFrame.class);

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private PoeNinjaPrices poeNinjaPrices;
    private Point mouseClickPoint; // Will reference to the last pressing (not clicking) position
    private User32 user32 = User32.INSTANCE;

    @Getter
    @Setter
    private TradeableBulk tradeables;
    @Getter
    @Setter
    private CurrencyOffers currencyOffers;

    private boolean isVisible;
    @Getter
    @Setter
    private List<Map> maps;
    @Getter
    @Setter
    private List<Map> tradeableMaps;
    @Getter
    private SearchParameter searchBuilder;
    @Getter
    @Setter
    private String currency = "";
    private String mapName = "";
    private boolean selectedCurrency = false;
    private boolean selectedTier = false;
    private boolean selectedMap = false;

    @Getter
    @Setter
    private boolean validAmountInput = false;
    @Getter
    @Setter
    private boolean validMaxPayInput = false;
    @Getter
    @Setter
    private boolean validAmountCurrencyInput = false;

    @Getter
    @Setter
    private boolean userWantsMinimize = false;
    private JTabbedPane tabbedPane;

    @Getter
    private PanelSingleMaps singleMapsPanel;
    @Getter
    private PanelBulkMaps panelBulkMaps;
    @Getter
    private PanelCurrencyBuyer currencyBuyerPanel;
    @Getter
    private PanelCurrencyPolling currencyPollerPanel;
    @Getter
    private PanelSettings settingsPanel;

    public MainFrame(PoeNinjaPrices poeNinjaPrices) {
        this.poeNinjaPrices = poeNinjaPrices;

        tradeables = new TradeableBulk();
        maps = new ArrayList<>();
        tradeableMaps = new ArrayList<>();
        searchBuilder = new SearchParameter();
        singleMapsPanel = new PanelSingleMaps();
        panelBulkMaps = new PanelBulkMaps();
        currencyBuyerPanel = new PanelCurrencyBuyer();
        currencyPollerPanel = new PanelCurrencyPolling();
        settingsPanel = new PanelSettings();
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        initFrame();
        this.setTitle("MapTrado Main");
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        addEventsForDragging();
        loadMapsFromJson(Config.get().getAllMaps());
        loadCurrencyFromJson();
    }

    private void initFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
            LOG.error("Mainframe::initFrame failure", e1);
        }

        tabbedPane.setBackground(new Color(32, 178, 170));
        getContentPane().add(tabbedPane, BorderLayout.NORTH);
        tabbedPane.addTab(singleMapsPanel.getTabTitle(), singleMapsPanel);
        tabbedPane.addTab(panelBulkMaps.getTabTitle(), panelBulkMaps);
        tabbedPane.addTab(currencyBuyerPanel.getTabTitle(), currencyBuyerPanel);
        tabbedPane.addTab(currencyPollerPanel.getTabTitle(), currencyPollerPanel);
        tabbedPane.addTab(settingsPanel.getTabTitle(), settingsPanel);

        this.setPreferredSize(new Dimension(390, 343));
        this.setForeground(Color.GRAY);
        this.setFont(new Font("Calibri", Font.PLAIN, 12));
        this.setBackground(Color.GRAY);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(390, 343);
        this.setLocationRelativeTo(null);
        this.getContentPane().requestFocusInWindow();
        this.setAlwaysOnTop(true);
        SwingUtilities.updateComponentTreeUI(this);
        isVisible = false;
        this.setVisible(false);
        this.setResizable(false);
        setDefaultLookAndFeelDecorated(true);

        // Bulk Maps Panel Listeners
        AmountTxtBoxListener amountListener = new AmountTxtBoxListener(this);
        ElderChBoxListener elderCbListener = new ElderChBoxListener(this);
        ShapedChBoxListener shapedCbListener = new ShapedChBoxListener(this);
        PricePerMapTxtBoxListener pricePerMapListener = new PricePerMapTxtBoxListener(this);
        UpdateButtonBulksListener updateBulkListener = new UpdateButtonBulksListener(this);
        MapCmbBoxBulksListener mapCmbListener = new MapCmbBoxBulksListener(this);
        MapsBulksCmbListener currencyBulksCmbListener = new MapsBulksCmbListener(this);
        NextButtonBulksListener nextTradeBulksListener = new NextButtonBulksListener(this);
        panelBulkMaps.getTxt_amount_bulks().getDocument().addDocumentListener(amountListener);
        panelBulkMaps.getChckbxShapedMap().addActionListener(shapedCbListener);
        panelBulkMaps.getChckbxElderMap().addActionListener(elderCbListener);
        panelBulkMaps.getTxtbox_pricePerMap().getDocument().addDocumentListener(pricePerMapListener);
        panelBulkMaps.getBtn_nextTrade_bulks().addActionListener(nextTradeBulksListener);
        panelBulkMaps.getCmb_currency_bulks().addActionListener(currencyBulksCmbListener);
        panelBulkMaps.getCmb_maps_bulks().addActionListener(mapCmbListener);
        panelBulkMaps.getUpdateButton().addActionListener(updateBulkListener);

        // Currency Panel Listeners
        NeededAmountTxtBoxListener neededAmountListener = new NeededAmountTxtBoxListener(this);
        MaxPayTxtBoxListener maxPayListener = new MaxPayTxtBoxListener(this);
        UpdateButtonCurrencyListener updateCurrencyListener = new UpdateButtonCurrencyListener(this);
        NextButtonCurrencyListener nextCurrencyListener = new NextButtonCurrencyListener(this);
        CurrencyTabCmbBoxWantListener wantListener = new CurrencyTabCmbBoxWantListener(this);
        CurrencyTabCmbBoxPayListener payListener = new CurrencyTabCmbBoxPayListener(this);
        currencyBuyerPanel.getTxtCurrencyTabNeededAmount().getDocument().addDocumentListener(neededAmountListener);
        currencyBuyerPanel.getTxtCurrencyTabMaxPay().getDocument().addDocumentListener(maxPayListener);
        currencyBuyerPanel.getUpdateButton().addActionListener(updateCurrencyListener);
        currencyBuyerPanel.getBtnNextTradeCurrencyTab().addActionListener(nextCurrencyListener);
        currencyBuyerPanel.getCmbCurrencyTabWant().addActionListener(wantListener);
        currencyBuyerPanel.getCmbCurrencyTabPay().addActionListener(payListener);

        setupCurrencyPollingListeners();

        // Single Maps Panel Listeners
        CurrencyComboboxListener currencyListener = new CurrencyComboboxListener(this);
        CorruptedCheckBoxListener corruptedBoxListener = new CorruptedCheckBoxListener(this);
        UpdateButtonListener updateListener = new UpdateButtonListener(this);
        WhiteCheckBoxListener whiteBoxListener = new WhiteCheckBoxListener(this);
        NextButtonListener nextTradeListener = new NextButtonListener(this);
        TierComboboxListener tierListener = new TierComboboxListener(this);
        MapComboboxListener mapListener = new MapComboboxListener(this);
        singleMapsPanel.getChckbx_white().addActionListener(whiteBoxListener);
        singleMapsPanel.getUpdateButton().addActionListener(updateListener);
        singleMapsPanel.getChckbx_corrupted().addActionListener(corruptedBoxListener);
        singleMapsPanel.getBtn_nextTrade().addActionListener(nextTradeListener);
        singleMapsPanel.getCmb_currency().addActionListener(currencyListener);
        singleMapsPanel.getCmb_tier().addActionListener(tierListener);
        tierListener.loadMapsFromSelectedTier("Tier 1");
        singleMapsPanel.getCmb_map().addActionListener(mapListener);

        // All exit button listeners
        ExitButtonListener exitListener = new ExitButtonListener(this);
        singleMapsPanel.getBtnExit().addActionListener(exitListener);
        settingsPanel.getBtnExit().addActionListener(exitListener);
        panelBulkMaps.getBtnExit().addActionListener(exitListener);
        currencyBuyerPanel.getBtnExit().addActionListener(exitListener);
        currencyPollerPanel.getBtnExit().addActionListener(exitListener);

        // All minimize button listeners
        MinimizeButtonListener minimizeListener = new MinimizeButtonListener(this);
        singleMapsPanel.getBtnMinimize().addActionListener(minimizeListener);
        settingsPanel.getBtnMinimize().addActionListener(minimizeListener);
        panelBulkMaps.getBtnMinimize().addActionListener(minimizeListener);
        currencyBuyerPanel.getBtnMinimize().addActionListener(minimizeListener);
        currencyPollerPanel.getBtnMinimize().addActionListener(minimizeListener);

        LeagueChangeListener leagueChangeListener = new LeagueChangeListener(this);
        UpdateSettingsListener updateSettingsListener = new UpdateSettingsListener(this);
        settingsPanel.getUpdateButton().addActionListener(updateSettingsListener);
        settingsPanel.getLeagueSelection().addItemListener(leagueChangeListener);

        AutomateTradesChkBoxListener automateTradeChBxListener = new AutomateTradesChkBoxListener(this);
        panelBulkMaps.getChckbxAutomateTrading().addActionListener(automateTradeChBxListener);
        currencyBuyerPanel.getChckbxAutomateTrading().addActionListener(automateTradeChBxListener);
        singleMapsPanel.getChckbxAutomateTrading().addActionListener(automateTradeChBxListener);
    }

    private void setupCurrencyPollingListeners() {
        NeededAmountTxtBoxListener neededAmountListener = new NeededAmountTxtBoxListener(this);
        MaxPayTxtBoxListener maxPayListener = new MaxPayTxtBoxListener(this);
        UpdateButtonCurrencyListener updateCurrencyListener = new UpdateButtonCurrencyListener(this);
        NextButtonCurrencyListener nextCurrencyListener = new NextButtonCurrencyListener(this);
        CurrencyTabCmbBoxWantListener wantListener = new CurrencyTabCmbBoxWantListener(this);
        CurrencyTabCmbBoxPayListener payListener = new CurrencyTabCmbBoxPayListener(this);

        currencyPollerPanel.getTxtCurrencyTabNeededAmount().getDocument().addDocumentListener(neededAmountListener);
        currencyPollerPanel.getTxtCurrencyTabMaxPay().getDocument().addDocumentListener(maxPayListener);
        currencyPollerPanel.getUpdateButton().addActionListener(updateCurrencyListener);
        currencyPollerPanel.getBtnNextTradeCurrencyTab().addActionListener(nextCurrencyListener);
        currencyPollerPanel.getCmbCurrencyTabWant().addActionListener(wantListener);
        currencyPollerPanel.getCmbCurrencyTabPay().addActionListener(payListener);
    }


    public void setForegroundWindow(final String titleName) {
        user32.EnumWindows((hWnd, arg1) -> {
            byte[] windowText = new byte[512];
            user32.GetWindowTextA(hWnd, windowText, 512);
            String wText = Native.toString(windowText);

            if (wText.isEmpty()) {
                return true;
            }
            if (wText.equals(titleName)) {
                user32.SetForegroundWindow(hWnd);
                return false;
            }
            return true;
        }, null);
    }

    public void loadMapsFromJson(String[] maps) {
        LOG.debug("selectedTier " + selectedTier);

        List<String> allMapsAsList = Arrays.asList(maps);
        Collections.sort(allMapsAsList, String.CASE_INSENSITIVE_ORDER);
        panelBulkMaps.getCmb_maps_bulks().removeAllItems();

        for (int i = 0; i < allMapsAsList.size(); i++) {
            panelBulkMaps.getCmb_maps_bulks().addItem(allMapsAsList.get(i));
        }
    }

    public void loadCurrencyFromJson() {
        List<String> currencysAsList = new ArrayList<>();
        List<String> bulkCurrencyAsList = new ArrayList<>();

        JSONArray poeTradeNames = Config.get().getPoeTradeCurrencies().names();
        for (int i = 0; i < poeTradeNames.length(); i++) {
            currencysAsList.add(poeTradeNames.get(i).toString());
        }

        JSONArray bulkCurrencyNames = Config.get().getBulkCurrencies().names();
        for (int i = 0; i < bulkCurrencyNames.length(); i++) {
            bulkCurrencyAsList.add(bulkCurrencyNames.get(i).toString());
        }

        Collections.sort(currencysAsList, String.CASE_INSENSITIVE_ORDER);
        Collections.sort(bulkCurrencyAsList, String.CASE_INSENSITIVE_ORDER);

        for (int i = 0; i < currencysAsList.size(); i++) {
            getCurrencyBuyerPanel().getCmbCurrencyTabWant().addItem(currencysAsList.get(i));
            getCurrencyBuyerPanel().getCmbCurrencyTabPay().addItem(currencysAsList.get(i));
            getCurrencyPollerPanel().getCmbCurrencyTabWant().addItem(currencysAsList.get(i));
        }
        for (int i = 0; i < bulkCurrencyAsList.size(); i++) {
            getCurrencyPollerPanel().getCmbCurrencyTabPay().addItem(bulkCurrencyAsList.get(i));
        }
    }

    private void addEventsForDragging() {
        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseClickPoint = e.getPoint(); // update the position
            }
        });

        tabbedPane.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point newPoint = e.getLocationOnScreen();
                newPoint.translate(-mouseClickPoint.x, -mouseClickPoint.y); // Moves the point by given values from its location
                setLocation(newPoint); // set the new location
            }
        });
    }


    public void setFrameVisible() {
        this.setVisible(true);
    }

    public void setFrameInvisible() {
        this.setVisible(false);
    }

    public boolean isFrameVisible() {
        return this.isVisible();
    }

    public User32 getUser32() {
        return user32;
    }

    public void setUser32(User32 user32) {
        this.user32 = user32;
    }


}
