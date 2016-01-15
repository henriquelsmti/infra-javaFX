package br.com.datarey.frame.crud;

import br.com.datarey.controller.BaseDialogController;
import br.com.datarey.frame.base.BaseDialog;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;

/**
 * Created by henrique.luiz on 15/01/2016.
 */
public class SelectColumnPopUpController extends BaseDialogController<Object> {

    @FXML
    private TableView<TableColumn<?, ?>> tableView;

    @FXML
    private TableColumn<TableColumn<?, ?>, String> coluna;

    @FXML
    private TableColumn<TableColumn<?, ?>, Boolean> check;

    @Override
    protected void init() {
        super.init();
        iniciarButtonTypes();

        coluna.setCellValueFactory( (value) -> {

            VBox vBox = (VBox) value.getValue().getGraphic();

            for(Node node : vBox.getChildren()){
                if(node instanceof Label){
                    return new SimpleStringProperty(((Label)node).getText());
                }
            }

            return null;

        });

        check.setCellValueFactory( (value) -> {

           return value.getValue().visibleProperty();

        });

        check.setCellFactory(CheckBoxTableCell.<TableColumn<?, ?>>forTableColumn(check));
    }

    public void setColumns(List<TableColumn<?, ?>> columns){
        tableView.getItems().clear();
        tableView.getItems().addAll(columns);
    }

    private void iniciarButtonTypes(){
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelarButtonType = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialog().getDialogPane().getButtonTypes().addAll(cancelarButtonType, okButtonType);

    }
}
