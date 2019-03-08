package PoeNinjaPricesJson;

import lombok.Getter;
import lombok.Setter;

public class PoeNinjaPricesAsObject {

    @Getter
    @Setter
    private Line lines[];
    @Getter
    @Setter
    private CurrencyDetail currencyDetails[];

}
