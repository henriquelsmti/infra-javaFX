package br.com.datarey.test;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Init {

	@Inject
	private FXMLLoader fxmlLoader;
	
	public void start(Stage primaryStage){

		InputStream is = getClass().getResourceAsStream("sample.fxml");;
        Parent root;
		try {
			root = fxmlLoader.load(is);
			primaryStage.setTitle("Hello World");
			primaryStage.setScene(new Scene(root, 300, 275));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
