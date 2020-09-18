package connector;

import app.Config;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import model.NinjaJsonModel;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PoeNinjaFetcher extends BaseConnector {

    final String POE_SEARCHLINK = "https://www.pathofexile.com/api/trade/exchange/%s";
    final String POE_SEARCHLINK_FOR_RESULT = "https://www.pathofexile.com/api/trade/fetch/";

    @Getter
    @Setter
    private List<String> resultList = new ArrayList<>();

    public String sendGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        String result = "";
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpResponse response = client.execute(httpGet);
            result = convertStreamToString(response.getEntity().getContent());
        } catch (IOException ioe) {
            log.error("PoeNinjaFetcher::sendGet to " + url + ", Returned IOException: " + ioe.getMessage());
        }
        return result;
    }

    public String sendPost(String jsonData) {
        String postUrl = String.format(POE_SEARCHLINK, Config.get().getEncodedLeagueSelection());
        HttpPost post = new HttpPost(postUrl);

        // add headers
        post.addHeader("User-Agent", USER_AGENT);
        post.addHeader("accept", "*/*");
        post.addHeader("Host", "www.pathofexile.com");
        post.addHeader("Connection", "keep-alive");
        post.addHeader("Origin", "https://www.pathofexile.com");
        post.addHeader("X-Requested-With", "XMLHttpRequest");
        post.addHeader("Content-Type", "application/json");

        StringEntity params = new StringEntity(jsonData, Config.get().ENCODING_TYPE);
        post.setEntity(params);

        log.debug("Post data: " + jsonData);
        String result = "";
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            log.debug("Sending 'POST' request to URL : " + String.format(POE_SEARCHLINK, Config.get().getEncodedLeagueSelection()));
            log.debug("Post Entity : " + post.getEntity());
            HttpResponse response = client.execute(post);
            log.debug("Response Code : " + response.getStatusLine().getStatusCode());
            result = convertStreamToString(response.getEntity().getContent());
        } catch (IOException ioe) {
            log.error("PoeNinjaFetcher::sendPost to " + postUrl + ", Returned IOException: " + ioe.getMessage());
        }

        log.debug(result);
        return result;

    }

    @Override
    public Logger getLogger() {
        return this.log;
    }

    public String generateSearchString(String jsonResponse) {
        String link = POE_SEARCHLINK_FOR_RESULT;
        NinjaJsonModel responseObject = GSON.fromJson(jsonResponse, NinjaJsonModel.class);

        int max_length = 3;
        List<String> tmpList = new ArrayList<>();
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
        log.debug("Link: " + link);
        return link;
    }

    public void storeResultsFromResponseAsList(String responseFromPost) {
        NinjaJsonModel responseObject = GSON.fromJson(responseFromPost, NinjaJsonModel.class);
        for (String result : responseObject.getResult()) {
            this.resultList.add(result);
        }
    }
}
