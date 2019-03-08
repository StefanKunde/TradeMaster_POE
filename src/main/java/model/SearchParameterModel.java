package model;

import app.Config;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class SearchParameterModel {

    private BasicNameValuePair type = new BasicNameValuePair("type", "");
    private BasicNameValuePair base = new BasicNameValuePair("base", "");
    private BasicNameValuePair name = new BasicNameValuePair("name", "");
    private BasicNameValuePair dmg_min = new BasicNameValuePair("dmg_min", "");
    private BasicNameValuePair dmg_max = new BasicNameValuePair("dmg_max", "");
    private BasicNameValuePair aps_min = new BasicNameValuePair("aps_min", "");
    private BasicNameValuePair aps_max = new BasicNameValuePair("aps_max", "");
    private BasicNameValuePair crit_min = new BasicNameValuePair("crit_min", "");
    private BasicNameValuePair crit_max = new BasicNameValuePair("crit_max", "");
    private BasicNameValuePair dps_min = new BasicNameValuePair("dps_min", "");
    private BasicNameValuePair dps_max = new BasicNameValuePair("dps_max", "");
    private BasicNameValuePair edps_min = new BasicNameValuePair("edps_min", "");
    private BasicNameValuePair edps_max = new BasicNameValuePair("edps_max", "");
    private BasicNameValuePair pdps_min = new BasicNameValuePair("pdps_min", "");
    private BasicNameValuePair pdps_max = new BasicNameValuePair("pdps_max", "");
    private BasicNameValuePair armour_min = new BasicNameValuePair("armour_min", "");
    private BasicNameValuePair armour_max = new BasicNameValuePair("armour_max", "");
    private BasicNameValuePair evasion_min = new BasicNameValuePair("evasion_min", "");
    private BasicNameValuePair evasion_max = new BasicNameValuePair("evasion_max", "");
    private BasicNameValuePair shield_min = new BasicNameValuePair("shield_min", "");
    private BasicNameValuePair shield_max = new BasicNameValuePair("shield_max", "");
    private BasicNameValuePair block_min = new BasicNameValuePair("block_min", "");
    private BasicNameValuePair block_max = new BasicNameValuePair("block_max", "");
    private BasicNameValuePair sockets_min = new BasicNameValuePair("sockets_min", "");
    private BasicNameValuePair sockets_max = new BasicNameValuePair("sockets_max", "");
    private BasicNameValuePair link_min = new BasicNameValuePair("link_min", "");
    private BasicNameValuePair link_max = new BasicNameValuePair("link_max", "");
    private BasicNameValuePair sockets_r = new BasicNameValuePair("sockets_r", "");
    private BasicNameValuePair sockets_g = new BasicNameValuePair("sockets_g", "");
    private BasicNameValuePair sockets_b = new BasicNameValuePair("sockets_b", "");
    private BasicNameValuePair sockets_w = new BasicNameValuePair("sockets_w", "");
    private BasicNameValuePair linked_r = new BasicNameValuePair("linked_r", "");
    private BasicNameValuePair linked_g = new BasicNameValuePair("linked_g", "");
    private BasicNameValuePair linked_b = new BasicNameValuePair("linked_b", "");
    private BasicNameValuePair linked_w = new BasicNameValuePair("linked_w", "");
    private BasicNameValuePair rlevel_min = new BasicNameValuePair("rlevel_min", "");
    private BasicNameValuePair rlevel_max = new BasicNameValuePair("rlevel_max", "");
    private BasicNameValuePair rstr_min = new BasicNameValuePair("rstr_min", "");
    private BasicNameValuePair rstr_max = new BasicNameValuePair("rstr_max", "");
    private BasicNameValuePair rdex_min = new BasicNameValuePair("rdex_min", "");
    private BasicNameValuePair rdex_max = new BasicNameValuePair("rdex_max", "");
    private BasicNameValuePair rint_min = new BasicNameValuePair("rint_min", "");
    private BasicNameValuePair rint_max = new BasicNameValuePair("rint_max", "");
    private BasicNameValuePair mod_name = new BasicNameValuePair("mod_name", "");
    private BasicNameValuePair mod_min = new BasicNameValuePair("mod_min", "");
    private BasicNameValuePair mod_max = new BasicNameValuePair("mod_max", "");
    private BasicNameValuePair mod_weight = new BasicNameValuePair("mod_weight", "");
    private BasicNameValuePair group_type = new BasicNameValuePair("group_type", "And");
    private BasicNameValuePair group_min = new BasicNameValuePair("group_min", "");
    private BasicNameValuePair group_max = new BasicNameValuePair("group_max", "");
    private BasicNameValuePair group_count = new BasicNameValuePair("group_count", "1");
    private BasicNameValuePair q_min = new BasicNameValuePair("q_min", "");
    private BasicNameValuePair q_max = new BasicNameValuePair("q_max", "");
    private BasicNameValuePair level_min = new BasicNameValuePair("level_min", "");
    private BasicNameValuePair level_max = new BasicNameValuePair("level_max", "");
    private BasicNameValuePair ilvl_min = new BasicNameValuePair("ilvl_min", "");
    private BasicNameValuePair ilvl_max = new BasicNameValuePair("ilvl_max", "");
    private BasicNameValuePair rarity = new BasicNameValuePair("rarity", "");
    private BasicNameValuePair progress_min = new BasicNameValuePair("progress_min", "");
    private BasicNameValuePair progress_max = new BasicNameValuePair("progress_max", "");
    private BasicNameValuePair sockets_a_min = new BasicNameValuePair("sockets_a_min", "");
    private BasicNameValuePair sockets_a_max = new BasicNameValuePair("sockets_a_max", "");
    private BasicNameValuePair map_series = new BasicNameValuePair("map_series", "");
    private BasicNameValuePair altart = new BasicNameValuePair("altart", "");
    private BasicNameValuePair identified = new BasicNameValuePair("identified", "");
    private BasicNameValuePair corrupted = new BasicNameValuePair("corrupted", "");
    private BasicNameValuePair shaper = new BasicNameValuePair("shaper", "");
    private BasicNameValuePair elder = new BasicNameValuePair("elder", "");
    private BasicNameValuePair crafted = new BasicNameValuePair("crafted", "");
    private BasicNameValuePair enchanted = new BasicNameValuePair("enchanted", "");
    private BasicNameValuePair mirrored = new BasicNameValuePair("mirrored", "");
    private BasicNameValuePair veiled = new BasicNameValuePair("veiled", "");
    private BasicNameValuePair seller = new BasicNameValuePair("seller", "");
    private BasicNameValuePair thread = new BasicNameValuePair("thread", "");
    private BasicNameValuePair online = new BasicNameValuePair("online", "x");
    private BasicNameValuePair capquality = new BasicNameValuePair("capquality", "x");
    private BasicNameValuePair buyout_min = new BasicNameValuePair("buyout_min", "");
    private BasicNameValuePair buyout_max = new BasicNameValuePair("buyout_max", "");
    private BasicNameValuePair buyout_currency = new BasicNameValuePair("buyout_currency", "");
    private BasicNameValuePair has_buyout = new BasicNameValuePair("has_buyout", "1");
    private BasicNameValuePair exact_currency = new BasicNameValuePair("exact_currency", "");


    public List<NameValuePair> generateSearchData() {
        List<NameValuePair> searchData = new ArrayList<NameValuePair>();
        searchData.add(new BasicNameValuePair("league", Config.get().getLeagueSelection()));
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
        this.buyout_currency = new BasicNameValuePair("buyout_currency", currency.toLowerCase());
    }

    public void setTier(String tierLevel) {
        this.level_min = new BasicNameValuePair("level_min", tierLevel);
    }

    public void setRarity(String rarity) {
        this.rarity = new BasicNameValuePair("rarity", rarity);
    }

    public void setCorrupted(boolean corrupted) {
        this.corrupted = new BasicNameValuePair("corrupted", corrupted ? "1" : "0");
    }


}
