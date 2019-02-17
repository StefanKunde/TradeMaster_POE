package connector;

import config.Config;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PoeNinjaPriceFetcher extends BaseConnector {

    private static final Logger LOG = LoggerFactory.getLogger(PoeNinjaPriceFetcher.class);

	private static final String POE_SEARCHLINK = "https://poe.ninja/api/data/currencyoverview?league=%s&type=Currency&date=%s";

    public PoeNinjaPriceFetcher() {}

    // HTTP GET request
	public String sendGet() throws Exception {
		String url = generateSearchURL();
		HttpGet httpGet = new HttpGet(url);
		LOG.debug("GET REQUEST TO: " + url);

        HttpResponse response;
        String result;
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            response = client.execute(httpGet);
            result = convertStreamToString(response.getEntity().getContent());
        }

        return result;
	}
	
	private String generateSearchURL() {
        LocalDate ld = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format(POE_SEARCHLINK,  Config.getEncodedLeagueSelection(), ld.format(dtf));
	}

	@Override
	public Logger getLogger() {
        return LOG;
    }
}
