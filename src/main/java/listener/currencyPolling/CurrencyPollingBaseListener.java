package listener.currencyPolling;

import gui.MainFrame;
import listener.currency.CurrencyBaseListener;
import org.slf4j.Logger;

public abstract class CurrencyPollingBaseListener extends CurrencyBaseListener {


    public CurrencyPollingBaseListener(MainFrame frame) {
        super(frame);
    }

    @Override
    protected void runCommonChecks(String selectedPayItem, String selectedWantItem) {
        setSelectionAndPayCurrencies(selectedPayItem, selectedWantItem);
    }

    public abstract Logger getLogger();
}
