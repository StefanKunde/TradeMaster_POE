package items;

import java.util.ArrayList;
import java.util.List;

import connector.CurrencyPoeTradeFetcher;
import connector.PoeNinjaPriceFetcher;
import handler.CurrencyPoeTradeHandler;
import handler.PoeNinjaPriceCheckHandler;

public class PoeNinjaPrices {
	
	List<PoeNinjaPriceItem> priceItemList;
	
	public PoeNinjaPrices() {
		priceItemList = new ArrayList<PoeNinjaPriceItem>();
	}
	
	public void addPriceItem(PoeNinjaPriceItem item) {
		priceItemList.add(item);
	}

	public List<PoeNinjaPriceItem> getPriceItemList() {
		return priceItemList;
	}

	public void setPriceItemList(List<PoeNinjaPriceItem> priceItemList) {
		this.priceItemList = priceItemList;
	}

	public void getPrices() {
		PoeNinjaPriceFetcher fetcher = new PoeNinjaPriceFetcher();
		String pricesAsJsonText = "";
		try {
			pricesAsJsonText = fetcher.sendGet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PoeNinjaPriceCheckHandler handler = new PoeNinjaPriceCheckHandler(pricesAsJsonText);
		priceItemList = handler.getPriceItemList();
		
		
	}
}
