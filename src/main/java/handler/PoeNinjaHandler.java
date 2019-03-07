package handler;

import com.google.gson.Gson;
import connector.PoeNinjaFetcher;
import items.TradeableBulk;
import jsonNinjaResult.Result;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class PoeNinjaHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PoeNinjaHandler.class);

    private final int MAX_REQUESTS = 4;
    private PoeNinjaFetcher poeConnector = new PoeNinjaFetcher();
    private PoeTradeBulkItemExchangeSearchData searchData;

    @Getter
    private TradeableBulk tradeables = new TradeableBulk();

    public PoeNinjaHandler(PoeTradeBulkItemExchangeSearchData searchData) {
        this.searchData = searchData;
    }

    private String generateJsonSearchString() {
        StringBuilder sb = new StringBuilder("{\"exchange\":{\"status\":{\"option\":\"online\"},");
        sb.append("\"have\":[\"").append(searchData.getHave()).append("\"],");
        sb.append("\"want\":[\"").append(searchData.getWant()).append("\"],");
        sb.append("\"minimum\":").append(searchData.getMinimum()).append("}}");
        return sb.toString();
    }

    public void processBulkRequests() {
        try {
            String responseFromPost = poeConnector.sendPost(generateJsonSearchString());
            poeConnector.storeResultsFromResponseAsList(responseFromPost);

            Gson gson = new Gson();
            int i = 0;
            while (poeConnector.getResultList().size() > 0 && i < MAX_REQUESTS) {
                String searchLink = poeConnector.generateSearchString(responseFromPost);
                String response = poeConnector.sendGet(searchLink);
                Result result = gson.fromJson(response, Result.class);
                tradeables.addResults(result.getResult());
                TimeUnit.MILLISECONDS.sleep(300);
                i++;
            }
        } catch (Exception e) {
            LOG.error("e", e);
        }

        this.tradeables.generateTradebleItems(searchData.getMinimum());
    }
}
