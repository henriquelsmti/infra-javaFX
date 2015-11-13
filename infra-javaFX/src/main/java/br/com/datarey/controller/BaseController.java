package br.com.datarey.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javafx.fxml.FXML;
import jfxtras.labs.scene.control.BeanPathAdapter;
import br.com.datarey.controller.type.ComponentsType;
import br.com.datarey.dataBind.DataBind;
import br.com.datarey.util.UtilDataBind;

public abstract class BaseController {
	
	
	@SuppressWarnings("rawtypes")
	private Map<String, Map<Field, BeanPathAdapter>>  fieldsBean = new HashMap<>();
	private Map<String, Field> fieldsScene = new HashMap<>();
	private Map<Field, Field> fieldsProp = new HashMap<>();
	@FXML
	protected void initialize() {
		//pegando super class por causa do Prox do CDI
		Class<?> clazz = this.getClass().getSuperclass();
		
		initFieldsScene(clazz);
		initFieldsBean(clazz);
		binder();
		init();
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void initFieldsBean(Class<?> clazz){
		Map<Field, BeanPathAdapter> map;
		BeanPathAdapter beanPathAdapter;
		
		Object value;
		for(String fieldSceneName : fieldsScene.keySet()){
			for(Field field : clazz.getDeclaredFields()){
				if(field.getName().equals(UtilDataBind.getFieldsBeanNameFormated(fieldsScene.get(fieldSceneName)))){
					beanPathAdapter = null;
					map = new HashMap<>();
					value = getValue(field);
					if(value == null){
						value = createNewValue(field);
					}
					if(fieldSceneName.contains(".")){
						beanPathAdapter = new BeanPathAdapter(value);
						map.put(field, beanPathAdapter);
						fieldsBean.put(field.getName(), map);
					}else{
						fieldsProp.put(fieldsScene.get(fieldSceneName), field);
					}
						
				}
			}
		}
	}
	
	Object createNewValue(Field field){
		try {
			return field.getType().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	void initFieldsScene(Class<?> clazz){
		for(Field field : clazz.getDeclaredFields()){
			if(field.isAnnotationPresent(DataBind.class)){
				fieldsScene.put(getFieldsBeanName(field), field);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void binder(){
		Set<String> fieldsSceneKeys = fieldsScene.keySet();
		Set<Field> fieldsPropKeys = fieldsProp.keySet();
		Map<Field, BeanPathAdapter> mapFieldsBean;
		BeanPathAdapter adapter;
		Object property;
		String beanName;
		Field fieldScene;
		DataBind dataBind;
		for(String fieldsSceneKey : fieldsSceneKeys){
			if(fieldsSceneKey.contains(".")){
				fieldScene = fieldsScene.get(fieldsSceneKey);
				beanName = UtilDataBind.getFieldsBeanNameFormated(fieldScene);
				mapFieldsBean = fieldsBean.get(beanName);
				adapter = mapFieldsBean.get(mapFieldsBean.keySet().iterator().next());
				dataBind = fieldScene.getDeclaredAnnotationsByType(DataBind.class)[0];
				getComponentsType(fieldScene.getType()).binder(fieldScene, adapter, dataBind, this);
			}
		}
		
		for(Field fieldPropKey : fieldsPropKeys){
			property = getValue(fieldsProp.get(fieldPropKey));
			if(property != null)
				getComponentsType(fieldPropKey.getType()).binder(fieldPropKey, property, this);
		}
	}
	
	ComponentsType getComponentsType(Class<?> clazz){
		for(ComponentsType componentsType : ComponentsType .values()){
			if(componentsType.getClazz().equals(clazz)){
				return componentsType;
			}
		}
		return null;
	}
	
	private String getFieldsBeanName(Field field){
		return ((DataBind)field.getAnnotationsByType(DataBind.class)[0]).mappedBy();
	}
	
	Object getValue(Field field){
		try {
			field.setAccessible(true);
			return field.get(this);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void init(){}

	@SuppressWarnings("rawtypes")
	Map<String, Map<Field, BeanPathAdapter>> getFieldsBean() {
		return this.fieldsBean;
	}


	Map<Field, Field> getFieldsProp() {
		return this.fieldsProp;
	}
	

	
}
