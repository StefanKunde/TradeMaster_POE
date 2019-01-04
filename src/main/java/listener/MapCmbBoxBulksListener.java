package listener;

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
			frame.getPanelBulkMaps().getBtn_update_bulkbuyer().setEnabled(true);
		}
	}
}
