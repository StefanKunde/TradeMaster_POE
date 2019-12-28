package connector;

import app.Config;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;


public class PoeTradeFetcher extends BaseConnector {

    private static final Logger LOG = LoggerFactory.getLogger(PoeTradeFetcher.class);
    private static final String POE_SEARCHLINK = "https://poe.trade/search";

    public String sendGet(String url) {
        HttpGet request = new HttpGet(url);

        // add request headers
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("accept", USER_AGENT);

        String result = "";
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            LOG.debug("Sending 'GET' request to URL : " + url);
            HttpResponse response = client.execute(request);
            LOG.debug("Response Code: " + response.getStatusLine().getStatusCode());
            result = convertStreamToString(response.getEntity().getContent());
        } catch (IOException ioe) {
            LOG.error("PoeTradeFetcher::sendPost to " + url + ", Returned IOException: " + ioe.getMessage());
        }

        return result;
    }

    public String sendPost(String url, List<NameValuePair> postData) {
        HttpPost post = new HttpPost(url);

        // add headers
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        post.setEntity(new UrlEncodedFormEntity(postData, Charset.forName(Config.get().ENCODING_TYPE)));

        LOG.debug("Post data: " + postData);

        String result = "";
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            LOG.debug("Sending 'POST' request to URL : " + url);
            LOG.debug("Post parameters : " + post.getEntity());
            HttpResponse response = client.execute(post);
            LOG.debug("Response Code : " + response.getStatusLine().getStatusCode());
            result = convertStreamToString(response.getEntity().getContent());
        } catch (IOException ioe) {
            LOG.error("PoeTradeFetcher::sendPost to " + url + ", Returned IOException: " + ioe.getMessage());
        }

        return result;
    }


    private String getRequestLinkForSearchData(List<NameValuePair> searchData) {
        String response = sendPost(POE_SEARCHLINK, searchData);
        return filterLinkFromResponse(response);
    }

    public String getAllMapsFromRequestAsHtml(List<NameValuePair> searchData) {
        String requestLink = getRequestLinkForSearchData(searchData);
        String response = sendGet(requestLink);
        return response;
    }

    private String filterLinkFromResponse(String response) {
        String requestLinkFromResponse = "";
        Document doc = Jsoup.parse(response);
        Elements body = doc.select("a");
        for (Element headline : body) {
            requestLinkFromResponse = headline.html();
        }
        return requestLinkFromResponse;
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }
}