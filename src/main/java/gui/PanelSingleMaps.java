package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.stefank.Main;

public class PanelSingleMaps extends JPanel {
	private static final long serialVersionUID = 1L;
	
	String[] tiers = {"Tier 1", "Tier 2", "Tier 3", "Tier 4", "Tier 5", "Tier 6", "Tier 7", "Tier 8", 
			"Tier 9", "Tier 10", "Tier 11", "Tier 12", "Tier 13", "Tier 14", "Tier 15", "Tier 16"};
	String[] currencys = { "ANY", "chaos", "alch", "chisel", "vaal", "fuse" };
	
	// Components
	JLabel lblCurrency;
	JLabel lblTier;
	JLabel lblMap;
	JLabel lbl_count;
	JLabel lblLoading;
	JLabel lblMadeByEzkk;
	JLabel lblMaptradoV;
	JLabel label_1;
	JButton btn_update;
	JButton btn_exit;
	JButton btn_nextTrade;
	JButton btn_minimize;
	JComboBox<?> cmb_tier;
	JComboBox<String> cmb_map;
	JComboBox<?> cmb_currency;
	JCheckBox chckbx_corrupted;
	JCheckBox chckbx_white;
	JCheckBox chckbxAutomateTrading;
	
	public PanelSingleMaps() {
		init();
	}

