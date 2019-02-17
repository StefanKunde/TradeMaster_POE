package com.stefank;


import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;

import com.sun.jna.Native;
import com.sun.jna.PointerType;
import gui.IHideable;
import utility.User32;


public class WindowManager implements Runnable {

    private User32 user32 = User32.INSTANCE;
    private JDialog myFrame;

    public WindowManager(JDialog frame) {
        this.myFrame = frame;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                byte[] windowText = new byte[512];
                PointerType hwnd = user32.GetForegroundWindow();
                User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);

                String acticeWindowTitle = Native.toString(windowText);

                if (Native.toString(windowText).equals("Path of Exile")) {
                    if (!((IHideable) myFrame).isFrameVisible() && !((IHideable) myFrame).isUserWantsMinimize()) {
                        myFrame.show();
                    }
                } else {

                    boolean isActiveWindowMain = acticeWindowTitle.equals("MapTrado Main");
                    boolean isActiveWindowMini = acticeWindowTitle.equals("MapTrado Mini");

                    if (!isActiveWindowMain && !isActiveWindowMini) {
                        ((IHideable) myFrame).setFrameInvisible();
                        myFrame.hide();
                    }
                    if (((IHideable) myFrame).isUserWantsMinimize()) {
                        myFrame.hide();
                    }
                }
            }

        }, 0, 700);
    }
}
