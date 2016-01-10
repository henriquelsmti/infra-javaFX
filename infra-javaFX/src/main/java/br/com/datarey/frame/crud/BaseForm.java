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

public class BaseForm<E extends Entidade> extends BaseWindow{

    private static final String SOURCE = "baseCRUD.fxml";

    private static final Logger LOGGER = Logger.getLogger(BaseCRUD.class);

    private String sourceForm;

    private BaseFormController formController;

    public BaseForm(String sourceForm) {
        super(SOURCE);
        this.sourceForm = sourceForm;
    }

    @PostConstruct
    @Override
    protected void init(){
        try {
            stage = new Stage();
            Parent root;
            InputStream is = this.getClass().getResourceAsStream(sourceForm);
            is = new BufferedInputStream(is);
            formController = fxmlLoader.getController();
            root = fxmlLoader.load(is);
            fxmlLoader = Context.getBean(FXMLLoader.class);
            fxmlLoader.setController(formController);
            BorderPane borderPane = fxmlLoader.load(BaseForm.class.getResourceAsStream(SOURCE));
            formController.setContent(root);


            stage.setTitle(getTitle());
            stage.setScene(new Scene(borderPane, getWidth(), getHeight()));
            formController.setStage(stage);
        } catch(IOException e) {
            LOGGER.error(e);
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
