package listener.bulkMaps;

import gui.MainFrame;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@Slf4j
public class PricePerMapTxtBoxListener implements DocumentListener {

    private MainFrame frame;

    public PricePerMapTxtBoxListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
//        String userInput = "";
//        try {
//            userInput = e.getDocument().getText(0, e.getDocument().getLength());
//        } catch (BadLocationException e1) {
//            LOG.error("PricePerMapBoxListener::insertUpdate", e1);
//        }

        // If we're typing here then it's not 'ANY', and if its blank or invalid we're defaulting it to 100.
        if (frame.isValidAmountInput()) {
            frame.getPanelBulkMaps().getUpdateButton().setEnabled(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
