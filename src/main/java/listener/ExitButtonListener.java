package listener;

import app.Main;
import gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButtonListener implements ActionListener {

	private MainFrame frame;
	
	public ExitButtonListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        Main.shutdown();
	}

}
