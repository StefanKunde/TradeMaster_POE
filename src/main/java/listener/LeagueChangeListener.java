package listener;

import gui.MainFrame;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LeagueChangeListener implements ItemListener {

    private MainFrame frame;

    public LeagueChangeListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Activate update on change from original.
            frame.getSettingsPanel().getUpdateButton().setEnabled(true);
        }
    }

}
