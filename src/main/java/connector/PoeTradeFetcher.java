package connector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.GzipDecompressingEntity;
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

import java.util.List;


public class PoeTradeFetcher extends BaseConnector {

    private static final Logger LOG = LoggerFactory.getLogger(PoeTradeFetcher.class);

    final String POE_SEARCHLINK = "http://poe.trade/search";

    // HTTP GET request
    public String sendGet(String url) throws Exception {
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("accept", USER_AGENT);

        HttpResponse response;
        String result;
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            LOG.debug("Sending 'GET' request to URL : " + url);
            response = client.execute(request);
            LOG.debug("Response Code: " + response.getStatusLine().getStatusCode());
            result = convertStreamToString(response.getEntity().getContent());
        }

        return result;
    }

    // HTTP POST request
    public String sendPost(String url, List<NameValuePair> postData) throws Exception {
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");

        post.setEntity(new UrlEncodedFormEntity(postData));

        LOG.debug("Post data: " + postData);

        HttpResponse response;
        String result;
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            LOG.debug("Sending 'POST' request to URL : " + url);
            LOG.debug("Post parameters : " + post.getEntity());
            response = client.execute(post);
            LOG.debug("Response Code : " + response.getStatusLine().getStatusCode());
            result = convertStreamToString(response.getEntity().getContent());
        }

        return result;
    }


    private String getRequestLinkForSearchData(List<NameValuePair> searchData) {
        String response = "";
        try {
            response = this.sendPost(this.POE_SEARCHLINK, searchData);
        } catch (Exception e) {
            LOG.error("PoeTradeFetcher::getRequestLinkForSearchData", e);
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
            LOG.error("PoeTradeFetcher::getAllMapsFromRequestAsHtml", e);
        }

        return response;
    }

    private String filterLinkFromResponse(String response) {
        String requestLinkFromResponse = "";
        Document doc = Jsoup.parse(response);
        LOG.debug("##filterLinkFromResponse - " + doc.title());
        Elements body = doc.select("a");
        for (Element headline : body) {
            requestLinkFromResponse = headline.html();
        }
        LOG.debug("##requestLinkFromResponse - " + doc.title());
        return requestLinkFromResponse;
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }
}