package connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CurrencyPoeTradeFetcher {

	final String POE_SEARCHLINK = "http://currency.poe.trade/search?league=Betrayal&online=x&stock=";

	private final String USER_AGENT = "Mozilla/5.0";
	
	
	// HTTP GET request
	public String sendGet(String wantedCurrencyID, String currencyToPayWithID) throws Exception {

		String url = generateSearchUrl(wantedCurrencyID, currencyToPayWithID);
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("accept", USER_AGENT);

		HttpResponse response = client.execute(request);
		HttpEntity entity = new GzipDecompressingEntity(response.getEntity());
		

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + 
                       response.getStatusLine().getStatusCode());
		
		
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(entity.getContent()));
		
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		EntityUtils.consume(entity);
		
		String resultString = result.toString();
		
		return resultString;
		

	}


	private String generateSearchUrl(String wantedCurrencyID, String currencyToPayWithID) {
		String url = "";
		
		url += POE_SEARCHLINK;
		url += "&want=" + wantedCurrencyID;
		url += "&have=" + currencyToPayWithID;
		
		System.out.println(url);
		return url;
	}

}
