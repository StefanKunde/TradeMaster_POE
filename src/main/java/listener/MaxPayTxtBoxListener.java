package listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import gui.MainFrame;

public class MaxPayTxtBoxListener implements DocumentListener {

private MainFrame frame;
	
	public MaxPayTxtBoxListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		String userInput = "";
		try {
			userInput = e.getDocument().getText(0, e.getDocument().getLength());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(isNumeric(userInput) && Integer.valueOf(userInput) >= 1 && Integer.valueOf(userInput) <= 10000) { // between 1 and 10000
			frame.setValidMaxPayInput(true);
			
			if(frame.isValidAmountCurrencyInput()) {
				frame.getCurrencyBuyerPanel().getBtn_update_currency().setEnabled(true);
			}
			
			System.out.println("MaxPayTxtBoxListener true");
		} else {
			frame.setValidMaxPayInput(false);
			frame.getCurrencyBuyerPanel().getBtn_update_currency().setEnabled(false);
			System.out.println("MaxPayTxtBoxListener false");
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		
		
	}
	
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}

}
