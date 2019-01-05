package com.stefank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gui.MainFrame;
import items.PoeNinjaPrices;


public class Main {
	
	public static void main(String[] args) 
	{
		PoeNinjaPrices prices = new PoeNinjaPrices();
		prices.getPrices();
		ExecutorService executor = Executors.newFixedThreadPool(3);
		MainFrame mainFrame = new MainFrame(prices);
		executor.execute( new WindowManager(mainFrame));
	}
}
