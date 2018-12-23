package connector;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;



public class SearchParameter {
	
	BasicNameValuePair league = new BasicNameValuePair("league", "Betrayal");
	BasicNameValuePair type = new BasicNameValuePair("type", "");
	BasicNameValuePair base = new BasicNameValuePair("base", "");
	BasicNameValuePair name = new BasicNameValuePair("name", "");
	BasicNameValuePair dmg_min = new BasicNameValuePair("dmg_min", "");
	BasicNameValuePair dmg_max = new BasicNameValuePair("dmg_max", "");
	BasicNameValuePair aps_min = new BasicNameValuePair("aps_min", "");
	BasicNameValuePair aps_max = new BasicNameValuePair("aps_max", "");
	BasicNameValuePair crit_min = new BasicNameValuePair("crit_min", "");
	BasicNameValuePair crit_max = new BasicNameValuePair("crit_max", "");
	BasicNameValuePair dps_min = new BasicNameValuePair("dps_min", "");
	BasicNameValuePair dps_max = new BasicNameValuePair("dps_max", "");
	BasicNameValuePair edps_min = new BasicNameValuePair("edps_min", "");
	BasicNameValuePair edps_max = new BasicNameValuePair("edps_max", "");
	BasicNameValuePair pdps_min = new BasicNameValuePair("pdps_min", "");
	BasicNameValuePair pdps_max = new BasicNameValuePair("pdps_max", "");
	BasicNameValuePair armour_min = new BasicNameValuePair("armour_min", "");
	BasicNameValuePair armour_max = new BasicNameValuePair("armour_max", "");
	BasicNameValuePair evasion_min = new BasicNameValuePair("evasion_min", "");
	BasicNameValuePair evasion_max = new BasicNameValuePair("evasion_max", "");
	BasicNameValuePair shield_min = new BasicNameValuePair("shield_min", "");
	BasicNameValuePair shield_max = new BasicNameValuePair("shield_max", "");
	BasicNameValuePair block_min = new BasicNameValuePair("block_min", "");
	BasicNameValuePair block_max = new BasicNameValuePair("block_max", "");
	BasicNameValuePair sockets_min = new BasicNameValuePair("sockets_min", "");
	BasicNameValuePair sockets_max = new BasicNameValuePair("sockets_max", "");
	BasicNameValuePair link_min = new BasicNameValuePair("link_min", "");
	BasicNameValuePair link_max = new BasicNameValuePair("link_max", "");
	BasicNameValuePair sockets_r = new BasicNameValuePair("sockets_r", "");
	BasicNameValuePair sockets_g = new BasicNameValuePair("sockets_g", "");
	BasicNameValuePair sockets_b = new BasicNameValuePair("sockets_b", "");
	BasicNameValuePair sockets_w = new BasicNameValuePair("sockets_w", "");
	BasicNameValuePair linked_r = new BasicNameValuePair("linked_r", "");
	BasicNameValuePair linked_g = new BasicNameValuePair("linked_g", "");
	BasicNameValuePair linked_b = new BasicNameValuePair("linked_b", "");
	BasicNameValuePair linked_w = new BasicNameValuePair("linked_w", "");
	BasicNameValuePair rlevel_min = new BasicNameValuePair("rlevel_min", "");
	BasicNameValuePair rlevel_max = new BasicNameValuePair("rlevel_max", "");
	BasicNameValuePair rstr_min = new BasicNameValuePair("rstr_min", "");
	BasicNameValuePair rstr_max = new BasicNameValuePair("rstr_max", "");
	BasicNameValuePair rdex_min = new BasicNameValuePair("rdex_min", "");
	BasicNameValuePair rdex_max = new BasicNameValuePair("rdex_max", "");
	BasicNameValuePair rint_min = new BasicNameValuePair("rint_min", "");
	BasicNameValuePair rint_max = new BasicNameValuePair("rint_max", "");
	BasicNameValuePair mod_name = new BasicNameValuePair("mod_name", "");
	BasicNameValuePair mod_min = new BasicNameValuePair("mod_min", "");
	BasicNameValuePair mod_max = new BasicNameValuePair("mod_max", "");
	BasicNameValuePair mod_weight = new BasicNameValuePair("mod_weight", "");
	BasicNameValuePair group_type = new BasicNameValuePair("group_type", "And");
	BasicNameValuePair group_min = new BasicNameValuePair("group_min", "");
	BasicNameValuePair group_max = new BasicNameValuePair("group_max", "");
	BasicNameValuePair group_count = new BasicNameValuePair("group_count", "1");
	BasicNameValuePair q_min = new BasicNameValuePair("q_min", "");
	BasicNameValuePair q_max = new BasicNameValuePair("q_max", "");
	BasicNameValuePair level_min = new BasicNameValuePair("level_min", "");
	BasicNameValuePair level_max = new BasicNameValuePair("level_max", "");
	BasicNameValuePair ilvl_min = new BasicNameValuePair("ilvl_min", "");
	BasicNameValuePair ilvl_max = new BasicNameValuePair("ilvl_max", "");
	BasicNameValuePair rarity = new BasicNameValuePair("rarity", "");
	BasicNameValuePair progress_min = new BasicNameValuePair("progress_min", "");
	BasicNameValuePair progress_max = new BasicNameValuePair("progress_max", "");
	BasicNameValuePair sockets_a_min = new BasicNameValuePair("sockets_a_min", "");
	BasicNameValuePair sockets_a_max = new BasicNameValuePair("sockets_a_max", "");
	BasicNameValuePair map_series = new BasicNameValuePair("map_series", "");
	BasicNameValuePair altart = new BasicNameValuePair("altart", "");
	BasicNameValuePair identified = new BasicNameValuePair("identified", "");
	BasicNameValuePair corrupted = new BasicNameValuePair("corrupted", "");
	BasicNameValuePair shaper = new BasicNameValuePair("shaper", "");
	BasicNameValuePair elder = new BasicNameValuePair("elder", "");
	BasicNameValuePair crafted = new BasicNameValuePair("crafted", "");	
	BasicNameValuePair enchanted = new BasicNameValuePair("enchanted", "");
	BasicNameValuePair mirrored = new BasicNameValuePair("mirrored", "");
	BasicNameValuePair veiled = new BasicNameValuePair("veiled", "");	
	BasicNameValuePair seller = new BasicNameValuePair("seller", "");
	BasicNameValuePair thread = new BasicNameValuePair("thread", "");
	BasicNameValuePair online = new BasicNameValuePair("online", "x");	
	BasicNameValuePair capquality = new BasicNameValuePair("capquality", "x");
	BasicNameValuePair buyout_min = new BasicNameValuePair("buyout_min", "");
	BasicNameValuePair buyout_max = new BasicNameValuePair("buyout_max", "");	
	BasicNameValuePair buyout_currency = new BasicNameValuePair("buyout_currency", "");
	BasicNameValuePair has_buyout = new BasicNameValuePair("has_buyout", "1");	
	BasicNameValuePair exact_currency = new BasicNameValuePair("exact_currency", "");	

	
	
