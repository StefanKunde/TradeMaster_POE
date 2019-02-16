package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import javax.swing.JComboBox;

import io.sentry.Sentry;
import org.json.JSONObject;

import com.stefank.Main;

import gui.MainFrame;

public class CurrencyTabCmbBoxWantListener implements ActionListener {

private MainFrame frame;
	
	public CurrencyTabCmbBoxWantListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedWantItem = (String) ((JComboBox) e.getSource()).getSelectedItem();
		String selectedPayItem = (String) frame.getCurrencyBuyerPanel().getCmb_currencyTab_pay().getSelectedItem();
		
		// Get currency ID from selected Item
		String text = new Scanner(Main.class.getResourceAsStream("currencyPoeTrade.json")).useDelimiter("\\A").next();
		byte[] bytes;
		String mapsAsJsonString = "";
		try {
			bytes = text.getBytes("UTF-8");
			mapsAsJsonString = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException event) {
			Sentry.capture(event);
		}
        JSONObject json = new JSONObject(mapsAsJsonString);
        JSONObject currencys = (JSONObject) json.get("Currency");
        
        String selectedPayItemCurrencyID = "";
        String selectedWantItemCurrencyID = "";
		for( int i = 0; i < currencys.names().length(); i++) {
			if(currencys.names().get(i).equals(selectedPayItem)) {
				selectedPayItemCurrencyID = (String) currencys.opt(currencys.names().get(i).toString());
			}
			if(currencys.names().get(i).equals(selectedWantItem)) {
				selectedWantItemCurrencyID = (String) currencys.opt(currencys.names().get(i).toString());
			}
		}
		
		boolean amountIsEmpty = frame.getCurrencyBuyerPanel().getTxt_currencyTab_neededAmount().getText().equals("");
		
		if(selectedPayItemCurrencyID.equals(selectedWantItemCurrencyID)) {
			frame.getCurrencyBuyerPanel().getLbl_priceCheck().setText("No prices for matching currencys...");
		} else {
			if(selectedPayItemCurrencyID.equals("4") || selectedWantItemCurrencyID.equals("4")) {
				
				double pricePerUnit = 0;
				String currencyName = "";
				boolean userWantsToBuyChaos = selectedWantItemCurrencyID.equals("4");
				
				if(userWantsToBuyChaos) {
					for(int i = 0; i < frame.getPoeNinjaPrices().getPriceItemList().size(); i++) {
						if(frame.getPoeNinjaPrices().getPriceItemList().get(i).getCurrencyID().equals(selectedPayItemCurrencyID)) {
							pricePerUnit = frame.getPoeNinjaPrices().getPriceItemList().get(i).getChaosEquivalent();
							currencyName = frame.getPoeNinjaPrices().getPriceItemList().get(i).getCurrencyTypeName();
						}
					}
				} else {
					for(int i = 0; i < frame.getPoeNinjaPrices().getPriceItemList().size(); i++) {
						if(frame.getPoeNinjaPrices().getPriceItemList().get(i).getCurrencyID().equals(selectedWantItemCurrencyID)) {
							pricePerUnit = frame.getPoeNinjaPrices().getPriceItemList().get(i).getChaosEquivalent();
							currencyName = frame.getPoeNinjaPrices().getPriceItemList().get(i).getCurrencyTypeName();
						}
					}
				}
				
				// Price with 1 unit
				if(amountIsEmpty) {
					if(userWantsToBuyChaos) {
						frame.getCurrencyBuyerPanel().getLbl_priceCheck().setText("1x Chaos Orb" + " = " + (1 / pricePerUnit) + " " + currencyName);
					} else {
						frame.getCurrencyBuyerPanel().getLbl_priceCheck().setText("1x " + currencyName + " = " + pricePerUnit + " Chaos Orb");
					}
					
				} else {
					// Price with amount
					double userAmount = Double.valueOf( frame.getCurrencyBuyerPanel().getTxt_currencyTab_neededAmount().getText() );
					double price = 1 / pricePerUnit * userAmount;
					String priceAsStringRounded = String.format("%.2f", price);
					
					if(userWantsToBuyChaos) {
						frame.getCurrencyBuyerPanel().getLbl_priceCheck().setText(userAmount +  " Chaos Orb" + " = " + priceAsStringRounded + " " + currencyName);
					} else {
						price = pricePerUnit * userAmount;
						priceAsStringRounded = String.format("%.2f", price);
						frame.getCurrencyBuyerPanel().getLbl_priceCheck().setText(userAmount + " " + currencyName + " = " + priceAsStringRounded  + " Chaos Orb");
					}
				}
				
				// Prices with selected bulkamount
				
				
			} else {
				frame.getCurrencyBuyerPanel().getLbl_priceCheck().setText("Only chaos prices are supported.");
			}
		}
	}

}
