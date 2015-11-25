package br.com.datarey.controller;

import javafx.scene.control.Dialog;

public abstract class BaseDialogController<T> extends AbstractController {

    private Dialog<T> dialog;

    public Dialog<T> getDialog() {
        return this.dialog;
    }

    public void setDialog(Dialog<T> dialog) {
        this.dialog = dialog;
    }
    
    public void setInitialFocus(){}
}
