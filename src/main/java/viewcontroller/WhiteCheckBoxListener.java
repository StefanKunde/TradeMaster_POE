package viewcontroller;

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
		if( ((JCheckBox) e.getSource()).isSelected()) {
			frame.getChckbx_corrupted().setSelected(false);
			frame.getChckbx_corrupted().setEnabled(false);
			
			frame.getSearchBuilder().setRarity("normal");
			frame.getSearchBuilder().setCorrupted(false);
			
		} else {
			frame.getChckbx_corrupted().setEnabled(true);
		}
	}

}
