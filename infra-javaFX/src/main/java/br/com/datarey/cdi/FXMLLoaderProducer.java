package br.com.datarey.cdi;

import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class FXMLLoaderProducer {
	@Inject
	Instance<Object> instance;

	@Produces
	public FXMLLoader createLoader() {
		FXMLLoader loader = new FXMLLoader();
		Callback<Class<?>, Object> callback = (param) -> instance.select(param).get();
		loader.setControllerFactory(callback);
		return loader;
	}
}
