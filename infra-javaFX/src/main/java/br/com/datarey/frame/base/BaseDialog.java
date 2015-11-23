package br.com.datarey.frame.base;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.stage.Window;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.com.datarey.controller.BaseDialogController;

public abstract class BaseDialog<T> {

    private static final Logger LOGGER = Logger.getLogger(BaseDialog.class);  
    
    @Inject
    protected FXMLLoader fxmlLoader;
    
    private BaseDialogController<T> baseController;

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

    boolean init() {
        Parent root;
        try {
            InputStream is = new FileInputStream(source);
            is = new BufferedInputStream(is);
            if(baseController != null)
                fxmlLoader.setController(baseController);
            
            root = fxmlLoader.load(is);
            dialog.setTitle(title);
            dialog.getDialogPane().setContent(root);
            
            BaseDialogController<T> baseController = fxmlLoader.getController();
            if(baseController != null){
                baseController.setDialog(dialog);
            }
            return true;
        } catch(IOException e) {
            LOGGER.error(e);
            return false;
        }
    }

    public void show(Window window) {
        dialog = new Dialog<>();
        dialog.initOwner(window);
        if(init())
            dialog.show();
    }

    public T showAndWait(Window window) {
        dialog = new Dialog<>();
        dialog.initOwner(window);
        if(init())
            return dialog.showAndWait().get();
        return null;
    }
    
    public void show() {
        dialog = new Dialog<>();
        if(init())
            dialog.show();
    }

    public T showAndWait() {
        dialog = new Dialog<>();
        if(init())
            return dialog.showAndWait().get();
        return null;
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

    protected void setBaseController(BaseDialogController<T> baseController) {
        this.baseController = baseController;
    }
    
}