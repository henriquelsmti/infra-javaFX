package br.com.datarey.frame.crud;

import br.com.datarey.frame.base.BaseWindow;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class BaseCRUD<C extends BaseCRUDController> extends BaseWindow {

    @Inject
    private C baseCRUDController;

    private static final Logger LOGGER = Logger.getLogger(BaseCRUD.class);

    private static final URL SOURCE = BaseCRUD.class.getResource("baseCRUD.fxml");

    private final Class<?> formClass;

    public BaseCRUD(Class<?> formClass) {
        super(SOURCE);
        this.formClass = formClass;
    }

    @PostConstruct
    protected void postConstruct() {
        try {
            stage = new Stage();
            Parent root;
            InputStream is =  new FileInputStream(SOURCE.getPath());
            is = new BufferedInputStream(is);
            fxmlLoader.setController(baseCRUDController);
            root = fxmlLoader.load(is);
            stage.setTitle(getTitle());
            stage.setScene(new Scene(root, getWidth(), getHeight()));
            baseCRUDController.setFormClass(formClass);
            baseCRUDController.setStage(stage);
        } catch (IOException e) {
            LOGGER.trace(e);
        }
    }


}
