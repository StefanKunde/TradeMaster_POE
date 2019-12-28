package gui;

import app.Main;
import com.sun.jna.Native;
import com.sun.jna.PointerType;
import app.Config;
import connector.CurrencyPoeTradeFetcher;
import handler.CurrencyPoeTradeHandler;
import listener.currencyPolling.*;
import listener.live.LiveStartListener;
import model.SearchParameterModel;
import items.CurrencyOffers;
import items.PoeTradeResultModel;
import items.PoeNinjaPrices;
import items.TradeableBulk;
import listener.*;
import listener.bulkMaps.*;
import listener.currency.*;
import listener.settings.LeagueChangeListener;
import listener.settings.UpdateSettingsListener;
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
import java.util.concurrent.ScheduledFuture;

public class MainFrame extends JDialog {

    private Logger LOG = LoggerFactory.getLogger(MainFrame.class);

    @Getter
    @Setter
    private ScheduledFuture pollingTimerThread;

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

    @Getter
    @Setter
    private List<PoeTradeResultModel> maps;
    @Getter
    @Setter
    private List<PoeTradeResultModel> tradeableMaps;
    @Getter
    private SearchParameterModel searchBuilder;
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
    private LivePanel livePanel;

    @Getter
    private PanelSettings settingsPanel;

    public MainFrame(PoeNinjaPrices poeNinjaPrices) {
        this.poeNinjaPrices = poeNinjaPrices;

        tradeables = new TradeableBulk();
        maps = new ArrayList<>();
        tradeableMaps = new ArrayList<>();
        searchBuilder = new SearchParameterModel();
        singleMapsPanel = new PanelSingleMaps();
        panelBulkMaps = new PanelBulkMaps();
        currencyBuyerPanel = new PanelCurrencyBuyer();
        currencyPollerPanel = new PanelCurrencyPolling();
        livePanel = new LivePanel();
        settingsPanel = new PanelSettings();
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        initFrame();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("MapTrado Main");
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        addEventsForDragging();
        loadMapsFromJson(Config.get().getAllMaps());
        loadCurrencyFromJson();
        setupThreadAndShowFrame();
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
        tabbedPane.addTab(livePanel.getTabTitle(), livePanel);
        tabbedPane.addTab(settingsPanel.getTabTitle(), settingsPanel);

        setPreferredSize(new Dimension(370, 343));
        setForeground(Color.GRAY);
        setFont(new Font("Calibri", Font.PLAIN, 12));
        setBackground(Color.GRAY);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(370, 343);
        setLocationRelativeTo(null);
        getContentPane().requestFocusInWindow();
        setAlwaysOnTop(true);
        SwingUtilities.updateComponentTreeUI(this);
        setVisible(false);
        setResizable(false);
        setDefaultLookAndFeelDecorated(true);

        // Live Panel
        LiveStartListener liveListener = new LiveStartListener(this);
        listener.live.NextButtonListener liveNextbutton = new listener.live.NextButtonListener(this);
        livePanel.getUpdateButton().addActionListener(liveListener);
        livePanel.getBtnNextTrade().addActionListener(liveNextbutton);

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

        // Populate Generator with first cut data.
        getSearchBuilder().setMapName(singleMapsPanel.getCmb_map().getSelectedItem().toString());
        getSearchBuilder().setCurrency(singleMapsPanel.getCmb_currency().getSelectedItem().toString());
        getSearchBuilder().setTier(singleMapsPanel.getCmb_tier().getSelectedItem().toString());
        getSearchBuilder().setRarity(singleMapsPanel.getChckbx_white().isSelected() ? "normal" : "");
        getSearchBuilder().setCorrupted(singleMapsPanel.getChckbx_corrupted().isSelected());

        // All exit button listeners
        ExitButtonListener exitListener = new ExitButtonListener(this);
        singleMapsPanel.getBtnExit().addActionListener(exitListener);
        settingsPanel.getBtnExit().addActionListener(exitListener);
        panelBulkMaps.getBtnExit().addActionListener(exitListener);
        currencyBuyerPanel.getBtnExit().addActionListener(exitListener);
        currencyPollerPanel.getBtnExit().addActionListener(exitListener);

        // All minimize button listeners
        MinimizeButtonListener minimizeListener = new MinimizeButtonListener(this, new MinimizedFrame(this));
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
        LOG.debug("Initializing Polling Listeners");
        UpdateCurrencyPollingListener updateButtonCurrencyListener = new UpdateCurrencyPollingListener(this);
        NextButtonCurrencyPollingListener nextButtonCurrencyPollingListener = new NextButtonCurrencyPollingListener(this);
        MinimumAmountTxtBoxListener minimumAmountTxtBoxListener = new MinimumAmountTxtBoxListener(this);
        MaximumPricePpuTxtBoxListener maximumPricePpuTxtBoxListener = new MaximumPricePpuTxtBoxListener(this);
        WhatDoIWantComboBoxListener whatDoIWantComboBoxListener = new WhatDoIWantComboBoxListener(this);
        WhatDoIPayComboBoxListener whatDoIPayComboBoxListener = new WhatDoIPayComboBoxListener(this);
        PollingTimeListener pollingTimeListener = new PollingTimeListener(this);
        EnablePollingCheckboxListener enablePollingCheckboxListener = new EnablePollingCheckboxListener(this);
        currencyPollerPanel.getTxtCurrencyTabNeededAmount().getDocument().addDocumentListener(minimumAmountTxtBoxListener);
        currencyPollerPanel.getTxtCurrencyTabMaxPay().getDocument().addDocumentListener(maximumPricePpuTxtBoxListener);
        currencyPollerPanel.getPollingInMillis().getDocument().addDocumentListener(pollingTimeListener);
        currencyPollerPanel.getCheckboxEnablePolling().addActionListener(enablePollingCheckboxListener);
        currencyPollerPanel.getUpdateButton().addActionListener(updateButtonCurrencyListener);
        currencyPollerPanel.getBtnNextTradeCurrencyTab().addActionListener(nextButtonCurrencyPollingListener);
        currencyPollerPanel.getCmbCurrencyTabWant().addActionListener(whatDoIWantComboBoxListener);
        currencyPollerPanel.getCmbCurrencyTabPay().addActionListener(whatDoIPayComboBoxListener);
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

    public void setupThreadAndShowFrame() {
        Main.scheduleThreadTimer(() -> {
            byte[] windowText = new byte[512];
            PointerType hwnd = user32.GetForegroundWindow();
            User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);

            String activeWindowTitle = Native.toString(windowText);

//            if (!isVisible()) {
//                setVisible(true);
//            }

            if (Native.toString(windowText).equals("Path of Exile")) {
                if (!Main.isMinimised() && !isVisible()) {
                    setVisible(true);
                    setForegroundWindow("Path of Exile");
                }
                return;
            }
            boolean isActiveWindowMain = activeWindowTitle.equals("MapTrado Main");
            boolean isActiveWindowMini = activeWindowTitle.equals("MapTrado Mini");

            if (!isActiveWindowMain && !isActiveWindowMini && isVisible()) {
                setVisible(false);
            }
            if (Main.isMinimised() && isVisible()) {
                setVisible(false);
            }
        }, 0 , 700);
    }


