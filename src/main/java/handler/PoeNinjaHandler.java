package handler;

import connector.PoeNinjaFetcher;
import items.TradeableBulk;
import jsonNinjaResult.Result;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import model.PoeTradeBulkItemExchangeSearchDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@Slf4j
public class PoeNinjaHandler {

    private final int MAX_REQUESTS = 5;
    private PoeNinjaFetcher poeConnector = new PoeNinjaFetcher();
    private PoeTradeBulkItemExchangeSearchDataModel searchData;

    @Getter
    private TradeableBulk tradeables = new TradeableBulk();

    public PoeNinjaHandler(PoeTradeBulkItemExchangeSearchDataModel searchData) {
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
        String responseFromPost = poeConnector.sendPost(generateJsonSearchString());
        poeConnector.storeResultsFromResponseAsList(responseFromPost);

        if (!poeConnector.getResultList().isEmpty()) {
            for (int i = 0; i < MAX_REQUESTS; i++) {
                String searchLink = poeConnector.generateSearchString(responseFromPost);
                String response = poeConnector.sendGet(searchLink);
                Result result = poeConnector.GSON.fromJson(response, Result.class);
                tradeables.addResults(result.getResult());
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException ie) {
                    log.error("PoeNinjaHandler::processBulkRequests, sleep attempt had an interruption occur - " + ie.getMessage());
                }
            }
        }
        tradeables.generateTradebleItems(searchData.getMinimum());
    }
}
