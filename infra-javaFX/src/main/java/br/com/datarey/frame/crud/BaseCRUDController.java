package br.com.datarey.frame.crud;

import br.com.datarey.app.BaseReport;
import br.com.datarey.context.Context;
import br.com.datarey.controller.BaseController;
import br.com.datarey.model.Entidade;
import br.com.datarey.service.BaseService;
import br.com.datarey.table.ColumnSearch;
import br.com.datarey.util.MessageType;
import br.com.datarey.util.MessageUtil;
import br.com.generic.dao.SearchEntityListBuilder;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLObjectElement;
import netscape.javascript.JSObject;

import javax.inject.Inject;
import javax.swing.text.html.HTMLDocument;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCRUDController<E extends Entidade, S extends BaseService<E>> extends BaseController{

    @Inject
    private S baseService;

    @Inject
    MessageUtil messageUtil;

    @Inject
    private SelectColumnPopUp selectColumnPopUp;

    private Class<?> formClass;

    @FXML
    private TableView<E> table;

    @FXML
    protected Label statusLabel;

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
        ByteArrayOutputStream stream = getReport().exportToPDF(getReport().gerarRelatorio(table.getItems(), getReport().listarDicionario()));

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.getLoadWorker().stateProperty().addListener(( ov,  oldState, newState) ->{

            if (newState == Worker.State.SUCCEEDED) {
                JSObject document = (JSObject) webEngine.executeScript("document");
                JSObject a = (JSObject) document.getMember("abrir");
                document.call("abrir", stream.toByteArray());
            }
        });

        webEngine.load(getClass().getResource("/br/com/datarey/PDFView/web/viewer.html").toString());

        Stage stage = new Stage();

        stage.setScene(new Scene(webView, 800, 1200));
        stage.show();


        java.io.File file = new java.io.File("C:\\log\\arquivo.pdf");
        FileOutputStream in = null;
        try {
            in = new FileOutputStream(file);
            in.write(stream.toByteArray());
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
                    builder.like(item.getField(), ((String) item.getValueSearch() + "%"));
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
            column.setVisible(item.isVisible());
            column.setCellValueFactory(item.getCellData());
            column.setPrefWidth(item.getPrefWidth());

            columns.add(column);
        }
        table.getColumns().addAll(columns);

    }

    public void setFormClass(Class<?> formClass) {
        this.formClass = formClass;
    }

    protected abstract BaseReport getReport();
}
