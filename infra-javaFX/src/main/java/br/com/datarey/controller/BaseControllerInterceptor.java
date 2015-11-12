package br.com.datarey.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import jfxtras.labs.scene.control.BeanPathAdapter;


@Interceptor @Bind @Priority(Interceptor.Priority.APPLICATION)
public class BaseControllerInterceptor {

	@AroundInvoke
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object auditar(InvocationContext context) throws Exception {
		if(context.getMethod().getModifiers() != Modifier.PUBLIC){
			return context.proceed();
		}
		Object retorno = context.proceed();
		BaseController controller = (BaseController)context.getTarget();
		Map<String, Map<Field, BeanPathAdapter>>  fieldsBean = controller.getFieldsBean();
		Map<Field, BeanPathAdapter> bind;
		Field field;
		BeanPathAdapter adapter;
		Object value;
		Set<String> keys = fieldsBean.keySet();
		for(String key : keys){
			bind = fieldsBean.get(key);
			field = bind.keySet().iterator().next();
			adapter = bind.get(field);
			value = field.get(controller);
			adapter.setBean(value);
			
		}
		
		return retorno;
	}
}
