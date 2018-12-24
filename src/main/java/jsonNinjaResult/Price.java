package jsonNinjaResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

	@SerializedName("exchange")
	@Expose
	public Exchange exchange;
	@SerializedName("item")
	@Expose
	public Item_ item;
	public Exchange getExchange() {
		return exchange;
	}
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
	public Item_ getItem() {
		return item;
	}
	public void setItem(Item_ item) {
		this.item = item;
	}
}