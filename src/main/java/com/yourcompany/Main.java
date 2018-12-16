package com.yourcompany;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws IOException 
	{
	    
	    try {
			//BorderPane root = new BorderPane();
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("MapTradeHelperView.fxml"));
			Scene scene = new Scene(root);
			stage.setMinHeight(500);
			stage.setMinWidth(500);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
