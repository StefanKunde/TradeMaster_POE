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
import javax.swing.JTextField;

import com.stefank.Main;

public class PanelBulkMaps extends JPanel {
	
	private static final long serialVersionUID = 1L;
	String[] currencys = { "ANY", "chaos", "alch", "chisel", "vaal", "fuse" };
	
	JButton btn_minimize_bulks;
	JButton btn_exit_bulks;
	JButton btn_update_bulkbuyer;
	JButton btn_nextTrade_bulks;
	JLabel lbl_currency_bulks;
	JLabel lbl_tradeables_bulks;
	JLabel lbl_created_bulks;
	JLabel lblMaptradoV_1;
	JLabel label;
	JLabel lbl_payPerMap;
	JLabel lblBulkAmount;
	JLabel lbl_map_bulks;
	JComboBox<String> cmb_maps_bulks;
	JComboBox<?> cmb_currency_bulks;
	JTextField txtbox_pricePerMap;
	JTextField txt_amount_bulks;
	JCheckBox chckbxShapedMap;
	JCheckBox chckbxElderMap;

	public PanelBulkMaps() {
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
		setPreferredSize(new Dimension(420, 315));
		setForeground(Color.GRAY);
		setLayout(null);
		
		// BUTTONS
		btn_minimize_bulks = new JButton();
		btn_minimize_bulks.setBounds(322, -1, 19, 24);
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
		
		btn_exit_bulks = new JButton();
		btn_exit_bulks.setSize(19, 24);
		btn_exit_bulks.setLocation(342, -1);
		btn_exit_bulks.setForeground(new Color(0, 0, 0));
		btn_exit_bulks.setBackground(new Color(204, 0, 0));
		btn_exit_bulks.setOpaque(false);
		btn_exit_bulks.setContentAreaFilled(false);
		btn_exit_bulks.setBorderPainted(false);
		btn_exit_bulks.setIcon(new ImageIcon(cancelIcon));
		btn_exit_bulks.setFocusPainted(false);
		
		btn_update_bulkbuyer = new JButton();
		btn_update_bulkbuyer.setText("Update");
		btn_update_bulkbuyer.setEnabled(false);
		btn_update_bulkbuyer.setBounds(139, 186, 152, 43);
		
		btn_nextTrade_bulks = new JButton();
		btn_nextTrade_bulks.setText("Next Trade");
		btn_nextTrade_bulks.setEnabled(false);
		btn_nextTrade_bulks.setBounds(10, 250, 134, 50);
		
		// LABELS
		lbl_currency_bulks = new JLabel("Pay with:");
		lbl_currency_bulks.setForeground(Color.WHITE);
		lbl_currency_bulks.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_currency_bulks.setBackground(Color.GRAY);
		lbl_currency_bulks.setBounds(11, 37, 77, 14);
		
		lbl_tradeables_bulks = new JLabel("Tradeables: ");
		lbl_tradeables_bulks.setForeground(Color.WHITE);
		lbl_tradeables_bulks.setEnabled(false);
		lbl_tradeables_bulks.setBounds(154, 250, 128, 14);
		
		lbl_created_bulks = new JLabel("beta 1.3");
		lbl_created_bulks.setForeground(new Color(255, 235, 205));
		lbl_created_bulks.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lbl_created_bulks.setBackground(new Color(0, 128, 0));
		lbl_created_bulks.setBounds(294, 286, 45, 14);
		
		lblMaptradoV_1 = new JLabel("Trademaster - Bulkbuyer");
		lblMaptradoV_1.setForeground(new Color(255, 235, 205));
		lblMaptradoV_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblMaptradoV_1.setBackground(new Color(0, 128, 0));
		lblMaptradoV_1.setBounds(73, -1, 218, 24);
		
		label = new JLabel("Created by ezkk2");
		label.setForeground(new Color(255, 235, 205));
		label.setFont(new Font("Tahoma", Font.PLAIN, 8));
		label.setBackground(new Color(0, 128, 0));
		label.setBounds(210, 286, 86, 14);
		
		lbl_payPerMap = new JLabel("Max price per map:");
		lbl_payPerMap.setForeground(Color.WHITE);
		lbl_payPerMap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_payPerMap.setBackground(Color.GRAY);
		lbl_payPerMap.setBounds(11, 61, 128, 14);
		
		lblBulkAmount = new JLabel("Bulk amount:");
		lblBulkAmount.setForeground(Color.WHITE);
		lblBulkAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBulkAmount.setBackground(Color.GRAY);
		lblBulkAmount.setBounds(11, 121, 77, 14);
		
		lbl_map_bulks = new JLabel("Map:");
		lbl_map_bulks.setForeground(Color.WHITE);
		lbl_map_bulks.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_map_bulks.setBackground(Color.GRAY);
		lbl_map_bulks.setBounds(11, 98, 77, 14);
		
		//COMBOBOXES
		cmb_maps_bulks = new JComboBox();
		cmb_maps_bulks.setBounds(139, 95, 152, 20);
		
		cmb_currency_bulks = new JComboBox(currencys);
		cmb_currency_bulks.setBounds(139, 34, 152, 20);
		
		// TEXTBOXES
		txtbox_pricePerMap = new JTextField();
		txtbox_pricePerMap.setEnabled(false);
		txtbox_pricePerMap.setColumns(10);
		txtbox_pricePerMap.setBounds(139, 58, 153, 20);
		
		txt_amount_bulks = new JTextField();
		txt_amount_bulks.setBounds(139, 118, 152, 20);
		txt_amount_bulks.setColumns(10);
		
		// CHECKBOXES
		chckbxShapedMap = new JCheckBox("SHAPED MAP?");
		chckbxShapedMap.setBackground(new Color(153, 204, 204));
		chckbxShapedMap.setBounds(31, 145, 153, 23);
		
		chckbxElderMap = new JCheckBox("ELDER MAP?");
		chckbxElderMap.setBackground(new Color(188, 143, 143));
		chckbxElderMap.setBounds(186, 145, 153, 23);
		
		add(btn_minimize_bulks);
		add(btn_exit_bulks);
		add(btn_update_bulkbuyer);
		add(btn_nextTrade_bulks);
		add(lbl_currency_bulks);
		add(lbl_tradeables_bulks);
		add(lbl_created_bulks);
		add(lblMaptradoV_1);
		add(label);
		add(lbl_payPerMap);
		add(lblBulkAmount);
		add(lbl_map_bulks);
		add(cmb_maps_bulks);
		add(cmb_currency_bulks);
		add(txtbox_pricePerMap);
		add(txt_amount_bulks);
		add(chckbxShapedMap);
		add(chckbxElderMap);
	}

