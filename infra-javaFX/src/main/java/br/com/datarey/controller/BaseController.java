package br.com.datarey.controller;

import br.com.datarey.util.MessageUtil;
import javafx.stage.Stage;

import javax.inject.Inject;

public abstract class BaseController extends AbstractController {

    @Inject
    private MessageUtil messageUtil;

    private Stage stage;

    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public MessageUtil getMessageUtil() {
        return messageUtil;
    }
}
