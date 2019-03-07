package listener.bulkMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import config.Config;

import gui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class ShapedChBoxListener implements ActionListener {

    private MainFrame frame;

    public ShapedChBoxListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JCheckBox) e.getSource()).isSelected()) {
            frame.getPanelBulkMaps().getChckbxElderMap().setEnabled(false);
            frame.loadMapsFromJson(Config.get().getShapedMaps());
        } else {
            frame.getPanelBulkMaps().getChckbxElderMap().setEnabled(true);
            frame.loadMapsFromJson(Config.get().getAllMaps());
        }
    }

}
