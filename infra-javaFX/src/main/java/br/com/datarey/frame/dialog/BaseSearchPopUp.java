package br.com.datarey.frame.dialog;

import br.com.datarey.frame.base.BaseDialog;

public abstract class BaseSearchPopUp<T, C extends BaseSearchController<T, ?>> extends BaseDialog<T, C>{

    public BaseSearchPopUp(String title) {
        super(BaseSearchPopUp.class.getResource("baseSearchPopUp.fxml").toString().replace("file:/", ""));
        setHeight(500);
        setWidth(600);
        setTitle(title);
    }
}
