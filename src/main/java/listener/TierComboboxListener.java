package listener;

import com.stefank.Main;
import gui.MainFrame;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
	
	private void loadMapsFromSelectedTier(String selectedTier) {
		LOG.debug("selectedTier " + selectedTier);
		if(selectedTier.startsWith("Tier ")) {
	        //mapsAsJsonString = new String(Files.readAllBytes(Paths.get("/resources/maps.json")));
			@SuppressWarnings("resource")
			String text = new Scanner(Main.class.getResourceAsStream("maps.json")).useDelimiter("\\A").next();
			
			byte[] bytes;
			String mapsAsJsonString = "";
			try {
				bytes = text.getBytes("UTF-8");
				mapsAsJsonString = new String(bytes, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				LOG.error("TierComboboxListener::loadMapsFromSelectedTier", e);
			}
	        
	        JSONObject json = new JSONObject(mapsAsJsonString);
	        JSONArray maps = json.getJSONArray(selectedTier);
	        List<String> mapsAsList = new ArrayList<String>();
	        // convert json array into arraylist
	        for(int i = 0; i < maps.length(); i++) {
	        	mapsAsList.add(maps.get(i).toString());
	        }
	        Collections.sort(mapsAsList, String.CASE_INSENSITIVE_ORDER);
	        
	        
	        String[] mapsAsArray = new String[mapsAsList.size()];
	        frame.getSingleMapsPanel().getCmb_map().removeAllItems();
	        
	        for(int i = 0; i < mapsAsList.size(); i++) {
	        	frame.getSingleMapsPanel().getCmb_map().addItem( mapsAsList.get(i) );
	        }
	        
	        String segments[] = selectedTier.split(" ");
	        frame.getSearchBuilder().setTier(segments[1]);
	        
	        frame.getSingleMapsPanel().getCmb_map().setEnabled(true);
	        frame.getSingleMapsPanel().getUpdateButton().setEnabled(true);
		}
	}

}
