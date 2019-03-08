package listener.singleMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import gui.MainFrame;

public class WhiteCheckBoxListener implements ActionListener {

	private MainFrame frame;
	
	public WhiteCheckBoxListener(MainFrame frame) {
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JCheckBox) e.getSource()).isSelected()) {
			frame.getSingleMapsPanel().getChckbx_corrupted().setSelected(false);
			frame.getSingleMapsPanel().getChckbx_corrupted().setEnabled(false);
			
			frame.getSearchBuilder().setRarity("normal");
		} else {
			frame.getSingleMapsPanel().getChckbx_corrupted().setEnabled(true);
			frame.getSearchBuilder().setRarity("");
		}
	}
}
