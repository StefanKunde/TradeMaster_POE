package listener.currency;

import app.Config;
import gui.MainFrame;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;

import java.awt.event.ActionListener;

public abstract class CurrencyBaseListener implements ActionListener {

    protected MainFrame frame;

    protected String selectedPayItemCurrencyID = "";
    protected String selectedWantItemCurrencyID = "";

    public CurrencyBaseListener(MainFrame frame) {
        this.frame = frame;
    }

    protected void runCommonChecks(String selectedPayItem, String selectedWantItem) {
        setSelectionAndPayCurrencies(selectedPayItem, selectedWantItem);

        frame.getCurrencyBuyerPanel().setLabelPriceCheckText("Only Chaos Supported.");
        // 4 = Chaos
        if ("4".equalsIgnoreCase(selectedPayItemCurrencyID) || "4".equalsIgnoreCase(selectedWantItemCurrencyID)) {
            boolean amountIsEmpty = frame.getCurrencyBuyerPanel().getTxtCurrencyTabNeededAmount().getText().isEmpty();
            double pricePerUnit = 0;
            String currencyName = "";

            for (int i = 0; i < frame.getPoeNinjaPrices().getPriceItemList().size(); i++) {
                if (frame.getPoeNinjaPrices().getPriceItemList().get(i).getCurrencyID().equals(selectedWantItemCurrencyID)) {
                    pricePerUnit = frame.getPoeNinjaPrices().getPriceItemList().get(i).getChaosEquivalent();
                    currencyName = frame.getPoeNinjaPrices().getPriceItemList().get(i).getCurrencyTypeName();
                }
            }

            // Price with 1 unit
            if (amountIsEmpty) {
                frame.getCurrencyBuyerPanel().setLabelPriceCheckText("1x " + currencyName + " = " + pricePerUnit + " Chaos Orb");
            } else {
                // Price with amount
                double userAmount = Double.valueOf(frame.getCurrencyBuyerPanel().getTxtCurrencyTabNeededAmount().getText());
                double price = pricePerUnit * userAmount;
                String priceAsStringRounded = String.format("%.2f", price);
                String lineAmt = userAmount + " " + currencyName + " = " + priceAsStringRounded + " Chaos Orb (" + (price / userAmount) + ") PPU";
                frame.getCurrencyBuyerPanel().setLabelPriceCheckText(lineAmt);
            }
        }
    }

    protected void setSelectionAndPayCurrencies(String selectedPayItem, String selectedWantItem) {
        JSONArray names = Config.get().getPoeTradeCurrencies().names();
        for (int i = 0; i < names.length(); i++) {
            if (names.get(i).equals(selectedPayItem)) {
                selectedPayItemCurrencyID = (String) Config.get().getPoeTradeCurrencies().opt(names.get(i).toString());
            }
            if (names.get(i).equals(selectedWantItem)) {
                selectedWantItemCurrencyID = (String) Config.get().getPoeTradeCurrencies().opt(names.get(i).toString());
            }
        }
    }

    public abstract Logger getLogger();
}
