package app;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

public class Config {

    private static Config config;

    public static Config get() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    @Getter
    private final String VOICENAME = "kevin16";

    @Getter
    @Setter
    public boolean useAutomatedTrading = false;

    public final String ENCODING_TYPE = "UTF-8";

    @Getter
    @Setter
    private String leagueSelection;
    @Getter
    @Setter
    private JSONObject poeTradeCurrencies;
    @Getter
    @Setter
    private JSONObject bulkCurrencies;
    @Getter
    @Setter
    private String[] leagues;
    @Getter
    @Setter
    private String[] allMaps;
    @Getter
    @Setter
    private JSONObject tieredMaps;
    @Getter
    @Setter
    private String[] shapedMaps;
    @Getter
    @Setter
    private String[] elderMaps;
    @Getter
    @Setter
    private String[] bulkBuyWithCurrencies;

    public final String[] MAP_TIERS = {"Tier 1", "Tier 2", "Tier 3", "Tier 4", "Tier 5", "Tier 6", "Tier 7", "Tier 8", "Tier 9", "Tier 10", "Tier 11", "Tier 12", "Tier 13", "Tier 14", "Tier 15", "Tier 16"};


    public String getEncodedLeagueSelection() {
        return getLeagueSelection().replace(" ", "%20");
    }
}
