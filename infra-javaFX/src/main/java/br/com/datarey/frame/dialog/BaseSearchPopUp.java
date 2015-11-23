package br.com.datarey.frame.dialog;

import br.com.datarey.frame.base.BaseDialog;
import br.com.datarey.service.BaseService;

public abstract class BaseSearchPopUp<T> extends BaseDialog<T>{

    public BaseSearchPopUp(String title) {
        super(BaseSearchPopUp.class.getResource("baseSearchPopUp.fxml").toString().replace("file:/", ""));
        setHeight(500);
        setWidth(600);
        setTitle(title);
    }

    protected abstract BaseService<T> getBaseService();
}
