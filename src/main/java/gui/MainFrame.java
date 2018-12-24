package gui;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static java.nio.charset.StandardCharsets.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.stefank.Main;
import com.sun.jna.Native;

import connector.SearchParameter;
import items.Map;
import items.TradeableBulk;
import listener.AmountTxtBoxListener;
import listener.CorruptedCheckBoxListener;
import listener.CurrencyBulksCmbListener;
import listener.CurrencyComboboxListener;
import listener.ElderChBoxListener;
import listener.ExitButtonListener;
import listener.MapCmbBoxBulksListener;
import listener.MapComboboxListener;
import listener.MinimizeButtonListener;
import listener.NextButtonBulksListener;
import listener.NextButtonListener;
import listener.PricePerMapTxtBoxListener;
import listener.ShapedChBoxListener;
import listener.TierComboboxListener;
import listener.UpdateButtonBulksListener;
import listener.UpdateButtonListener;
import listener.WhiteCheckBoxListener;
import utility.User32;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class MainFrame extends JDialog implements IHideable {
	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private static final Charset ISO = Charset.forName("ISO-8859-1");
	private User32 user32 = User32.INSTANCE;
	TradeableBulk tradeables;
	JPanel panel;
	JPanel panel_oneMap;
	JLabel lblCurrency;
	JLabel lbl_count;
	JLabel lblTier;
	JLabel lblMap;
	JLabel lblLoading;
	boolean isVisible;
	String[] currencys = { "ANY", "chaos", "alch", "chisel", "vaal", "fuse" };
	String[] tiers = {"Tier 1", "Tier 2", "Tier 3", "Tier 4", "Tier 5", "Tier 6", "Tier 7", "Tier 8", 
			"Tier 9", "Tier 10", "Tier 11", "Tier 12", "Tier 13", "Tier 14", "Tier 15", "Tier 16"};
	JButton btn_update;
	JButton btn_nextTrade;
	JButton btn_exit;
	JButton btn_exit_bulks;
	JButton btn_minimize;
	//JButton btn_minimize;
	JComboBox cmb_currency;
	JComboBox cmb_tier;
	JComboBox cmb_map;
	
	JCheckBox chckbx_corrupted;
	JCheckBox chckbx_white;
	
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
	
	boolean userWantsMinimize = false;
	private JLabel lblMadeByEzkk;
	private JTabbedPane tabbedPane;
	private JLabel lblMaptradoV;
	private JPanel panel_bulksMaps;
	JButton btn_minimize_bulks;
	private JLabel lbl_currency_bulks;
	private JComboBox cmb_currency_bulks;
	private JLabel lblBulkAmount;
	private JTextField txt_amount_bulks;
	JCheckBox chckbxElderMap;
	JCheckBox chckbxShapedMap;
	JComboBox cmb_maps_bulks;
	JLabel lbl_tradeables_bulks;
	JButton btn_update_bulks;
	JButton btn_nextTrade_bulks;
	JLabel lbl_map_bulks;
	private JLabel lblMaptradoV_1;
	private JLabel label;
	private JLabel label_1;
	private JTextField txtbox_pricePerMap;
	
	public MainFrame() {
		tradeables = new TradeableBulk();
		maps = new ArrayList<Map>();
		tradeableMaps = new ArrayList<Map>();
		searchBuilder = new SearchParameter();
		panel = new JPanel();
		this.setTitle("MapTrado Main");
		panel_oneMap = new JPanel();
		panel_bulksMaps = new JPanel();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		lblCurrency = new JLabel("Pay with:");
		lblCurrency.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrency.setBounds(18, 34, 77, 14);
		lblTier = new JLabel("Tier:");
		lblTier.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTier.setBounds(18, 62, 77, 14);
		lbl_count = new JLabel("Tradeables: ");
		lbl_tradeables_bulks = new JLabel("Tradeables: ");
		lbl_count.setForeground(Color.WHITE);
		lbl_count.setBounds(105, 200, 128, 14);
		lblMap = new JLabel("Map:");
		lblMap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMap.setBounds(18, 91, 77, 14);
		lblLoading = new JLabel("loading...");
		lblMaptradoV = new JLabel("MapTrado - Singlebuyer");
		lblMadeByEzkk = new JLabel("alpha 0.0.1");
		lbl_currency_bulks = new JLabel("Pay with:");
		lbl_map_bulks = new JLabel("Map:");
		lblLoading.setBounds(182, 213, 99, 50);
		btn_update = new JButton();
		btn_update.setBounds(105, 156, 152, 43);
		btn_exit = new JButton();
		btn_update_bulks = new JButton();
		btn_exit_bulks = new JButton();
		btn_exit_bulks.setSize(19, 24);
		btn_exit_bulks.setLocation(320, -1);
		btn_nextTrade_bulks = new JButton();
		btn_exit.setBounds(320, -1, 19, 24);
		btn_nextTrade = new JButton();
		btn_nextTrade.setBounds(10, 250, 134, 50);
		btn_minimize = new JButton();
		btn_minimize_bulks = new JButton();
		btn_minimize.setBounds(300, -1, 19, 24);
		btn_minimize_bulks.setBounds(300, -1, 19, 24);
		cmb_tier = new JComboBox(tiers);
		cmb_tier.setBounds(105, 59, 152, 20);
		cmb_map = new JComboBox(new Object[]{});
		cmb_maps_bulks = new JComboBox(new Object[]{});
		cmb_map.setBounds(105, 87, 152, 22);
		cmb_currency = new JComboBox(currencys);
		cmb_currency_bulks = new JComboBox(currencys);
		cmb_currency.setBounds(105, 31, 152, 20);
		chckbx_white = new JCheckBox("White");
		chckbx_white.setBounds(18, 122, 87, 23);
		chckbxElderMap = new JCheckBox("ELDER MAP?");
		chckbx_corrupted = new JCheckBox("Corrupted and Rare");
		chckbx_corrupted.setBounds(105, 122, 131, 23);
		lblBulkAmount = new JLabel("Bulk amount:");
		txt_amount_bulks = new JTextField();
		txtbox_pricePerMap = new JTextField();
		txtbox_pricePerMap.setEnabled(false);
		
		initFrame();
		loadMapsFromJson();
	}
	
	private void initFrame() {
		this.setPreferredSize(new Dimension(400, 200));
		this.setForeground(Color.GRAY);
		this.setFont(new Font("Calibri", Font.PLAIN, 12));
		this.setBackground(Color.GRAY);
		panel_oneMap.setBackground(new Color(51, 51, 51));
		panel_oneMap.setForeground(Color.GRAY);
		panel_oneMap.setPreferredSize(new Dimension(420, 315));
		lblCurrency.setForeground(new Color(255, 255, 255));
		lblCurrency.setBackground(Color.GRAY);
		btn_update.setEnabled(false);
		btn_update.setText("Update");
		panel_oneMap.setLayout(null);
		panel_oneMap.add(lblCurrency);
		panel_oneMap.add(btn_update);
		
		
		
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
		//this.getContentPane().add(panel_1);
		
		Image cancelIcon = null;
		try {
			cancelIcon = ImageIO.read(Main.class.getResourceAsStream("cancel.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btn_exit.setForeground(new Color(0, 0, 0));
		btn_exit.setBackground(new Color(204, 0, 0));
		btn_exit.setOpaque(false);
		btn_exit.setContentAreaFilled(false);
		btn_exit.setBorderPainted(false);
		btn_exit.setIcon(new ImageIcon(cancelIcon));
		btn_exit.setFocusPainted(false);
		btn_exit_bulks.setForeground(new Color(0, 0, 0));
		btn_exit_bulks.setBackground(new Color(204, 0, 0));
		btn_exit_bulks.setOpaque(false);
		btn_exit_bulks.setContentAreaFilled(false);
		btn_exit_bulks.setBorderPainted(false);
		btn_exit_bulks.setIcon(new ImageIcon(cancelIcon));
		btn_exit_bulks.setFocusPainted(false);
		
		
		
		panel_oneMap.add(btn_exit);
		panel_oneMap.add(cmb_tier);
		lblTier.setForeground(Color.WHITE);
		lblTier.setBackground(Color.GRAY);
		panel_oneMap.add(lblTier);
		cmb_map.setEnabled(false);
		panel_oneMap.add(cmb_map);
		lblMap.setForeground(Color.WHITE);
		lblMap.setBackground(Color.GRAY);
		panel_oneMap.add(lblMap);
		lbl_count.setEnabled(false);
		panel_oneMap.add(lbl_count);
		btn_nextTrade.setEnabled(false);
		btn_nextTrade.setText("Next Trade");
		panel_oneMap.add(btn_nextTrade);
		
		
		Image minimizeIcon = null;
		try {
			minimizeIcon = ImageIO.read(Main.class.getResourceAsStream("minimize.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btn_minimize.setOpaque(false);
		btn_minimize.setForeground(Color.BLACK);
		btn_minimize.setContentAreaFilled(false);
		btn_minimize.setBorderPainted(false);
		btn_minimize.setBackground(new Color(204, 0, 0));
		btn_minimize.setOpaque(false);
		btn_minimize.setContentAreaFilled(false);
		btn_minimize.setBorderPainted(false);
		btn_minimize.setIcon(new ImageIcon(minimizeIcon));
		btn_minimize.setFocusPainted(false);
		
		btn_minimize_bulks.setOpaque(false);
		btn_minimize_bulks.setForeground(Color.BLACK);
		btn_minimize_bulks.setContentAreaFilled(false);
		btn_minimize_bulks.setBorderPainted(false);
		btn_minimize_bulks.setBackground(new Color(204, 0, 0));
		btn_minimize_bulks.setOpaque(false);
		btn_minimize_bulks.setContentAreaFilled(false);
		btn_minimize_bulks.setBorderPainted(false);
		btn_minimize_bulks.setIcon(new ImageIcon(minimizeIcon));
		btn_minimize_bulks.setFocusPainted(false);
		
		
		
		panel_oneMap.add(btn_minimize);
		panel_oneMap.add(cmb_currency);
		lblLoading.setIcon(new ImageIcon(MainFrame.class.getResource("/com/stefank/Spinner.gif")));
		lblLoading.setForeground(Color.WHITE);
		panel_oneMap.add(lblLoading);
		chckbx_white.setBackground(UIManager.getColor("Button.background"));
		panel_oneMap.add(chckbx_white);
		chckbx_corrupted.setBackground(new Color(250, 128, 114));
		panel_oneMap.add(chckbx_corrupted);
		
		lblMadeByEzkk.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblMadeByEzkk.setBounds(294, 286, 45, 14);
		lblMadeByEzkk.setBackground(new Color(0, 128, 0));
		lblMadeByEzkk.setForeground(new Color(255, 235, 205));
		panel_oneMap.add(lblMadeByEzkk);
		tabbedPane.add("Buy single maps", panel_oneMap);
		
		lblMaptradoV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMaptradoV.setForeground(new Color(255, 235, 205));
		lblMaptradoV.setBackground(new Color(0, 128, 0));
		lblMaptradoV.setBounds(68, -1, 213, 24);
		panel_oneMap.add(lblMaptradoV);
		
		label_1 = new JLabel("Created by ezkk2");
		label_1.setForeground(new Color(255, 235, 205));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		label_1.setBackground(new Color(0, 128, 0));
		label_1.setBounds(210, 286, 86, 14);
		panel_oneMap.add(label_1);
		
		
		panel_bulksMaps.setBackground(new Color(51, 51, 51));
		panel_bulksMaps.setPreferredSize(new Dimension(420, 315));
		panel_bulksMaps.setForeground(Color.GRAY);
		tabbedPane.addTab("Buy bulks of maps", null, panel_bulksMaps, null);
		panel_bulksMaps.setLayout(null);
		
		
		
		panel_bulksMaps.add(btn_minimize_bulks);
		
		panel_bulksMaps.add(btn_exit_bulks);
		
		
		lbl_currency_bulks.setForeground(Color.WHITE);
		lbl_currency_bulks.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_currency_bulks.setBackground(Color.GRAY);
		lbl_currency_bulks.setBounds(11, 37, 77, 14);
		panel_bulksMaps.add(lbl_currency_bulks);
		
		
		cmb_currency_bulks.setBounds(139, 34, 152, 20);
		panel_bulksMaps.add(cmb_currency_bulks);
		
		
		lblBulkAmount.setForeground(Color.WHITE);
		lblBulkAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBulkAmount.setBackground(Color.GRAY);
		lblBulkAmount.setBounds(11, 121, 77, 14);
		panel_bulksMaps.add(lblBulkAmount);
		
		
		txt_amount_bulks.setBounds(139, 118, 152, 20);
		panel_bulksMaps.add(txt_amount_bulks);
		txt_amount_bulks.setColumns(10);
		
		
		lbl_map_bulks.setForeground(Color.WHITE);
		lbl_map_bulks.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_map_bulks.setBackground(Color.GRAY);
		lbl_map_bulks.setBounds(11, 98, 77, 14);
		panel_bulksMaps.add(lbl_map_bulks);
		
		
		cmb_maps_bulks.setBounds(139, 95, 152, 20);
		panel_bulksMaps.add(cmb_maps_bulks);
		
		
		chckbxElderMap.setBackground(new Color(188, 143, 143));
		chckbxElderMap.setBounds(186, 145, 153, 23);
		panel_bulksMaps.add(chckbxElderMap);
		
		
		lbl_tradeables_bulks.setForeground(Color.WHITE);
		lbl_tradeables_bulks.setEnabled(false);
		lbl_tradeables_bulks.setBounds(154, 250, 128, 14);
		panel_bulksMaps.add(lbl_tradeables_bulks);
		
		
		btn_update_bulks.setText("Update");
		btn_update_bulks.setEnabled(false);
		btn_update_bulks.setBounds(139, 186, 152, 43);
		panel_bulksMaps.add(btn_update_bulks);
		
		
		btn_nextTrade_bulks.setText("Next Trade");
		btn_nextTrade_bulks.setEnabled(false);
		btn_nextTrade_bulks.setBounds(10, 250, 134, 50);
		panel_bulksMaps.add(btn_nextTrade_bulks);
		
		JLabel lbl_created_bulks = new JLabel("alpha 0.0.1");
		lbl_created_bulks.setForeground(new Color(255, 235, 205));
		lbl_created_bulks.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lbl_created_bulks.setBackground(new Color(0, 128, 0));
		lbl_created_bulks.setBounds(294, 286, 45, 14);
		panel_bulksMaps.add(lbl_created_bulks);
		
		lblMaptradoV_1 = new JLabel("MapTrado - Bulkbuyer");
		lblMaptradoV_1.setForeground(new Color(255, 235, 205));
		lblMaptradoV_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMaptradoV_1.setBackground(new Color(0, 128, 0));
		lblMaptradoV_1.setBounds(73, -1, 195, 24);
		panel_bulksMaps.add(lblMaptradoV_1);
		
		label = new JLabel("Created by ezkk2");
		label.setForeground(new Color(255, 235, 205));
		label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		label.setBackground(new Color(0, 128, 0));
		label.setBounds(210, 286, 86, 14);
		panel_bulksMaps.add(label);
		
		JLabel lbl_payPerMap = new JLabel("Max price per map:");
		lbl_payPerMap.setForeground(Color.WHITE);
		lbl_payPerMap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_payPerMap.setBackground(Color.GRAY);
		lbl_payPerMap.setBounds(11, 61, 128, 14);
		panel_bulksMaps.add(lbl_payPerMap);
		
		
		txtbox_pricePerMap.setColumns(10);
		txtbox_pricePerMap.setBounds(139, 58, 153, 20);
		panel_bulksMaps.add(txtbox_pricePerMap);
		
		chckbxShapedMap = new JCheckBox("SHAPED MAP?");
		chckbxShapedMap.setBackground(new Color(153, 204, 204));
		chckbxShapedMap.setBounds(31, 145, 153, 23);
		panel_bulksMaps.add(chckbxShapedMap);
		lblLoading.setVisible(false);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(366, 378);
		this.setLocationRelativeTo(null);
		this.getContentPane().requestFocusInWindow();
	    //FrameDragListener frameDragListener = new FrameDragListener(this);
//	    this.addMouseListener(frameDragListener);
//	    this.addMouseMotionListener(frameDragListener);
        this.setAlwaysOnTop(true);
		SwingUtilities.updateComponentTreeUI(this);
		isVisible = false;
		this.setVisible(false);
		this.setResizable(false);
		setDefaultLookAndFeelDecorated(true);
		
		// Add Listeners
		AmountTxtBoxListener amountListener = new AmountTxtBoxListener(this);
		txt_amount_bulks.getDocument().addDocumentListener(amountListener);
		
		PricePerMapTxtBoxListener pricePerMapListener = new PricePerMapTxtBoxListener(this);
		txtbox_pricePerMap.getDocument().addDocumentListener(pricePerMapListener);
		
		CorruptedCheckBoxListener corruptedBoxListener = new CorruptedCheckBoxListener(this);
		chckbx_corrupted.addActionListener(corruptedBoxListener);
		
		WhiteCheckBoxListener whiteBoxListener = new WhiteCheckBoxListener(this);
		chckbx_white.addActionListener(whiteBoxListener);
		
		ShapedChBoxListener shapedCbListener = new ShapedChBoxListener(this);
		chckbxShapedMap.addActionListener(shapedCbListener);
		
		ElderChBoxListener elderCbListener = new ElderChBoxListener(this);
		chckbxElderMap.addActionListener(elderCbListener);
		
		ExitButtonListener exitListener = new ExitButtonListener(this);
		btn_exit.addActionListener(exitListener);
		btn_exit_bulks.addActionListener(exitListener);
		
		MinimizeButtonListener minimizeListener = new MinimizeButtonListener(this);
		btn_minimize.addActionListener(minimizeListener);
		btn_minimize_bulks.addActionListener(minimizeListener);
		
		UpdateButtonListener updateListener = new UpdateButtonListener(this);
		btn_update.addActionListener(updateListener);
		
		UpdateButtonBulksListener updateBulkListener = new UpdateButtonBulksListener(this);
		btn_update_bulks.addActionListener(updateBulkListener);
		
		NextButtonListener nextTradeListener = new NextButtonListener(this);
		btn_nextTrade.addActionListener(nextTradeListener);
		
		NextButtonBulksListener nextTradeBulksListener = new NextButtonBulksListener(this);
		btn_nextTrade_bulks.addActionListener(nextTradeBulksListener);
		
		CurrencyComboboxListener currencyListener = new CurrencyComboboxListener(this);
		cmb_currency.addActionListener(currencyListener);
		
		CurrencyBulksCmbListener currencyBulksCmbListener = new CurrencyBulksCmbListener(this);
		cmb_currency_bulks.addActionListener(currencyBulksCmbListener);
		
		MapCmbBoxBulksListener mapCmbListener = new MapCmbBoxBulksListener(this);
		cmb_maps_bulks.addActionListener(mapCmbListener);
		
		TierComboboxListener tierListener = new TierComboboxListener(this);
		cmb_tier.addActionListener(tierListener);
		
		MapComboboxListener mapListener = new MapComboboxListener(this);
		cmb_map.addActionListener(mapListener);
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
	
	public JTextField getTxtbox_pricePerMap() {
		return txtbox_pricePerMap;
	}

	public void setTxtbox_pricePerMap(JTextField txtbox_pricePerMap) {
		this.txtbox_pricePerMap = txtbox_pricePerMap;
	}

	public JCheckBox getChckbx_corrupted() {
		return chckbx_corrupted;
	}

	public void setChckbx_corrupted(JCheckBox chckbx_corrupted) {
		this.chckbx_corrupted = chckbx_corrupted;
	}

	public JCheckBox getChckbx_white() {
		return chckbx_white;
	}

	public void setChckbx_white(JCheckBox chckbx_white) {
		this.chckbx_white = chckbx_white;
	}

	public JLabel getLblLoading() {
		return lblLoading;
	}

	public void setLblLoading(JLabel lblLoading) {
		this.lblLoading = lblLoading;
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

	public JPanel getPanel() {
		return panel_oneMap;
	}

	public void setPanel(JPanel panel) {
		this.panel_oneMap = panel;
	}

	public JLabel getLblCurrency() {
		return lblCurrency;
	}

	public void setLblCurrency(JLabel lblCurrency) {
		this.lblCurrency = lblCurrency;
	}

	public JLabel getLbl_count() {
		return lbl_count;
	}

	public void setLbl_count(JLabel lbl_count) {
		this.lbl_count = lbl_count;
	}

	public String[] getCurrencys() {
		return currencys;
	}

	public void setCurrencys(String[] currencys) {
		this.currencys = currencys;
	}

	public String[] getTiers() {
		return tiers;
	}

	public void setTiers(String[] tiers) {
		this.tiers = tiers;
	}

	public JButton getBtn_update() {
		return btn_update;
	}

	public void setBtn_update(JButton btn_update) {
		this.btn_update = btn_update;
	}

	public JButton getBtn_nextTrade() {
		return btn_nextTrade;
	}

	public void setBtn_nextTrade(JButton btn_nextTrade) {
		this.btn_nextTrade = btn_nextTrade;
	}
	
	public JButton getBtn_exit() {
		return btn_exit;
	}

	public void setBtn_exit(JButton btn_exit) {
		this.btn_exit = btn_exit;
	}

	public JComboBox getCmb_currency() {
		return cmb_currency;
	}

	public void setCmb_currency(JComboBox cmb_currency) {
		this.cmb_currency = cmb_currency;
	}

	public JComboBox getCmb_tier() {
		return cmb_tier;
	}

	public void setCmb_tier(JComboBox cmb_tier) {
		this.cmb_tier = cmb_tier;
	}

	public JComboBox getCmb_map() {
		return cmb_map;
	}

	public void setCmb_map(JComboBox cmb_map) {
		this.cmb_map = cmb_map;
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

	public JLabel getLblTier() {
		return lblTier;
	}

	public void setLblTier(JLabel lblTier) {
		this.lblTier = lblTier;
	}

	public JLabel getLblMap() {
		return lblMap;
	}

	public void setLblMap(JLabel lblMap) {
		this.lblMap = lblMap;
	}

	public JButton getBtn_minimize() {
		return btn_minimize;
	}

	public void setBtn_minimize(JButton btn_minimize) {
		this.btn_minimize = btn_minimize;
	}

	public boolean isUserWantsMinimize() {
		return userWantsMinimize;
	}

	public void setUserWantsMinimize(boolean userWantsMinimize) {
		this.userWantsMinimize = userWantsMinimize;
	}

	public JPanel getPanel_oneMap() {
		return panel_oneMap;
	}

	public void setPanel_oneMap(JPanel panel_oneMap) {
		this.panel_oneMap = panel_oneMap;
	}

	public JButton getBtn_exit_bulks() {
		return btn_exit_bulks;
	}

	public void setBtn_exit_bulks(JButton btn_exit_bulks) {
		this.btn_exit_bulks = btn_exit_bulks;
	}

	public JLabel getLblMadeByEzkk() {
		return lblMadeByEzkk;
	}

	public void setLblMadeByEzkk(JLabel lblMadeByEzkk) {
		this.lblMadeByEzkk = lblMadeByEzkk;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public JLabel getLblMaptradoV() {
		return lblMaptradoV;
	}

	public void setLblMaptradoV(JLabel lblMaptradoV) {
		this.lblMaptradoV = lblMaptradoV;
	}

	public JPanel getPanel_bulksMaps() {
		return panel_bulksMaps;
	}

	public void setPanel_bulksMaps(JPanel panel_bulksMaps) {
		this.panel_bulksMaps = panel_bulksMaps;
	}

	public JButton getBtn_minimize_bulks() {
		return btn_minimize_bulks;
	}

	public void setBtn_minimize_bulks(JButton btn_minimize_bulks) {
		this.btn_minimize_bulks = btn_minimize_bulks;
	}

	public JLabel getLbl_currency_bulks() {
		return lbl_currency_bulks;
	}

	public void setLbl_currency_bulks(JLabel lbl_currency_bulks) {
		this.lbl_currency_bulks = lbl_currency_bulks;
	}

	public JComboBox getCmb_currency_bulks() {
		return cmb_currency_bulks;
	}

	public void setCmb_currency_bulks(JComboBox cmb_currency_bulks) {
		this.cmb_currency_bulks = cmb_currency_bulks;
	}

	public JLabel getLblBulkAmount() {
		return lblBulkAmount;
	}

	public void setLblBulkAmount(JLabel lblBulkAmount) {
		this.lblBulkAmount = lblBulkAmount;
	}

	public JTextField getTxt_amount_bulks() {
		return txt_amount_bulks;
	}

	public void setTxt_amount_bulks(JTextField txt_amount_bulks) {
		this.txt_amount_bulks = txt_amount_bulks;
	}

	public JCheckBox getChckbxElderMap() {
		return chckbxElderMap;
	}

	public void setChckbxElderMap(JCheckBox chckbxElderMap) {
		this.chckbxElderMap = chckbxElderMap;
	}

	public JComboBox getCmb_maps_bulks() {
		return cmb_maps_bulks;
	}

	public void setCmb_maps_bulks(JComboBox cmb_maps_bulks) {
		this.cmb_maps_bulks = cmb_maps_bulks;
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

	public JButton getBtn_update_bulks() {
		return btn_update_bulks;
	}

	public void setBtn_update_bulks(JButton btn_update_bulks) {
		this.btn_update_bulks = btn_update_bulks;
	}

	public JButton getBtn_nextTrade_bulks() {
		return btn_nextTrade_bulks;
	}

	public void setBtn_nextTrade_bulks(JButton btn_nextTrade_bulks) {
		this.btn_nextTrade_bulks = btn_nextTrade_bulks;
	}

	public JLabel getLbl_tradeables_bulks() {
		return lbl_tradeables_bulks;
	}

	public void setLbl_tradeables_bulks(JLabel lbl_tradeables_bulks) {
		this.lbl_tradeables_bulks = lbl_tradeables_bulks;
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

	public JCheckBox getChckbxShapedMap() {
		return chckbxShapedMap;
	}

	public void setChckbxShapedMap(JCheckBox chckbxShapedMap) {
		this.chckbxShapedMap = chckbxShapedMap;
	}

	public void loadMapsFromJson() {
		String[] allMaps;
		List<String> allMapsAsList = new ArrayList<String>();
		System.out.println("selectedTier " + selectedTier);
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
        getCmb_maps_bulks().removeAllItems();
        
        for(int i = 0; i < allMapsAsList.size(); i++) {
        	getCmb_maps_bulks().addItem( allMapsAsList.get(i) );
        }
	}
}
