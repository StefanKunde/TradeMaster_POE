package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainFrame;

public class ElderChBoxListener implements ActionListener {

	private MainFrame frame;
	
	public ElderChBoxListener(MainFrame frame) {
		this.frame = frame;
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame.isLoadedElderMaps()) {
			frame.getPanelBulkMaps().getChckbxShapedMap().setEnabled(true);
			frame.setLoadedShapedMaps(false);
			frame.setLoadedElderMaps(false);
		} else {
			frame.getPanelBulkMaps().getChckbxShapedMap().setEnabled(false);
			frame.setLoadedElderMaps(true);
			frame.loadMapsFromJson();
		}
		
	}

}
