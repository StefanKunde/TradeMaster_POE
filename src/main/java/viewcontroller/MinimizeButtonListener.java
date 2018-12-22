package viewcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

import com.stefank.WindowManager;

import gui.MainFrame;
import gui.MinimizedFrame;

public class MinimizeButtonListener implements ActionListener {
	
	private MainFrame myMainFrame;
	private MinimizedFrame minFrame;
	public boolean userWantsMinimize;
	private boolean startedThread = false;
	
	public MinimizeButtonListener(MainFrame frame) {
		this.myMainFrame = frame;
		this.minFrame = new MinimizedFrame(frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
			SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	System.out.println("Minimize..");
	        		
	            	myMainFrame.setUserWantsMinimize(true);
	            	myMainFrame.setFrameInvisible();
	        		
	        		if(!startedThread) {
	        			ExecutorService executor = Executors.newFixedThreadPool(3);
		        		executor.execute( new WindowManager(minFrame));
	        		} else {
	        			minFrame.setFrameVisible();
	        		}
	        		
	        		startedThread = true;
	        		
	        		
					}
			    });
			
				startedThread = true;
			 }
		
		
		
}


