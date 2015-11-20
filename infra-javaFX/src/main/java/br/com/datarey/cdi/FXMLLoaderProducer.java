package br.com.datarey.cdi;

import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import javax.enterprise.inject.Produces;

import br.com.datarey.context.Context;

public class FXMLLoaderProducer {
	

	@Produces
	public FXMLLoader createLoader() {
		FXMLLoader loader = new FXMLLoader();
		
		Callback<Class<?>, Object> callback = (param) -> Context.getBean(param);
		loader.setControllerFactory(callback);
		return loader;
	}
}
