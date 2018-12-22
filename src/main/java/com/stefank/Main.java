package com.stefank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
	
	public static void main(String[] args) 
	{
		ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(WindowManager::new);
	}
}
