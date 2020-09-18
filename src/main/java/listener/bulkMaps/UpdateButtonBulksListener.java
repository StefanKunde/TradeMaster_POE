package listener.bulkMaps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Main;
import gui.MainFrame;
import lombok.extern.slf4j.Slf4j;
import model.PoeTradeBulkItemExchangeSearchDataModel;
import handler.PoeNinjaHandler;

@Slf4j
public class UpdateButtonBulksListener implements ActionListener {

    private MainFrame frame;

    public UpdateButtonBulksListener(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getPanelBulkMaps().disableUpdateButtonAndSetPendingText();
        frame.getPanelBulkMaps().getTradeables().setText("Loading...");

        Main.getExecutor().execute(() -> {
            int minimumAmount = Integer.valueOf(frame.getPanelBulkMaps().getTxt_amount_bulks().getText());

            String mapFromCmbBox = frame.getPanelBulkMaps().getCmb_maps_bulks().getSelectedItem().toString();
            String payWithCmbBox = frame.getPanelBulkMaps().getCmb_currency_bulks().getSelectedItem().toString();

            String map = mapFromCmbBox.replaceAll(" ", "-").toLowerCase();

            PoeTradeBulkItemExchangeSearchDataModel.Builder searchData = new PoeTradeBulkItemExchangeSearchDataModel.Builder();
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

                log.debug("Currency before filtering: " + currency);
                frame.getTradeables().filterByCurrencyAndMaxPrice(currency, pricePerMap);
            }

            frame.getPanelBulkMaps().getTradeables().setText("Tradeables: " + frame.getTradeables().getFilteredTradeableItems().size());


            if (frame.getTradeables().getFilteredTradeableItems().size() > 0) {
                frame.getPanelBulkMaps().getBtn_nextTrade_bulks().setEnabled(true);
            }

            frame.getPanelBulkMaps().enableAndResetUpdateButtonText();
//            Toolkit.getDefaultToolkit().beep();
        });
    }
}
