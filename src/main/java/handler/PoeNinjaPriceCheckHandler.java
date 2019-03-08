package handler;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import PoeNinjaPricesJson.PoeNinjaPricesAsObject;
import connector.BaseConnector;
import items.PoeNinjaPriceItem;

public class PoeNinjaPriceCheckHandler {

    private String pricesAsJson;

    public PoeNinjaPriceCheckHandler(String pricesAsJson) {
        this.pricesAsJson = pricesAsJson;
    }

    public List<PoeNinjaPriceItem> getPriceItemList() {
        List<PoeNinjaPriceItem> priceList = new ArrayList<>();

        PoeNinjaPricesAsObject items = BaseConnector.GSON.fromJson(pricesAsJson, PoeNinjaPricesAsObject.class);

        for (int i = 0; i < items.getLines().length; i++) {
            PoeNinjaPriceItem item = new PoeNinjaPriceItem();
            item.setCurrencyTypeName(items.getLines()[i].getCurrencyTypeName());
            item.setChaosEquivalent(items.getLines()[i].getChaosEquivalent());

            for (int j = 0; j < items.getCurrencyDetails().length; j++) {
                if (items.getCurrencyDetails()[j].getName().equals(items.getLines()[i].getCurrencyTypeName())) {
                    item.setCurrencyID(items.getCurrencyDetails()[j].getPoeTradeId());
                }
            }
            priceList.add(item);
        }

        return priceList;
    }


}
