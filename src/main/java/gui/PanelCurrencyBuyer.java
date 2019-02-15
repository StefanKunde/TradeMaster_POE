package gui;

import java.awt.*;
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

public class PanelCurrencyBuyer extends PanelBase {

    private static final long serialVersionUID = 1L;

    JButton btn_nextTrade_currencyTab;
    JLabel lblIWant;
    JLabel lblAmount;
    JLabel lblPayForAmount;


    JLabel lblWhatDoI;
    JLabel lbl_priceCheck;
    JComboBox<String> cmb_currencyTab_pay;
    JComboBox<String> cmb_currencyTab_want;
    JTextField txt_currencyTab_neededAmount;
    JTextField txt_currencyTab_MAXpay;
    JCheckBox chckbxAutomateTrading;

    @Override
    protected void initTab() {
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

        // CHECKBOXES
        chckbxAutomateTrading = new JCheckBox("Automatically send offers?");
        chckbxAutomateTrading.setForeground(new Color(255, 255, 255));
        chckbxAutomateTrading.setBackground(new Color(255, 0, 0));
        chckbxAutomateTrading.setFont(new Font("Tahoma", Font.PLAIN, 8));
        chckbxAutomateTrading.setBounds(10, 227, 134, 23);

        // TEXTBOXES
        txt_currencyTab_neededAmount = new JTextField();
        txt_currencyTab_neededAmount.setColumns(10);
        txt_currencyTab_neededAmount.setBounds(172, 59, 153, 20);

        txt_currencyTab_MAXpay = new JTextField();
        txt_currencyTab_MAXpay.setColumns(10);
        txt_currencyTab_MAXpay.setBounds(172, 115, 153, 20);

        add(btn_nextTrade_currencyTab);
        add(lblIWant);
        add(lblAmount);
        add(lblPayForAmount);
        add(lblWhatDoI);
        add(lbl_priceCheck);
        add(cmb_currencyTab_pay);
        add(cmb_currencyTab_want);
        add(chckbxAutomateTrading);
        add(txt_currencyTab_neededAmount);
        add(txt_currencyTab_MAXpay);
    }


    @Override
    public String getTabTitle() {
        return "Trademaster - Currency";
    }

    public JButton getBtn_nextTrade_currencyTab() {
        return btn_nextTrade_currencyTab;
    }

    public JComboBox<String> getCmb_currencyTab_pay() {
        return cmb_currencyTab_pay;
    }

    public JComboBox<String> getCmb_currencyTab_want() {
        return cmb_currencyTab_want;
    }

    public JTextField getTxt_currencyTab_neededAmount() {
        return txt_currencyTab_neededAmount;
    }

    public JTextField getTxt_currencyTab_MAXpay() {
        return txt_currencyTab_MAXpay;
    }

    public JLabel getLbl_priceCheck() {
        return lbl_priceCheck;
    }

    public JCheckBox getChckbxAutomateTrading() {
        return chckbxAutomateTrading;
    }
}
