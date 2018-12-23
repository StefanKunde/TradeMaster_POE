package viewcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import gui.MainFrame;
import handler.TradeHandler;
import items.Map;
import items.Maps;

public class UpdateButtonListener implements ActionListener {

	private MainFrame frame;
	
	public UpdateButtonListener(MainFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		TradeHandler trader = new TradeHandler(frame.getSearchBuilder().generateSearchData());
		List<Element> buyableMaps = trader.fetchBuyableMapsAsHtml();
		frame.setMaps(new ArrayList<Map>());
		
		for( int i = 0; i < buyableMaps.size(); i++) {
			frame.getMaps().add( new Map (buyableMaps.get(i)));
		}
		
		Maps myMaps = new Maps(frame.getMaps());
		myMaps.initializeMaps();
		
		System.out.println(myMaps.getMaps().size());
		
		myMaps.filterByCurrency(frame.getCurrency());
		
		frame.getLbl_count().setText("Tradeables count: " + myMaps.getMaps().size());
		frame.getLbl_count().setEnabled(true);
		frame.setTradeableMaps(myMaps.getMaps());
		
		if(frame.getTradeableMaps().size() > 0) {
			frame.getBtn_nextTrade().setEnabled( true);
		}
		
	}

}
