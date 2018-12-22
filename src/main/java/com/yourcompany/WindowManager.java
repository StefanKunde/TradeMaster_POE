package com.yourcompany;


import java.util.Timer;
import java.util.TimerTask;

import com.sun.jna.Native;
import com.sun.jna.PointerType;



public class WindowManager {

	private User32 user32 = User32.INSTANCE;
	Frame myFrame = new Frame();
	
	public WindowManager() {
	    Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
            @Override
            public void run() {
	            	System.out.println("running...");
	                byte[] windowText = new byte[512];
	                PointerType hwnd = user32.GetForegroundWindow();
	                User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);
	                System.out.println(Native.toString(windowText));
	                
	                if(!Native.toString(windowText).equals("Path of Exile")){
	                	if(!Native.toString(windowText).equals("JFrame Example")) {
	                		myFrame.setInvisible();
	                		System.out.println("hide window!");
	                	}
	                }else{
	                	if(!myFrame.isVisible()) {
	                		myFrame.setVisible();
	                	}
	                	
	                	System.out.println("show window!");
	                	
	                }
                }
        },0,700);
	}
	
	public void setForegroundWindow(final String titleName){
        user32.EnumWindows((hWnd, arg1) -> {
            byte[] windowText = new byte[512];
            user32.GetWindowTextA(hWnd, windowText, 512);
            String wText = Native.toString(windowText);

            if (wText.isEmpty()) {
                return true;
            }
            if (wText.equals(titleName)) {
                user32.SetForegroundWindow(hWnd);
                return false;
            }
            return true;
        }, null);
	}
}
