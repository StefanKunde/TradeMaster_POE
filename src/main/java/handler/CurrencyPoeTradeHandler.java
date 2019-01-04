package handler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import items.CurrencyOffer;
import items.CurrencyOffers;

public class CurrencyPoeTradeHandler {
	
	private String html;
	private CurrencyOffers offers;
	private CurrencyOffers filteredOffers;
	
	public CurrencyPoeTradeHandler(String html) {
		this.html = html;
		getOffersFromHtml();
	}

	private void getOffersFromHtml() {
		offers = new CurrencyOffers();
		
		Document doc = Jsoup.parse(html);
		Elements items = doc.select("div.displayoffer");
		for (Element item : items) {
			addOfferFromElement(item);
		}
	}

	private void addOfferFromElement(Element item) {
		CurrencyOffer offer = new CurrencyOffer();
		
		String buyvalue = item.attributes().get("data-buyvalue");
		String sellvalue = item.attributes().get("data-sellvalue");
		String nickname = item.attributes().get("data-ign");
		String stock = "";
		String buyCurrency = item.getElementsByClass("currency-name").get(1).text();
		String sellCurrency = item.getElementsByClass("currency-name").get(0).text();
		
		
		if(item.attributes().hasKey("data-stock")) {
			stock = item.attributes().get("data-stock");
			offer.setStock( (int)Double.parseDouble(stock) );
		}
		offer.setBuyValue( (int)Double.parseDouble(buyvalue) );
		offer.setSellValue( (int)Double.parseDouble(sellvalue) );
		offer.setNickname(nickname);
		offer.setBuyCurrency(buyCurrency);
		offer.setSellCurrency(sellCurrency);
		offer.calculatePricePerUnit();
		offers.addOffer(offer);
		
		// TODO: Filter out duplicated users
	}
	
	public void filterTradesByUserInput(int wantedAmount, int maxPrice) {
		filteredOffers = new CurrencyOffers();
		
		// Calculate matching prices
		double pricePerUnitUserWantsToPayMAX = (double)maxPrice / (double)wantedAmount;
		
		for( int i = 0; i < offers.getAllOffersAsList().size(); i++) {
			CurrencyOffer offer = offers.getAllOffersAsList().get(i);
			
			if((double)offer.getPricePerUnit() <= (double)pricePerUnitUserWantsToPayMAX) {
				if((double)offer.getSellValue() >= (double)wantedAmount || (double)offer.getStock() >= (double)wantedAmount) {
					if(offer.getBuyValue() >= 1) {
						filteredOffers.addOffer(offer);
					}
				}
			}
		}
	}

	public CurrencyOffers getOffers() {
		return offers;
	}

	public void setOffers(CurrencyOffers offers) {
		this.offers = offers;
	}

	public void calculateFilteredPricesByAmount(int wantedAmount) {
		for(int i = 0; i < this.filteredOffers.getAllOffersAsList().size(); i++) {
			this.filteredOffers.getAllOffersAsList().get(i).calculatePriceByAmount(wantedAmount);
		}
	}

	public CurrencyOffers getFilteredOffers() {
		return filteredOffers;
	}

	public void setFilteredOffers(CurrencyOffers filteredOffers) {
		this.filteredOffers = filteredOffers;
	}
}
