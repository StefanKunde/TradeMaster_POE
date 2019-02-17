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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utility.User32;

public class MinimizeButtonListener implements ActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(MinimizeButtonListener.class);

    private User32 user32 = User32.INSTANCE;
    private MainFrame myMainFrame;
    private MinimizedFrame minFrame;
    private boolean startedThread = false;

    public MinimizeButtonListener(MainFrame frame) {
        this.myMainFrame = frame;
        this.minFrame = new MinimizedFrame(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SwingUtilities.invokeLater(() -> {

            LOG.debug("Minimize..");

            myMainFrame.setUserWantsMinimize(true);
            myMainFrame.setFrameInvisible();

            if (!startedThread) {
                LOG.debug("### Minimized Frame Thread Started ####");
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        byte[] windowText = new byte[512];
                        PointerType hwnd = user32.GetForegroundWindow();
                        User32.INSTANCE.GetWindowTextA(hwnd, windowText, 512);

                        String acticeWindowTitle = Native.toString(windowText);

                        if (Native.toString(windowText).equals("Path of Exile")) {
                            if (!minFrame.isUserWantsMinimize() && !minFrame.isFrameVisible()) {
                                minFrame.setVisible(true);
                            }
                        } else {

                            boolean isActiveWindowMain = acticeWindowTitle.equals("MapTrado Main");
                            boolean isActiveWindowMini = acticeWindowTitle.equals("MapTrado Mini");

                            if (!isActiveWindowMain && !isActiveWindowMini) {
                                minFrame.setFrameInvisible();
                                minFrame.setVisible(false);
                            } else {
                                if (minFrame.isUserWantsMinimize()) {
                                    minFrame.setVisible(true);
                                }
                            }

                        }
                    }

                }, 0, 700);
            } else {
                minFrame.setFrameVisible();
                minFrame.setUserWantsMinimize(false);
                minFrame.setVisible(true);
            }

            startedThread = true;
        });
    }
}