    public CurrencyPoeTradeHandler executeCurrencySearch(PanelCurrencyBuyer currencyPanel) {
        String wantedCurrency = currencyPanel.getCmbCurrencyTabWant().getSelectedItem().toString();
        String currencyToPayWith = currencyPanel.getCmbCurrencyTabPay().getSelectedItem().toString();

        // Get amount I want
        String wantedAmountString = currencyPanel.getTxtCurrencyTabNeededAmount().getText();

        int wantedAmount = Integer.valueOf(wantedAmountString);
        // Get Max price i want to pay
        double maxPrice;
        try {
            maxPrice = Double.valueOf(currencyPanel.getTxtCurrencyTabMaxPay().getText());
        } catch (NumberFormatException nfe) {
            LOG.debug("Invalid max-price, default to 0");
            maxPrice = 0;
        }

        // Prepare request: Get ids from selected currencies
        String wantedCurrencyID = "";
        String currencyToPayWithID = "";

        JSONArray poeTradeNames = Config.get().getPoeTradeCurrencies().names();

        for (int i = 0; i < poeTradeNames.length(); i++) {
            if (poeTradeNames.get(i).equals(wantedCurrency)) {
                wantedCurrencyID = (String) Config.get().getPoeTradeCurrencies().opt(poeTradeNames.get(i).toString());
            }
            if (poeTradeNames.get(i).equals(currencyToPayWith)) {
                currencyToPayWithID = (String) Config.get().getPoeTradeCurrencies().opt(poeTradeNames.get(i).toString());
            }
        }

        // Send request to poe.currency.trade
        CurrencyPoeTradeFetcher tradeFetcher = new CurrencyPoeTradeFetcher();
        String response = tradeFetcher.sendGet(wantedAmountString, wantedCurrencyID, currencyToPayWithID);

        // Load all offers from html response
        CurrencyPoeTradeHandler handler = new CurrencyPoeTradeHandler(response);

        // Filter out possible trades
        handler.filterTradesByUserInput(wantedAmount, maxPrice);
        handler.calculateFilteredPricesByAmount(wantedAmount);
        handler.getFilteredOffers().generateTradeMessages(wantedAmount);

        for (int i = 0; i < handler.getFilteredOffers().getAllOffersAsList().size(); i++) {
            LOG.debug(handler.getFilteredOffers().getAllOffersAsList().get(i).getTradeMessage());
        }

        // Add tradeables to nextbutton
        setCurrencyOffers(handler.getFilteredOffers());

        // display tradeables amount
        getCurrencyPollerPanel().getTradeables().setText("Tradeables: " + getCurrencyOffers().getAllOffersAsList().size());

        return handler;
    }
}
