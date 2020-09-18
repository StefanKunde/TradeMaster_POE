package listener.currency;

import gui.MainFrame;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Slf4j
public class CurrencyTabCmbBoxWantListener extends CurrencyBaseListener {

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
        return log;
    }
}
