package viewcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Frame;

public class ExitButtonListener implements ActionListener {

	private Frame frame;
	
	public ExitButtonListener(Frame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
