package items;

import app.Config;
import lombok.Getter;
import lombok.Setter;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TradeableItem {

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private double pricePerMap;
    @Setter
    private int mapStock;
    @Getter
    @Setter
    private String currencyToPay;
    @Getter
    @Setter
    private String itemToSell;
    @Getter
    @Setter
    private String mapTier;

    public String generateTradeMessage(int amount) {
        DecimalFormat df = new DecimalFormat("####.#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String priceForBulk = df.format(amount * pricePerMap);
        if (priceForBulk.contains(".0")) {
            priceForBulk = priceForBulk.replace(".0", "");
        }

        StringBuilder sb = new StringBuilder("@");
        sb.append(getUsername()).append(" ");
        sb.append("Hi, I'd like to buy your ").append(amount).append(" ");
        sb.append(getItemToSell()).append(" ");
        sb.append("(T").append(getMapTier()).append(") for my ").append(priceForBulk).append(" ");
        sb.append(getCurrencyToPay()).append(" orb in ").append(Config.get().getLeagueSelection());
        return sb.toString();
    }


}

