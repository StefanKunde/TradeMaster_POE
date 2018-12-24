package items;

import java.util.ArrayList;
import java.util.List;

import jsonNinjaResult.Listing;
import jsonNinjaResult.Result_;

public class TradeableBulk {
	
	private List<Result_> results;
	private List<TradeableItem> tradeableItems;
	private List<TradeableItem> filteredTradeableItems;
	
	public TradeableBulk() {
		results = new ArrayList<Result_>();
		this.tradeableItems = new ArrayList<TradeableItem>();
		filteredTradeableItems = this.tradeableItems;
	}

	public List<Result_> getResults() {
		return results;
	}

	public void setResults(List<Result_> results) {
		this.results = results;
	}
	
	public void addResult(Result_ result) {
		this.results.add(result);
	}
	
	public void addResults(List<Result_> resultList) {
		for(Result_ result : resultList) {
			this.results.add(result);
		}
	}

	public void generateTradebleItems(int bulkAmount) {
		for(Result_ result : this.results) {
			TradeableItem item = new TradeableItem();
			Listing listing = result.getListing();
			
			// Calculate price per map
			double exchangeAmount = listing.getPrice().getExchange().getAmount();
			int itemAmount = listing.getPrice().getItem().getAmount();
			
			String currencyToPay = listing.getPrice().getExchange().getCurrency();
			int mapStock = listing.getPrice().getItem().getStock();
			double pricePerMap = exchangeAmount / itemAmount;
			String userName = listing.getAccount().getLastCharacterName();
			String iconPath = result.getItem().getIcon();
			String itemToSell = result.getItem().getTypeLine();
			String mapTier = result.getItem().getProperties().get(0).getValues().get(0).get(0);
			
			item.setCurrencyToPay(currencyToPay);
			item.setMapStock(mapStock);
			item.setPricePerMap((int)pricePerMap);
			item.setUsername(userName);
			item.setIconUrl(iconPath);
			item.setItemToSell(itemToSell);
			item.setMapTier(mapTier);
			
			if(!(mapStock < bulkAmount) ) {
				tradeableItems.add(item);
			}
		}
		
		this.filteredTradeableItems = tradeableItems;
	}

	public List<TradeableItem> getTradeableItems() {
		return tradeableItems;
	}

	public void setTradeableItems(List<TradeableItem> tradeableItems) {
		this.tradeableItems = tradeableItems;
	}

	public List<TradeableItem> getFilteredTradeableItems() {
		return filteredTradeableItems;
	}

	public void setFilteredTradeableItems(List<TradeableItem> filteredTradeableItems) {
		this.filteredTradeableItems = filteredTradeableItems;
	}
	
	public void filterByCurrencyAndMaxPrice(String currency, int maxPrice) {
		List<TradeableItem> tmpTradeables = new ArrayList<TradeableItem>();
		
		for(TradeableItem item : this.tradeableItems) {
			if(item.getCurrencyToPay().equals(currency) && item.getPricePerMap() <= maxPrice) {
				tmpTradeables.add(item);
			}
		}
		
		this.filteredTradeableItems = tmpTradeables;
	}
}
