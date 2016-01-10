package br.com.datarey.frame.crud;

import br.com.datarey.context.Context;
import br.com.datarey.controller.BaseController;
import br.com.datarey.model.Entidade;
import br.com.datarey.service.BaseService;
import br.com.datarey.table.ColumnSearch;
import br.com.generic.dao.SearchEntityListBuilder;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCRUDController<E extends Entidade, S extends BaseService<E>, F extends BaseForm> extends BaseController{

    @Inject
    private S baseService;

    @SuppressWarnings("unchecked")
    private final Class<F> formClass = (Class<F>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[2];

    @FXML
    private TableView<E> table;

    private List<ColumnSearch> columnSearchs = new ArrayList<>();

    private ObservableList<TableColumn<E, String>> columns = new SimpleListProperty<>();

    @Override
    protected void init() {
        initColunms();
    }

    @FXML
    private void editColunmsAction(){

    }

    @FXML
    private void editColunmsKey(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            editColunmsAction();
        }
    }

    @FXML
    private void searchKey(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            searchAction();
        }
    }

    @FXML
    private void adicionarActionListener(){
        F form = Context.getBean(formClass);
        form.newEntity();
    }

    private void adicionarKeyListener(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            adicionarActionListener();
        }
    }

    @FXML
    private void searchAction(){
        SearchEntityListBuilder<E> builder = baseService.listEntities();
        for(ColumnSearch item : columnSearchs){
            if(item.getValueSearch() != null){
                if (item.getValueSearch().getClass().equals(String.class)){
                    builder.like(item.getField(), (String) item.getValueSearch());
                }else{
                    builder.eq(item.getField(), item.getValueSearch());
                }
            }
        }
        table.getItems().clear();
        table.getItems().addAll(builder.list());
    }

    protected void addColumnSearch(ColumnSearch columnSearch){
        columnSearchs.add(columnSearch);
    }

    @SuppressWarnings("unchecked")
    private void initColunms(){
        TableColumn<E, String> column;
        for(ColumnSearch item : columnSearchs){
            if(item.isVisible()){
                column = new TableColumn<>(item.getTitle());
                column.setCellValueFactory(item.getCellData());
                if(item.getGraphic() != null)
                    column.setGraphic(item.getGraphic());
                column.setPrefWidth(item.getPrefWidth());
                columns.add(column);
            }
        }
        table.getColumns().addAll(columns);
    }



}
