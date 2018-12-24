package jsonNinjaResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exchange {

	@SerializedName("currency")
	@Expose
	public String currency;
	@SerializedName("amount")
	@Expose
	public Double amount;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}