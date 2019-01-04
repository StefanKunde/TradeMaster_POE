package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.stefank.Main;

import connector.CurrencyPoeTradeFetcher;
import gui.MainFrame;
import handler.CurrencyPoeTradeHandler;
import handler.JsonNinjaSearchData;
import handler.PoeNinjaHandler;

public class UpdateButtonCurrencyListener implements ActionListener {

	private MainFrame frame;
	
	public UpdateButtonCurrencyListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.getLbl_tradeables_bulks().setText("Loading...");
		frame.getBtn_update_bulks().setEnabled(false);
		
		// Get currency I want from list
		String wantedCurrency = frame.getCmb_currencyTab_want().getSelectedItem().toString();
		// Get currency I want to pay from list
		String currencyToPayWith = frame.getCmb_currencyTab_pay().getSelectedItem().toString();
		
		// Get amount I want 
		int wantedAmount = Integer.valueOf( frame.getTxt_currencyTab_neededAmount().getText() );
		// Get Max price i want to pay
		int maxPrice = Integer.valueOf( frame.getTxt_currencyTab_MAXpay().getText() );
		
		// Prepare request: Get ids from selected currencys
		String wantedCurrencyID = "";
		String currencyToPayWithID = "";
		
		String text = new Scanner(Main.class.getResourceAsStream("currencyPoeTrade.json")).useDelimiter("\\A").next();
		byte[] bytes;
		String mapsAsJsonString = "";
		try {
			bytes = text.getBytes("UTF-8");
			mapsAsJsonString = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException event) {
			// TODO Auto-generated catch block
			event.printStackTrace();
		}
        JSONObject json = new JSONObject(mapsAsJsonString);
        JSONObject currencys = (JSONObject) json.get("Currency");
        
		for( int i = 0; i < currencys.names().length(); i++) {
			if(currencys.names().get(i).equals(wantedCurrency)) {
				wantedCurrencyID = (String) currencys.opt(currencys.names().get(i).toString());
			}
			if(currencys.names().get(i).equals(currencyToPayWith)) {
				currencyToPayWithID = (String) currencys.opt(currencys.names().get(i).toString());
			}
		}
		
		// Send request to poe.currency.trade
		String response = "";
		CurrencyPoeTradeFetcher tradeFetcher = new CurrencyPoeTradeFetcher();
		try {
			response = tradeFetcher.sendGet(wantedCurrencyID, currencyToPayWithID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Load all offers from html response
		CurrencyPoeTradeHandler handler = new CurrencyPoeTradeHandler(response);
		
		// Filter out possible trades
		handler.filterTradesByUserInput(wantedAmount, maxPrice);
		handler.calculateFilteredPricesByAmount(wantedAmount);
		handler.getFilteredOffers().generateTradeMessages(wantedAmount);
		
		for(int i = 0; i < handler.getFilteredOffers().getAllOffersAsList().size(); i++) {
			System.out.println(handler.getFilteredOffers().getAllOffersAsList().get(i).getTradeMessage());
		}
		
		// Add tradeables to Nextbutton
		frame.setCurrencyOffers(handler.getFilteredOffers());
		
		// display tradeables amount
		frame.getLbl_tradeables_currencyTab().setText("Tradeables: " + frame.getCurrencyOffers().getAllOffersAsList().size());
		
		if(handler.getFilteredOffers().getAllOffersAsList().size() > 0) {
			frame.getBtn_nextTrade_currencyTab().setEnabled(true);
		} else {
			frame.getBtn_nextTrade_currencyTab().setEnabled(false);
		}
	}

}
