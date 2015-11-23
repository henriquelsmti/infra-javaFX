package br.com.datarey.frame.dialog;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import javafx.fxml.FXML;
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
import br.com.datarey.service.BaseService;
import br.com.datarey.service.ItemPesquisa;
import br.com.datarey.service.type.Regra;

public class BaseSearchController<T> extends BaseDialogController<T> {

    @Inject
    private Event<AddRegistroEvent> addEvent;
    
    private String entidadeClassName;
    
    @FXML
    private ChoiceBox<ItemTipoPesquisa> choiceBox;

    @FXML
    private TextField pesquisa;

    @FXML
    @DataBind(mappedBy="list")
    private TableView<T> tableView;
    
    private BaseService<T> baseService;
    
    private List<T> list;

    @Override
    protected void init() {
        iniciarButtonTypes();
    }
    
    
    private void iniciarButtonTypes(){
        ButtonType okButtonType = new ButtonType("Pesquisar", ButtonData.OK_DONE);
        ButtonType cancelarButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        getDialog().getDialogPane().getButtonTypes().addAll(okButtonType, cancelarButtonType);
        getDialog().setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                return tableView.getSelectionModel().getSelectedItem();
            }
            return null;
        });
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
        if(!list.isEmpty()){
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
        addEvent.fire(new AddRegistroEvent(entidadeClassName));
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

    public void setBaseService(BaseService<T> baseService) {
        this.baseService = baseService;
    }

    public void setEntidadeClassName(String entidadeClassName) {
        this.entidadeClassName = entidadeClassName;
    }
    
    

}
