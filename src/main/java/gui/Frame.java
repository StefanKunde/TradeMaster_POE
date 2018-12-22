package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jsoup.nodes.Element;

import com.sun.jna.Native;

import connector.SearchParameter;
import handler.TradeHandler;
import items.Map;
import items.Maps;
import utility.User32;
import viewcontroller.CurrencyComboboxListener;
import viewcontroller.ExitButtonListener;
import viewcontroller.MapComboboxListener;
import viewcontroller.NextButtonListener;
import viewcontroller.TierComboboxListener;
import viewcontroller.UpdateButtonListener;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Frame {
	private User32 user32 = User32.INSTANCE;
	JFrame frame;
	JPanel panel;
	JLabel lblCurrency;
	JLabel lbl_count;
	JLabel lblTier;
	JLabel lblMap;
	boolean isVisible;
	String[] currencys = { "ANY", "chaos", "alchemy", "chisel", "vaal" };
	String[] tiers = {"Tier 1", "Tier 2", "Tier 3", "Tier 4", "Tier 5", "Tier 6", "Tier 7", "Tier 8", 
			"Tier 9", "Tier 10", "Tier 11", "Tier 12", "Tier 13", "Tier 14", "Tier 15"};
	JButton btn_update;
	JButton btn_nextTrade;
	JButton btn_exit;
	private JComboBox cmb_currency;
	private JComboBox cmb_tier;
	private JComboBox cmb_map;
	
	List<Map> maps;
	List<Map> tradeableMaps;
	//List<String> tier_1_maps = new ArrayList<String>();
	
	SearchParameter searchBuilder;
	String currency = "";
	String mapName = "";
	
	boolean selectedCurrency = false;
	boolean selectedTier = false;
	boolean selectedMap = false;
	
	
	
	public Frame() {
		maps = new ArrayList<Map>();
		tradeableMaps = new ArrayList<Map>();
		searchBuilder = new SearchParameter();
		panel = new JPanel();
		frame = new JFrame("JFrame Example");
		panel = new JPanel();
		lblCurrency = new JLabel("Currency");
		lblTier = new JLabel("Tier");
		lbl_count = new JLabel("Tradeables: ");
		lblMap = new JLabel("Map");
		btn_update = new JButton();
		btn_exit = new JButton("X");
		btn_nextTrade = new JButton();
		cmb_tier = new JComboBox(tiers);
		cmb_currency = new JComboBox(currencys);
		cmb_map = new JComboBox(new Object[]{});
		
		initFrame();
	}
	
	private void initFrame() {
		frame.setForeground(Color.GRAY);
		frame.setFont(new Font("Calibri", Font.PLAIN, 12));
		frame.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBackground(new Color(51, 51, 51));
		panel.setForeground(Color.GRAY);
		
		lblCurrency.setForeground(new Color(255, 255, 255));
		lblCurrency.setBackground(Color.GRAY);
		lblCurrency.setBounds(10, 9, 71, 14);
		
		btn_update.setEnabled(false);
		btn_update.setBounds(155, 102, 129, 29);
		btn_update.setText("Update");
		panel.setLayout(null);
		panel.add(lblCurrency);
		panel.add(btn_update);
		
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

		frame.getContentPane().add(panel);
		cmb_currency.setBounds(91, 5, 243, 22);
		panel.add(cmb_currency);
		btn_exit.setForeground(new Color(0, 0, 0));
		btn_exit.setBackground(new Color(204, 0, 0));
		btn_exit.setBounds(349, 11, 39, 29);
		panel.add(btn_exit);
		cmb_tier.setBounds(91, 34, 243, 22);
		panel.add(cmb_tier);
		lblTier.setForeground(Color.WHITE);
		lblTier.setBackground(Color.GRAY);
		lblTier.setBounds(10, 38, 71, 14);
		panel.add(lblTier);
		cmb_map.setEnabled(false);
		cmb_map.setBounds(91, 67, 243, 22);
		panel.add(cmb_map);
		lblMap.setForeground(Color.WHITE);
		lblMap.setBackground(Color.GRAY);
		lblMap.setBounds(10, 71, 71, 14);
		panel.add(lblMap);
		lbl_count.setEnabled(false);
		lbl_count.setBounds(155, 142, 129, 14);
		panel.add(lbl_count);
		btn_nextTrade.setEnabled(false);
		btn_nextTrade.setText("Next Trade");
		btn_nextTrade.setBounds(10, 192, 161, 54);
		panel.add(btn_nextTrade);
		frame.setSize(398, 257);
		frame.setLocationRelativeTo(null);
	    frame.setUndecorated(true);
	    FrameDragListener frameDragListener = new FrameDragListener(frame);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);
		frame.setAlwaysOnTop(true);
		//frame.setMinimumSize(new Dimension(300, 300));
		SwingUtilities.updateComponentTreeUI(frame);
		this.isVisible = false;
		
		// Add Listeners
		ExitButtonListener exitListener = new ExitButtonListener(this);
		btn_exit.addActionListener(exitListener);
		
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
	
	
	public void setVisible() {
		frame.setVisible(true);
		this.isVisible = true;
	}
	
	public void setInvisible() {
		frame.setVisible(false);
		this.isVisible = false;
	}
	
	public boolean isVisible() {
		return this.isVisible;
	}

	public User32 getUser32() {
		return user32;
	}

	public void setUser32(User32 user32) {
		this.user32 = user32;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
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
}
