package items;

import connector.PoeNinjaPriceFetcher;
import handler.PoeNinjaPriceCheckHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PoeNinjaPrices {

    private static final Logger LOG = LoggerFactory.getLogger(PoeNinjaPrices.class);

    private List<PoeNinjaPriceItem> priceItemList;

    public PoeNinjaPrices() {
        priceItemList = new ArrayList<PoeNinjaPriceItem>();
    }

    public List<PoeNinjaPriceItem> getPriceItemList() {
        return priceItemList;
    }

    public void getPrices() {
        PoeNinjaPriceFetcher fetcher = new PoeNinjaPriceFetcher();
        String pricesAsJsonText = "";
        try {
            pricesAsJsonText = fetcher.sendGet();
        } catch (Exception e) {
            LOG.error("PoeNinjaPrices::getPrices()", e);
        }

        PoeNinjaPriceCheckHandler handler = new PoeNinjaPriceCheckHandler(pricesAsJsonText);
        priceItemList = handler.getPriceItemList();
    }
}
