package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import io.sentry.Sentry;
import org.json.JSONObject;

import com.stefank.Main;

import connector.CurrencyPoeTradeFetcher;
import gui.MainFrame;
import handler.CurrencyPoeTradeHandler;

public class UpdateButtonCurrencyListener implements ActionListener {

    private MainFrame frame;

    public UpdateButtonCurrencyListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getPanelBulkMaps().getTradeables().setText("Loading...");
        frame.getPanelBulkMaps().getUpdateButton().setEnabled(false);

        // Get currency I want from list
        String wantedCurrency = frame.getCurrencyBuyerPanel().getCmb_currencyTab_want().getSelectedItem().toString();
        // Get currency I want to pay from list
        String currencyToPayWith = frame.getCurrencyBuyerPanel().getCmb_currencyTab_pay().getSelectedItem().toString();

        // Get amount I want
        String wantedAmountString = frame.getCurrencyBuyerPanel().getTxt_currencyTab_neededAmount().getText();
        int wantedAmount = Integer.valueOf(wantedAmountString);
        // Get Max price i want to pay
        int maxPrice = Integer.valueOf(frame.getCurrencyBuyerPanel().getTxt_currencyTab_MAXpay().getText());

        // Prepare request: Get ids from selected currencys
        String wantedCurrencyID = "";
        String currencyToPayWithID = "";

        @SuppressWarnings("resource")
        String text = new Scanner(Main.class.getResourceAsStream("currencyPoeTrade.json")).useDelimiter("\\A").next();
        byte[] bytes;
        String mapsAsJsonString = "";
        try {
            bytes = text.getBytes("UTF-8");
            mapsAsJsonString = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException event) {
            Sentry.capture(event);
        }
        JSONObject json = new JSONObject(mapsAsJsonString);
        JSONObject currencys = (JSONObject) json.get("Currency");

        for (int i = 0; i < currencys.names().length(); i++) {
            if (currencys.names().get(i).equals(wantedCurrency)) {
                wantedCurrencyID = (String) currencys.opt(currencys.names().get(i).toString());
            }
            if (currencys.names().get(i).equals(currencyToPayWith)) {
                currencyToPayWithID = (String) currencys.opt(currencys.names().get(i).toString());
            }
        }

        // Send request to poe.currency.trade
        String response = "";
        CurrencyPoeTradeFetcher tradeFetcher = new CurrencyPoeTradeFetcher();
        try {
            response = tradeFetcher.sendGet(wantedAmountString, wantedCurrencyID, currencyToPayWithID);
        } catch (Exception e1) {
            Sentry.capture(e1);
        }

        // Load all offers from html response
        CurrencyPoeTradeHandler handler = new CurrencyPoeTradeHandler(response);

        // Filter out possible trades
        handler.filterTradesByUserInput(wantedAmount, maxPrice);
        handler.calculateFilteredPricesByAmount(wantedAmount);
        handler.getFilteredOffers().generateTradeMessages(wantedAmount);

        for (int i = 0; i < handler.getFilteredOffers().getAllOffersAsList().size(); i++) {
            System.out.println(handler.getFilteredOffers().getAllOffersAsList().get(i).getTradeMessage());
        }

        // Add tradeables to Nextbutton
        frame.setCurrencyOffers(handler.getFilteredOffers());

        // display tradeables amount
        frame.getCurrencyBuyerPanel().getTradeables().setText("Tradeables: " + frame.getCurrencyOffers().getAllOffersAsList().size());

        if (handler.getFilteredOffers().getAllOffersAsList().size() > 0) {
            frame.getCurrencyBuyerPanel().getBtn_nextTrade_currencyTab().setEnabled(true);
        } else {
            frame.getCurrencyBuyerPanel().getBtn_nextTrade_currencyTab().setEnabled(false);
        }
    }

}
