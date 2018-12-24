package jsonNinjaResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Listing {

	@SerializedName("method")
	@Expose
	public String method;
	@SerializedName("indexed")
	@Expose
	public String indexed;
	@SerializedName("stash")
	@Expose
	public Stash stash;
	@SerializedName("whisper")
	@Expose
	public String whisper;
	@SerializedName("account")
	@Expose
	public Account account;
	@SerializedName("price")
	@Expose
	public Price price;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getIndexed() {
		return indexed;
	}
	public void setIndexed(String indexed) {
		this.indexed = indexed;
	}
	public Stash getStash() {
		return stash;
	}
	public void setStash(Stash stash) {
		this.stash = stash;
	}
	public String getWhisper() {
		return whisper;
	}
	public void setWhisper(String whisper) {
		this.whisper = whisper;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
}