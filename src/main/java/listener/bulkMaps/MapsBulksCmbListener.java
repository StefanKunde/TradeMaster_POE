package listener.bulkMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import gui.MainFrame;

public class MapsBulksCmbListener implements ActionListener {

    private MainFrame frame;

    public MapsBulksCmbListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedCurrency = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();
        if (!"ANY".equalsIgnoreCase(selectedCurrency)) {
            frame.getPanelBulkMaps().getTxtbox_pricePerMap().setEnabled(true);
        } else {
            frame.getPanelBulkMaps().getTxtbox_pricePerMap().setText("");
            frame.getPanelBulkMaps().getTxtbox_pricePerMap().setEnabled(false);
        }

        if (frame.isValidAmountInput()) {
            frame.getPanelBulkMaps().getUpdateButton().setEnabled(true);
        } else {
            frame.getPanelBulkMaps().getUpdateButton().setEnabled(false);
        }
    }

}
