package connector;

import config.Config;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;


public class ConfigurationConnector extends BaseConnector {

    private static final String CONF_ENDPOINT = "https://www.redwoodit.com.au/trademaster/api/configure";
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationConnector.class);

    private static ConfigurationConnector instance;

    public static void configure() {
        if (instance == null) {
            instance = new ConfigurationConnector();
        }

        JSONObject data = instance.retrieveData();
        Config.get().setBulkCurrencies((JSONObject) data.get("BulkCurrency"));
        Config.get().setPoeTradeCurrencies((JSONObject) data.get("AllCurrency"));
        Config.get().setLeagueSelection(data.get("DefaultLeagueSelection").toString());
        Config.get().setLeagues(mapJSONArrayToStringArray(data.getJSONArray("Leagues")));
        Config.get().setAllMaps(mapJSONArrayToStringArray(data.getJSONArray("AllMaps")));
        Config.get().setTieredMaps((JSONObject) data.get("TieredMaps"));
        Config.get().setShapedMaps(mapJSONArrayToStringArray(data.getJSONArray("ShapedMaps")));
        Config.get().setElderMaps(mapJSONArrayToStringArray(data.getJSONArray("ElderMaps")));
        Config.get().setBulkBuyWithCurrencies(mapJSONArrayToStringArray(data.getJSONArray("BulkBuyWithCurrencies")));
        LOG.debug(data.toString());

        // Clean up
        instance = null;
    }

    private static String[] mapJSONArrayToStringArray(JSONArray jsonArray) {
        String[] result = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            result[i] = (String) jsonArray.get(i);
        }
        return result;
    }

    private JSONObject retrieveData() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            LOG.debug("Sending 'GET' request to URL : " + CONF_ENDPOINT);
            HttpGet httpGet = new HttpGet(CONF_ENDPOINT);
            httpGet.addHeader("Accept", "application/json");
            HttpResponse response = client.execute(httpGet);
            LOG.debug("Response Code : " + response.getStatusLine().getStatusCode());
            String stringResult = convertStreamToString(response.getEntity().getContent());
            return new JSONObject(stringResult);
        } catch (Exception e) {
            throw new RuntimeException("Killing Application - Configuration failed to load content.", e);
        }
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }
}