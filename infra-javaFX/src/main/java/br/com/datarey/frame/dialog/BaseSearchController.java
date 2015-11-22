package br.com.datarey.frame.dialog;

import java.util.Arrays;
import java.util.List;

import br.com.datarey.controller.BaseDialogController;
import br.com.datarey.dataBind.DataBind;
import br.com.datarey.service.BaseService;
import br.com.datarey.service.ItemPesquisa;
import br.com.datarey.service.type.Regra;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class BaseSearchController<T> extends BaseDialogController<T> {

    @FXML
    private ChoiceBox<ItemTipoPesquisa> choiceBox;

    @FXML
    private TextField pesquisa;

    @FXML
    private Button pesquisarButton;

    @FXML
    @DataBind(mappedBy="list")
    private TableView<T> tableView;
    
    private BaseService<T> baseService;
    
    @SuppressWarnings("unused")
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
    }
    
    @FXML
    public void pesquisarKeyDown(KeyEvent event){
        if(event.equals(KeyCode.ENTER)){
            pesquisar();
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

    public void setBaseService(BaseService<T> baseService) {
        this.baseService = baseService;
    }
    
    

}