	private void init() {
		Image cancelIcon = null;
		Image minimizeIcon = null;
		try {
			cancelIcon = ImageIO.read(Main.class.getResourceAsStream("cancel.png"));
			minimizeIcon = ImageIO.read(Main.class.getResourceAsStream("minimize.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setBackground(new Color(51, 51, 51));
		setForeground(Color.GRAY);
		setPreferredSize(new Dimension(420, 315));
		setLayout(null);
		
		// LABELS
		lblCurrency = new JLabel("Pay with:");
		lblCurrency.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrency.setBounds(18, 34, 77, 14);
		lblCurrency.setForeground(new Color(255, 255, 255));
		lblCurrency.setBackground(Color.GRAY);
		
		lblTier = new JLabel("Tier:");
		lblTier.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTier.setBounds(18, 62, 77, 14);
		lblTier.setForeground(Color.WHITE);
		lblTier.setBackground(Color.GRAY);
		
		lblMap = new JLabel("Map:");
		lblMap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMap.setBounds(18, 91, 77, 14);
		lblMap.setForeground(Color.WHITE);
		lblMap.setBackground(Color.GRAY);
		
		lbl_count = new JLabel("Tradeables: ");
		lbl_count.setForeground(Color.WHITE);
		lbl_count.setBounds(105, 200, 128, 14);
		lbl_count.setEnabled(false);
		
		lblLoading = new JLabel("loading...");
		lblLoading.setBounds(182, 213, 99, 50);
		lblLoading.setIcon(new ImageIcon(MainFrame.class.getResource("/com/stefank/Spinner.gif")));
		lblLoading.setForeground(Color.WHITE);
		lblLoading.setVisible(false);
		
		lblMadeByEzkk = new JLabel("beta 1.3");
		lblMadeByEzkk.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblMadeByEzkk.setBounds(294, 286, 45, 14);
		lblMadeByEzkk.setBackground(new Color(0, 128, 0));
		lblMadeByEzkk.setForeground(new Color(255, 235, 205));
		
		lblMaptradoV = new JLabel("Trademaster - Singlebuyer");
		lblMaptradoV.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMaptradoV.setForeground(new Color(255, 235, 205));
		lblMaptradoV.setBackground(new Color(0, 128, 0));
		lblMaptradoV.setBounds(70, -1, 242, 24);
		
		label_1 = new JLabel("Created by ezkk2");
		label_1.setForeground(new Color(255, 235, 205));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		label_1.setBackground(new Color(0, 128, 0));
		label_1.setBounds(210, 286, 86, 14);
		
		// BUTTONS
		btn_update = new JButton();
		btn_update.setBounds(105, 156, 152, 43);
		btn_update.setEnabled(false);
		btn_update.setText("Update");
		
		btn_exit = new JButton();
		btn_exit.setBounds(342, -1, 19, 24);
		btn_exit.setForeground(new Color(0, 0, 0));
		btn_exit.setBackground(new Color(204, 0, 0));
		btn_exit.setOpaque(false);
		btn_exit.setContentAreaFilled(false);
		btn_exit.setBorderPainted(false);
		btn_exit.setIcon(new ImageIcon(cancelIcon));
		btn_exit.setFocusPainted(false);
		
		btn_nextTrade = new JButton();
		btn_nextTrade.setBounds(10, 250, 134, 50);
		btn_nextTrade.setEnabled(false);
		btn_nextTrade.setText("Next Trade");
		
		btn_minimize = new JButton();
		btn_minimize.setBounds(322, -1, 19, 24);
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
		
		// COMBOBOXES
		cmb_tier = new JComboBox(tiers);
		cmb_tier.setBounds(105, 59, 152, 20);
		
		cmb_map = new JComboBox(new Object[]{});
		cmb_map.setBounds(105, 87, 152, 22);
		cmb_map.setEnabled(false);
		
		cmb_currency = new JComboBox(currencys);
		cmb_currency.setBounds(105, 31, 152, 20);
		
		// CHECKBOXES
		chckbx_corrupted = new JCheckBox("Corrupted and Rare");
		chckbx_corrupted.setBounds(105, 122, 131, 23);
		chckbx_corrupted.setBackground(new Color(250, 128, 114));
		
		chckbx_white = new JCheckBox("White");
		chckbx_white.setBounds(18, 122, 87, 23);
		chckbx_white.setBackground(UIManager.getColor("Button.background"));
		
		chckbxAutomateTrading = new JCheckBox("Automatically send offers?");
		chckbxAutomateTrading.setForeground(new Color(255, 255, 255));
		chckbxAutomateTrading.setBackground(new Color(255, 0, 0));
		chckbxAutomateTrading.setFont(new Font("Tahoma", Font.PLAIN, 8));
		chckbxAutomateTrading.setBounds(10, 227, 134, 23);
		
		add(lblCurrency);
		add(btn_update);
		add(btn_exit);
		add(cmb_tier);
		add(lblTier);
		add(cmb_map);
		add(lblMap);
		add(lbl_count);
		add(btn_nextTrade);
		add(btn_minimize);
		add(cmb_currency);
		add(lblLoading);
		add(chckbx_white);
		add(chckbx_corrupted);
		add(chckbxAutomateTrading);
		add(lblMadeByEzkk);
		add(lblMaptradoV);
		add(label_1);
	}

	public JLabel getLblCurrency() {
		return lblCurrency;
	}

	public void setLblCurrency(JLabel lblCurrency) {
		this.lblCurrency = lblCurrency;
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

	public JLabel getLbl_count() {
		return lbl_count;
	}

	public void setLbl_count(JLabel lbl_count) {
		this.lbl_count = lbl_count;
	}

	public JLabel getLblLoading() {
		return lblLoading;
	}

	public void setLblLoading(JLabel lblLoading) {
		this.lblLoading = lblLoading;
	}

	public JLabel getLblMadeByEzkk() {
		return lblMadeByEzkk;
	}

	public void setLblMadeByEzkk(JLabel lblMadeByEzkk) {
		this.lblMadeByEzkk = lblMadeByEzkk;
	}

	public JLabel getLblMaptradoV() {
		return lblMaptradoV;
	}

	public void setLblMaptradoV(JLabel lblMaptradoV) {
		this.lblMaptradoV = lblMaptradoV;
	}

	public JLabel getLabel_1() {
		return label_1;
	}

	public void setLabel_1(JLabel label_1) {
		this.label_1 = label_1;
	}

	public JButton getBtn_update() {
		return btn_update;
	}

	public void setBtn_update(JButton btn_update) {
		this.btn_update = btn_update;
	}

	public JButton getBtn_exit() {
		return btn_exit;
	}

	public void setBtn_exit(JButton btn_exit) {
		this.btn_exit = btn_exit;
	}

	public JButton getBtn_nextTrade() {
		return btn_nextTrade;
	}

	public void setBtn_nextTrade(JButton btn_nextTrade) {
		this.btn_nextTrade = btn_nextTrade;
	}

	public JButton getBtn_minimize() {
		return btn_minimize;
	}

	public void setBtn_minimize(JButton btn_minimize) {
		this.btn_minimize = btn_minimize;
	}

	public JComboBox<?> getCmb_tier() {
		return cmb_tier;
	}

	public void setCmb_tier(JComboBox<?> cmb_tier) {
		this.cmb_tier = cmb_tier;
	}

	public JComboBox<String> getCmb_map() {
		return cmb_map;
	}

	public void setCmb_map(JComboBox<String> cmb_map) {
		this.cmb_map = cmb_map;
	}

	public JComboBox<?> getCmb_currency() {
		return cmb_currency;
	}

	public void setCmb_currency(JComboBox<?> cmb_currency) {
		this.cmb_currency = cmb_currency;
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

	public JCheckBox getChckbxAutomateTrading() {
		return chckbxAutomateTrading;
	}
}
