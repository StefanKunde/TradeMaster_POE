package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import config.Config;

public class PanelBulkMaps extends PanelBase {

    private static final long serialVersionUID = 1L;

    private JButton btn_nextTrade_bulks;
    private JLabel lbl_currency_bulks;

    private JLabel lbl_payPerMap;
    private JLabel lblBulkAmount;
    private JLabel lbl_map_bulks;
    private JComboBox<String> cmb_maps_bulks;
    private JComboBox<?> cmb_currency_bulks;
    private JTextField txtbox_pricePerMap;
    private JTextField txt_amount_bulks;
    private JCheckBox chckbxShapedMap;
    private JCheckBox chckbxElderMap;
    private JCheckBox chckbxAutomateTrading;

    @Override
    protected void initTab() {
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
        cmb_maps_bulks = new JComboBox(Config.get().getAllMaps());
        cmb_maps_bulks.setBounds(139, 95, 152, 20);

        cmb_currency_bulks = new JComboBox(Config.get().getBulkBuyWithCurrencies());
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

        chckbxAutomateTrading = new JCheckBox("Automatically send offers?");
        chckbxAutomateTrading.setForeground(new Color(255, 255, 255));
        chckbxAutomateTrading.setBackground(new Color(255, 0, 0));
        chckbxAutomateTrading.setFont(new Font("Tahoma", Font.PLAIN, 8));
        chckbxAutomateTrading.setBounds(10, 227, 134, 23);

        add(btn_nextTrade_bulks);
        add(lbl_currency_bulks);
        add(lbl_payPerMap);
        add(lblBulkAmount);
        add(lbl_map_bulks);
        add(cmb_maps_bulks);
        add(cmb_currency_bulks);
        add(txtbox_pricePerMap);
        add(txt_amount_bulks);
        add(chckbxShapedMap);
        add(chckbxElderMap);
        add(chckbxAutomateTrading);
    }


    @Override
    public String getTabTitle() {
        return "Bulk Maps";
    }

    public JButton getBtn_nextTrade_bulks() {
        return btn_nextTrade_bulks;
    }

    public JComboBox<String> getCmb_maps_bulks() {
        return cmb_maps_bulks;
    }

    public JComboBox<?> getCmb_currency_bulks() {
        return cmb_currency_bulks;
    }

    public JTextField getTxtbox_pricePerMap() {
        return txtbox_pricePerMap;
    }

    public JTextField getTxt_amount_bulks() {
        return txt_amount_bulks;
    }

    public JCheckBox getChckbxShapedMap() {
        return chckbxShapedMap;
    }

    public JCheckBox getChckbxElderMap() {
        return chckbxElderMap;
    }

    public JCheckBox getChckbxAutomateTrading() {
        return chckbxAutomateTrading;
    }
}
