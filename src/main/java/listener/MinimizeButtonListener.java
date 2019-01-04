package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;

import com.sun.jna.Native;
import com.sun.jna.PointerType;

import gui.IHideable;
import gui.MainFrame;
import gui.MinimizedFrame;
import utility.User32;

public class MinimizeButtonListener implements ActionListener {
	
	private User32 user32 = User32.INSTANCE;
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
	            @SuppressWarnings("deprecation")
				@Override
	            public void run() {
	            	System.out.println("Minimize..");
	            	
	            	myMainFrame.setUserWantsMinimize(true);
	            	myMainFrame.setFrameInvisible();
	        		
	        		if(!startedThread) {
	        			
	        			System.out.println("started thread!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	        			Timer timer = new Timer();
	        			timer.schedule(new TimerTask() {
	        				@Override
	        				public void run() {
	        					System.out.println("running...");
	        					System.out.println("My Frame title: " + minFrame.getTitle());
	        	                byte[] windowText = new byte[512];
	        	                PointerType hwnd = user32.GetForegroundWindow();
	        	                User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);
	        	                
	        	                String acticeWindowTitle = Native.toString(windowText);
	        	                System.out.println("From MinimizeButtonListener: " + minFrame.getTitle());
	        	                if(Native.toString(windowText).equals("Path of Exile")){
	        	                	if(!((IHideable) minFrame).isFrameVisible() && !((IHideable) minFrame).isUserWantsMinimize()) {
	        	                		minFrame.show();
	        	                		System.out.println("show window!");
	        	                	}
	        	                } else {
	        	                	
	        	                	boolean isActiveWindowMain = acticeWindowTitle.equals("MapTrado Main");
	        	                	boolean isActiveWindowMini = acticeWindowTitle.equals("MapTrado Mini");
	        	                	
	        	                	if(!isActiveWindowMain && !isActiveWindowMini) {
	        	                		((IHideable) minFrame).setFrameInvisible();
	        	                		minFrame.hide();
	        	                	}
	        	                	if(((IHideable) minFrame).isUserWantsMinimize()) {
	        	                		minFrame.hide();
	        	                		System.out.println("hide window!");
	        	                	}
	        	                }
	        				}
	        				
	        			},0,700);
	        		} else {
	        			minFrame.setFrameVisible();
	        			minFrame.setUserWantsMinimize(false);
	        			minFrame.show();
	        		}
	        		
	        		startedThread = true;
	        		
	        		
					}
			    });
			
			 }
		
		
		
}


