package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainFrame;
import handler.JsonNinjaSearchData;
import handler.PoeNinjaHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateButtonBulksListener implements ActionListener {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateButtonBulksListener.class);

    private MainFrame frame;

    public UpdateButtonBulksListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getPanelBulkMaps().getTradeables().setText("Loading...");
        frame.getPanelBulkMaps().getUpdateButton().setEnabled(false);
        int bulkAmount = Integer.valueOf(frame.getPanelBulkMaps().getTxt_amount_bulks().getText());
        boolean isElder = frame.getPanelBulkMaps().getChckbxElderMap().isSelected();
        String mapFromCmbBox = frame.getPanelBulkMaps().getCmb_maps_bulks().getSelectedItem().toString();
        PoeNinjaHandler ninjaHandler = new PoeNinjaHandler(bulkAmount);
        JsonNinjaSearchData searchData = new JsonNinjaSearchData();

        // TODO: Generate json searchData...
        String map = "";
        if (isElder) {
            map += "elder-";
        }
        map += mapFromCmbBox.replaceAll(" ", "-");
        map += "-map";

        searchData.setBulkAmount(bulkAmount);
        searchData.setMap(map);
        ninjaHandler.setSearchData(searchData);
        ninjaHandler.generateJsonSearchString();
        ninjaHandler.handleBulkRequests();

        frame.setTradeables(ninjaHandler.getTradeableBulks());

        // Filter by currency and price per map (If not Any selected)
        if (frame.getPanelBulkMaps().getTxtbox_pricePerMap().isEnabled()) {
            int pricePerMap;
            try {
                pricePerMap = Integer.valueOf(frame.getPanelBulkMaps().getTxtbox_pricePerMap().getText());
            } catch (NumberFormatException nfe) {
                // If invalid text/or number, set it to default of 100, meaning price is no option but we only want 'of type'.
                pricePerMap = 100;
            }

            String currency = frame.getPanelBulkMaps().getCmb_currency_bulks().getSelectedItem().toString();

            LOG.debug("Currency before filtering: " + currency);
            frame.getTradeables().filterByCurrencyAndMaxPrice(currency, pricePerMap);
        }

        frame.getPanelBulkMaps().getTradeables().setText("Tradeables: " + frame.getTradeables().getFilteredTradeableItems().size());


        if (frame.getTradeables().getFilteredTradeableItems().size() > 0) {
            frame.getPanelBulkMaps().getBtn_nextTrade_bulks().setEnabled(true);
        }
    }

}
