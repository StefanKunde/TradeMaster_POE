package items;

import lombok.Getter;
import org.jsoup.nodes.Element;

public class PoeTradeResultModel {

    @Getter
    private String ign;
    @Getter
    private String name;
    private String league;
    private String data_x;
    private String data_y;
    @Getter
    private String priceAmount;
    @Getter
    private String paymentCurrency;
    private String data_tab;
    private String data_map_tier;

    public PoeTradeResultModel(Element htmlElement) {
        mapDataFromElementToObject(htmlElement);
    }

    private void mapDataFromElementToObject(Element htmlElement) {
        data_x = htmlElement.attributes().get("data-x");
        data_y = htmlElement.attributes().get("data-y");
        ign = htmlElement.attributes().get("data-ign");
        name = htmlElement.attributes().get("data-name");
        league = htmlElement.attributes().get("data-league");
        data_tab = htmlElement.attributes().get("data-tab");
        data_map_tier = htmlElement.attributes().get("data-map-tier");

        String buyout = htmlElement.attributes().get("data-buyout");
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


    public String generateItemText() {
        StringBuilder sb = new StringBuilder("@");
        sb.append(ign).append(" Hi, I would like to buy your ").append(name);
        sb.append(" listed for ").append(priceAmount).append(" ").append(paymentCurrency);
        sb.append(" in ").append(league).append(" (stash tab \"").append(data_tab).append("\"; position: left ").append(data_x);
        sb.append(", top ").append(data_y).append(")");
        return sb.toString();
    }

    public String generateVoiceText() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" is available in ").append(league).append(" listing price ").append(priceAmount).append(" ").append(paymentCurrency);
        return sb.toString();
    }
}
