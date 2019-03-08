package items;

import lombok.Getter;
import org.jsoup.nodes.Element;

public class Map {

    private Element mapAsHtmlElement;
    @Getter
    private String ign;
    private String name;
    private String league;
    private String data_x;
    private String data_y;
    private String priceAmount;
    @Getter
    private String paymentCurrency;
    private String data_tab;
    private String data_map_tier;

    public Map(Element mapAsHtmlElement) {
        this.mapAsHtmlElement = mapAsHtmlElement;
        this.getDataFromHtmlMap();
    }

    private void getDataFromHtmlMap() {
        data_x = mapAsHtmlElement.attributes().get("data-x");
        data_y = mapAsHtmlElement.attributes().get("data-y");
        ign = mapAsHtmlElement.attributes().get("data-ign");
        name = mapAsHtmlElement.attributes().get("data-name");
        league = mapAsHtmlElement.attributes().get("data-league");
        data_tab = mapAsHtmlElement.attributes().get("data-tab");
        data_map_tier = mapAsHtmlElement.attributes().get("data-map-tier");

        String buyout = mapAsHtmlElement.attributes().get("data-buyout");
        String[] buyoutSplit = buyout.split(" ");
        priceAmount = buyoutSplit[0];
        paymentCurrency = buyoutSplit[1];

        data_x = String.valueOf(Integer.parseInt(data_x) + 1);
        data_y = String.valueOf(Integer.parseInt(data_y) + 1);
    }

    public String generateTradeText() {
        StringBuilder sb = new StringBuilder("@");
        sb.append(ign).append(" Hi, I'd like to buy your ");
        sb.append(name).append(" (T").append(data_map_tier).append(") listed for ");
        sb.append(priceAmount).append(" ").append(paymentCurrency).append(" in ");
        sb.append(league).append(" (stash tab \"").append(data_tab).append("\"; position: left ").append(data_x);
        sb.append(", top ").append(data_y).append(" )");
        return sb.toString();
    }

}
