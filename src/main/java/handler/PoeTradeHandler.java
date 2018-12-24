package handler;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import connector.PoeTradeFetcher;
import items.Map;

public class PoeTradeHandler {
	
	PoeTradeFetcher poeConnector;
	List<NameValuePair> searchData;
	Map map;
	
	public PoeTradeHandler(List<NameValuePair> searchData) {
		this.searchData = searchData;
		this.poeConnector = new PoeTradeFetcher();
	}
	
	public List<Element> fetchBuyableMapsAsHtml() {
		String responseFromRequest = poeConnector.getAllMapsFromRequestAsHtml(this.searchData);
		
		List<Element> elements = new ArrayList<Element>();
		Document doc = Jsoup.parse(responseFromRequest);
		Elements items = doc.select("[id^=item-container-]");
		for (Element item : items) {
			elements.add(item);
		}
		
		return elements;
	}

}
