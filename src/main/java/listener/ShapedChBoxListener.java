package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stefank.Main;

import gui.MainFrame;

public class ShapedChBoxListener implements ActionListener {

	private MainFrame frame;
	
	public ShapedChBoxListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame.isLoadedShapedMaps()) {
			frame.getPanelBulkMaps().getChckbxElderMap().setEnabled(true);
			frame.setLoadedElderMaps(false);
			frame.setLoadedShapedMaps(false);
		} else {
			frame.getPanelBulkMaps().getChckbxElderMap().setEnabled(false);
			frame.setLoadedShapedMaps(true);
			loadMapsFromJson("shapedMaps.json");
		}
	}
	
	private void loadMapsFromJson(String fileName) {
		String[] allMaps;
		List<String> allMapsAsList = new ArrayList<String>();
		@SuppressWarnings("resource")
		String text = new Scanner(Main.class.getResourceAsStream(fileName)).useDelimiter("\\A").next();
		byte[] bytes;
		String mapsAsJsonString = "";
		try {
			bytes = text.getBytes("UTF-8");
			mapsAsJsonString = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JSONObject json = new JSONObject(mapsAsJsonString);
        JSONArray maps = json.getJSONArray("maps");
        // convert json array into arraylist
        for(int i = 0; i < maps.length(); i++) {
        	
        	allMapsAsList.add(maps.get(i).toString());
        }
        Collections.sort(allMapsAsList, String.CASE_INSENSITIVE_ORDER);
        frame.getPanelBulkMaps().getCmb_maps_bulks().removeAllItems();
        
        for(int i = 0; i < allMapsAsList.size(); i++) {
        	frame.getPanelBulkMaps().getCmb_maps_bulks().addItem( allMapsAsList.get(i) );
        }
	}

}
