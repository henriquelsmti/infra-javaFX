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

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFormController<E extends Entidade, S extends BaseService<E>> extends BaseController {

    @Inject
    private S baseService;

    protected E entity;

    @FXML
    private Button salvarButton;

    @FXML
    private Button cancelarButton;

    @FXML
    private Button inativarAtivarButton;

    @FXML
    private Button recarregarButton;

    @FXML
    private ImageView iconInativarAtivar;

    @FXML
    private BorderPane borderPane;

    private Image imageInativar = new Image("");

    private Image imageAtivar = new Image("");

    @FXML
    protected void recarregarActionListener() {
        entity = baseService.findEntityById(entity.getId());
    }

    @FXML
    protected void recarregarKeyListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            recarregarActionListener();
        }
    }

    @FXML
    protected void inativarAtivarActionListener() {
        if (entity.getEstado() == EntidadeEstado.ATIVO) {
            entity = baseService.inactivate(entity);
            iconInativarAtivar.setImage(imageAtivar);
        } else {
            entity = baseService.activate(entity);
            iconInativarAtivar.setImage(imageInativar);
        }
    }

    @FXML
    protected void inativarAtivarKeyListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            inativarAtivarActionListener();
        }
    }

    @FXML
    protected void cancelarAtivarActionListener() {
        getStage().close();
    }

    @FXML
    protected void cancelarAtivarKeyListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            cancelarAtivarActionListener();
        }
    }

    @FXML
    protected void salvarAtivarActionListener() {
        entity = baseService.save(entity);
    }

    @FXML
    protected void salvarAtivarKeyListener(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

        }
    }

    @Override
    protected void init() {
        super.init();

        getStage().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode().equals(KeyCode.F1)) {
                salvarAtivarActionListener();
            }else if(event.getCode().equals(KeyCode.F2)){
                cancelarAtivarActionListener();
            }else if(event.getCode().equals(KeyCode.F3)){
                inativarAtivarActionListener();
            }else if(event.getCode().equals(KeyCode.F5)){
                recarregarActionListener();
            }
        });


    }

    public void newEntity(){
        entity = getService().createModel();
    }

    public void show(E entity){
        this.entity = entity;
    }

    public void setContent(Parent node){
        borderPane.setCenter(node);
    }

    public Button getCancelarButton() {
        return cancelarButton;
    }

    public Button getInativarAtivarButton() {
        return inativarAtivarButton;
    }

    public Button getRecarregarButton() {
        return recarregarButton;
    }

    public Button getSalvarButton() {
        return salvarButton;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public S getService() {
        return baseService;
    }
}
