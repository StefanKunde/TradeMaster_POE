package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import config.Config;
import gui.MainFrame;

public class AutomateTradesChkBoxListener implements ActionListener {

	private MainFrame frame;
	
	public AutomateTradesChkBoxListener(MainFrame frame) {
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if( ((JCheckBox) e.getSource()).isSelected()) {
			Config.get().setUseAutomatedTrading(true);
			frame.getSingleMapsPanel().getChckbxAutomateTrading().setSelected(true);
			frame.getCurrencyBuyerPanel().getChckbxAutomateTrading().setSelected(true);
			frame.getPanelBulkMaps().getChckbxAutomateTrading().setSelected(true);
		} else {
			Config.get().setUseAutomatedTrading(false);
			frame.getSingleMapsPanel().getChckbxAutomateTrading().setSelected(false);
			frame.getCurrencyBuyerPanel().getChckbxAutomateTrading().setSelected(false);
			frame.getPanelBulkMaps().getChckbxAutomateTrading().setSelected(false);
		}
	}

}
