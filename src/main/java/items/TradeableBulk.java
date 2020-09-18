package items;

import java.util.ArrayList;
import java.util.List;

import jsonNinjaResult.Listing;
import jsonNinjaResult.Result_;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class TradeableBulk {

    @Getter
    @Setter
    private List<Result_> results = new ArrayList<>();
    @Getter
    private List<TradeableItem> tradeableItems = new ArrayList<>();
    @Getter
    private List<TradeableItem> filteredTradeableItems = tradeableItems;


    public void addResults(List<Result_> resultList) {
        if (resultList != null && !resultList.isEmpty()) {
            for (Result_ result : resultList) {
                this.results.add(result);
            }
        }
    }

    public void generateTradebleItems(int bulkAmount) {
        for (Result_ result : this.results) {
            TradeableItem item = new TradeableItem();
            Listing listing = result.getListing();

            // Calculate price per map
            double exchangeAmount = listing.getPrice().getExchange().getAmount();
            int itemAmount = listing.getPrice().getItem().getAmount();

            String currencyToPay = listing.getPrice().getExchange().getCurrency();
            int mapStock = listing.getPrice().getItem().getStock();

            double pricePerMap = exchangeAmount / itemAmount;

            String userName = listing.getAccount().getLastCharacterName();
            String itemToSell = result.getItem().getTypeLine();
            String mapTier = result.getItem().getProperties().get(0).getValues().get(0).get(0);

            item.setCurrencyToPay(currencyToPay);
            item.setMapStock(mapStock);
            item.setPricePerMap(pricePerMap);
            item.setUsername(userName);
            item.setItemToSell(itemToSell);
            item.setMapTier(mapTier);

            if (!(mapStock < bulkAmount)) {
                tradeableItems.add(item);
            }
        }

        this.filteredTradeableItems = tradeableItems;
    }

    public void filterByCurrencyAndMaxPrice(String currency, int maxPrice) {
        List<TradeableItem> tmpTradeables = new ArrayList<>();

        for (TradeableItem item : tradeableItems) {
            if (item.getCurrencyToPay().equalsIgnoreCase(currency) && item.getPricePerMap() <= maxPrice) {
                tmpTradeables.add(item);
                log.debug("Added item to filtered tradables: currency =  " + item.getCurrencyToPay() + " pricePerMap: " + item.getPricePerMap());
            }
        }
        this.filteredTradeableItems = tmpTradeables;
    }
}
