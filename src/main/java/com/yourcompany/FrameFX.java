package com.yourcompany;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FrameFX extends Application {

	public Stage myStage;
	public void run(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			myStage = stage;
			//BorderPane root = new BorderPane();
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("MapTradeHelperView.fxml"));
			Scene scene = new Scene(root);
			myStage.setMinHeight(500);
			myStage.setMinWidth(500);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			myStage.setScene(scene);
			myStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void showWindow() {
		this.myStage.show();
	}
	
	public void hideWindow() {
		this.myStage.hide();
	}

}
