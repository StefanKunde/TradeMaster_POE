package items;

import config.Config;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TradeableItemTest {


    private TradeableItem target;

    @Before
    public void setUp() {
        target = new TradeableItem();
        Config.leagueSelection = "Standard";
    }

    @After
    public void tearDown() {
        target = null;
    }

    @Test
    public void testStandardGeneratedMessage() {
        // Setup Data Requirements
        target.setUsername("VeenarM");
        target.setCurrencyToPay("chaos");
        target.setMapTier("4");
        target.setItemToSell("Bog Map");
        target.setPricePerMap(2);

        // Set expectation and run test case.
        String expected = "@VeenarM Hi, I'd like to buy your 5 Bog Map (T4) for my 10 chaos orb in Standard";
        Assert.assertEquals(expected, target.generateTradeMessage(5));
    }

}