package listener.currencyPolling;

import gui.MainFrame;
import listener.currency.CurrencyDocumentBaseListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

@Slf4j
public class PollingTimeListener extends CurrencyPollingDocumentBaseListener {

    public PollingTimeListener(MainFrame frame) {
        super(frame);
    }

    @Override
    public void eventChecks(DocumentEvent e) {
        frame.getCurrencyPollerPanel().getCheckboxEnablePolling().setEnabled(false);

        boolean isValid = true;
        Integer pollingTime = 1000;
        try {
            pollingTime = Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength()));
        } catch (BadLocationException | NumberFormatException err) {
            log.error("PollingTimeListener::eventChecks failed - invalid polling time: " + err.getMessage());
            isValid = false;
        }

        if (pollingTime < 500) {
            isValid = false;
        }

        if (isValid) {
            frame.getCurrencyPollerPanel().getCheckboxEnablePolling().setEnabled(true);
        }
    }


    @Override
    public Logger getLogger() {
        return log;
    }

}
