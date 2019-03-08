package connector;

import app.Config;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CurrencyPoeTradeFetcher extends BaseConnector {

    private static final Logger LOG = LoggerFactory.getLogger(CurrencyPoeTradeFetcher.class);

    private static final String POE_SEARCHLINK = "http://currency.poe.trade/search?league=%s&online=x&stock=%s&want=%s&have=%s";

    public String sendGet(String wantedStock, String wantedCurrencyID, String currencyToPayWithID) {

        String url = String.format(POE_SEARCHLINK, Config.get().getEncodedLeagueSelection(), wantedStock, wantedCurrencyID, currencyToPayWithID);

        HttpGet request = new HttpGet(url);
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("accept", USER_AGENT);

        String result = "";
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            LOG.debug("Sending 'GET' request to URL : " + url);
            HttpResponse response = client.execute(request);
            LOG.debug("Response Code : " + response.getStatusLine().getStatusCode());
            result = convertStreamToString(response.getEntity().getContent());
        } catch (IOException ioe) {
            LOG.error("CurrencyPoeTradeFetcher::sendGet to " + url + ", Returned IOException: " + ioe.getMessage());
        }

        return result;
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

}
