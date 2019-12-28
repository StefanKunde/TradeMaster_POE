package listener.currencyPolling;

import app.Config;
import app.Main;
import connector.CurrencyPoeTradeFetcher;
import gui.MainFrame;
import handler.CurrencyPoeTradeHandler;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ScheduledFuture;

public class EnablePollingCheckboxListener implements ActionListener {
    private Logger LOG = LoggerFactory.getLogger(EnablePollingCheckboxListener.class);

    private MainFrame frame;

    public EnablePollingCheckboxListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ScheduledFuture sfThread = frame.getPollingTimerThread();
        if (((JCheckBox) e.getSource()).isSelected()) {
            togglePage(false);

            sfThread = Main.scheduleThreadTimer(() -> {
                CurrencyPoeTradeHandler handler = frame.executeCurrencySearch(frame.getCurrencyPollerPanel());

                if (handler.getFilteredOffers().getAllOffersAsList().size() > 0) {
                    frame.getCurrencyPollerPanel().getBtnNextTradeCurrencyTab().setEnabled(true);
                    Toolkit.getDefaultToolkit().beep();
                } else {
                    frame.getCurrencyPollerPanel().getBtnNextTradeCurrencyTab().setEnabled(false);
                }
            }, 0, Integer.parseInt(frame.getCurrencyPollerPanel().getPollingInMillis().getText()));
        } else {
            togglePage(true);

            sfThread.cancel(true);
            while (!sfThread.isCancelled()) {
                LOG.debug("Polling thread closing...");
            }
            sfThread = null;
        }

        frame.setPollingTimerThread(sfThread);
    }

    private void togglePage(boolean value) {
        frame.getCurrencyPollerPanel().getPollingInMillis().setEnabled(value);
        frame.getCurrencyPollerPanel().getCmbCurrencyTabPay().setEnabled(value);
        frame.getCurrencyPollerPanel().getCmbCurrencyTabWant().setEnabled(value);
        frame.getCurrencyPollerPanel().getTxtCurrencyTabNeededAmount().setEnabled(value);
        frame.getCurrencyPollerPanel().getTxtCurrencyTabMaxPay().setEnabled(value);
        frame.getCurrencyPollerPanel().getTxtCurrencyTabNeededAmount().setEnabled(value);
        frame.getCurrencyPollerPanel().getPollingInMillis().setEnabled(value);
        frame.getCurrencyPollerPanel().getUpdateButton().setEnabled(value);
    }
}