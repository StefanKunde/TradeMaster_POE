package gui;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PanelCurrencyBuyer extends PanelBase {

    private static final long serialVersionUID = 1L;

    private JButton btnNextTradeCurrencyTab;
    private JLabel lblIWant;
    protected JLabel lblAmount;
    private JLabel lblPayForAmount;

    private JLabel lblWhatDoI;
    private JLabel lblPriceCheck;
    private JComboBox<String> cmbCurrencyTabPay;
    private JComboBox<String> cmbCurrencyTabWant;
    private JTextField txtCurrencyTabNeededAmount;
    private JTextField txtCurrencyTabMaxPay;
    private JCheckBox chckbxAutomateTrading;

    @Override
    protected void initTab() {
        btnNextTradeCurrencyTab = new JButton();
        btnNextTradeCurrencyTab.setText("Next Trade");
        btnNextTradeCurrencyTab.setEnabled(false);
        btnNextTradeCurrencyTab.setBounds(10, 250, 134, 50);

        // LABELS
        lblIWant = new JLabel("What do I want?");
        lblIWant.setForeground(Color.WHITE);
        lblIWant.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblIWant.setBackground(Color.GRAY);
        lblIWant.setBounds(10, 34, 152, 14);

        lblAmount = new JLabel("Needed amount");
        lblAmount.setForeground(Color.WHITE);
        lblAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblAmount.setBackground(Color.GRAY);
        lblAmount.setBounds(172, 34, 153, 14);

        lblPayForAmount = new JLabel("Max price individual item");
        lblPayForAmount.setForeground(Color.WHITE);
        lblPayForAmount.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblPayForAmount.setBackground(Color.GRAY);
        lblPayForAmount.setBounds(172, 90, 152, 14);


        lblWhatDoI = new JLabel("What do I pay?");
        lblWhatDoI.setForeground(Color.WHITE);
        lblWhatDoI.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblWhatDoI.setBackground(Color.GRAY);
        lblWhatDoI.setBounds(10, 90, 152, 14);

        lblPriceCheck = new JLabel("Price info box...");
        lblPriceCheck.setForeground(new Color(255, 235, 205));
        lblPriceCheck.setForeground(SystemColor.info);
        lblPriceCheck.setBounds(10, 158, 152, 66);
        lblPriceCheck.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblPriceCheck.setSize(314, 43);
        lblPriceCheck.setLocation(10, 146);

        // COMBOBOXES
        cmbCurrencyTabPay = new JComboBox();
        cmbCurrencyTabPay.setBounds(10, 115, 152, 20);

        cmbCurrencyTabWant = new JComboBox();
        cmbCurrencyTabWant.setBounds(10, 59, 152, 20);

        // CHECKBOXES
        chckbxAutomateTrading = new JCheckBox("Automatically send offers?");
        chckbxAutomateTrading.setForeground(new Color(255, 255, 255));
        chckbxAutomateTrading.setBackground(new Color(255, 0, 0));
        chckbxAutomateTrading.setFont(new Font("Tahoma", Font.PLAIN, 8));
        chckbxAutomateTrading.setBounds(10, 227, 134, 23);

        // TEXTBOXES
        txtCurrencyTabNeededAmount = new JTextField();
        txtCurrencyTabNeededAmount.setColumns(10);
        txtCurrencyTabNeededAmount.setBounds(172, 59, 153, 20);

        txtCurrencyTabMaxPay = new JTextField();
        txtCurrencyTabMaxPay.setColumns(10);
        txtCurrencyTabMaxPay.setBounds(172, 115, 153, 20);

        add(btnNextTradeCurrencyTab);
        add(lblIWant);
        add(lblAmount);
        add(lblPayForAmount);
        add(lblWhatDoI);
        add(lblPriceCheck);
        add(cmbCurrencyTabPay);
        add(cmbCurrencyTabWant);
        add(chckbxAutomateTrading);
        add(txtCurrencyTabNeededAmount);
        add(txtCurrencyTabMaxPay);
    }


    @Override
    public String getTabTitle() {
        return "Currency";
    }


    public JButton getBtnNextTradeCurrencyTab() {
        return btnNextTradeCurrencyTab;
    }

    public JComboBox<String> getCmbCurrencyTabPay() {
        return cmbCurrencyTabPay;
    }

    public JComboBox<String> getCmbCurrencyTabWant() {
        return cmbCurrencyTabWant;
    }

    public JTextField getTxtCurrencyTabNeededAmount() {
        return txtCurrencyTabNeededAmount;
    }

    public JTextField getTxtCurrencyTabMaxPay() {
        return txtCurrencyTabMaxPay;
    }

    public void setLabelPriceCheckText(String value) {
        this.lblPriceCheck.setText(value);
    }

    public JCheckBox getChckbxAutomateTrading() {
        return chckbxAutomateTrading;
    }
}
