package helper;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

@Slf4j
public class RobotHelper {

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
                log.error("InterruptedException::RobotHelper", e);
            }
        } catch (AWTException e) {
            log.error("AWTException::RobotHelper", e);
        }
    }

}
