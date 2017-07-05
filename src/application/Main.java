package application;

import java.io.IOException;

import application.Controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	private AnchorPane anchorPane;

	@Override
	public void start(Stage primaryStage) {

		try {
			anchorPane = (AnchorPane) FXMLLoader.load(Main.class.getResource("mainScene.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		primaryStage.setScene(new Scene(anchorPane));
		primaryStage.setResizable(false);
		primaryStage.show();

		Controller control = new Controller();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
