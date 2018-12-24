package com.stefank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import connector.PoeNinjaFetcher;
import gui.MainFrame;


public class Main {
	
	public static void main(String[] args) 
	{
		ExecutorService executor = Executors.newFixedThreadPool(3);
		MainFrame mainFrame = new MainFrame();
		executor.execute( new WindowManager(mainFrame));
		
//		PoeNinjaFetcher fetcher = new PoeNinjaFetcher();
//		try {
//			String responseFromPost = fetcher.sendPost("https://www.pathofexile.com/api/trade/exchange/Betrayal", null);
//			String searchLink = fetcher.generateSearchString(responseFromPost);
//			String responseSearch = fetcher.sendGet(searchLink);
//			
//			System.out.println("Response: " + responseSearch);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
