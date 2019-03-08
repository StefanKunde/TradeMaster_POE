package items;

import java.util.ArrayList;
import java.util.List;

public class CurrencyOffers {

    private List<CurrencyOffer> offers = new ArrayList<>();

    public void addOffer(CurrencyOffer offer) {
        offers.add(offer);
    }

    public List<CurrencyOffer> getAllOffersAsList() {
        return offers;
    }

    public void generateTradeMessages(int wantedAmount) {
        for (int i = 0; i < offers.size(); i++) {
            offers.get(i).generateTradeMessage(wantedAmount);
        }
    }
}
