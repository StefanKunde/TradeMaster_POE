package com.yourcompany;

import javafx.stage.Stage;

public class StageHolder {
	public static Stage stage;
	
	public StageHolder(Stage stage) {
		StageHolder.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	
}
