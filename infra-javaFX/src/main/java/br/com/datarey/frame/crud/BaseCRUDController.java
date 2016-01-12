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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCRUDController<E extends Entidade, S extends BaseService<E>> extends BaseController{

    @Inject
    private S baseService;

    private Class<?> formClass;

    @FXML
    private TableView<E> table;

    private List<ColumnSearch> columnSearchs = new ArrayList<>();

    private List<TableColumn<E, String>> columns = new ArrayList<>();

    @Override
    @FXML
    public void initialize() {
        super.initialize();
        initColunms();
    }

    @Override
    protected void init() {
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void editColunmsAction(){

    }

    @FXML
    public void editColunmsKey(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            editColunmsAction();
        }
    }

    @FXML
    public void searchKey(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            searchAction();
        }
    }

    @FXML
    public void adicionarActionListener(){
        BaseForm<E> form = Context.getBean(formClass);
        form.newEntity();
    }
    @FXML
    public void adicionarKeyListener(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            adicionarActionListener();
        }
    }



    @FXML
    public void editarActionListener(){
        BaseForm<E> form = Context.getBean(formClass);
        form.show(table.getSelectionModel().getSelectedItem());
    }
    @FXML
    public void editarKeyListener(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            editarActionListener();
        }
    }

    @FXML
    public void searchAction(){
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
                if(item.getGraphic() != null){
                    column = new TableColumn<>();
                    VBox vBox = new VBox();
                    vBox.setAlignment(Pos.CENTER);
                    vBox.getChildren().addAll(new Label(item.getTitle()), item.getGraphic());
                    vBox.setPadding(new Insets(0, 0, 2, 0));
                    column.setGraphic(vBox);
                }else{
                    column = new TableColumn<>(item.getTitle());
                }
                column.setCellValueFactory(item.getCellData());
                column.setPrefWidth(item.getPrefWidth());

                columns.add(column);
            }
        }
        table.getColumns().addAll(columns);
    }

    public void setFormClass(Class<?> formClass) {
        this.formClass = formClass;
    }
}
