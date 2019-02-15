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

public class PanelSingleMaps extends PanelBase {
	private static final long serialVersionUID = 1L;

	// Components
    private JLabel lblCurrency;
    private JLabel lblTier;
    private JLabel lblMap;
    private JLabel lblLoading;

    private JButton btn_nextTrade;
    private JComboBox<String> cmb_tier;

    private JComboBox<String> cmb_map;
    private JComboBox<String> cmb_currency;

    private JCheckBox chckbx_corrupted;
    private JCheckBox chckbx_white;
    private JCheckBox chckbxAutomateTrading;

	@Override
	protected void initTab() {
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

		lblLoading = new JLabel("loading...");
		lblLoading.setBounds(182, 213, 99, 50);
		lblLoading.setIcon(new ImageIcon(MainFrame.class.getResource("/com/stefank/Spinner.gif")));
		lblLoading.setForeground(Color.WHITE);
		lblLoading.setVisible(false);

		
		// BUTTONS
		btn_nextTrade = new JButton();
		btn_nextTrade.setBounds(10, 250, 134, 50);
		btn_nextTrade.setEnabled(false);
		btn_nextTrade.setText("Next Trade");

		// COMBO BOXEs
		cmb_tier = new JComboBox(MAP_TIERS);
		cmb_tier.setBounds(105, 59, 152, 20);
		
		cmb_map = new JComboBox(new String[]{});
		cmb_map.setBounds(105, 87, 152, 22);
		cmb_map.setEnabled(false);
		
		cmb_currency = new JComboBox(CURRENCY_TYPES);
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
		add(cmb_tier);
		add(lblTier);
		add(cmb_map);
		add(lblMap);
		add(btn_nextTrade);
		add(cmb_currency);
		add(lblLoading);
		add(chckbx_white);
		add(chckbx_corrupted);
		add(chckbxAutomateTrading);
	}


	@Override
	public String getTabTitle() {
		return "Trademaster - Singlebuyer";
	}

	public JButton getBtn_nextTrade() {
		return btn_nextTrade;
	}

	public JComboBox<?> getCmb_tier() {
		return cmb_tier;
	}

	public JComboBox<String> getCmb_map() {
		return cmb_map;
	}

	public JComboBox<?> getCmb_currency() {
		return cmb_currency;
	}

	public JCheckBox getChckbx_corrupted() {
		return chckbx_corrupted;
	}

	public JCheckBox getChckbx_white() {
		return chckbx_white;
	}

	public JCheckBox getChckbxAutomateTrading() {
		return chckbxAutomateTrading;
	}
}
