package com.stefank;


import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import com.sun.jna.Native;
import com.sun.jna.PointerType;
import com.sun.jna.platform.linux.LibC.Sysinfo;

import gui.MainFrame;
import gui.IHideable;
import utility.User32;



public class WindowManager implements Runnable {

	private User32 user32 = User32.INSTANCE;
	JFrame myFrame;
	
	public WindowManager(IHideable frame) {
		this.myFrame = (JFrame) frame;
	}

	@Override
	public void run() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("running...");
				System.out.println("My Frame title: " + myFrame.getTitle());
                byte[] windowText = new byte[512];
                PointerType hwnd = user32.GetForegroundWindow();
                User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);
                
                String acticeWindowTitle = Native.toString(windowText);
                
                if(Native.toString(windowText).equals("Path of Exile")){
                	if(!((IHideable) myFrame).isFrameVisible() && !((IHideable) myFrame).isUserWantsMinimize()) {
                		myFrame.show();
                		System.out.println("show window!");
                	}
                } else {
                	
                	boolean isActiveWindowMain = acticeWindowTitle.equals("MapTrado Main");
                	boolean isActiveWindowMini = acticeWindowTitle.equals("MapTrado Mini");
                	
                	if(!isActiveWindowMain && !isActiveWindowMini) {
                		((IHideable) myFrame).setFrameInvisible();
                		myFrame.hide();
                	}
                	if(((IHideable) myFrame).isUserWantsMinimize()) {
                		myFrame.hide();
                		System.out.println("hide window!");
                	}
                }
			}
			
		},0,1000);
	}
}
