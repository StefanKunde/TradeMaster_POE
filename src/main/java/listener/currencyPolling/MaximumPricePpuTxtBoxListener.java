package listener.currencyPolling;

import gui.MainFrame;
import listener.currency.CurrencyDocumentBaseListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

public class MaximumPricePpuTxtBoxListener extends CurrencyDocumentBaseListener {
    private Logger LOG = LoggerFactory.getLogger(MaximumPricePpuTxtBoxListener.class);

    public MaximumPricePpuTxtBoxListener(MainFrame frame) {
        super(frame);
    }

    @Override
    public void eventChecks(DocumentEvent e) {
        String userInput = "";
        try {
            userInput = e.getDocument().getText(0, e.getDocument().getLength());
        } catch (BadLocationException e1) {
            LOG.error("MaxPayTxtBoxListener::insertUpdate", e1);
        }

        frame.setValidMaxPayInput(false);
        frame.getCurrencyBuyerPanel().getUpdateButton().setEnabled(false);

        try {
            double dblInput = Double.valueOf(userInput);
            if (dblInput > 0.1 && dblInput < 10000) {
                frame.setValidMaxPayInput(true);
                if (frame.isValidAmountCurrencyInput()) {
                    frame.getCurrencyBuyerPanel().getUpdateButton().setEnabled(true);
                }

                LOG.debug("MaxPayTxtBoxListener true");
                String selectedPayItem = (String) frame.getCurrencyBuyerPanel().getCmbCurrencyTabPay().getSelectedItem();
                String selectedWantItem = (String) frame.getCurrencyBuyerPanel().getCmbCurrencyTabWant().getSelectedItem();
                runCommonChecks(selectedPayItem, selectedWantItem);
            }
        } catch (NumberFormatException nfe) {
            LOG.debug("Invalid double entered. MaxPayTxtBoxListener - false");
        }
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }


}