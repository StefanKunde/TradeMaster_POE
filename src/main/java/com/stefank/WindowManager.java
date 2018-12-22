package com.stefank;


import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import com.sun.jna.Native;
import com.sun.jna.PointerType;

import gui.MainFrame;
import gui.IHideable;
import utility.User32;



public class WindowManager implements Runnable {

	private User32 user32 = User32.INSTANCE;
	JFrame myFrame;
	
	public WindowManager(IHideable frame) {
		this.myFrame = (JFrame) frame;
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

	@Override
	public void run() {
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
                	if(!Native.toString(windowText).equals(myFrame.getTitle())) {
                		((IHideable) myFrame).setFrameInvisible();
                		myFrame.hide();
                		System.out.println("hide window!");
                	}
                	if(((IHideable) myFrame).isUserWantsMinimize()) {
                		myFrame.hide();
                		System.out.println("hide window!");
                	}
                }else{
                	if(!((IHideable) myFrame).isFrameVisible() && !((IHideable) myFrame).isUserWantsMinimize()) {
                		((IHideable) myFrame).setFrameVisible();
                		myFrame.show();
                		System.out.println("show window!");
                	}
                }
			}
			
		},0,200);
	}
}
