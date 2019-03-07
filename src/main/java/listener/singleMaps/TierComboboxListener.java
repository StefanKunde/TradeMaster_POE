package listener.singleMaps;

import config.Config;
import gui.MainFrame;
import listener.bulkMaps.ShapedChBoxListener;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class TierComboboxListener implements ActionListener {
    private Logger LOG = LoggerFactory.getLogger(ShapedChBoxListener.class);

    private MainFrame frame;

    public TierComboboxListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedItem = (String) ((JComboBox) e.getSource()).getSelectedItem();
        this.loadMapsFromSelectedTier(selectedItem);
    }

    public void loadMapsFromSelectedTier(String selectedTier) {
        LOG.debug("selectedTier " + selectedTier);

        if (selectedTier.startsWith("Tier ")) {
            JSONObject tierMaps = Config.get().getTieredMaps();
            JSONArray tieredMapsArray = tierMaps.getJSONArray(selectedTier);
            List<String> mapsAsList = new ArrayList<>();
            for (int i = 0; i < tieredMapsArray.length(); i++) {
                mapsAsList.add(tieredMapsArray.get(i).toString());
            }
            Collections.sort(mapsAsList, String.CASE_INSENSITIVE_ORDER);
            frame.getSingleMapsPanel().getCmb_map().removeAllItems();

            for (int i = 0; i < mapsAsList.size(); i++) {
                frame.getSingleMapsPanel().getCmb_map().addItem(mapsAsList.get(i));
            }

            String segments[] = selectedTier.split(" ");
            frame.getSearchBuilder().setTier(segments[1]);
            frame.getSingleMapsPanel().getCmb_map().setEnabled(true);
            frame.getSingleMapsPanel().getUpdateButton().setEnabled(true);
        }
    }

}
