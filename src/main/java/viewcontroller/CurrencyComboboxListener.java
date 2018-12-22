package viewcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import gui.Frame;

public class CurrencyComboboxListener implements ActionListener {
	private Frame frame;
	
	public CurrencyComboboxListener(Frame frame) {
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
