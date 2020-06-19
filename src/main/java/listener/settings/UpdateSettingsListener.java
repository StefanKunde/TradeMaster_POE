package listener.settings;

import app.Config;
import app.Main;
import gui.MainFrame;
import items.PoeNinjaPrices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UpdateSettingsListener implements ActionListener {

    private Logger LOG = LoggerFactory.getLogger(UpdateSettingsListener.class);

    private MainFrame frame;

    public UpdateSettingsListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LOG.debug(frame.getSettingsPanel().getLeagueSelection().getSelectedItem().toString());
        String selectedLeague = frame.getSettingsPanel().getLeagueSelection().getSelectedItem().toString();

        if (!Arrays.asList(Config.get().getLeagues()).contains(selectedLeague)) {
            throw new RuntimeException("Invalid League Selected/Attempted");
        }

        Config.get().setLeagueSelection(selectedLeague);

        Main.getExecutor().execute(() -> {
            // Re-update the PoE Ninja Prices
            this.frame.getSettingsPanel().updatePricesLoaded("Loading of PoeNinjaPrices ...");
            frame.setPoeNinjaPrices(new PoeNinjaPrices());
            if (frame.getPoeNinjaPrices().getPriceItemList() == null || frame.getPoeNinjaPrices().getPriceItemList().size() < 1) {
                this.frame.getSettingsPanel().updatePricesLoaded("Loading of PoeNinjaPrices Failed");
            } else {
                this.frame.getSettingsPanel().updatePricesLoaded("Loading of PoeNinjaPrices Succeeded");
            }
        });

    }

}
