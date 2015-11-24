package br.com.datarey.frame.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import br.com.datarey.controller.BaseDialogController;
import br.com.datarey.databind.DataBind;
import br.com.datarey.event.AddRegistroEvent;
import br.com.datarey.event.AddRegistroResponceEvent;
import br.com.datarey.service.BaseService;
import br.com.datarey.service.ItemPesquisa;
import br.com.datarey.service.type.Regra;

public abstract class BaseSearchController<T, S extends BaseService<T>> extends BaseDialogController<T> {

    @Inject
    private Event<AddRegistroEvent> addEvent;
    
    @Inject
    protected S baseService;
    
    private String entidadeClassName;
    
    @FXML
    private ChoiceBox<ItemTipoPesquisa> choiceBox;

    @FXML
    private TextField pesquisa;

    @FXML
    @DataBind(mappedBy="list")
    private TableView<T> tableView;
    
    private List<T> list;

    @Override
    protected void init() {
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
        Node can =  getDialog().getDialogPane().lookupButton(cancelarButtonType);
        ok.setDisable(true);

        tableView.setOnKeyPressed(event -> { 
             ok.setDisable(tableView.getSelectionModel().getSelectedItem() == null); 
        });
        tableView.setOnMouseClicked(event -> { 
            ok.setDisable(tableView.getSelectionModel().getSelectedItem() == null); 
        });
        javafx.event.Event.fireEvent(can , new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.LEFT, true, true, true, true));
        
        pesquisa.requestFocus();
    }
    
    
    @FXML
    public void pesquisar(){
        ItemPesquisa item;
        ItemTipoPesquisa itemTipoPesquisa = choiceBox.getSelectionModel().getSelectedItem();
        String propriedade = itemTipoPesquisa.getPropriedade();
        
        if(itemTipoPesquisa.getRegra().equals(Regra.CONTEM)){
            item = new ItemPesquisa(propriedade,  Regra.CONTEM, pesquisa.getText() + "%");
        }else{
            item = new ItemPesquisa(propriedade, 
                    itemTipoPesquisa.getRegra(), pesquisa.getText() + "%");
        }
        list = baseService.pesquisar(Arrays.asList(item));
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

    public ChoiceBox<ItemTipoPesquisa> getChoiceBox() {
        return choiceBox;
    }

    public void setChoiceBox(ChoiceBox<ItemTipoPesquisa> choiceBox) {
        this.choiceBox = choiceBox;
    }


    public void setEntidadeClassName(String entidadeClassName) {
        this.entidadeClassName = entidadeClassName;
    }
    
    

}
