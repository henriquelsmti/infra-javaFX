package br.com.datarey.controller;

import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import jfxtras.labs.scene.control.BeanPathAdapter;


@Interceptor @Bindable @Priority(Interceptor.Priority.APPLICATION)
public class BaseControllerInterceptor {

	@AroundInvoke
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object auditar(InvocationContext context) throws Exception {
		if(context.getMethod().getModifiers() != Modifier.PUBLIC){
			return context.proceed();
		}
		Object retorno = context.proceed();
		Map<String, BeanPathAdapter>  beanPathAdapters = ((BaseController)context.getTarget()).getBeanPathAdapters();
		Set<String> keys = beanPathAdapters.keySet();
		for(String key : keys){
			beanPathAdapters.get(key).setBean(beanPathAdapters.get(key).getBean());
		}
		
		return retorno;
	}
}
