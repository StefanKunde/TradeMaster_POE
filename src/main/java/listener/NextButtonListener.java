package listener;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import config.Config;
import gui.MainFrame;
import helper.RobotHelper;


public class NextButtonListener implements ActionListener {

	private MainFrame frame;
	
	public NextButtonListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame.getTradeableMaps().size() > 0) {
			//lbl_trade.setText(this.tradeableMaps.get(0).generateTradeText());
			String myString = frame.getTradeableMaps().get(0).generateTradeText();
			StringSelection stringSelection = new StringSelection(myString);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			frame.getTradeableMaps().remove(0);
			frame.getSingleMapsPanel().getLbl_count().setText("Tradeables left: " + frame.getTradeableMaps().size());
			frame.setForegroundWindow("Path of Exile");
			if(Config.isUseAutomatedTrading()) {
				RobotHelper.sendClipboardTextToChat();
			}
		} else {
			frame.getSingleMapsPanel().getLbl_count().setText("no tradeables left.");
		}
		
	}
	
}
