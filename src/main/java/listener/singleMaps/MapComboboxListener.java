package listener.singleMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import gui.MainFrame;

public class MapComboboxListener implements ActionListener {

    private MainFrame frame;

    public MapComboboxListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = (String) ((JComboBox) e.getSource()).getSelectedItem();
        frame.getSearchBuilder().setMapName(selectedItem);
    }

}
