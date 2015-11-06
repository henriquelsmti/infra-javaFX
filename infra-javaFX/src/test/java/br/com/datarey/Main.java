package br.com.datarey;


import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Weld weld ;
	private static WeldContainer container;

    @Override
    public void start(Stage primaryStage) throws Exception{
    	Init init = container.instance().select(Init.class).get();
    	init.start(primaryStage);
    }


    public static void main(String[] args) {
    	weld = new Weld();
        container = weld.initialize();
        launch(args);
    }
}
