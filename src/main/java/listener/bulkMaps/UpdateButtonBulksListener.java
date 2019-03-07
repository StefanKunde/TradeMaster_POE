package listener.bulkMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.MainFrame;
import handler.PoeTradeBulkItemExchangeSearchData;
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

        int minimumAmount = Integer.valueOf(frame.getPanelBulkMaps().getTxt_amount_bulks().getText());

        String mapFromCmbBox = frame.getPanelBulkMaps().getCmb_maps_bulks().getSelectedItem().toString();
        String payWithCmbBox = frame.getPanelBulkMaps().getCmb_currency_bulks().getSelectedItem().toString();


        String map = mapFromCmbBox.replaceAll(" ", "-").toLowerCase();

        PoeTradeBulkItemExchangeSearchData.Builder searchData = new PoeTradeBulkItemExchangeSearchData.Builder();
        searchData.addMinimum(minimumAmount).addWant(map).addHave(payWithCmbBox);

        PoeNinjaHandler ninjaHandler = new PoeNinjaHandler(searchData.build());
        ninjaHandler.processBulkRequests();

        frame.setTradeables(ninjaHandler.getTradeables());

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
