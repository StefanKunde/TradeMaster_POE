package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainFrame;
import gui.MinimizedFrame;

public class MaximizeButtonListener implements ActionListener {

    private MinimizedFrame minimizeFrame;
    private MainFrame mainframe;

    public MaximizeButtonListener(MinimizedFrame minimizeFrame, MainFrame mainFrame) {
        this.minimizeFrame = minimizeFrame;
        this.mainframe = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        minimizeFrame.setUserWantsMinimize(false);
        minimizeFrame.setFrameInvisible();

        mainframe.setUserWantsMinimize(false);
        mainframe.setFrameVisible();
    }

}
