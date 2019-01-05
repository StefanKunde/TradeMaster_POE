package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.stefank.Main;

public class PanelCurrencyBuyer extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	JButton btn_minimize_cur;
	JButton btn_exit_cur;
	JButton btn_update_currency;
	JButton btn_nextTrade_currencyTab;
	JLabel lblIWant;
	JLabel lbl_tradeables_currencyTab;
	JLabel lblAmount;
	JLabel lblPayForAmount;
	JLabel lblBeta;
	JLabel lblTrademasterCurrency;
	JLabel label_8;
	JLabel lblWhatDoI;
	JLabel lbl_priceCheck;
	JComboBox<String> cmb_currencyTab_pay;
	JComboBox<String> cmb_currencyTab_want;
	JTextField txt_currencyTab_neededAmount;
	JTextField txt_currencyTab_MAXpay;
	

	public PanelCurrencyBuyer() {
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
		
		setLayout(null);
		setPreferredSize(new Dimension(420, 315));
		setForeground(Color.GRAY);
		setBackground(new Color(51, 51, 51));
		
		// BUTTONS
		btn_minimize_cur = new JButton();
		btn_minimize_cur.setSize(19, 24);
		btn_minimize_cur.setLocation(322, -1);
		btn_minimize_cur.setOpaque(false);
		btn_minimize_cur.setForeground(Color.BLACK);
		btn_minimize_cur.setContentAreaFilled(false);
		btn_minimize_cur.setBorderPainted(false);
		btn_minimize_cur.setBackground(new Color(204, 0, 0));
		btn_minimize_cur.setOpaque(false);
		btn_minimize_cur.setContentAreaFilled(false);
		btn_minimize_cur.setBorderPainted(false);
		btn_minimize_cur.setIcon(new ImageIcon(minimizeIcon));
		btn_minimize_cur.setFocusPainted(false);
		
		btn_exit_cur = new JButton();
		btn_exit_cur.setForeground(new Color(0, 0, 0));
		btn_exit_cur.setBackground(new Color(204, 0, 0));
		btn_exit_cur.setOpaque(false);
		btn_exit_cur.setContentAreaFilled(false);
		btn_exit_cur.setBorderPainted(false);
		btn_exit_cur.setIcon(new ImageIcon(cancelIcon));
		btn_exit_cur.setFocusPainted(false);
		btn_exit_cur.setBounds(342, -1, 19, 24);
		
		btn_update_currency = new JButton();
		btn_update_currency.setText("Update");
		btn_update_currency.setEnabled(false);
		btn_update_currency.setBounds(139, 186, 152, 43);
		btn_update_currency.setLocation(172, 196);
		
		btn_nextTrade_currencyTab = new JButton();
		btn_nextTrade_currencyTab.setText("Next Trade");
		btn_nextTrade_currencyTab.setEnabled(false);
		btn_nextTrade_currencyTab.setBounds(10, 250, 134, 50);
		
		// LABELS
		lblIWant = new JLabel("What do I want?");
		lblIWant.setForeground(Color.WHITE);
		lblIWant.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIWant.setBackground(Color.GRAY);
		lblIWant.setBounds(10, 34, 152, 14);
		
		lbl_tradeables_currencyTab = new JLabel("Tradeables: ");
		lbl_tradeables_currencyTab.setForeground(Color.WHITE);
		lbl_tradeables_currencyTab.setEnabled(false);
		lbl_tradeables_currencyTab.setBounds(154, 250, 128, 14);
		lbl_tradeables_currencyTab.setLocation(172, 250);
		
		lblAmount = new JLabel("Needed amount");
		lblAmount.setForeground(Color.WHITE);
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAmount.setBackground(Color.GRAY);
		lblAmount.setBounds(172, 34, 153, 14);
		
		lblPayForAmount = new JLabel("MAX pay for amount");
		lblPayForAmount.setForeground(Color.WHITE);
		lblPayForAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPayForAmount.setBackground(Color.GRAY);
		lblPayForAmount.setBounds(172, 90, 152, 14);
		
		lblBeta = new JLabel("beta 1.3");
		lblBeta.setForeground(new Color(255, 235, 205));
		lblBeta.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblBeta.setBackground(new Color(0, 128, 0));
		lblBeta.setBounds(294, 286, 45, 14);
		
		lblTrademasterCurrency = new JLabel("Trademaster - Currency");
		lblTrademasterCurrency.setForeground(new Color(255, 235, 205));
		lblTrademasterCurrency.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTrademasterCurrency.setBackground(new Color(0, 128, 0));
		lblTrademasterCurrency.setBounds(75, -1, 237, 24);
		
		label_8 = new JLabel("Created by ezkk2");
		label_8.setForeground(new Color(255, 235, 205));
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 8));
		label_8.setBackground(new Color(0, 128, 0));
		label_8.setBounds(210, 286, 86, 14);
		
		lblWhatDoI = new JLabel("What do I pay?");
		lblWhatDoI.setForeground(Color.WHITE);
		lblWhatDoI.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWhatDoI.setBackground(Color.GRAY);
		lblWhatDoI.setBounds(10, 90, 152, 14);
		
		lbl_priceCheck = new JLabel("Priceinfobox...");
		lbl_priceCheck.setForeground(new Color(255, 235, 205));
		lbl_priceCheck.setForeground(SystemColor.info);
		lbl_priceCheck.setBounds(10, 158, 152, 66);
		lbl_priceCheck.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_priceCheck.setSize(314, 43);
		lbl_priceCheck.setLocation(10, 146);
		
		// COMBOBOXES
		cmb_currencyTab_pay = new JComboBox();
		cmb_currencyTab_pay.setBounds(10, 115, 152, 20);
		
		cmb_currencyTab_want = new JComboBox();
		cmb_currencyTab_want.setBounds(10, 59, 152, 20);
		
		// TEXTBOXES
		txt_currencyTab_neededAmount = new JTextField();
		txt_currencyTab_neededAmount.setColumns(10);
		txt_currencyTab_neededAmount.setBounds(172, 59, 153, 20);
		
		txt_currencyTab_MAXpay = new JTextField();
		txt_currencyTab_MAXpay.setColumns(10);
		txt_currencyTab_MAXpay.setBounds(172, 115, 153, 20);
		
		add(btn_minimize_cur);
		add(btn_exit_cur);
		add(btn_update_currency);
		add(btn_nextTrade_currencyTab);
		add(lblIWant);
		add(lbl_tradeables_currencyTab);
		add(lblAmount);
		add(lblPayForAmount);
		add(lblBeta);
		add(lblTrademasterCurrency);
		add(label_8);
		add(lblWhatDoI);
		add(lbl_priceCheck);
		add(cmb_currencyTab_pay);
		add(cmb_currencyTab_want);
		add(txt_currencyTab_neededAmount);
		add(txt_currencyTab_MAXpay);
	}

	public JButton getBtn_minimize_cur() {
		return btn_minimize_cur;
	}

	public void setBtn_minimize_cur(JButton btn_minimize_cur) {
		this.btn_minimize_cur = btn_minimize_cur;
	}

	public JButton getBtn_exit_cur() {
		return btn_exit_cur;
	}

	public void setBtn_exit_cur(JButton btn_exit_cur) {
		this.btn_exit_cur = btn_exit_cur;
	}

	public JButton getBtn_update_currency() {
		return btn_update_currency;
	}

	public void setBtn_update_currency(JButton btn_update_currency) {
		this.btn_update_currency = btn_update_currency;
	}

	public JButton getBtn_nextTrade_currencyTab() {
		return btn_nextTrade_currencyTab;
	}

	public void setBtn_nextTrade_currencyTab(JButton btn_nextTrade_currencyTab) {
		this.btn_nextTrade_currencyTab = btn_nextTrade_currencyTab;
	}

	public JLabel getLblIWant() {
		return lblIWant;
	}

	public void setLblIWant(JLabel lblIWant) {
		this.lblIWant = lblIWant;
	}

	public JLabel getLbl_tradeables_currencyTab() {
		return lbl_tradeables_currencyTab;
	}

	public void setLbl_tradeables_currencyTab(JLabel lbl_tradeables_currencyTab) {
		this.lbl_tradeables_currencyTab = lbl_tradeables_currencyTab;
	}

	public JLabel getLblAmount() {
		return lblAmount;
	}

	public void setLblAmount(JLabel lblAmount) {
		this.lblAmount = lblAmount;
	}

	public JLabel getLblPayForAmount() {
		return lblPayForAmount;
	}

	public void setLblPayForAmount(JLabel lblPayForAmount) {
		this.lblPayForAmount = lblPayForAmount;
	}

	public JLabel getLblBeta() {
		return lblBeta;
	}

	public void setLblBeta(JLabel lblBeta) {
		this.lblBeta = lblBeta;
	}

	public JLabel getLblTrademasterCurrency() {
		return lblTrademasterCurrency;
	}

	public void setLblTrademasterCurrency(JLabel lblTrademasterCurrency) {
		this.lblTrademasterCurrency = lblTrademasterCurrency;
	}

	public JLabel getLabel_8() {
		return label_8;
	}

	public void setLabel_8(JLabel label_8) {
		this.label_8 = label_8;
	}

	public JLabel getLblWhatDoI() {
		return lblWhatDoI;
	}

	public void setLblWhatDoI(JLabel lblWhatDoI) {
		this.lblWhatDoI = lblWhatDoI;
	}

	public JComboBox<String> getCmb_currencyTab_pay() {
		return cmb_currencyTab_pay;
	}

	public void setCmb_currencyTab_pay(JComboBox<String> cmb_currencyTab_pay) {
		this.cmb_currencyTab_pay = cmb_currencyTab_pay;
	}

	public JComboBox<String> getCmb_currencyTab_want() {
		return cmb_currencyTab_want;
	}

	public void setCmb_currencyTab_want(JComboBox<String> cmb_currencyTab_want) {
		this.cmb_currencyTab_want = cmb_currencyTab_want;
	}

	public JTextField getTxt_currencyTab_neededAmount() {
		return txt_currencyTab_neededAmount;
	}

	public void setTxt_currencyTab_neededAmount(JTextField txt_currencyTab_neededAmount) {
		this.txt_currencyTab_neededAmount = txt_currencyTab_neededAmount;
	}

	public JTextField getTxt_currencyTab_MAXpay() {
		return txt_currencyTab_MAXpay;
	}

	public void setTxt_currencyTab_MAXpay(JTextField txt_currencyTab_MAXpay) {
		this.txt_currencyTab_MAXpay = txt_currencyTab_MAXpay;
	}

	public JLabel getLbl_priceCheck() {
		return lbl_priceCheck;
	}

	public void setLbl_priceCheck(JLabel lbl_priceCheck) {
		this.lbl_priceCheck = lbl_priceCheck;
	}
	
	
	
	
}
