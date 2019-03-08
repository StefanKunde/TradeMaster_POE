package items;

import connector.PoeNinjaPriceFetcher;
import handler.PoeNinjaPriceCheckHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PoeNinjaPrices {

    private static final Logger LOG = LoggerFactory.getLogger(PoeNinjaPrices.class);

    private List<PoeNinjaPriceItem> priceItemList = new ArrayList<>();

    public PoeNinjaPrices() {
        loadPrices();
    }

    public List<PoeNinjaPriceItem> getPriceItemList() {
        return priceItemList;
    }

    private void loadPrices() {
        PoeNinjaPriceFetcher fetcher = new PoeNinjaPriceFetcher();
        String pricesAsJsonText = fetcher.sendGet();

        PoeNinjaPriceCheckHandler handler = new PoeNinjaPriceCheckHandler(pricesAsJsonText);
        priceItemList = handler.getPriceItemList();
    }
}
