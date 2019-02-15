package connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import config.Config;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class CurrencyPoeTradeFetcher {

    private static final String POE_SEARCHLINK = "http://currency.poe.trade/search?league=%s&online=x&stock=%s&want=%s&have=%s";

    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public String sendGet(String wantedStock, String wantedCurrencyID, String currencyToPayWithID) throws Exception {

        String url = generateSearchUrl(wantedStock, wantedCurrencyID, currencyToPayWithID);

        @SuppressWarnings({"resource"})
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("accept", USER_AGENT);

        HttpResponse response = client.execute(request);
        HttpEntity entity = new GzipDecompressingEntity(response.getEntity());

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        StringBuffer result = new StringBuffer();
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()))) {
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } finally {
            EntityUtils.consume(entity);
        }

        return result.toString();
    }

    private String generateSearchUrl(String wantedStock, String wantedCurrencyID, String currencyToPayWithID) {
        return String.format(POE_SEARCHLINK, Config.getEncodedLeagueSelection(), wantedStock, wantedCurrencyID, currencyToPayWithID);
    }

}
