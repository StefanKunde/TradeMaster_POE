package listener.currencyPolling;

import gui.MainFrame;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;

public abstract class CurrencyPollingDocumentBaseListener extends CurrencyPollingBaseListener implements DocumentListener {

    public CurrencyPollingDocumentBaseListener(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Nothign here
    }

    public void changedUpdate(DocumentEvent e) {
        eventChecks(e);
    }

    public void removeUpdate(DocumentEvent e) {
        eventChecks(e);
    }

    public void insertUpdate(DocumentEvent e) {
        eventChecks(e);
    }

    public abstract void eventChecks(DocumentEvent e);
}
