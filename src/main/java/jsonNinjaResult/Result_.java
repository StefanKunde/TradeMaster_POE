package jsonNinjaResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result_ {

	@SerializedName("id")
	@Expose
	public String id;
	@SerializedName("item")
	@Expose
	public Item item;
	@SerializedName("listing")
	@Expose
	public Listing listing;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Listing getListing() {
		return listing;
	}
	public void setListing(Listing listing) {
		this.listing = listing;
	}
}