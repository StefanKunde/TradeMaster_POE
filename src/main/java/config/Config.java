package config;

public class Config {
	
	public static boolean useAutomatedTrading = false;

	public static String leagueSelection = "Betrayal";

	public static String getEncodedLeagueSelection() {
		return leagueSelection.replace(" ", "%20");
	};
	
	public static boolean isUseAutomatedTrading() {
		return useAutomatedTrading;
	}

	public static void setUseAutomatedTrading(boolean useAutomatedTrading) {
		Config.useAutomatedTrading = useAutomatedTrading;
	}
}
