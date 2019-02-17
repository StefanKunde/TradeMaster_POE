package items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyOffer {
	private Logger LOG = LoggerFactory.getLogger(CurrencyOffer.class);

	private String nickname = "";
	private int sellValue = 0;
	private String sellCurrency = "";
	private String buyCurrency = "";
	private int buyValue = 0;
	private int stock = 0;
	private double pricePerUnit = 0;
	private int amountPrice = 0;
	private String tradeMessage = "";
	
	public CurrencyOffer() {}
	
	public void calculatePriceByAmount(int wantedAmount) {
		this.amountPrice = (int) ( (double)wantedAmount * (double)pricePerUnit);
	}
	
	public void calculatePricePerUnit() {
		if(sellValue > 0) {
			pricePerUnit = (double)buyValue / (double)sellValue;
		} else {
			LOG.info("ERROR PRICE calculating... Zero div...");
		}
	}
	
	public void generateTradeMessage(int wantedAmount) {
		int priceForAll = (int) ((double)wantedAmount * (double)pricePerUnit);
		String message = "";
		message += "@" + nickname + " ";
		message += "Hi, I'd like to buy your " + wantedAmount + " ";
		message += sellCurrency + " ";
		message += "for my " + priceForAll + " ";
		message += buyCurrency + " in Betrayal.";
		
		this.tradeMessage = message;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSellValue() {
		return sellValue;
	}

	public void setSellValue(int sellValue) {
		this.sellValue = sellValue;
	}

	public int getBuyValue() {
		return buyValue;
	}

	public void setBuyValue(int buyValue) {
		this.buyValue = buyValue;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public String getSellCurrency() {
		return sellCurrency;
	}

	public void setSellCurrency(String sellCurrency) {
		this.sellCurrency = sellCurrency;
	}

	public String getBuyCurrency() {
		return buyCurrency;
	}

	public void setBuyCurrency(String buyCurrency) {
		this.buyCurrency = buyCurrency;
	}

	public String getTradeMessage() {
		return this.tradeMessage;
	}
}
