package br.com.datarey.frame.base;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.inject.Inject;

import javafx.stage.StageStyle;
import org.apache.log4j.Logger;

import static br.com.datarey.context.Context.getBean;
import br.com.datarey.controller.BaseDialogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.stage.Window;

public abstract class BaseDialog<T, C extends BaseDialogController<T>> {

    private static final Logger LOGGER = Logger.getLogger(BaseDialog.class);  
    
    @Inject
    private C baseController;

    protected  StageStyle stageStyle;

    private Dialog<T> dialog;

    private URL source;
    private int width;
    private int height;
    private String title;
    private Object data;

    public BaseDialog(URL source) {
        super();
        this.source = source;
    }

    public BaseDialog(URL source, int width, int height, String title) {
        super();
        this.source = source;
        this.width = width;
        this.height = height;
        this.title = title;
    }

    boolean iniciarFrame() {
        Parent root;
        FXMLLoader fxmlLoader = getBean(FXMLLoader.class);
        try {
            InputStream is = new FileInputStream(source.getPath());
            is = new BufferedInputStream(is);
            if(baseController != null){
                baseController.setDialog(dialog);
                baseController.setData(data);
                fxmlLoader.setController(baseController);

                
            }
            fxmlLoader.setRoot(null);
            root = fxmlLoader.load(is);
            if(stageStyle != null){
                dialog.initStyle(stageStyle);
            }
            dialog.setTitle(title);
            dialog.getDialogPane().setContent(root);
            dialog.getDialogPane().getContent().requestFocus();
            baseController.setInitialFocus();
            return true;
        } catch(IOException e) {
            LOGGER.trace(e);
            return false;
        }
    }

    public void show(Window window) {
        dialog = new Dialog<>();
        dialog.initOwner(window);
        if(iniciarFrame())
            dialog.show();
    }

    public T showAndWait(Window window) {
        dialog = new Dialog<>();
        dialog.initOwner(window);
        if(iniciarFrame())
            return dialog.showAndWait().get();
        return null;
    }
    
    public void show() {
        dialog = new Dialog<>();
        if(iniciarFrame())
            dialog.show();
    }

    public T showAndWait() {
        dialog = new Dialog<>();
        if(iniciarFrame())
            return dialog.showAndWait().get();
        return null;
    }

    public C getBaseController() {
        return baseController;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
