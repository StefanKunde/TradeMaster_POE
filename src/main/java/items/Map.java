package items;

import org.jsoup.nodes.Element;

public class Map {
	
	private Element mapAsHtmlElement;
	String ign;
	String name;
	String league;
	String data_x;
	String data_y;
	String priceAmount;
	String paymentCurrency;
	String data_tab;
	String data_map_tier;
	
	public Map(Element mapAsHtmlElement) {
		this.mapAsHtmlElement = mapAsHtmlElement;
		this.getDataFromHtmlMap();
	}
	
	private void getDataFromHtmlMap() {
		this.setData_x(mapAsHtmlElement.attributes().get("data-x"));
		this.setData_y(mapAsHtmlElement.attributes().get("data-y"));
		this.setIgn(mapAsHtmlElement.attributes().get("data-ign"));
		this.setName(mapAsHtmlElement.attributes().get("data-name"));
		this.setLeague(mapAsHtmlElement.attributes().get("data-league"));
		this.setData_tab(mapAsHtmlElement.attributes().get("data-tab"));
		this.setData_map_tier(mapAsHtmlElement.attributes().get("data-map-tier"));
		this.setPriceAndCurrencyFromDataBuyout(mapAsHtmlElement.attributes().get("data-buyout").toString());
		
		int datax = Integer.parseInt(this.data_x) + 1;
		int datay = Integer.parseInt(this.data_y) + 1;
		this.setData_x( String.valueOf(datax) );
		this.setData_y( String.valueOf(datay) );
		
	}
	
	private void setPriceAndCurrencyFromDataBuyout(String buyout) {
		String[] buyoutSplit = buyout.split(" ");
		this.setPriceAmount(buyoutSplit[0]);
		this.setPaymentCurrency(buyoutSplit[1]);
	}
	
	public String generateTradeText() {
		String tradeText = "@" + this.ign + " Hey buddy, I'd like to buy your " 
							+ this.name + " (T" + this.data_map_tier + ") listed for "
							+ this.priceAmount + " " + this.paymentCurrency + " in " + this.league
							+ " (stash tab \"" + this.data_tab + "\"; position: left " + this.data_x + ", top " + this.data_y + ")";

		return tradeText;
	}

	public String getIgn() {
		return ign;
	}


	public void setIgn(String ign) {
		this.ign = ign;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLeague() {
		return league;
	}


	public void setLeague(String league) {
		this.league = league;
	}


	public String getData_x() {
		return data_x;
	}


	public void setData_x(String data_x) {
		this.data_x = data_x;
	}


	public String getData_y() {
		return data_y;
	}


	public void setData_y(String data_y) {
		this.data_y = data_y;
	}


	public String getPriceAmount() {
		return priceAmount;
	}


	public void setPriceAmount(String priceAmount) {
		this.priceAmount = priceAmount;
	}


	public String getPaymentCurrency() {
		return paymentCurrency;
	}


	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}


	public String getData_tab() {
		return data_tab;
	}


	public void setData_tab(String data_tab) {
		this.data_tab = data_tab;
	}

	public String getData_map_tier() {
		return data_map_tier;
	}

	public void setData_map_tier(String data_map_tier) {
		this.data_map_tier = data_map_tier;
	}
	
	
}