	public String[] getCurrencys() {
		return currencys;
	}

	public void setCurrencys(String[] currencys) {
		this.currencys = currencys;
	}

	public JButton getBtn_minimize_bulks() {
		return btn_minimize_bulks;
	}

	public void setBtn_minimize_bulks(JButton btn_minimize_bulks) {
		this.btn_minimize_bulks = btn_minimize_bulks;
	}

	public JButton getBtn_exit_bulks() {
		return btn_exit_bulks;
	}

	public void setBtn_exit_bulks(JButton btn_exit_bulks) {
		this.btn_exit_bulks = btn_exit_bulks;
	}

	public JButton getBtn_update_bulkbuyer() {
		return btn_update_bulkbuyer;
	}

	public void setBtn_update_bulkbuyer(JButton btn_update_bulkbuyer) {
		this.btn_update_bulkbuyer = btn_update_bulkbuyer;
	}

	public JButton getBtn_nextTrade_bulks() {
		return btn_nextTrade_bulks;
	}

	public void setBtn_nextTrade_bulks(JButton btn_nextTrade_bulks) {
		this.btn_nextTrade_bulks = btn_nextTrade_bulks;
	}

	public JLabel getLbl_currency_bulks() {
		return lbl_currency_bulks;
	}

	public void setLbl_currency_bulks(JLabel lbl_currency_bulks) {
		this.lbl_currency_bulks = lbl_currency_bulks;
	}

	public JLabel getLbl_tradeables_bulks() {
		return lbl_tradeables_bulks;
	}

	public void setLbl_tradeables_bulks(JLabel lbl_tradeables_bulks) {
		this.lbl_tradeables_bulks = lbl_tradeables_bulks;
	}

	public JLabel getLbl_created_bulks() {
		return lbl_created_bulks;
	}

	public void setLbl_created_bulks(JLabel lbl_created_bulks) {
		this.lbl_created_bulks = lbl_created_bulks;
	}

	public JLabel getLblMaptradoV_1() {
		return lblMaptradoV_1;
	}

	public void setLblMaptradoV_1(JLabel lblMaptradoV_1) {
		this.lblMaptradoV_1 = lblMaptradoV_1;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JLabel getLbl_payPerMap() {
		return lbl_payPerMap;
	}

	public void setLbl_payPerMap(JLabel lbl_payPerMap) {
		this.lbl_payPerMap = lbl_payPerMap;
	}

	public JLabel getLblBulkAmount() {
		return lblBulkAmount;
	}

	public void setLblBulkAmount(JLabel lblBulkAmount) {
		this.lblBulkAmount = lblBulkAmount;
	}

	public JLabel getLbl_map_bulks() {
		return lbl_map_bulks;
	}

	public void setLbl_map_bulks(JLabel lbl_map_bulks) {
		this.lbl_map_bulks = lbl_map_bulks;
	}

	public JComboBox<String> getCmb_maps_bulks() {
		return cmb_maps_bulks;
	}

	public void setCmb_maps_bulks(JComboBox<String> cmb_maps_bulks) {
		this.cmb_maps_bulks = cmb_maps_bulks;
	}

	public JComboBox<?> getCmb_currency_bulks() {
		return cmb_currency_bulks;
	}

	public void setCmb_currency_bulks(JComboBox<?> cmb_currency_bulks) {
		this.cmb_currency_bulks = cmb_currency_bulks;
	}

	public JTextField getTxtbox_pricePerMap() {
		return txtbox_pricePerMap;
	}

	public void setTxtbox_pricePerMap(JTextField txtbox_pricePerMap) {
		this.txtbox_pricePerMap = txtbox_pricePerMap;
	}

	public JTextField getTxt_amount_bulks() {
		return txt_amount_bulks;
	}

	public void setTxt_amount_bulks(JTextField txt_amount_bulks) {
		this.txt_amount_bulks = txt_amount_bulks;
	}

	public JCheckBox getChckbxShapedMap() {
		return chckbxShapedMap;
	}

	public void setChckbxShapedMap(JCheckBox chckbxShapedMap) {
		this.chckbxShapedMap = chckbxShapedMap;
	}

	public JCheckBox getChckbxElderMap() {
		return chckbxElderMap;
	}

	public void setChckbxElderMap(JCheckBox chckbxElderMap) {
		this.chckbxElderMap = chckbxElderMap;
	}
}
