package listener;

import app.Main;
import gui.MainFrame;
import gui.MinimizedFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinimizeButtonListener implements ActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(MinimizeButtonListener.class);

    private MainFrame mainFrame;
    private MinimizedFrame minimizedFrame;

    public MinimizeButtonListener(MainFrame mainFrame, MinimizedFrame minimizeFrame) {
        this.mainFrame = mainFrame;
        this.minimizedFrame = minimizeFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LOG.debug("MinimizeButtonListener::actionPerformed(Setting Minimized to True)");
        Main.setMinimised(true);
        minimizedFrame.setVisible(true);
        mainFrame.setVisible(false);
    }
}


