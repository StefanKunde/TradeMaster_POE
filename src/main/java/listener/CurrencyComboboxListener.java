package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import gui.MainFrame;

public class CurrencyComboboxListener implements ActionListener {
	private MainFrame frame;
	
	public CurrencyComboboxListener(MainFrame frame) {
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(((JComboBox) e.getSource()).getSelectedItem());
		String selectedCurrency = (String) ((JComboBox) e.getSource()).getSelectedItem();
		if(selectedCurrency.equals("ANY")) {
			selectedCurrency = "";
		}
		frame.setCurrency(selectedCurrency);
		frame.getSearchBuilder().setCurrency(selectedCurrency);
		
	}

}
