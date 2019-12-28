package listener.currencyPolling;

import gui.MainFrame;
import listener.currency.CurrencyBaseListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * What do I want? - Currency Polling Tab Listener
 */
public class WhatDoIWantComboBoxListener extends CurrencyPollingBaseListener {
    private Logger LOG = LoggerFactory.getLogger(WhatDoIWantComboBoxListener.class);

    public WhatDoIWantComboBoxListener(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedWantItem = (String) ((JComboBox) e.getSource()).getSelectedItem();
        String selectedPayItem = (String) frame.getCurrencyPollerPanel().getCmbCurrencyTabPay().getSelectedItem();
        runCommonChecks(selectedPayItem, selectedWantItem);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }
}
