package helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotHelper {

    private static final Logger LOG = LoggerFactory.getLogger(RobotHelper.class);

    public static void sendClipboardTextToChat() {
        try {
            Robot robot = new Robot();
            try {
                Thread.sleep(50);
                robot.keyPress(KeyEvent.VK_ENTER);
                Thread.sleep(32);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(9);
                robot.keyPress(KeyEvent.VK_CONTROL);
                Thread.sleep(6);
                robot.keyPress(KeyEvent.VK_A);
                Thread.sleep(7);
                robot.keyRelease(KeyEvent.VK_A);
                Thread.sleep(3);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                Thread.sleep(4);
                robot.keyPress(KeyEvent.VK_CONTROL);
                Thread.sleep(9);
                robot.keyPress(KeyEvent.VK_V);
                Thread.sleep(10);
                robot.keyRelease(KeyEvent.VK_V);
                Thread.sleep(36);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                Thread.sleep(2);
                Thread.sleep(100);
                robot.keyPress(KeyEvent.VK_ENTER);
                Thread.sleep(32);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } catch (InterruptedException e) {
                LOG.error("InterruptedException::RobotHelper", e);
            }
        } catch (AWTException e) {
            LOG.error("AWTException::RobotHelper", e);
        }
    }

}
