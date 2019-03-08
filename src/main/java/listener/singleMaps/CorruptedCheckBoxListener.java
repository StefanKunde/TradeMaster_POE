package listener.singleMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import gui.MainFrame;

public class CorruptedCheckBoxListener implements ActionListener {

    private MainFrame frame;

    public CorruptedCheckBoxListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JCheckBox) e.getSource()).isSelected()) {
            frame.getSingleMapsPanel().getChckbx_white().setSelected(false);
            frame.getSingleMapsPanel().getChckbx_white().setEnabled(false);

            frame.getSearchBuilder().setRarity("rare");
            frame.getSearchBuilder().setCorrupted(true);
        } else {
            frame.getSingleMapsPanel().getChckbx_white().setEnabled(true);
            frame.getSearchBuilder().setRarity("");
        }
    }

}
