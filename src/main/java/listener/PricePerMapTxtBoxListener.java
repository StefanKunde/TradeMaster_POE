package listener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import gui.MainFrame;
import io.sentry.Sentry;

public class PricePerMapTxtBoxListener implements DocumentListener {

	private MainFrame frame;
	
	public PricePerMapTxtBoxListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		String userInput = "";
		try {
			userInput = e.getDocument().getText(0, e.getDocument().getLength());
		} catch (BadLocationException e1) {
			Sentry.capture(e1);
		}
		
		if(isNumeric(userInput) && Integer.valueOf(userInput) >= 1 && Integer.valueOf(userInput) <= 1000) { // between 1 and 100
			frame.setValidPricePerMapInput(true);
			
			if(frame.isValidAmountInput()) {
				frame.getPanelBulkMaps().getUpdateButton().setEnabled(true);
			}
			
		} else {
			frame.setValidPricePerMapInput(false);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
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
