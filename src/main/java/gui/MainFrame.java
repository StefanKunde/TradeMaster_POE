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

import org.jsoup.nodes.Element;

import com.stefank.Main;
import com.sun.jna.Native;

import connector.SearchParameter;
import handler.TradeHandler;
import items.Map;
import items.Maps;
import utility.User32;
import viewcontroller.CurrencyComboboxListener;
import viewcontroller.ExitButtonListener;
import viewcontroller.MapComboboxListener;
import viewcontroller.MinimizeButtonListener;
import viewcontroller.NextButtonListener;
import viewcontroller.TierComboboxListener;
import viewcontroller.UpdateButtonListener;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;


public class MainFrame extends JFrame implements IHideable {
	private User32 user32 = User32.INSTANCE;
	JPanel panel;
	private JPanel panel_1;
	JLabel lblCurrency;
	JLabel lbl_count;
	JLabel lblTier;
	JLabel lblMap;
	boolean isVisible;
	String[] currencys = { "ANY", "chaos", "alchemy", "chisel", "vaal", "fusing" };
	String[] tiers = {"Tier 1", "Tier 2", "Tier 3", "Tier 4", "Tier 5", "Tier 6", "Tier 7", "Tier 8", 
			"Tier 9", "Tier 10", "Tier 11", "Tier 12", "Tier 13", "Tier 14", "Tier 15", "Tier 16"};
	JButton btn_update;
	JButton btn_nextTrade;
	JButton btn_exit;
	JButton btn_minimize;
	//JButton btn_minimize;
	private JComboBox cmb_currency;
	private JComboBox cmb_tier;
	private JComboBox cmb_map;
	
	List<Map> maps;
	List<Map> tradeableMaps;
	
	SearchParameter searchBuilder;
	
	String currency = "";
	String mapName = "";
	
	boolean selectedCurrency = false;
	boolean selectedTier = false;
	boolean selectedMap = false;
	
	boolean userWantsMinimize = false;
	
	public MainFrame() {
		maps = new ArrayList<Map>();
		tradeableMaps = new ArrayList<Map>();
		searchBuilder = new SearchParameter();
		panel = new JPanel();
		this.setTitle("MapTrado Main");
		panel_1 = new JPanel();
		lblCurrency = new JLabel("Currency");
		lblTier = new JLabel("Tier");
		lbl_count = new JLabel("Tradeables: ");
		lblMap = new JLabel("Map");
		btn_update = new JButton();
		btn_exit = new JButton();
		btn_nextTrade = new JButton();
		btn_minimize = new JButton();
		cmb_tier = new JComboBox(tiers);
		cmb_map = new JComboBox(new Object[]{});
		cmb_currency = new JComboBox(currencys);
		
		initFrame();
		
		
	}
	
	private void initFrame() {
		this.setForeground(Color.GRAY);
		this.setFont(new Font("Calibri", Font.PLAIN, 12));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel_1.setBackground(new Color(51, 51, 51));
		panel_1.setForeground(Color.GRAY);
		
		lblCurrency.setForeground(new Color(255, 255, 255));
		lblCurrency.setBackground(Color.GRAY);
		lblCurrency.setBounds(10, 34, 71, 14);
		
		btn_update.setEnabled(false);
		btn_update.setBounds(155, 127, 129, 29);
		btn_update.setText("Update");
		panel_1.setLayout(null);
		
		
		cmb_currency.setBounds(91, 30, 243, 22);
		
		panel_1.add(lblCurrency);
		panel_1.add(btn_update);
		
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

		this.getContentPane().add(panel_1);
		btn_exit.setForeground(new Color(0, 0, 0));
		btn_exit.setBackground(new Color(204, 0, 0));
		btn_exit.setBounds(377, 5, 16, 16);
		Image cancelIcon = null;
		btn_exit.setOpaque(false);
		btn_exit.setContentAreaFilled(false);
		btn_exit.setBorderPainted(false);
		try {
			cancelIcon = ImageIO.read(Main.class.getResourceAsStream("cancel.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btn_exit.setIcon(new ImageIcon(cancelIcon));
		btn_exit.setFocusPainted(false);
		panel_1.add(btn_exit);
		cmb_tier.setBounds(91, 59, 243, 22);
		panel_1.add(cmb_tier);
		lblTier.setForeground(Color.WHITE);
		lblTier.setBackground(Color.GRAY);
		lblTier.setBounds(10, 63, 71, 14);
		panel_1.add(lblTier);
		cmb_map.setEnabled(false);
		cmb_map.setBounds(91, 92, 243, 22);
		panel_1.add(cmb_map);
		lblMap.setForeground(Color.WHITE);
		lblMap.setBackground(Color.GRAY);
		lblMap.setBounds(10, 96, 71, 14);
		panel_1.add(lblMap);
		lbl_count.setEnabled(false);
		lbl_count.setBounds(155, 167, 129, 14);
		panel_1.add(lbl_count);
		btn_nextTrade.setEnabled(false);
		btn_nextTrade.setText("Next Trade");
		btn_nextTrade.setBounds(10, 192, 161, 54);
		panel_1.add(btn_nextTrade);
		btn_minimize.setOpaque(false);
		btn_minimize.setForeground(Color.BLACK);
		btn_minimize.setContentAreaFilled(false);
		btn_minimize.setBorderPainted(false);
		btn_minimize.setBackground(new Color(204, 0, 0));
		btn_minimize.setBounds(355, 5, 16, 16);
		
		btn_minimize.setOpaque(false);
		btn_minimize.setContentAreaFilled(false);
		btn_minimize.setBorderPainted(false);
		Image minimizeIcon = null;
		try {
			minimizeIcon = ImageIO.read(Main.class.getResourceAsStream("minimize.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btn_minimize.setIcon(new ImageIcon(minimizeIcon));
		btn_minimize.setFocusPainted(false);
		
		
		panel_1.add(btn_minimize);
		
		panel_1.add(cmb_currency);
		
		this.setSize(398, 257);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.getContentPane().requestFocusInWindow();
	    FrameDragListener frameDragListener = new FrameDragListener(this);
	    this.addMouseListener(frameDragListener);
	    this.addMouseMotionListener(frameDragListener);
        this.setAlwaysOnTop(true);
		//frame.setMinimumSize(new Dimension(300, 300));
		SwingUtilities.updateComponentTreeUI(this);
		isVisible = false;
		this.setVisible(false);
		
		
		// Add Listeners
		ExitButtonListener exitListener = new ExitButtonListener(this);
		btn_exit.addActionListener(exitListener);
		
		MinimizeButtonListener minimizeListener = new MinimizeButtonListener(this);
		btn_minimize.addActionListener(minimizeListener);
		
		UpdateButtonListener updateListener = new UpdateButtonListener(this);
		btn_update.addActionListener(updateListener);
		
		NextButtonListener nextTradeListener = new NextButtonListener(this);
		btn_nextTrade.addActionListener(nextTradeListener);
		
		CurrencyComboboxListener currencyListener = new CurrencyComboboxListener(this);
		cmb_currency.addActionListener(currencyListener);
		
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

	public JFrame getFrame() {
		return this;
	}

	public JPanel getPanel() {
		return panel_1;
	}

	public void setPanel(JPanel panel) {
		this.panel_1 = panel;
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

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
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
	
	
}
