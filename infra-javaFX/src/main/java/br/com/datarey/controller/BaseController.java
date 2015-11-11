package br.com.datarey.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import jfxtras.labs.scene.control.BeanPathAdapter;

@Bindable
public abstract class BaseController {
	
	@SuppressWarnings("rawtypes")
	private Map<Object, BeanPathAdapter>  beanPathAdapters = new HashMap<>();
	
	@FXML
	private final void initialize() {
		Class<?> clazz = this.getClass();
		initBeanPathAdapters(clazz);
		init();
	}
	
	private void initBeanPathAdapters(Class<?> clazz){
		Object value;
		for(Field field : clazz.getFields()){
			value = getValue(field);
			if(field.isAnnotationPresent(br.com.datarey.dataBind.Bindable.class)){
				beanPathAdapters.put(
						field.getAnnotation(br.com.datarey.dataBind.Bindable.class), 
						(BeanPathAdapter<?>) value);
			}
		}
	}
	
	private Object getValue(Field field){
		try {
			return field.get(this);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void init(){}

	@SuppressWarnings("rawtypes")
	Map<String, BeanPathAdapter> getBeanPathAdapters() {
		return this.beanPathAdapters;
	}
	
	
}
