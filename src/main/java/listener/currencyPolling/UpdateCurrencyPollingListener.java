package listener.currencyPolling;

import app.Main;
import gui.MainFrame;
import handler.CurrencyPoeTradeHandler;
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
        frame.getCurrencyPollerPanel().disableUpdateButtonAndSetPendingText();
        frame.getCurrencyPollerPanel().getTradeables().setText("Loading...");
        frame.getCurrencyPollerPanel().getCheckboxEnablePolling().setEnabled(false);

        Main.getExecutor().execute(() -> {
            CurrencyPoeTradeHandler handler = frame.executeCurrencySearch(frame.getCurrencyPollerPanel());

            if (handler.getFilteredOffers().getAllOffersAsList().size() > 0) {
                frame.getCurrencyPollerPanel().getBtnNextTradeCurrencyTab().setEnabled(true);
            } else {
                frame.getCurrencyPollerPanel().getBtnNextTradeCurrencyTab().setEnabled(false);
            }

            frame.getCurrencyPollerPanel().getCheckboxEnablePolling().setEnabled(true);
            frame.getCurrencyPollerPanel().enableAndResetUpdateButtonText();
        });
    }
}
