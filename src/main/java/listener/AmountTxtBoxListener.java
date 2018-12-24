package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import gui.MainFrame;

public class AmountTxtBoxListener implements DocumentListener {

	private MainFrame frame;
	
	public AmountTxtBoxListener(MainFrame frame) {
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
		
		if(isNumeric(userInput) && Integer.valueOf(userInput) >= 1 && Integer.valueOf(userInput) <= 100) { // between 1 and 100
			frame.setValidAmountInput(true);
			frame.getBtn_update_bulks().setEnabled(true);
		} else {
			frame.setValidAmountInput(false);
			frame.getBtn_update_bulks().setEnabled(false);
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
