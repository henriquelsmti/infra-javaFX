package br.com.datarey.frame.crud;

import br.com.datarey.app.BaseReport;
import br.com.datarey.context.Context;
import br.com.datarey.controller.BaseController;
import br.com.datarey.model.Entidade;
import br.com.datarey.service.BaseService;
import br.com.datarey.table.ColumnSearch;
import br.com.datarey.util.MessageType;
import br.com.datarey.util.MessageUtil;
import br.com.datarey.util.ReportUtil;
import br.com.generic.dao.SearchEntityListBuilder;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseCRUDController<E extends Entidade, S extends BaseService<E>> extends BaseController{

    @Inject
    private S baseService;

    @Inject
    MessageUtil messageUtil;

    @Inject
    private SelectColumnPopUp selectColumnPopUp;

    private ReportUtil reportUtil;

    private Class<?> formClass;

    @FXML
    private TableView<E> table;

    @FXML
    private ToolBar toolbar;

    @FXML
    private GridPane gridPane;

    @FXML
    private MenuButton exportatButton;

    @FXML
    protected Label statusLabel;

    private List<ColumnSearch> columnSearchs = new ArrayList<>();

    private List<TableColumn<E, String>> columns = new ArrayList<>();

    @Override
    @FXML
    public void initialize() {
        super.initialize();
        initColunms();
        reportUtil = new ReportUtil(getReport(), getStage());
    }

    @Override
    protected void init() {
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void editColunmsAction(){
        selectColumnPopUp.setData(columns);
        selectColumnPopUp.showAndWait(getStage());
    }

    @FXML
    public void editColunmsKey(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            editColunmsAction();
        }
    }

    @FXML
    public void imprimirActionListener(){
        reportUtil.openPDF(table.getItems(), obterMetadados());
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
                    builder.like(item.getField(), ((String) item.getValueSearch()) + "%");
                }else{
                    builder.eq(item.getField(), item.getValueSearch());
                }
            }
        }
        table.getItems().clear();
        Task<List<E>> task = new Task<List<E>>() {
            @Override
            protected List<E> call() throws Exception {
                return builder.list();
            }

            @Override
            protected void succeeded() {
                table.getItems().addAll(getValue());
            }

            @Override
            protected void failed() {
                messageUtil.showMessage(getException().getMessage(), MessageType.ERROR);
            }
        };

        new Thread(task).start();
    }

    @FXML
    public void exportToPDF(){
        reportUtil.exportToPDF(table.getItems(), obterMetadados());
    }

    @FXML
    public void exportToRTF(){
        reportUtil.exportToRTF(table.getItems(), obterMetadados());
    }

    @FXML
    public void exportToDOCX(){
        reportUtil.exportToDOCX(table.getItems(), obterMetadados());
    }

    @FXML
    public void exportToXLSX(){
        reportUtil.exportToXLSX(table.getItems(), obterMetadados());
    }

    @FXML
    public void exportToPPTX(){
        reportUtil.exportToPPTX(table.getItems(), obterMetadados());
    }

    @FXML
    public void exportToODT(){
        reportUtil.exportToODT(table.getItems(), obterMetadados());
    }

    @FXML
    public void exportToODS(){
        reportUtil.exportToODS(table.getItems(), obterMetadados());
    }

    @FXML
    public void exportToHTML(){
        reportUtil.exportToHTML(table.getItems(), obterMetadados());
    }

    @FXML
    public void exportToTXT(){
        reportUtil.exportToTXT(table.getItems(), obterMetadados());
    }

    protected void addColumnSearch(ColumnSearch columnSearch){
        columnSearchs.add(columnSearch);
    }

    @SuppressWarnings("unchecked")
    private void initColunms(){
        TableColumn<E, String> column;
        for(ColumnSearch item : columnSearchs){
            column = new TableColumn<>();
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            if(item.getGraphic() != null) {
                vBox.getChildren().addAll(new Label(item.getTitle()), item.getGraphic());
            }else{
                vBox.getChildren().addAll(new Label(item.getTitle()));
            }
            vBox.setPadding(new Insets(0, 0, 2, 0));
            column.setGraphic(vBox);

            if(item.getAlignment() != null){
                column.setStyle("-fx-alignment: " + item.getAlignment());
            }
            column.visibleProperty().bindBidirectional(item.visibleProperty());
            column.setCellValueFactory(item.getCellData());
            column.setPrefWidth(item.getPrefWidth());

            columns.add(column);
        }
        table.getColumns().addAll(columns);

    }

    private List<Map<String, Object>> obterMetadados(){
        List<Map<String, Object>> dicionario = getReport().listarDicionario();
        List<Map<String, Object>> metadados = new ArrayList<>();

        for(ColumnSearch columnSearch : columnSearchs){
            if(columnSearch.isVisible()){
                for(Map<String, Object> item : dicionario){
                    if(item.get("campo").equals(columnSearch.getField())){
                        metadados.add(item);
                    }
                }
            }
        }

        return metadados;

    }

    public void setFormClass(Class<?> formClass) {
        this.formClass = formClass;
    }

    protected abstract BaseReport getReport();

    public ToolBar getToolbar() {
        return toolbar;
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
