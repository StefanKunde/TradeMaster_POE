package connector;

import config.Config;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyPoeTradeFetcher extends BaseConnector {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyPoeTradeFetcher.class);

    private static final String POE_SEARCHLINK = "http://currency.poe.trade/search?league=%s&online=x&stock=%s&want=%s&have=%s";

    // HTTP GET request
    public String sendGet(String wantedStock, String wantedCurrencyID, String currencyToPayWithID) throws Exception {
        String url = generateSearchUrl(wantedStock, wantedCurrencyID, currencyToPayWithID);

        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("accept", USER_AGENT);

        HttpResponse response;
        String result;
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            LOG.debug("Sending 'GET' request to URL : " + url);
            response = client.execute(request);
            LOG.debug("Response Code : " + response.getStatusLine().getStatusCode());
            result = convertHttpEntityContentToString(response.getEntity());
        }

        return result;
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

    private String generateSearchUrl(String wantedStock, String wantedCurrencyID, String currencyToPayWithID) {
        return String.format(POE_SEARCHLINK, Config.getEncodedLeagueSelection(), wantedStock, wantedCurrencyID, currencyToPayWithID);
    }
}
