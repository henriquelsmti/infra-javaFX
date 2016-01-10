package br.com.datarey.frame.crud;

import br.com.datarey.frame.base.BaseWindow;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseCRUD<C extends BaseCRUDController> extends BaseWindow{

    @Inject
    private C baseCRUDController;

    private static final Logger LOGGER = Logger.getLogger(BaseCRUD.class);

    private static final String SOURCE = "baseCRUD.fxml";

    public BaseCRUD() {
        super(SOURCE);
    }

    @PostConstruct
    @Override
    protected void init(){
        try {
            stage = new Stage();
            Parent root;
            InputStream is = BaseCRUD.class.getResourceAsStream(SOURCE);
            is = new BufferedInputStream(is);
            fxmlLoader.setController(baseCRUDController);
            root = fxmlLoader.load(is);
            stage.setTitle(getTitle());
            stage.setScene(new Scene(root, getWidth(), getHeight()));
            baseCRUDController.setStage(stage);
        } catch(IOException e) {
            LOGGER.error(e);
        }
    }


}
