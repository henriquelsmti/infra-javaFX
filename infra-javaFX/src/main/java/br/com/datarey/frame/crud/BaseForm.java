package br.com.datarey.frame.crud;

import br.com.datarey.context.Context;
import br.com.datarey.frame.base.BaseWindow;
import br.com.datarey.model.Entidade;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class BaseForm<E extends Entidade> extends BaseWindow{

    private static final String SOURCE = "baseForm.fxml";

    private static final Logger LOGGER = Logger.getLogger(BaseCRUD.class);

    private String sourceForm;

    private BaseFormController formController;

    public BaseForm(String sourceForm) {
        super(SOURCE);
        this.sourceForm = sourceForm;
    }

    @PostConstruct
    protected void postConstruct(){
        try {
            stage = new Stage();
            Parent root;
            InputStream is = this.getClass().getResourceAsStream(sourceForm);
            is = new BufferedInputStream(is);
            root = fxmlLoader.load(is);
            formController = fxmlLoader.getController();
            formController.setStage(stage);
            fxmlLoader = Context.getBean(FXMLLoader.class);
            fxmlLoader.setController(formController);
            is = new BufferedInputStream(BaseForm.class.getResourceAsStream(SOURCE));
            BorderPane borderPane = fxmlLoader.load(is);
            formController.setContent(root);
            stage.setTitle(getTitle());
            stage.setScene(new Scene(borderPane, getWidth(), getHeight()));
        } catch(IOException e) {
            LOGGER.trace(e);
            e.printStackTrace();
        }
    }

    public void newEntity(){
        formController.newEntity();
        super.show();
    }

    @Override
    public void show() {
        newEntity();
    }

    public void show(E entity){
        formController.show(entity);
        super.show();
    }
}
