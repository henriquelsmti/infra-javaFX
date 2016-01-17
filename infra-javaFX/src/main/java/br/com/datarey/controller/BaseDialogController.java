package br.com.datarey.controller;

import javafx.scene.control.Dialog;

public abstract class BaseDialogController<T> extends AbstractController {

    private Dialog<T> dialog;

    private Object data;

    public Dialog<T> getDialog() {
        return this.dialog;
    }

    public void setDialog(Dialog<T> dialog) {
        this.dialog = dialog;
    }
    
    public void setInitialFocus(){}

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
