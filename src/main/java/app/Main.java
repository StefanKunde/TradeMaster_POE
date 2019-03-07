package app;

import connector.ConfigurationConnector;
import gui.MainFrame;
import items.PoeNinjaPrices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void main(String... args) {
        ConfigurationConnector.configure();

        PoeNinjaPrices prices = new PoeNinjaPrices();
        prices.getPrices();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        MainFrame mainFrame = new MainFrame(prices);
        executor.execute(new WindowManager(mainFrame));
    }
}
