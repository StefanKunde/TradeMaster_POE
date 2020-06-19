package gui;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class PanelCurrencyPolling extends PanelCurrencyBuyer {

    private static final long serialVersionUID = 1L;

    private JLabel lblPolling;
    @Getter
    private JTextField pollingInMillis;
    @Getter
    private JCheckBox checkboxEnablePolling;

    @Override
    protected void initTab() {
        super.initTab();

        // LABELS
        lblPolling = new JLabel("Polling time");
        lblPolling.setForeground(Color.WHITE);
        lblPolling.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblPolling.setBackground(Color.GRAY);
        lblPolling.setBounds(172, 140, 75, 14);

        getLblAmount().setText("Minimum Search Order");

        // TEXTBOXES
        pollingInMillis = new JTextField();
        pollingInMillis.setColumns(10);
        pollingInMillis.setBounds(247, 140, 75, 20);
        pollingInMillis.setText("1000");

        // CHECKBOXES
        checkboxEnablePolling = new JCheckBox("Enable Polling?");
        checkboxEnablePolling.setForeground(new Color(255, 255, 255));
        checkboxEnablePolling.setBackground(new Color(255, 0, 0));
        checkboxEnablePolling.setFont(new Font("Tahoma", Font.PLAIN, 10));
        checkboxEnablePolling.setBounds(172, 165, 100, 23);
        checkboxEnablePolling.setEnabled(false);

        add(lblPolling);
        add(pollingInMillis);
        add(checkboxEnablePolling);

        // Don't want the price stuff for polling...
        remove(getLblPriceCheck());
    }


    @Override
    public String getTabTitle() {
        return "Currency Polling";
    }




}
