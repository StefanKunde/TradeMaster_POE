package listener.singleMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import gui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyComboboxListener implements ActionListener {
    private MainFrame frame;

    public CurrencyComboboxListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedCurrency = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();
        if ("Any".equalsIgnoreCase(selectedCurrency)) {
            selectedCurrency = "";
        }
        frame.setCurrency(selectedCurrency);
        frame.getSearchBuilder().setCurrency(selectedCurrency);
    }

}
