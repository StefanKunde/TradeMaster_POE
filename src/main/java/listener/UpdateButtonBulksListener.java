package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.MainFrame;
import handler.JsonNinjaSearchData;
import handler.PoeNinjaHandler;

	public class UpdateButtonBulksListener implements ActionListener {

		private MainFrame frame;
		
		public UpdateButtonBulksListener(MainFrame frame) {
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.getPanelBulkMaps().getLbl_tradeables_bulks().setText("Loading...");
			frame.getPanelBulkMaps().getBtn_update_bulkbuyer().setEnabled(false);
			int bulkAmount = Integer.valueOf( frame.getPanelBulkMaps().getTxt_amount_bulks().getText() );
			boolean isElder = frame.getPanelBulkMaps().getChckbxElderMap().isSelected();
			String mapFromCmbBox = frame.getPanelBulkMaps().getCmb_maps_bulks().getSelectedItem().toString();
			PoeNinjaHandler ninjaHandler = new PoeNinjaHandler(bulkAmount);
			JsonNinjaSearchData searchData = new JsonNinjaSearchData();
			
			// TODO: Generate json searchData...
			String map = "";
			if(isElder) {
				map += "elder-";
			}
			map += mapFromCmbBox.replaceAll(" ", "-");
			map += "-map"; 
			
			searchData.setBulkAmount(bulkAmount);
			searchData.setMap(map);
			ninjaHandler.setSearchData(searchData);
			ninjaHandler.generateJsonSearchString();
			ninjaHandler.handleBulkRequests();
			
			frame.setTradeables( ninjaHandler.getTradeableBulks() );
			
			// Filter by currency and price per map
			if(frame.getPanelBulkMaps().getTxtbox_pricePerMap().isEnabled() && frame.isValidPricePerMapInput()) {
				int pricePerMap;
				String currency;
				
				pricePerMap = Integer.valueOf(frame.getPanelBulkMaps().getTxtbox_pricePerMap().getText());
				currency = (String )frame.getPanelBulkMaps().getCmb_currency_bulks().getSelectedItem();
				System.out.println("Currency before filtering: "  + currency);
				
				frame.getTradeables().filterByCurrencyAndMaxPrice(currency, pricePerMap);
			}
			
			frame.getPanelBulkMaps().getLbl_tradeables_bulks().setText("Tradeables: " + frame.getTradeables().getFilteredTradeableItems().size());
			
			
			if(frame.getTradeables().getFilteredTradeableItems().size() > 0 ) {
				frame.getPanelBulkMaps().getBtn_nextTrade_bulks().setEnabled(true);
			}
		}

	}
