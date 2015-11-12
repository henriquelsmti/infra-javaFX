package br.com.datarey.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.datarey.controller.type.ComponentsType;
import br.com.datarey.dataBind.Bindable;
import br.com.datarey.dataBind.DataBind;
import br.com.datarey.util.UtilDataBind;
import javafx.fxml.FXML;
import jfxtras.labs.scene.control.BeanPathAdapter;

@Bind
public abstract class BaseController {
	
	@SuppressWarnings("rawtypes")
	private Map<String, Map<Field, BeanPathAdapter>>  fieldsBean = new HashMap<>();
	private Map<String, Field> fieldsScene = new HashMap<>();
	@FXML
	private final void initialize() {
		Class<?> clazz = this.getClass();
		initFieldsBean(clazz);
		initFieldsScene(clazz);
		binder();
		init();
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private void initFieldsBean(Class<?> clazz){
		Map<Field, BeanPathAdapter> map;
		BeanPathAdapter beanPathAdapter;
		for(Field field : clazz.getFields()){
			if(field.isAnnotationPresent(Bindable.class)){
				map = new HashMap<>();
				beanPathAdapter = new BeanPathAdapter(getValue(field));
				map.put(field, beanPathAdapter);
				fieldsBean.put(field.getName(), map);
			}
		}
	}
	
	private void initFieldsScene(Class<?> clazz){
		for(Field field : clazz.getFields()){
			if(field.isAnnotationPresent(DataBind.class)){
				fieldsScene.put(getFieldsBeanName(field), field);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void binder(){
		Set<String> fieldsSceneKeys = fieldsScene.keySet();
		Map<Field, BeanPathAdapter> mapFieldsBean;
		BeanPathAdapter adapter;
		String beanName;
		Field fieldScene;
		DataBind dataBind;
		for(String fieldsSceneKey : fieldsSceneKeys){
			fieldScene = fieldsScene.get(fieldsSceneKey);
			beanName = UtilDataBind.getFieldsBeanNameFormated(fieldScene);
			mapFieldsBean = fieldsBean.get(beanName);
			adapter = mapFieldsBean.get(mapFieldsBean.keySet().iterator().next());
			dataBind = fieldScene.getDeclaredAnnotationsByType(DataBind.class)[0];
			getComponentsType(fieldScene.getType()).binder(fieldScene, adapter, dataBind, this);
		}
	}
	
	private ComponentsType getComponentsType(Class<?> clazz){
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
	
	private Object getValue(Field field){
		try {
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
	
	
}
