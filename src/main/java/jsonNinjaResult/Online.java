package jsonNinjaResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Online {
	@SerializedName("league")
	@Expose
	public String league;

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}
}