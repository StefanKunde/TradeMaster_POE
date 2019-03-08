package listener.bulkMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainFrame;

public class MapCmbBoxBulksListener implements ActionListener {
	
	private MainFrame frame;
	
	public MapCmbBoxBulksListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame.isValidAmountInput()) {
			frame.getPanelBulkMaps().getUpdateButton().setEnabled(true);
		}
	}
}
