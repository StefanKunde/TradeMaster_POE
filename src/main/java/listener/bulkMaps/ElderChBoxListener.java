package listener.bulkMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Config;
import gui.MainFrame;

import javax.swing.*;

public class ElderChBoxListener implements ActionListener {

    private MainFrame frame;

    public ElderChBoxListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JCheckBox) e.getSource()).isSelected()) {
            frame.getPanelBulkMaps().getChckbxShapedMap().setEnabled(false);
            frame.loadMapsFromJson(Config.get().getElderMaps());
        } else {
            frame.getPanelBulkMaps().getChckbxShapedMap().setEnabled(true);
            frame.loadMapsFromJson(Config.get().getAllMaps());
        }
    }
}
