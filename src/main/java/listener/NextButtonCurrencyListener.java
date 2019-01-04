package listener;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainFrame;
import items.CurrencyOffers;
import items.TradeableBulk;

public class NextButtonCurrencyListener implements ActionListener {

private MainFrame frame;
	
	public NextButtonCurrencyListener(MainFrame frame) {
		this.frame = frame;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame.getCurrencyOffers().getAllOffersAsList().size() > 0) {
			
			if(frame.getCurrencyOffers().getAllOffersAsList().size() == 1) {
				frame.getBtn_nextTrade_currencyTab().setEnabled(false);
			}
			
			String tradeMessage = frame.getCurrencyOffers().getAllOffersAsList().get(0).getTradeMessage();
			StringSelection stringSelection = new StringSelection(tradeMessage);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			
			// Delete first one
			CurrencyOffers tmpOffers = new CurrencyOffers();
			for(int i = 1; i < frame.getCurrencyOffers().getAllOffersAsList().size(); i++) {
				tmpOffers.addOffer(frame.getCurrencyOffers().getAllOffersAsList().get(i));
			}
			frame.setCurrencyOffers(tmpOffers);
			
			frame.getLbl_tradeables_currencyTab().setText("Tradeables: " + frame.getCurrencyOffers().getAllOffersAsList().size());
			frame.setForegroundWindow("Path of Exile");
		}
	}

}