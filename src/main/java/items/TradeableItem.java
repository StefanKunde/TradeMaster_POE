package items;

public class TradeableItem {
	
	private String username;
	private int mapStock;
	private int pricePerMap;
	private String currencyToPay;
	private String itemToSell;
	private String mapTier;
	
	// Maybe for later:
	private String iconUrl;
	
	
	public TradeableItem() {
		
	}
	
	
	public String generateTradeMessage(int amount) {
		String message = "";
		int priceForBulk = amount * getPricePerMap();
		
		message += "@" + getUsername() + " ";
		message += "Hi, I'd like to buy your " + amount + " ";
		message += getItemToSell() + " ";
		message += "(T" + getMapTier()+ ") for my ";
		message += priceForBulk + " ";
		message += getCurrencyToPay() + " orb in Betryal";
		
		return message;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMapStock() {
		return mapStock;
	}

	public void setMapStock(int mapStock) {
		this.mapStock = mapStock;
	}

	public int getPricePerMap() {
		return pricePerMap;
	}

	public void setPricePerMap(int pricePerMap) {
		this.pricePerMap = pricePerMap;
	}

	public String getCurrencyToPay() {
		return currencyToPay;
	}

	public void setCurrencyToPay(String currencyToPay) {
		this.currencyToPay = currencyToPay;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getItemToSell() {
		return itemToSell;
	}

	public void setItemToSell(String itemToSell) {
		this.itemToSell = itemToSell;
	}

	public String getMapTier() {
		return mapTier;
	}

	public void setMapTier(String mapTier) {
		this.mapTier = mapTier;
	}
}

