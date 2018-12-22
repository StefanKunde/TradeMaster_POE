package viewcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import gui.Frame;

public class MapComboboxListener implements ActionListener {

	private Frame frame;
	
	public MapComboboxListener(Frame frame) {
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedItem = (String) ((JComboBox) e.getSource()).getSelectedItem();
		frame.getSearchBuilder().setMapName(selectedItem);
		
	}

}
