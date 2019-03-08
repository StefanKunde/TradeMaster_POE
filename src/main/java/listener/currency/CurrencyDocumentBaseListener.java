package listener.currency;

import gui.MainFrame;
import listener.currencyPolling.CurrencyPollingBaseListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;

public abstract class CurrencyDocumentBaseListener extends CurrencyPollingBaseListener implements DocumentListener {

    public CurrencyDocumentBaseListener(MainFrame frame) {
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
