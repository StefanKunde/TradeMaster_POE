package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import gui.MainFrame;

public class CurrencyBulksCmbListener implements ActionListener {
	
	private MainFrame frame;
	
	public CurrencyBulksCmbListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedCurrency = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();
		if(!selectedCurrency.equals("ANY")) {
			frame.getPanelBulkMaps().getTxtbox_pricePerMap().setEnabled(true);
		} else {
			frame.getPanelBulkMaps().getTxtbox_pricePerMap().setText("");
			frame.getPanelBulkMaps().getTxtbox_pricePerMap().setEnabled(false);
		}
		
		if(frame.isValidAmountInput() && frame.isValidPricePerMapInput()) {
			frame.getPanelBulkMaps().getUpdateButton().setEnabled(true);
		}
	}

}
