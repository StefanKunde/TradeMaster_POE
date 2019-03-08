package listener.currencyPolling;

import gui.MainFrame;
import listener.currency.CurrencyBaseListener;
import org.slf4j.Logger;

public abstract class CurrencyPollingBaseListener extends CurrencyBaseListener {


    public CurrencyPollingBaseListener(MainFrame frame) {
        super(frame);
    }

    public abstract Logger getLogger();
}
