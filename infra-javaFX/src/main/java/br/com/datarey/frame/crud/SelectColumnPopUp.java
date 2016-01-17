package br.com.datarey.frame.crud;

import br.com.datarey.frame.base.BaseDialog;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.List;

/**
 * Created by henrique.luiz on 15/01/2016.
 */
public class SelectColumnPopUp extends BaseDialog<Object , SelectColumnPopUpController> {


    public SelectColumnPopUp() {
        super(SelectColumnPopUp.class.getResource("selectColumnPopUp.fxml"), 500, 510, "Seleção de colunas");
        stageStyle = StageStyle.UNDECORATED;
    }

}
