package helper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotHelper {
	
	public static void sendClipboardTextToChat() {
		Robot robot = null;
		try {
			robot = new Robot();
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
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
