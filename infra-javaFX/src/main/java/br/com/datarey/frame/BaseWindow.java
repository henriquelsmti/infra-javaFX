package br.com.datarey.frame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.datarey.controller.AbstractController;
import br.com.datarey.controller.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BaseWindow {

	@Inject
	protected FXMLLoader fxmlLoader;
	
	protected Stage stage;
	
	private String source;
	private int width;
	private int height;
	private String title;
	
	public BaseWindow(String source) {
		super();
		this.source = source;
	}

	public BaseWindow(String source, int width, int height, String title) {
		super();
		this.source = source;
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	@PostConstruct
	void init(){
		stage = new Stage();
        Parent root;
		try {
			InputStream is = new FileInputStream(source);
			is = new BufferedInputStream(is);
			root = fxmlLoader.load(is);
			stage.setTitle(title);
			stage.setScene(new Scene(root, width, height));
			BaseController baseController = fxmlLoader.getController();
			baseController.setStage(stage);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void show(){
		stage.show();
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
