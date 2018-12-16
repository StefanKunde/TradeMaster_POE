package viewcontroller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

import com.yourcompany.Main;

import connector.SearchParameter;
import handler.TradeHandler;
import items.Map;
import items.Maps;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ViewController implements Initializable {
	
	List<Map> maps;
	List<Map> tradeableMaps;
	//List<String> tier_1_maps = new ArrayList<String>();
	
	SearchParameter searchBuilder;
	String currency = "";
	String mapName = "";
	
	boolean selectedCurrency = false;
	boolean selectedTier = false;
	boolean selectedMap = false;
	
	
	@FXML
	private Button btn_nextTrade;
	@FXML
	private Button btn_load;
	@FXML
	private ComboBox cmb_currencyToPay;
	@FXML
	private ComboBox cmb_tier;
	@FXML
	private ComboBox cmb_map;
//	@FXML
//	private Label lbl_trade;
	@FXML
	private TableView tbl_content;
	@FXML
	private TableColumn tblRow_item;
	@FXML
	private TableColumn tblRow_price;
	@FXML
	private TableColumn tblRow_seller;
	
	
	public ViewController() {
		searchBuilder = new SearchParameter();
	}
	
	@FXML
    private void onNextTradeClick(ActionEvent event)
    {
        this.getNextTrade();
        if(this.tradeableMaps.size() == 0) {
        	btn_nextTrade.setDisable(true);
        }
    }
	
	@FXML
    private void onCurrencySelected(ActionEvent event)
    {
		if(!cmb_currencyToPay.getValue().equals("Currency to pay ...")) {
			if(!cmb_currencyToPay.getValue().equals("ANY")) {
				System.out.println("Selected Currency: " + cmb_currencyToPay.getValue());
		        searchBuilder.setCurrency(cmb_currencyToPay.getValue().toString());
		        this.currency = cmb_currencyToPay.getValue().toString();
			}
			this.selectedCurrency = true;
		}
		else {
			btn_load.setDisable(true);
			btn_nextTrade.setDisable(true);
		}
		if(this.selectedTier && this.selectedMap) {
			btn_load.setDisable(false);
		}
    }
	
	@FXML
	private void onTierComboSelected(ActionEvent event) {
		String selectedTier = cmb_tier.getValue().toString();
		
		if(!selectedTier.equals("Select Tier ...")) {
			this.loadMapsFromSelectedTier(selectedTier);
			cmb_map.setDisable(false);
			this.selectedTier = true;
		}
		
		this.btn_load.setDisable(true);
		this.btn_nextTrade.setDisable(true);
	}
	
	@FXML
	private void onLoadClicked() {
		TradeHandler trader = new TradeHandler(this.searchBuilder.generateSearchData());
		List<Element> buyableMaps = trader.fetchBuyableMapsAsHtml();
		this.maps = new ArrayList<Map>();
		
		for( int i = 0; i < buyableMaps.size(); i++) {
			this.maps.add( new Map (buyableMaps.get(i)));
		}
		
		Maps myMaps = new Maps(this.maps);
		myMaps.initializeMaps();
		
		if(!cmb_currencyToPay.getValue().toString().equals("ANY")) {
			myMaps.filterByCurrency(cmb_currencyToPay.getValue().toString());
		}
		
		//lbl_trade.setText("Tradeables count: " + myMaps.getMaps().size());
		this.tradeableMaps = myMaps.getMaps();
		
		if(this.tradeableMaps.size() > 0) {
			btn_nextTrade.setDisable(false);
		}
	}
	
	@FXML
	private void onMapComboSelected(ActionEvent event) {
		String selectedMap = cmb_map.getValue().toString();
		if(!selectedMap.equals("Select map")) {
			System.out.println("Selected Map: " + cmb_map.getValue());
			searchBuilder.setMapName(cmb_map.getValue().toString());
			this.selectedMap = true;
			String selectedCurrency = cmb_currencyToPay.getValue().toString();
			if(!selectedCurrency.equals("Currency to pay ...")) {
				btn_load.setDisable(false);
				btn_load.setDisable(false);
			}
		}
	}
	
	private void getNextTrade() {
		if(tradeableMaps.size() > 0) {
			//lbl_trade.setText(this.tradeableMaps.get(0).generateTradeText());
			String myString = this.tradeableMaps.get(0).generateTradeText();
			StringSelection stringSelection = new StringSelection(myString);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			this.tradeableMaps.remove(0);
		} else {
			//lbl_trade.setText("no tradeables left.");
		}
	}

	private void loadMapsFromSelectedTier(String selectedTier) {
		if(selectedTier.startsWith("Tier ")) {
			String mapsAsJsonString = "";
	        //mapsAsJsonString = new String(Files.readAllBytes(Paths.get("/resources/maps.json")));
			String text = new Scanner(Main.class.getResourceAsStream("maps.json"), "UTF-8").useDelimiter("\\A").next();
			mapsAsJsonString = new String(text);
	        
	        JSONObject json = new JSONObject(mapsAsJsonString);
	        JSONArray maps = json.getJSONArray(selectedTier);
	        List<String> mapsAsList = new ArrayList<String>();
	        // convert json array into arraylist
	        for(int i = 0; i < maps.length(); i++) {
	        	mapsAsList.add(maps.get(i).toString());
	        }
	        Collections.sort(mapsAsList, String.CASE_INSENSITIVE_ORDER);
	        
	        cmb_map.getItems().removeAll(cmb_map.getItems());
	        for(int i = 0; i < mapsAsList.size(); i++) {
	        	cmb_map.getItems().add(mapsAsList.get(i));
	        }
	        cmb_map.getSelectionModel().select("Select map");
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Deactivate map combobox at start
		cmb_map.setDisable(true);
		btn_nextTrade.setDisable(true);
		btn_load.setDisable(true);
		
		// Init Currency to pay
		cmb_currencyToPay.getItems().removeAll(cmb_currencyToPay.getItems());
		cmb_currencyToPay.getItems().addAll("Currency to pay ...", "ANY", "chaos", "alchemy");
		cmb_currencyToPay.getSelectionModel().select("Currency to pay ...");
		
		// Init Tiers
		cmb_tier.getItems().removeAll(cmb_tier.getItems());
		cmb_tier.getItems().addAll("Select Tier ...", "Tier 1", "Tier 2", "Tier 3", "Tier 4", "Tier 5", "Tier 6", 
				"Tier 7", "Tier 8", "Tier 9", "Tier 10", "Tier 11", "Tier 12", "Tier 13", "Tier 14", "Tier 15", "Tier 16");
		cmb_tier.getSelectionModel().select("Select Tier ...");
		
		// Init TableView
		
		TableColumn firstNameCol = new TableColumn("Item");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("item"));
 
        
        TableColumn lastNameCol = new TableColumn("Price");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("price"));
		
        tbl_content.getColumns().addAll(firstNameCol, lastNameCol);
		
//		final ObservableList<MapTrade> data = FXCollections.observableArrayList(
//				new MapTrade("Test1","Test12","Test13"),
//				new MapTrade("Test1","Test12","Test13")
//				
//		);
		
		
		//tbl_content.setItems(data);
		
		tbl_content.setEditable(false);
		
	}

}
