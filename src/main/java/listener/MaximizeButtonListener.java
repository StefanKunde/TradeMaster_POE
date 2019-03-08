package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Main;
import gui.MainFrame;
import gui.MinimizedFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaximizeButtonListener implements ActionListener {
    private static final Logger LOG = LoggerFactory.getLogger(MaximizeButtonListener.class);

    private MinimizedFrame minimizeFrame;
    private MainFrame mainframe;

    public MaximizeButtonListener(MinimizedFrame minimizeFrame, MainFrame mainFrame) {
        this.minimizeFrame = minimizeFrame;
        this.mainframe = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LOG.debug("MaximizeButtonListener::actionPerformed(Setting Minimized to False)");
        Main.setMinimised(false);
        minimizeFrame.setVisible(false);
        mainframe.setVisible(true);
    }

}
