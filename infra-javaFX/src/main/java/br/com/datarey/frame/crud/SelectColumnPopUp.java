package br.com.datarey.frame.crud;

import br.com.datarey.frame.base.BaseDialog;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.stage.Window;

import java.util.List;

/**
 * Created by henrique.luiz on 15/01/2016.
 */
public class SelectColumnPopUp extends BaseDialog<Object , SelectColumnPopUpController> {

    private List<TableColumn<?, ?>> columns;

    public SelectColumnPopUp() {
        super(SelectColumnPopUp.class.getResource("selectColumnPopUp.fxml"), 500, 500, "Seleção de colunas");
    }

    public void setColumns(List<TableColumn<?, ?>> columns) {
        ((SelectColumnPopUpController)getBaseController()).setColumns(columns);
    }

    @Override
    public void show() {
        super.show();
        ((SelectColumnPopUpController)getBaseController()).setColumns(columns);
    }

    @Override
    public Object showAndWait() {
        Object o = super.showAndWait();
        ((SelectColumnPopUpController)getBaseController()).setColumns(columns);
        return o;
    }

    @Override
    public void show(Window window) {
        super.show(window);
        ((SelectColumnPopUpController)getBaseController()).setColumns(columns);
    }


    @Override
    public Object showAndWait(Window window) {
        Object o =  super.showAndWait(window);
        ((SelectColumnPopUpController)getBaseController()).setColumns(columns);
        return o;
    }
}
