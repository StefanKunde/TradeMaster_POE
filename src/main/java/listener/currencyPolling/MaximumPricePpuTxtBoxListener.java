package listener.currencyPolling;

import gui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

public class MaximumPricePpuTxtBoxListener extends CurrencyPollingDocumentBaseListener {
    private Logger LOG = LoggerFactory.getLogger(MaximumPricePpuTxtBoxListener.class);

    public MaximumPricePpuTxtBoxListener(MainFrame frame) {
        super(frame);
    }

    @Override
    public void eventChecks(DocumentEvent e) {
        frame.setValidMaxPayInput(false);
        frame.getCurrencyPollerPanel().getUpdateButton().setEnabled(false);
        frame.getCurrencyPollerPanel().getCheckboxEnablePolling().setEnabled(false);

        try {
            double dblInput = Double.valueOf(e.getDocument().getText(0, e.getDocument().getLength()));
            if (dblInput > 0.1 && dblInput < 10000) {
                frame.setValidMaxPayInput(true);
                if (frame.isValidAmountCurrencyInput()) {
                    frame.getCurrencyPollerPanel().getUpdateButton().setEnabled(true);
                }
            }
        } catch (BadLocationException | NumberFormatException err) {
            LOG.error("MaximumPricePpuTxtBoxListener::eventChecks - Invalid double entered. " + err.getMessage());
            frame.getCurrencyPollerPanel().getUpdateButton().setEnabled(false);
        }
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }


}
