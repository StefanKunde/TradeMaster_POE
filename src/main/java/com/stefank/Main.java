package com.stefank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import gui.MainFrame;


public class Main {
	
	public static void main(String[] args) 
	{
		ExecutorService executor = Executors.newFixedThreadPool(3);
		MainFrame mainFrame = new MainFrame();
		executor.execute( new WindowManager(mainFrame));
	}
}
