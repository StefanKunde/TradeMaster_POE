package PoeNinjaPricesJson;

public class PoeNinjaPricesAsObject {

	private Line lines[];
	private CurrencyDetail currencyDetails[];
	
	public PoeNinjaPricesAsObject() {
		
	}

	public Line[] getLines() {
		return lines;
	}

	public void setLines(Line[] lines) {
		this.lines = lines;
	}

	public CurrencyDetail[] getCurrencyDetails() {
		return currencyDetails;
	}

	public void setCurrencyDetails(CurrencyDetail[] currencyDetails) {
		this.currencyDetails = currencyDetails;
	}
	
	

}
