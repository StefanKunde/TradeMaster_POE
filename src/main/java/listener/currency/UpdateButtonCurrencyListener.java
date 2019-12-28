package listener.currency;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Config;
import app.Main;
import org.json.JSONArray;

import connector.CurrencyPoeTradeFetcher;
import gui.MainFrame;
import handler.CurrencyPoeTradeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateButtonCurrencyListener implements ActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateButtonCurrencyListener.class);

    private MainFrame frame;

    public UpdateButtonCurrencyListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCurrencyBuyerPanel().disableUpdateButtonAndSetPendingText();

        Main.getExecutor().execute(() -> {
            // Get Max price i want to pay
            double maxPrice;
            try {
                maxPrice = Double.valueOf(frame.getCurrencyBuyerPanel().getTxtCurrencyTabMaxPay().getText());
            } catch (NumberFormatException nfe) {
                LOG.debug("Invalid max-price, default to 0");
                maxPrice = 0;
            }

            String wantedCurrency = frame.getCurrencyBuyerPanel().getCmbCurrencyTabWant().getSelectedItem().toString();
            String currencyToPayWith = frame.getCurrencyBuyerPanel().getCmbCurrencyTabPay().getSelectedItem().toString();
            String wantedAmountString = frame.getCurrencyBuyerPanel().getTxtCurrencyTabNeededAmount().getText();

            int wantedAmount = Integer.valueOf(wantedAmountString);

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
            CurrencyPoeTradeFetcher tradeFetcher = new CurrencyPoeTradeFetcher();
            String response = tradeFetcher.sendGet(wantedAmountString, wantedCurrencyID, currencyToPayWithID);

            // Load all offers from html response
            CurrencyPoeTradeHandler handler = new CurrencyPoeTradeHandler(response);

            // Filter out possible trades
            handler.filterTradesByUserInput(wantedAmount, maxPrice);
            handler.calculateFilteredPricesByAmount(wantedAmount);
            handler.getFilteredOffers().generateTradeMessages(wantedAmount);

            for (int i = 0; i < handler.getFilteredOffers().getAllOffersAsList().size(); i++) {
                LOG.debug(handler.getFilteredOffers().getAllOffersAsList().get(i).getTradeMessage());
            }

            // Add trade-ables to next-button
            frame.setCurrencyOffers(handler.getFilteredOffers());

            // display tradeables amount
            frame.getCurrencyBuyerPanel().getTradeables().setText("Tradeables: " + frame.getCurrencyOffers().getAllOffersAsList().size());

            if (handler.getFilteredOffers().getAllOffersAsList().size() > 0) {
                frame.getCurrencyBuyerPanel().getBtnNextTradeCurrencyTab().setEnabled(true);
            } else {
                frame.getCurrencyBuyerPanel().getBtnNextTradeCurrencyTab().setEnabled(false);
            }

            frame.getCurrencyBuyerPanel().enableAndResetUpdateButtonText();
        });
    }

}
