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
		String selectedCurrency = (String) ((JComboBox) e.getSource()).getSelectedItem();
		if(!selectedCurrency.equals("ANY")) {
			frame.getTxtbox_pricePerMap().setEnabled(true);
		} else {
			frame.getTxtbox_pricePerMap().setText("");
			frame.getTxtbox_pricePerMap().setEnabled(false);
		}
	}

}
