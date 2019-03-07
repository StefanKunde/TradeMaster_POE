package app;


import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;

import com.sun.jna.Native;
import com.sun.jna.PointerType;
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

            @Override
            public void run() {
                byte[] windowText = new byte[512];
                PointerType hwnd = user32.GetForegroundWindow();
                User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);

                String acticeWindowTitle = Native.toString(windowText);

                if (!myFrame.isVisible()) {
                    myFrame.setVisible(true);
                }
//                if (Native.toString(windowText).equals("Path of Exile")) {
//                    if (!((IHideable) myFrame).isFrameVisible() && !((IHideable) myFrame).isUserWantsMinimize()) {
//                        myFrame.setVisible(true);
//                    }
//                } else {
//                    boolean isActiveWindowMain = acticeWindowTitle.equals("MapTrado Main");
//                    boolean isActiveWindowMini = acticeWindowTitle.equals("MapTrado Mini");
//
//                    if (!isActiveWindowMain && !isActiveWindowMini && myFrame.isVisible()) {
//                        ((IHideable) myFrame).setFrameInvisible();
//                        myFrame.setVisible(false);
//                    }
//                    if (((IHideable) myFrame).isUserWantsMinimize() && ((IHideable) myFrame).isFrameVisible()) {
//                        myFrame.setVisible(false);
//                    }
//                }
            }

        }, 0, 700);
    }
}
