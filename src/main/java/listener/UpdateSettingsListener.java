package listener;

import config.Config;
import gui.MainFrame;
import items.PoeNinjaPrices;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UpdateSettingsListener implements ActionListener {

    private MainFrame frame;

    public UpdateSettingsListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(frame.getSettingsPanel().getLeagueSelection().getSelectedItem().toString());
        String selectedLeague = frame.getSettingsPanel().getLeagueSelection().getSelectedItem().toString();

        if (!Arrays.asList(MainFrame.AVAILABLE_LEAGUES).contains(selectedLeague)) {
            throw new RuntimeException("Invalid League Selected/Attempted");
        }

        Config.leagueSelection = selectedLeague;

        // Re-update the PoE Ninja Prices
        PoeNinjaPrices prices = new PoeNinjaPrices();
        prices.getPrices();
        frame.setPoeNinjaPrices(prices);
    }

}
