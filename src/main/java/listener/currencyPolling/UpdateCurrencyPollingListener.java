package listener.currencyPolling;

import config.Config;
import connector.CurrencyPoeTradeFetcher;
import gui.MainFrame;
import handler.CurrencyPoeTradeHandler;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCurrencyPollingListener implements ActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateCurrencyPollingListener.class);

    private MainFrame frame;

    public UpdateCurrencyPollingListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getPanelBulkMaps().getTradeables().setText("Loading...");
        frame.getPanelBulkMaps().getUpdateButton().setEnabled(false);

        // Get currency I want from list
        String wantedCurrency = frame.getCurrencyBuyerPanel().getCmbCurrencyTabWant().getSelectedItem().toString();
        // Get currency I want to pay from list
        String currencyToPayWith = frame.getCurrencyBuyerPanel().getCmbCurrencyTabPay().getSelectedItem().toString();

        // Get amount I want
        String wantedAmountString = frame.getCurrencyBuyerPanel().getTxtCurrencyTabNeededAmount().getText();

        int wantedAmount = Integer.valueOf(wantedAmountString);
        // Get Max price i want to pay
        double maxPrice;
        try {
            maxPrice = Double.valueOf(frame.getCurrencyBuyerPanel().getTxtCurrencyTabMaxPay().getText());
        } catch (NumberFormatException nfe) {
            LOG.debug("Invalid max-price, default to 0");
            maxPrice = 0;
        }

        // Prepare request: Get ids from selected currencies
        String wantedCurrencyID = "";
        String currencyToPayWithID = "";


        JSONArray poeTradeNames = Config.get().getPoeTradeCurrencies().names();

        for (int i = 0; i < poeTradeNames.length(); i++) {
            if (poeTradeNames.get(i).equals(wantedCurrency)) {
                wantedCurrencyID = (String) Config.get().getPoeTradeCurrencies().opt(poeTradeNames.get(i).toString());
            }
            if (poeTradeNames.get(i).equals(currencyToPayWith)) {
                currencyToPayWithID = (String) Config.get().getPoeTradeCurrencies().opt(poeTradeNames.get(i).toString());
            }
        }

        // Send request to poe.currency.trade
        String response = "";
        CurrencyPoeTradeFetcher tradeFetcher = new CurrencyPoeTradeFetcher();
        try {
            response = tradeFetcher.sendGet(wantedAmountString, wantedCurrencyID, currencyToPayWithID);
        } catch (Exception e1) {
            LOG.error("", e1);
        }

        // Load all offers from html response
        CurrencyPoeTradeHandler handler = new CurrencyPoeTradeHandler(response);

        // Filter out possible trades
        handler.filterTradesByUserInput(wantedAmount, maxPrice);
        handler.calculateFilteredPricesByAmount(wantedAmount);
        handler.getFilteredOffers().generateTradeMessages(wantedAmount);

        for (int i = 0; i < handler.getFilteredOffers().getAllOffersAsList().size(); i++) {
            LOG.debug(handler.getFilteredOffers().getAllOffersAsList().get(i).getTradeMessage());
        }

        // Add tradeables to Nextbutton
        frame.setCurrencyOffers(handler.getFilteredOffers());

        // display tradeables amount
        frame.getCurrencyBuyerPanel().getTradeables().setText("Tradeables: " + frame.getCurrencyOffers().getAllOffersAsList().size());

        if (handler.getFilteredOffers().getAllOffersAsList().size() > 0) {
            frame.getCurrencyBuyerPanel().getBtnNextTradeCurrencyTab().setEnabled(true);
        } else {
            frame.getCurrencyBuyerPanel().getBtnNextTradeCurrencyTab().setEnabled(false);
        }
    }

}
