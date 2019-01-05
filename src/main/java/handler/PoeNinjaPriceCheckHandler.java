package handler;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import PoeNinjaPricesJson.PoeNinjaPricesAsObject;
import items.PoeNinjaPriceItem;

public class PoeNinjaPriceCheckHandler {
	
	String pricesAsJson;
	public PoeNinjaPriceCheckHandler(String pricesAsJson) {
		this.pricesAsJson = pricesAsJson;
	}
	public List<PoeNinjaPriceItem> getPriceItemList() {
		List<PoeNinjaPriceItem> priceList = new ArrayList<PoeNinjaPriceItem>();
		
		Gson gson = new Gson();
		PoeNinjaPricesAsObject items = gson.fromJson(pricesAsJson, PoeNinjaPricesAsObject.class);
		
		for(int i = 0; i < items.getLines().length; i++) {
			PoeNinjaPriceItem item = new PoeNinjaPriceItem();
			item.setCurrencyTypeName(items.getLines()[i].getCurrencyTypeName());
			item.setChaosEquivalent(items.getLines()[i].getChaosEquivalent());
			
			for(int j = 0; j < items.getCurrencyDetails().length; j++) {
				if(items.getCurrencyDetails()[j].getName().equals(items.getLines()[i].getCurrencyTypeName())) {
					item.setCurrencyID(items.getCurrencyDetails()[j].getPoeTradeId());
				}
			}
			priceList.add(item);
		}
		
		/*
		for(int i = 0; i < priceList.size(); i++) {
			System.out.println(priceList.get(i).getCurrencyTypeName() + ": " + priceList.get(i).getChaosEquivalent() + " ID: " + priceList.get(i).getCurrencyID());
		}
		*/
		
		return priceList;
	}
	
	
	
	

}
