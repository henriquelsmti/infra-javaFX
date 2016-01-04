package br.com.datarey.test;

import javafx.application.Application;
import javafx.stage.Stage;
import br.com.datarey.context.Context;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Test init = Context.getBean(Test.class);
        init.start(primaryStage);
    }

    public static void main(String[] args) {

        launch(args);
    }
}
