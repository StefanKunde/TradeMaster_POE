package listener.currency;

import gui.MainFrame;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

@Slf4j
public class NeededAmountTxtBoxListener extends CurrencyDocumentBaseListener {

    public NeededAmountTxtBoxListener(MainFrame frame) {
        super(frame);
    }

    @Override
    public void eventChecks(DocumentEvent e) {
        String userInput = "";
        try {
            userInput = e.getDocument().getText(0, e.getDocument().getLength());
        } catch (BadLocationException e1) {
            log.error("MaxPayTxtBoxListener::insertUpdate", e1);
        }

        //Default
        frame.setValidAmountCurrencyInput(false);
        frame.getCurrencyBuyerPanel().getUpdateButton().setEnabled(false);

        try {
            int inputInt = Integer.valueOf(userInput);
            if (inputInt >= 1 && inputInt <= 10000) { // between 1 and 10000
                frame.setValidAmountCurrencyInput(true);
                if (frame.isValidMaxPayInput()) {
                    frame.getCurrencyBuyerPanel().getUpdateButton().setEnabled(true);
                }

                String selectedPayItem = (String) frame.getCurrencyBuyerPanel().getCmbCurrencyTabPay().getSelectedItem();
                String selectedWantItem = (String) frame.getCurrencyBuyerPanel().getCmbCurrencyTabWant().getSelectedItem();
                runCommonChecks(selectedPayItem, selectedWantItem);
            }
        } catch (NumberFormatException nfe) {
            log.debug("Invalid number input, not re-updating");
        }
    }


    @Override
    public Logger getLogger() {
        return log;
    }

}
