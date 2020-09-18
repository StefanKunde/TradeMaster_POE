package connector;

import app.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class PoeNinjaPriceFetcher extends BaseConnector {

    private static final String POE_SEARCHLINK = "https://poe.ninja/api/data/currencyoverview?league=%s&type=Currency&date=%s";

    public String sendGet() {
        String url = generateSearchURL();
        HttpGet httpGet = new HttpGet(url);
        String result = "";
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            log.debug("PoeNinjaPriceFetcher::sendGet() to URL: " + url);
            HttpResponse response = client.execute(httpGet);
            result = convertStreamToString(response.getEntity().getContent());
        } catch (IOException ioe) {
            log.error("PoeNinjaPriceFetcher::sendGet() to URL: " + url + ". Message: " + ioe.getMessage());
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
        return log;
    }
}
