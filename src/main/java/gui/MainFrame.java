package gui;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.stefank.Main;
import com.sun.jna.Native;

import connector.SearchParameter;
import items.CurrencyOffers;
import items.Map;
import items.PoeNinjaPrices;
import items.TradeableBulk;
import listener.AmountTxtBoxListener;
import listener.CorruptedCheckBoxListener;
import listener.CurrencyBulksCmbListener;
import listener.CurrencyComboboxListener;
import listener.CurrencyTabCmbBoxPayListener;
import listener.CurrencyTabCmbBoxWantListener;
import listener.ElderChBoxListener;
import listener.ExitButtonListener;
import listener.MapCmbBoxBulksListener;
import listener.MapComboboxListener;
import listener.MaxPayTxtBoxListener;
import listener.MinimizeButtonListener;
import listener.NeededAmountTxtBoxListener;
import listener.NextButtonBulksListener;
import listener.NextButtonCurrencyListener;
import listener.NextButtonListener;
import listener.PricePerMapTxtBoxListener;
import listener.ShapedChBoxListener;
import listener.TierComboboxListener;
import listener.UpdateButtonBulksListener;
import listener.UpdateButtonCurrencyListener;
import listener.UpdateButtonListener;
import listener.WhiteCheckBoxListener;
import utility.User32;
import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.JRootPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class MainFrame extends JDialog implements IHideable {
	private static final long serialVersionUID = 1L;
	PoeNinjaPrices poeNinjaPrices;
	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private static final Charset ISO = Charset.forName("ISO-8859-1");
	String[] currencys = { "ANY", "chaos", "alch", "chisel", "vaal", "fuse" };
	private Point mouseClickPoint; // Will reference to the last pressing (not clicking) position
	private User32 user32 = User32.INSTANCE;
	TradeableBulk tradeables;
	CurrencyOffers currencyOffers;
	
	boolean isVisible;
	List<Map> maps;
	List<Map> tradeableMaps;
	SearchParameter searchBuilder;
	String currency = "";
	String mapName = "";
	boolean selectedCurrency = false;
	boolean selectedTier = false;
	boolean selectedMap = false;
	boolean validAmountInput = false;
	boolean validPricePerMapInput = false;
	boolean loadedShapedMaps = false;
	boolean loadedElderMaps = false;
	boolean validMaxPayInput = false;
	boolean validAmountCurrencyInput = false;
	boolean userWantsMinimize = false;
	private JTabbedPane tabbedPane;
	
	PanelSingleMaps singleMapsPanel;
	PanelBulkMaps panelBulkMaps;
	PanelCurrencyBuyer currencyBuyerPanel;
	
	public MainFrame(PoeNinjaPrices poeNinjaPrices) {
		this.poeNinjaPrices = poeNinjaPrices;
		tradeables = new TradeableBulk();
		maps = new ArrayList<Map>();
		tradeableMaps = new ArrayList<Map>();
		searchBuilder = new SearchParameter();
		singleMapsPanel = new PanelSingleMaps();
		panelBulkMaps = new PanelBulkMaps();
		currencyBuyerPanel = new PanelCurrencyBuyer();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		initFrame();
		this.setTitle("MapTrado Main");
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		addEventsForDragging();
		loadMapsFromJson();
		loadCurrencyFromJson();
	}
	
	private void initFrame() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		tabbedPane.setBackground(new Color(32, 178, 170));
		getContentPane().add(tabbedPane, BorderLayout.NORTH);
		tabbedPane.add("Buy single maps", singleMapsPanel);
		tabbedPane.addTab("Buy bulks of maps", null, panelBulkMaps, null);
		tabbedPane.addTab("Currency", null, currencyBuyerPanel, null);
		
		this.setPreferredSize(new Dimension(400, 200));
		this.setForeground(Color.GRAY);
		this.setFont(new Font("Calibri", Font.PLAIN, 12));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(366, 343);
		this.setLocationRelativeTo(null);
		this.getContentPane().requestFocusInWindow();
        this.setAlwaysOnTop(true);
		SwingUtilities.updateComponentTreeUI(this);
		isVisible = false;
		this.setVisible(false);
		this.setResizable(false);
		setDefaultLookAndFeelDecorated(true);
		
		// Add Listeners
		AmountTxtBoxListener amountListener = new AmountTxtBoxListener(this);
		getPanelBulkMaps().getTxt_amount_bulks().getDocument().addDocumentListener(amountListener);
		
		NeededAmountTxtBoxListener neededAmountListener = new NeededAmountTxtBoxListener(this);
		currencyBuyerPanel.getTxt_currencyTab_neededAmount().getDocument().addDocumentListener(neededAmountListener);
		
		MaxPayTxtBoxListener maxPayListener = new MaxPayTxtBoxListener(this);
		currencyBuyerPanel.getTxt_currencyTab_MAXpay().getDocument().addDocumentListener(maxPayListener);
		
		PricePerMapTxtBoxListener pricePerMapListener = new PricePerMapTxtBoxListener(this);
		getPanelBulkMaps().getTxtbox_pricePerMap().getDocument().addDocumentListener(pricePerMapListener);
		
		CorruptedCheckBoxListener corruptedBoxListener = new CorruptedCheckBoxListener(this);
		singleMapsPanel.getChckbx_corrupted().addActionListener(corruptedBoxListener);
		
		WhiteCheckBoxListener whiteBoxListener = new WhiteCheckBoxListener(this);
		singleMapsPanel.getChckbx_white().addActionListener(whiteBoxListener);
		
		ShapedChBoxListener shapedCbListener = new ShapedChBoxListener(this);
		getPanelBulkMaps().getChckbxShapedMap().addActionListener(shapedCbListener);
		
		ElderChBoxListener elderCbListener = new ElderChBoxListener(this);
		getPanelBulkMaps().getChckbxElderMap().addActionListener(elderCbListener);
		
		ExitButtonListener exitListener = new ExitButtonListener(this);
		singleMapsPanel.getBtn_exit().addActionListener(exitListener);
		getPanelBulkMaps().getBtn_exit_bulks().addActionListener(exitListener);
		currencyBuyerPanel.getBtn_exit_cur().addActionListener(exitListener);
		
		MinimizeButtonListener minimizeListener = new MinimizeButtonListener(this);
		singleMapsPanel.getBtn_minimize().addActionListener(minimizeListener);
		getPanelBulkMaps().getBtn_minimize_bulks().addActionListener(minimizeListener);
		currencyBuyerPanel.getBtn_minimize_cur().addActionListener(minimizeListener);
		
		UpdateButtonListener updateListener = new UpdateButtonListener(this);
		singleMapsPanel.getBtn_update().addActionListener(updateListener);
		
		UpdateButtonBulksListener updateBulkListener = new UpdateButtonBulksListener(this);
		getPanelBulkMaps().getBtn_update_bulkbuyer().addActionListener(updateBulkListener);
		
		UpdateButtonCurrencyListener updateCurrencyListener = new UpdateButtonCurrencyListener(this);
		currencyBuyerPanel.getBtn_update_currency().addActionListener(updateCurrencyListener);
		
		NextButtonListener nextTradeListener = new NextButtonListener(this);
		singleMapsPanel.getBtn_nextTrade().addActionListener(nextTradeListener);
		
		NextButtonBulksListener nextTradeBulksListener = new NextButtonBulksListener(this);
		getPanelBulkMaps().getBtn_nextTrade_bulks().addActionListener(nextTradeBulksListener);
		
		NextButtonCurrencyListener nextCurrencyListener = new NextButtonCurrencyListener(this);
		currencyBuyerPanel.getBtn_nextTrade_currencyTab().addActionListener(nextCurrencyListener);
		
		CurrencyComboboxListener currencyListener = new CurrencyComboboxListener(this);
		singleMapsPanel.getCmb_currency().addActionListener(currencyListener);
		
		CurrencyBulksCmbListener currencyBulksCmbListener = new CurrencyBulksCmbListener(this);
		getPanelBulkMaps().getCmb_currency_bulks().addActionListener(currencyBulksCmbListener);
		
		MapCmbBoxBulksListener mapCmbListener = new MapCmbBoxBulksListener(this);
		getPanelBulkMaps().getCmb_maps_bulks().addActionListener(mapCmbListener);
		
		TierComboboxListener tierListener = new TierComboboxListener(this);
		singleMapsPanel.getCmb_tier().addActionListener(tierListener);
		
		MapComboboxListener mapListener = new MapComboboxListener(this);
		singleMapsPanel.getCmb_map().addActionListener(mapListener);
		
		CurrencyTabCmbBoxWantListener wantListener = new CurrencyTabCmbBoxWantListener(this);
		currencyBuyerPanel.getCmb_currencyTab_want().addActionListener(wantListener);
		
		CurrencyTabCmbBoxPayListener payListener = new CurrencyTabCmbBoxPayListener(this);
		currencyBuyerPanel.getCmb_currencyTab_pay().addActionListener(payListener);
	}
	
	public void setForegroundWindow(final String titleName){
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
	
	public void loadMapsFromJson() {
		List<String> allMapsAsList = new ArrayList<String>();
		System.out.println("selectedTier " + selectedTier);
		@SuppressWarnings("resource")
		String text = new Scanner(Main.class.getResourceAsStream("allMaps.json")).useDelimiter("\\A").next();
		byte[] bytes;
		String mapsAsJsonString = "";
		try {
			bytes = text.getBytes("UTF-8");
			mapsAsJsonString = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JSONObject json = new JSONObject(mapsAsJsonString);
        JSONArray maps = json.getJSONArray("Maps");
        // convert json array into arraylist
        for(int i = 0; i < maps.length(); i++) {
        	allMapsAsList.add(maps.get(i).toString());
        }
        Collections.sort(allMapsAsList, String.CASE_INSENSITIVE_ORDER);
        getPanelBulkMaps().getCmb_maps_bulks().removeAllItems();
        
        for(int i = 0; i < allMapsAsList.size(); i++) {
        	getPanelBulkMaps().getCmb_maps_bulks().addItem( allMapsAsList.get(i) );
        }
	}
	
	public void loadCurrencyFromJson() {
		List<String> currencysAsList = new ArrayList<String>();
		
		@SuppressWarnings("resource")
		String text = new Scanner(Main.class.getResourceAsStream("currencyPoeTrade.json")).useDelimiter("\\A").next();
		byte[] bytes;
		String currencysAsJson = "";
		try {
			bytes = text.getBytes("UTF-8");
			currencysAsJson = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject(currencysAsJson);
		JSONObject test = (JSONObject) json.get("Currency");
		
		
		for( int i = 0; i < test.names().length(); i++) {
			currencysAsList.add(test.names().get(i).toString());
		}
		
		Collections.sort(currencysAsList, String.CASE_INSENSITIVE_ORDER);
		getCurrencyBuyerPanel().getCmb_currencyTab_want().removeAllItems();
		getCurrencyBuyerPanel().getCmb_currencyTab_pay().removeAllItems();
        
        for(int i = 0; i < currencysAsList.size(); i++) {
        	getCurrencyBuyerPanel().getCmb_currencyTab_want().addItem( currencysAsList.get(i) );
        	getCurrencyBuyerPanel().getCmb_currencyTab_pay().addItem( currencysAsList.get(i) );
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
		this.getFrame().setVisible(true);
	}
	
	public void setFrameInvisible() {
		this.getFrame().setVisible(false);
	}
	
	public boolean isFrameVisible() {
		return this.getFrame().isVisible();
	}

	public User32 getUser32() {
		return user32;
	}

	public void setUser32(User32 user32) {
		this.user32 = user32;
	}

	public JDialog getFrame() {
		return this;
	}

	public String[] getCurrencys() {
		return currencys;
	}

	public void setCurrencys(String[] currencys) {
		this.currencys = currencys;
	}

	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}

	public List<Map> getTradeableMaps() {
		return tradeableMaps;
	}

	public void setTradeableMaps(List<Map> tradeableMaps) {
		this.tradeableMaps = tradeableMaps;
	}

	public SearchParameter getSearchBuilder() {
		return searchBuilder;
	}

	public void setSearchBuilder(SearchParameter searchBuilder) {
		this.searchBuilder = searchBuilder;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public boolean isSelectedCurrency() {
		return selectedCurrency;
	}

	public void setSelectedCurrency(boolean selectedCurrency) {
		this.selectedCurrency = selectedCurrency;
	}

	public boolean isSelectedTier() {
		return selectedTier;
	}

	public void setSelectedTier(boolean selectedTier) {
		this.selectedTier = selectedTier;
	}

	public boolean isSelectedMap() {
		return selectedMap;
	}

	public void setSelectedMap(boolean selectedMap) {
		this.selectedMap = selectedMap;
	}

	public boolean isUserWantsMinimize() {
		return userWantsMinimize;
	}

	public void setUserWantsMinimize(boolean userWantsMinimize) {
		this.userWantsMinimize = userWantsMinimize;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	
	public boolean isValidAmountInput() {
		return validAmountInput;
	}

	public void setValidAmountInput(boolean validAmountInput) {
		this.validAmountInput = validAmountInput;
	}
	
	public boolean isValidPricePerMapInput() {
		return validPricePerMapInput;
	}

	public void setValidPricePerMapInput(boolean validPricePerMapInput) {
		this.validPricePerMapInput = validPricePerMapInput;
	}
	
	public TradeableBulk getTradeables() {
		return tradeables;
	}

	public void setTradeables(TradeableBulk tradeables) {
		this.tradeables = tradeables;
	}

	public boolean isLoadedShapedMaps() {
		return loadedShapedMaps;
	}

	public void setLoadedShapedMaps(boolean loadedShapedMaps) {
		this.loadedShapedMaps = loadedShapedMaps;
	}

	public boolean isLoadedElderMaps() {
		return loadedElderMaps;
	}

	public void setLoadedElderMaps(boolean loadedElderMaps) {
		this.loadedElderMaps = loadedElderMaps;
	}
	
	public boolean isValidMaxPayInput() {
		return validMaxPayInput;
	}

	public void setValidMaxPayInput(boolean validMaxPayInput) {
		this.validMaxPayInput = validMaxPayInput;
	}

	public boolean isValidAmountCurrencyInput() {
		return validAmountCurrencyInput;
	}

	public void setValidAmountCurrencyInput(boolean validAmountCurrencyInput) {
		this.validAmountCurrencyInput = validAmountCurrencyInput;
	}

	public CurrencyOffers getCurrencyOffers() {
		return currencyOffers;
	}

	public void setCurrencyOffers(CurrencyOffers currencyOffers) {
		this.currencyOffers = currencyOffers;
	}
	
	public PanelSingleMaps getSingleMapsPanel() {
		return singleMapsPanel;
	}

	public PanelBulkMaps getPanelBulkMaps() {
		return panelBulkMaps;
	}

	public PanelCurrencyBuyer getCurrencyBuyerPanel() {
		return currencyBuyerPanel;
	}

	public PoeNinjaPrices getPoeNinjaPrices() {
		return poeNinjaPrices;
	}
}
