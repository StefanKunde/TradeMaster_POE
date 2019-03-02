package listener;

import gui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class MaxPayTxtBoxListener implements DocumentListener {
    private Logger LOG = LoggerFactory.getLogger(MaxPayTxtBoxListener.class);

    private MainFrame frame;

    public MaxPayTxtBoxListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String userInput = "";
        try {
            userInput = e.getDocument().getText(0, e.getDocument().getLength());
        } catch (BadLocationException e1) {
            LOG.error("MaxPayTxtBoxListener::insertUpdate", e1);
        }

        if (isNumeric(userInput) && Integer.valueOf(userInput) >= 1 && Integer.valueOf(userInput) <= 10000) { // between 1 and 10000
            frame.setValidMaxPayInput(true);

            if (frame.isValidAmountCurrencyInput()) {
                frame.getCurrencyBuyerPanel().getUpdateButton().setEnabled(true);
            }

            LOG.debug("MaxPayTxtBoxListener true");
        } else {
            frame.setValidMaxPayInput(false);
            frame.getCurrencyBuyerPanel().getUpdateButton().setEnabled(false);
            LOG.debug("MaxPayTxtBoxListener false");
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {


    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

}
