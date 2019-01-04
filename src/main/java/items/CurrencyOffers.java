package items;

import java.util.ArrayList;
import java.util.List;

public class CurrencyOffers {
	
	private List<CurrencyOffer> offers;

	public CurrencyOffers() {
		offers = new ArrayList<CurrencyOffer>();
	}
	
	public void addOffer(CurrencyOffer offer) {
		this.offers.add(offer);
	}
	
	public List<CurrencyOffer> getAllOffersAsList() {
		return this.offers;
	}

	public void generateTradeMessages(int wantedAmount) {
		for(int i = 0; i < offers.size(); i++) {
			offers.get(i).generateTradeMessage(wantedAmount);
		}
		
	}
}
