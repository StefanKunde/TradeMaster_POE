package items;

import app.Config;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CurrencyOffer {
    private Logger LOG = LoggerFactory.getLogger(CurrencyOffer.class);

    @Setter
    private String nickname = "";
    @Getter
    @Setter
    private double sellValue = 0;

    @Getter
    @Setter
    private String sellCurrency = "";
    @Getter
    @Setter
    private String buyCurrency = "";
    @Getter
    @Setter
    private double buyValue = 0;
    @Getter
    @Setter
    private int stock = 0;
    @Getter
    private double pricePerUnit = 0;
    private double amountPrice = 0;
    @Getter
    private String tradeMessage = "";

    public CurrencyOffer() {
    }

    public void calculatePriceByAmount(double wantedAmount) {
        this.amountPrice = wantedAmount * pricePerUnit;
    }

    public void calculatePricePerUnit() {
        if (sellValue > 0) {
            pricePerUnit = buyValue / sellValue;
        } else {
            LOG.info("ERROR PRICE calculating... Zero div...");
        }
    }

    public void generateTradeMessage(double wantedAmount) {
        double priceForAll = wantedAmount * pricePerUnit;

        if (buyValue == 1 && pricePerUnit < 1 && wantedAmount <= sellValue) {
            priceForAll = buyValue;
            wantedAmount = sellValue;
        }

        String priceTextForAll;

        if (priceForAll % 1 == 0) {
            priceTextForAll = String.valueOf((int) priceForAll);
        } else {
            DecimalFormat df = new DecimalFormat("####0.0");
            df.setRoundingMode(RoundingMode.CEILING);
            priceTextForAll = df.format(priceForAll);
        }


        String message = "";
        message += "@" + nickname + " ";
        message += "Hi, I'd like to buy your " + (int) wantedAmount + " ";
        message += sellCurrency + " ";
        message += "for my " + priceTextForAll + " ";
        message += buyCurrency + " in " + Config.get().getLeagueSelection() + ".";

        this.tradeMessage = message;
    }

}
