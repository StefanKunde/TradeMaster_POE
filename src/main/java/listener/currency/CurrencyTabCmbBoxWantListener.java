package listener.currency;

import gui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * What do I want? - Currency Tab Listener
 */
public class CurrencyTabCmbBoxWantListener extends CurrencyBaseListener {
    private Logger LOG = LoggerFactory.getLogger(CurrencyTabCmbBoxWantListener.class);

    public CurrencyTabCmbBoxWantListener(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedPayItem = (String) frame.getCurrencyBuyerPanel().getCmbCurrencyTabPay().getSelectedItem();
        String selectedWantItem = (String) ((JComboBox) e.getSource()).getSelectedItem();

        runCommonChecks(selectedPayItem, selectedWantItem);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }
}
