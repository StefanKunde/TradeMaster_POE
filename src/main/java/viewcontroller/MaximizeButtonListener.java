package viewcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainFrame;
import gui.MinimizedFrame;
import utility.User32;

public class MaximizeButtonListener implements ActionListener {
	
	private MinimizedFrame minimizeFrame;
	private MainFrame mainframe;
	
	public MaximizeButtonListener(MinimizedFrame minimizeFrame, MainFrame mainFrame) {
		this.minimizeFrame = minimizeFrame;
		this.mainframe = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		minimizeFrame.setUserWantsMinimize(true);
		minimizeFrame.setFrameInvisible();
		
		
		mainframe.setUserWantsMinimize(false);
		mainframe.setFrameVisible();
	}
	
	
}
