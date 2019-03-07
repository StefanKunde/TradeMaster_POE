package listener.bulkMaps;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import config.Config;
import gui.MainFrame;
import helper.RobotHelper;
import items.TradeableBulk;

public class NextButtonBulksListener implements ActionListener {

	private MainFrame frame;
	
	public NextButtonBulksListener(MainFrame frame) {
		this.frame = frame;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame.getTradeables().getFilteredTradeableItems().size() > 0) {
			
			if(frame.getTradeables().getFilteredTradeableItems().size() == 1) {
				frame.getPanelBulkMaps().getBtn_nextTrade_bulks().setEnabled(false);
			}
			
			String bulkAmount = frame.getPanelBulkMaps().getTxt_amount_bulks().getText();
			String tradeMessage = frame.getTradeables().getFilteredTradeableItems().get(0).generateTradeMessage(Integer.valueOf(bulkAmount));
			StringSelection stringSelection = new StringSelection(tradeMessage);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			
			TradeableBulk tmpTradeables = new TradeableBulk();
			if(frame.getTradeables().getFilteredTradeableItems().size() >= 1) {
				for(int i = 1; i < frame.getTradeables().getFilteredTradeableItems().size(); i++) {
					tmpTradeables.getTradeableItems().add(frame.getTradeables().getFilteredTradeableItems().get(i));
				}
				frame.setTradeables(tmpTradeables);
				frame.getPanelBulkMaps().getTradeables().setText("Tradeables: " + tmpTradeables.getTradeableItems().size());
			}
			
			frame.setForegroundWindow("Path of Exile");
			if(Config.get().isUseAutomatedTrading()) {
				RobotHelper.sendClipboardTextToChat();
			}
			
		} else {
			frame.getPanelBulkMaps().getTradeables().setText("Tradeables: " + frame.getTradeables().getFilteredTradeableItems().size());
			frame.getPanelBulkMaps().getBtn_nextTrade_bulks().setEnabled(false);
		}
		
		if(frame.getTradeables().getFilteredTradeableItems().size() == 0) {
			frame.getPanelBulkMaps().getUpdateButton().setEnabled(true);
		}
		
	}

}
