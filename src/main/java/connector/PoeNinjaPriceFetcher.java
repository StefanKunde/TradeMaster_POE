package connector;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;

public class PoeNinjaPriceFetcher {
	
	public PoeNinjaPriceFetcher() {}
	
	final String POE_SEARCHLINK = "https://poe.ninja/api/data/currencyoverview?league=Betrayal&type=Currency&date=";
	private final String USER_AGENT = "Mozilla/5.0";
	
	// HTTP GET request
	public String sendGet() throws Exception {
		String resultAsString = "";
		String url = generateSearchURL();

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		System.out.println("GET REQUEST TO: " + url);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		resultAsString = convertStreamToString(response.getEntity().getContent());
	
		return resultAsString;
	}
	
	private String generateSearchURL() {
		String url = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateToday = dateFormat.format(date);
		url += POE_SEARCHLINK;
		url += dateToday;
		
		return url;
	}

	public static String convertStreamToString(InputStream is) throws Exception {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	      sb.append(line + "\n");
	    }
	    is.close();
	    return sb.toString();
	}
}
