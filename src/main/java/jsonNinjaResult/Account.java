package jsonNinjaResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("lastCharacterName")
	@Expose
	private String lastCharacterName;
	@SerializedName("online")
	@Expose
	private Online online;
	@SerializedName("language")
	@Expose
	private String language;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastCharacterName() {
		return lastCharacterName;
	}
	public void setLastCharacterName(String lastCharacterName) {
		this.lastCharacterName = lastCharacterName;
	}
	public Online getOnline() {
		return online;
	}
	public void setOnline(Online online) {
		this.online = online;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
