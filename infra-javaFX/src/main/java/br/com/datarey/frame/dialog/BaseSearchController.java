package br.com.datarey.frame.dialog;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.com.datarey.controller.BaseDialogController;
import br.com.datarey.databind.DataBind;
import br.com.datarey.event.AddRegistroEvent;
import br.com.datarey.event.AddRegistroResponceEvent;
import br.com.datarey.frame.Predicate;
import br.com.datarey.service.BaseService;
import br.com.generic.dao.SearchEntityListBuilder;

public abstract class BaseSearchController<T, S extends BaseService<T>> extends BaseDialogController<T> {

    @Inject
    private Event<AddRegistroEvent> addEvent;
    
    @Inject
    protected S baseService;
    
    private String entidadeClassName;
    
    @FXML
    private ComboBox<Predicate> choiceBox;

    @FXML
    private TextField pesquisa;

    @FXML
    @DataBind(mappedBy="list")
    private TableView<T> tableView;
    
    private List<T> list;
    
    @Override
    protected void init() {
        super.init();
        iniciarButtonTypes();
    }
    
    private void iniciarButtonTypes(){
        ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancelarButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        getDialog().getDialogPane().getButtonTypes().addAll(cancelarButtonType, okButtonType);
        getDialog().setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return tableView.getSelectionModel().getSelectedItem();
            }
            return null;
        });
        Node ok =  getDialog().getDialogPane().lookupButton(okButtonType);
        ok.setDisable(true);

        tableView.setOnKeyPressed(event -> { 
             ok.setDisable(tableView.getSelectionModel().getSelectedItem() == null); 
        });
        tableView.setOnMouseClicked(event -> { 
            ok.setDisable(tableView.getSelectionModel().getSelectedItem() == null); 
        });
        
    }
    
    
    @FXML
    public void pesquisar(){
        Predicate predicate = choiceBox.getSelectionModel().getSelectedItem();
        
        SearchEntityListBuilder<T> builder = baseService.listEntities();
        
        if(predicate == Predicate.LIKE){
            builder = predicate.addPredicate(builder, pesquisa.getText() + "%");
        }else{
            builder = predicate.addPredicate(builder, pesquisa.getText() + "%");
        }
        list = builder.list();
        if(list != null && !list.isEmpty()){
            tableView.requestFocus();
        }
    }
    
    @FXML
    public void pesquisarKeyDown(KeyEvent event){
        if(event.equals(KeyCode.ENTER)){
            pesquisar();
        }
    }
    
    @FXML
    public void addKeyDown(KeyEvent event){
        if(event.equals(KeyCode.ENTER)){
            add();
        }
    }
    
    @FXML
    public void add(){
        addEvent.fire(new AddRegistroEvent(entidadeClassName, this));
    }
    
    @SuppressWarnings("unchecked")
    public void onAddRegistroEvent(@Observes AddRegistroResponceEvent event){
        if(event.getTarget() == this){
            list = list.isEmpty()? new ArrayList<>() : list;
            list.add((T)event.getRegistro());
            tableView.getItems().clear();
            tableView.getItems().addAll(list);
            tableView.getSelectionModel().select((T)event.getRegistro());
        }
        
    }

    public TableView<T> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<T> tableView) {
        this.tableView = tableView;
    }

    public ComboBox<Predicate> getChoiceBox() {
        return choiceBox;
    }

    public void setChoiceBox(ComboBox<Predicate> choiceBox) {
        this.choiceBox = choiceBox;
    }


    public void setEntidadeClassName(String entidadeClassName) {
        this.entidadeClassName = entidadeClassName;
    }
    
    @Override
    public void setInitialFocus() {
        pesquisa.requestFocus();
    }
    
    public S getBaseService() {
        return baseService;
    }


}
