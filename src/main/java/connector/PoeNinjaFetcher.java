package connector;

import com.google.gson.Gson;
import config.Config;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PoeNinjaFetcher extends BaseConnector {

    private static final Logger LOG = LoggerFactory.getLogger(PoeNinjaFetcher.class);

    final String POE_SEARCHLINK = "https://www.pathofexile.com/api/trade/exchange/%s";
    final String POE_SEARCHLINK_FOR_RESULT = "https://www.pathofexile.com/api/trade/fetch/";
    private String idFromPostRequest = "";
    private List<String> resultList;

    public PoeNinjaFetcher() {
        resultList = new ArrayList<String>();
    }

    // HTTP GET request
    public String sendGet(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response;
        String result;
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            response = client.execute(httpGet);
            result = convertStreamToString(response.getEntity().getContent());
        }
        return result;
    }

    // HTTP POST request
    public String sendPost(String jsonData) throws Exception {
        HttpPost post = new HttpPost(String.format(POE_SEARCHLINK, Config.getEncodedLeagueSelection()));

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

        LOG.debug("Post data: " + jsonData);

        HttpResponse response;
        String result;
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            LOG.debug("Sending 'POST' request to URL : " + String.format(POE_SEARCHLINK, Config.getEncodedLeagueSelection()));
            LOG.debug("Post Entity : " + post.getEntity());
            response = client.execute(post);
            LOG.debug("Response Code : " + response.getStatusLine().getStatusCode());
            result = convertStreamToString(response.getEntity().getContent());
        }

        LOG.debug(result);
        return result;

    }

    @Override
    public Logger getLogger() {
        return this.LOG;
    }

    public String generateSearchString(String jsonResponse) {
        String link = POE_SEARCHLINK_FOR_RESULT;

        Gson gson = new Gson();
        NinjaJsonObject responseObject = gson.fromJson(jsonResponse, NinjaJsonObject.class);

        int max_length = 3;

        List<String> tmpList = new ArrayList<String>();
        for (int i = 0; i < this.resultList.size(); i++) {
            if (i < max_length) {
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
        LOG.debug("Link: " + link);

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

        for (String result : responseObject.getResult()) {
            this.resultList.add(result);
        }
    }
}