	public SearchParameter() {
	}
	
	
	public List<NameValuePair> generateSearchData() {
		List<NameValuePair> searchData = new ArrayList<NameValuePair>();
		searchData.add(league);
		searchData.add(type);
		searchData.add(base);
		searchData.add(name);
		searchData.add(dmg_min);
		searchData.add(dmg_max);
		searchData.add(aps_min);
		searchData.add(aps_max);
		searchData.add(crit_min);
		searchData.add(crit_max);
		searchData.add(dps_min);
		searchData.add(dps_max);
		searchData.add(edps_min);
		searchData.add(edps_max);
		searchData.add(pdps_min);
		searchData.add(pdps_max);
		searchData.add(armour_min);
		searchData.add(armour_max);
		searchData.add(evasion_min);
		searchData.add(evasion_max);
		searchData.add(shield_min);
		searchData.add(shield_max);
		searchData.add(block_min);
		searchData.add(block_max);
		searchData.add(sockets_min);
		searchData.add(sockets_max);
		searchData.add(link_min);
		searchData.add(link_max);
		searchData.add(sockets_r);
		searchData.add(sockets_g);
		searchData.add(sockets_b);
		searchData.add(sockets_w);
		searchData.add(linked_r);
		searchData.add(linked_g);
		searchData.add(linked_b);
		searchData.add(linked_w);
		searchData.add(rlevel_min);
		searchData.add(rlevel_max);
		searchData.add(rstr_min);
		searchData.add(rstr_max);
		searchData.add(rdex_min);
		searchData.add(rdex_max);
		searchData.add(rint_min);
		searchData.add(rint_max);
		searchData.add(mod_name);
		searchData.add(mod_min);
		searchData.add(mod_max);
		searchData.add(mod_weight);
		searchData.add(group_type);
		searchData.add(group_min);
		searchData.add(group_max);
		searchData.add(group_count);
		searchData.add(q_min);
		searchData.add(q_max);
		searchData.add(level_min);
		searchData.add(level_max);
		searchData.add(ilvl_min);
		searchData.add(ilvl_max);
		searchData.add(rarity);
		searchData.add(progress_min);
		searchData.add(progress_max);
		searchData.add(sockets_a_min);
		searchData.add(sockets_a_max);
		searchData.add(map_series);
		searchData.add(altart);
		searchData.add(identified);
		searchData.add(corrupted);
		searchData.add(shaper);
		searchData.add(elder);
		searchData.add(crafted);
		searchData.add(enchanted);
		searchData.add(mirrored);
		searchData.add(veiled);
		searchData.add(seller);
		searchData.add(thread);
		searchData.add(online);
		searchData.add(capquality);
		searchData.add(buyout_min);
		searchData.add(buyout_max);
		searchData.add(buyout_currency);
		searchData.add(has_buyout);
		searchData.add(exact_currency);
		
		return searchData;
	}
	
	public void setMapName(String mapName) {
		this.name = new BasicNameValuePair("name", mapName + " Map");
	}
	
	public void setCurrency(String currency) {
		this.buyout_currency = new BasicNameValuePair("buyout_currency", currency);
	}
	
	public void setTier(String tierLevel) {
		this.level_min = new BasicNameValuePair("level_min", tierLevel);
	}
	

}
