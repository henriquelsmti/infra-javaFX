package br.com.datarey.controller;

import javafx.stage.Stage;

public abstract class BaseController extends AbstractController {

    private Stage stage;

    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
