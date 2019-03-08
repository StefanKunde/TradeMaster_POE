package connector;

import app.Config;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PoeNinjaPriceFetcher extends BaseConnector {

    private static final Logger LOG = LoggerFactory.getLogger(PoeNinjaPriceFetcher.class);
    private static final String POE_SEARCHLINK = "https://poe.ninja/api/data/currencyoverview?league=%s&type=Currency&date=%s";

    public String sendGet() {
        String url = generateSearchURL();
        HttpGet httpGet = new HttpGet(url);
        String result = "";
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            LOG.debug("PoeNinjaPriceFetcher::sendGet() to URL: " + url);
            HttpResponse response = client.execute(httpGet);
            result = convertStreamToString(response.getEntity().getContent());
        } catch (IOException ioe) {
            LOG.error("PoeNinjaPriceFetcher::sendGet() to URL: " + url + ". Message: " + ioe.getMessage());
        }

        return result;
    }

    private String generateSearchURL() {
        LocalDate ld = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format(POE_SEARCHLINK, Config.get().getEncodedLeagueSelection(), ld.format(dtf));
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }
}
