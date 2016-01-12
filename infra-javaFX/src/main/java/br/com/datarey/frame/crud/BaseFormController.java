package br.com.datarey.frame.crud;

import br.com.datarey.controller.BaseController;
import br.com.datarey.model.Entidade;
import br.com.datarey.model.type.EntidadeEstado;
import br.com.datarey.service.BaseService;
import br.com.datarey.table.ColumnSearch;
import br.com.generic.dao.SearchEntityListBuilder;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFormController<E extends Entidade, S extends BaseService<E>> extends BaseController {

    @Inject
    private S baseService;

    @FXML
    private Button salvarButton;

    @FXML
    private Button cancelarButton;

    @FXML
    private Button ativarInativarButton;

    @FXML
    private Button recarregarButton;

    @FXML
    private ImageView iconInativarAtivar;

    @FXML
    private BorderPane borderPane;

    private Image imageInativar = new Image("/br/com/datarey/img/icon_active.png");

    private Image imageAtivar =new Image("/br/com/datarey/img/icon_inactive.png");

    @FXML
    public void recarregarActionListener() {
        setEntity(baseService.findEntityById(getEntity().getId()));
    }

    @FXML
    public void recarregarKeyListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            recarregarActionListener();
        }
    }

    @FXML
    public void ativarInativarActionListener() {
        if (getEntity().getEstado() == EntidadeEstado.ATIVO) {
            setEntity(baseService.inactivate(getEntity()));
            iconInativarAtivar.setImage(imageAtivar);
        } else {
            setEntity(baseService.activate(getEntity()));
            iconInativarAtivar.setImage(imageInativar);
        }
    }

    @FXML
    public void ativarInativarKeyListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            ativarInativarActionListener();
        }
    }

    @FXML
    public void cancelarActionListener() {
        getStage().close();
    }

    @FXML
    public void cancelarKeyListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            cancelarActionListener();
        }
    }

    @FXML
    public void salvarActionListener() {
        setEntity(baseService.save(getEntity()));
    }

    @FXML
    public void salvarKeyListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            salvarActionListener();
        }
    }

    @Override
    public void setStage(Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode().equals(KeyCode.F1)) {
                salvarActionListener();
            }else if(event.getCode().equals(KeyCode.F2)){
                cancelarActionListener();
            }else if(event.getCode().equals(KeyCode.F3)){
                ativarInativarActionListener();
            }else if(event.getCode().equals(KeyCode.F5)){
                recarregarActionListener();
            }
        });
        super.setStage(stage);
    }

    public void verificarBotoes(){
        if(getEntity().getId() == null || getEntity().getId() == 0){
            recarregarButton.setDisable(true);
            ativarInativarButton.setDisable(true);
            iconInativarAtivar.setImage(imageAtivar);
        }else{
            recarregarButton.setDisable(false);
            ativarInativarButton.setDisable(false);
            if (getEntity().getEstado() == EntidadeEstado.ATIVO){
                iconInativarAtivar.setImage(imageInativar);
            }else{
                iconInativarAtivar.setImage(imageAtivar);
            }
        }
    }

    public void newEntity(){
        setEntity(getService().createModel());
    }

    public void show(E entity){
        setEntity(entity);
    }

    public void setContent(Parent node){
        borderPane.setCenter(node);
    }

    public Button getCancelarButton() {
        return cancelarButton;
    }

    public Button getAtivarInativarButton() {
        return ativarInativarButton;
    }

    public Button getRecarregarButton() {
        return recarregarButton;
    }

    public Button getSalvarButton() {
        return salvarButton;
    }

    public abstract E getEntity();

    public abstract void setEntity(E entity);

    public S getService() {
        return baseService;
    }
}
