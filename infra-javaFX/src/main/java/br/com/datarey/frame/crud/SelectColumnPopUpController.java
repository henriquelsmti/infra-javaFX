package br.com.datarey.frame.crud;

import br.com.datarey.controller.BaseDialogController;
import br.com.datarey.frame.base.BaseDialog;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<Integer, Boolean> estadoAnterior;

    @Override
    protected void init() {
        super.init();
        iniciarButtonTypes();
        tableView.getItems().clear();
        tableView.getItems().addAll((Collection<? extends TableColumn<?, ?>>) getData());
        iniciarEstadoAnterior();
        tableView.setEditable(true);
        coluna.setCellValueFactory( (value) -> {

            VBox vBox = (VBox) value.getValue().getGraphic();

            for(Node node : vBox.getChildren()){
                if(node instanceof Label){
                    return new SimpleStringProperty(((Label)node).getText());
                }
            }

            return null;

        });

        coluna.setEditable(false);

        check.setCellValueFactory( (value) -> {

           return value.getValue().visibleProperty();

        });
        check.setEditable(true);

        check.setCellFactory(CheckBoxTableCell.<TableColumn<?, ?>>forTableColumn(check));
    }


    private void iniciarEstadoAnterior(){
        estadoAnterior = new HashMap<>();

        for(int i = 0; i < tableView.getItems().size(); i++){
            estadoAnterior.put(i, tableView.getItems().get(i).isVisible());
        }
    }

    private void voltarEstado(){
        for(int i = 0; i < tableView.getItems().size(); i++){
            tableView.getItems().get(i).setVisible(estadoAnterior.get(i));
        }
    }



    private void iniciarButtonTypes(){
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelarButtonType = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        getDialog().getDialogPane().getButtonTypes().addAll(cancelarButtonType, okButtonType);

        getDialog().setResultConverter(dialogButton -> {
            if (dialogButton == cancelarButtonType) {
                voltarEstado();
            }
            return new SimpleObjectProperty<>();
        });

    }
}
