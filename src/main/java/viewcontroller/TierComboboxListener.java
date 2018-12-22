package viewcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stefank.Main;

import gui.MainFrame;

public class TierComboboxListener implements ActionListener {
	
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
		System.out.println("selectedTier " + selectedTier);
		if(selectedTier.startsWith("Tier ")) {
			String mapsAsJsonString = "";
	        //mapsAsJsonString = new String(Files.readAllBytes(Paths.get("/resources/maps.json")));
			String text = new Scanner(Main.class.getResourceAsStream("maps.json"), "UTF-8").useDelimiter("\\A").next();
			mapsAsJsonString = new String(text);
	        
	        JSONObject json = new JSONObject(mapsAsJsonString);
	        JSONArray maps = json.getJSONArray(selectedTier);
	        List<String> mapsAsList = new ArrayList<String>();
	        // convert json array into arraylist
	        for(int i = 0; i < maps.length(); i++) {
	        	mapsAsList.add(maps.get(i).toString());
	        }
	        Collections.sort(mapsAsList, String.CASE_INSENSITIVE_ORDER);
	        
	        
	        String[] mapsAsArray = new String[mapsAsList.size()];
	        frame.getCmb_map().removeAllItems();
	        
	        for(int i = 0; i < mapsAsList.size(); i++) {
	        	frame.getCmb_map().addItem( mapsAsList.get(i) );
	        }
	        
	        frame.getCmb_map().setEnabled(true);
	        frame.getBtn_update().setEnabled(true);
		}
	}

}
