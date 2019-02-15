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


@SuppressWarnings("deprecation")
public class PoeTradeFetcher {

    final String POE_SEARCHLINK = "http://poe.trade/search";

    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public String sendGet(String url) throws Exception {

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

        StringBuilder result = new StringBuilder();
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

    // HTTP POST request
    public String sendPost(String url, List<NameValuePair> postData) throws Exception {

        @SuppressWarnings({"resource"})
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        post.setEntity(new UrlEncodedFormEntity(postData));

        System.out.println("Post data: " + postData);

        HttpResponse response = client.execute(post);
        //HttpEntity entity = new GzipDecompressingEntity(response.getEntity());
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        StringBuilder result = new StringBuilder();
        try (BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()))) {
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }


    private String getRequestLinkForSearchData(List<NameValuePair> searchData) {

        String response = "";
        try {
            response = this.sendPost(this.POE_SEARCHLINK, searchData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String requestLink = this.filterLinkFromResponse(response);

        return requestLink;

    }

    public String getAllMapsFromRequestAsHtml(List<NameValuePair> searchData) {
        String response = "";
        String requestLink = this.getRequestLinkForSearchData(searchData);
        try {
            response = this.sendGet(requestLink);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private String filterLinkFromResponse(String response) {
        String requestLinkFromResponse = "";

        Document doc = Jsoup.parse(response);
        System.out.println(doc.title());
        Elements body = doc.select("a");
        for (Element headline : body) {
            requestLinkFromResponse = headline.html();
        }

        System.out.println("requestLinkFromResponse: " + requestLinkFromResponse);
        return requestLinkFromResponse;
    }

}