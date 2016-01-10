package br.com.datarey.frame.base;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.com.datarey.controller.BaseController;

public abstract class BaseWindow {

    private static final Logger LOGGER = Logger.getLogger(BaseWindow.class);  
    
    @Inject
    protected FXMLLoader fxmlLoader;

    protected Stage stage;

    private String source;
    private int width = 800;
    private int height = 600;
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
    protected void init() {
        try {
            stage = new Stage();
            Parent root;
            InputStream is = this.getClass().getResourceAsStream(source);
            is = new BufferedInputStream(is);
            root = fxmlLoader.load(is);
            stage.setTitle(title);
            stage.setScene(new Scene(root, width, height));
            BaseController baseController = fxmlLoader.getController();
            if (baseController != null)
                baseController.setStage(stage);
        } catch(IOException e) {
            LOGGER.error(e);
        }
    }

    public void show() {
        stage.show();
        stage.requestFocus();
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
    
    public void setOnCloseRequest(EventHandler<WindowEvent> event){
        stage.setOnCloseRequest(event);
    }

}
