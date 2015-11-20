package br.com.datarey.frame;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.datarey.controller.BaseDialogController;

public abstract class BaseDialog<T>{
	
	@Inject
	protected FXMLLoader fxmlLoader;
	
	protected Dialog<T> dialog;
	
	private String source;
	private int width;
	private int height;
	private String title;
	
	public BaseDialog(String source) {
		super();
		this.source = source;
	}

	public BaseDialog(String source, int width, int height, String title) {
		super();
		this.source = source;
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	@PostConstruct
	void init(){
		dialog = new Dialog<>();
        Parent root;
		try {
			InputStream is = new FileInputStream(source);
			is = new BufferedInputStream(is);
			root = fxmlLoader.load(is);
			dialog.setTitle(title);
			dialog.getDialogPane().setContent(root);
			
			BaseDialogController<T> baseController = fxmlLoader.getController();
			baseController.setDialog(dialog);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void show(){
		dialog.show();
	}
	
	public T showAndWait(){
		return dialog.showAndWait().get();
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
