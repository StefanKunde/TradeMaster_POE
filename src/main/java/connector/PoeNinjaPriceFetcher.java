package connector;

import config.Config;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PoeNinjaPriceFetcher {
	
	public PoeNinjaPriceFetcher() {}
	
	private static final String POE_SEARCHLINK = "https://poe.ninja/api/data/currencyoverview?league=%s&type=Currency&date=%s";

	// HTTP GET request
	public String sendGet() throws Exception {
		String url = generateSearchURL();
        CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		System.out.println("GET REQUEST TO: " + url);

		CloseableHttpResponse response = httpClient.execute(httpGet);
        String resultAsString = convertStreamToString(response.getEntity().getContent());
		return resultAsString;
	}
	
	private String generateSearchURL() {
        LocalDate ld = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return String.format(POE_SEARCHLINK,  Config.getEncodedLeagueSelection(), ld.format(dtf));
	}

	public static String convertStreamToString(InputStream is) throws Exception {
        StringBuilder sb = new StringBuilder();
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }

	    return sb.toString();
	}
}
