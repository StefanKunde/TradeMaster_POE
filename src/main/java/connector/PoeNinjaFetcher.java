package connector;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

@SuppressWarnings("deprecation")
public class PoeNinjaFetcher {
		
	final String POE_SEARCHLINK = "https://www.pathofexile.com/api/trade/exchange/Betrayal";
	final String POE_SEARCHLINK_FOR_RESULT = "https://www.pathofexile.com/api/trade/fetch/";
	private final String USER_AGENT = "Mozilla/5.0";
	private String idFromPostRequest = "";
	private List<String> resultList;
	
	
	public PoeNinjaFetcher() {
		resultList = new ArrayList<String>();
	}
	
	// HTTP GET request
	public String sendGet(String url) throws Exception {
		String resultAsString = "";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		resultAsString = convertStreamToString(response.getEntity().getContent());
	
		return resultAsString;
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

	// HTTP POST request
	public String sendPost(String jsonData) throws Exception {

		@SuppressWarnings({ "resource" })
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(POE_SEARCHLINK);

		// add header
		post.addHeader("User-Agent", USER_AGENT);
		post.addHeader("accept", "*/*");
		
		post.addHeader("Host", "www.pathofexile.com");
		post.addHeader("Connection", "keep-alive");
		post.addHeader("Origin", "https://www.pathofexile.com");
		post.addHeader("X-Requested-With", "XMLHttpRequest");
		post.addHeader("Content-Type", "application/json");
		
		// String demoData = "{\"exchange\":{\"status\":{\"option\":\"online\"},\"have\":[],\"want\":[\"elder-underground-sea-map\"]}}";
		StringEntity params = new StringEntity(jsonData);

		post.setEntity(params);
		
	
		System.out.println("Post data: " + jsonData);

		HttpResponse response = client.execute(post);
		//HttpEntity entity = new GzipDecompressingEntity(response.getEntity());
		System.out.println("\nSending 'POST' request to URL : " + POE_SEARCHLINK);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + 
                                    response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		
		String resultText = result.toString();
		
		System.out.println(resultText);

		return resultText;

	}
	
	public String generateSearchString(String jsonResponse) {
		String link = POE_SEARCHLINK_FOR_RESULT;
		
		Gson gson = new Gson();
		NinjaJsonObject responseObject = gson.fromJson(jsonResponse, NinjaJsonObject.class);
		
		int max_length = 3;
		
		List<String> tmpList = new ArrayList<String>();
		for(int i = 0; i < this.resultList.size(); i++) {
			if(i < max_length) {
				link += resultList.get(i) + ",";
			} else {
				tmpList.add(resultList.get(i));
			}
		}
		this.setResultList(tmpList);
		
		// delete last comma
		link = link.substring(0, link.length() - 1);
		
		link += "?query=" + responseObject.getId() + "&exchange";
		
		this.idFromPostRequest = responseObject.getId();
		System.out.println("Link: " + link);
		
		return link;
	}



	public String getIdFromPostRequest() {
		return this.idFromPostRequest;
	}
	
	public List<String> getResultList() {
		return resultList;
	}



	public void setResultList(List<String> resultList) {
		this.resultList = resultList;
	}



	public void storeResultsFromResponseAsList(String responseFromPost) {
		Gson gson = new Gson();
		NinjaJsonObject responseObject = gson.fromJson(responseFromPost, NinjaJsonObject.class);
		
		for(String result : responseObject.getResult()) {
			this.resultList.add(result);
		}
	}
}
