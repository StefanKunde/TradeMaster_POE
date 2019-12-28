package items;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Maps {

	@Getter @Setter
	private List<PoeTradeResultModel> maps;
	
	public Maps(List<PoeTradeResultModel> maps) {
		this.maps = maps;
	}
	
	public void initializeMaps() {
		this.removeDuplicateUsers();
	}
	
	private void removeDuplicateUsers() {
		List<PoeTradeResultModel> tmpMaps = new ArrayList<PoeTradeResultModel>();
		
		for( int i = 0; i < this.getMaps().size(); i++) {
			if( i == 0 ) {
				tmpMaps.add(this.getMaps().get(i));
			} else if( !this.containtsUserAlready(tmpMaps, this.getMaps().get(i).getIgn()) ) {
				tmpMaps.add(this.getMaps().get(i));
			}
		}
		
		this.setMaps(tmpMaps);
	}
		
	private boolean containtsUserAlready(List<PoeTradeResultModel> tmpMaps, String userToCheck) {
		boolean isDuplicate = false;
		
		int i = 0;
		while(!isDuplicate && tmpMaps.size() > i) {
			isDuplicate = tmpMaps.get(i).getIgn().equals(userToCheck);
			i++;
		}
		
		return isDuplicate;
	}
	
	public void filterByCurrency(String currency) {
		if(!currency.equals("")) {
			List<PoeTradeResultModel> tmpMaps = new ArrayList<>();
			
			for(int i = 0; i < this.getMaps().size(); i++) {
				if(this.getMaps().get(i).getPaymentCurrency().equalsIgnoreCase(currency)) {
					tmpMaps.add(this.getMaps().get(i));
				}
			}
			
			this.setMaps(tmpMaps);
		}
		
	}
	

}
