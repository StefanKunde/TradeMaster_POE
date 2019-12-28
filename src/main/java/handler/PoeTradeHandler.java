package handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import connector.PoeTradeFetcher;

public class PoeTradeHandler {

    private static final String CSS_QUERY = "[id^=item-container-]";
    private PoeTradeHandler() {}

    public static List<Element> fetchBuyableMapsAsHtml(List<NameValuePair> searchData) {
        String responseFromRequest = new PoeTradeFetcher().getAllMapsFromRequestAsHtml(searchData);
        List<Element> elements = new ArrayList<>();
        Document doc = Jsoup.parse(responseFromRequest);
        Elements items = doc.select(CSS_QUERY);
        for (Element item : items) {
            elements.add(item);
        }
        return elements;
    }

}
