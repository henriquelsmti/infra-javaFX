package br.com.datarey.controller;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.datarey.databind.Bindable;
import jfxtras.labs.scene.control.BeanPathAdapter;

@Interceptor
@Bindable
@Priority(Interceptor.Priority.APPLICATION)
public class BaseControllerInterceptor {

    @AroundInvoke
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object auditar(InvocationContext context) throws Exception{
        AbstractController controller = (AbstractController) context.getTarget();
        Map<Field, Field> fieldsProp = controller.getFieldsProp();
        Set<Field> fieldsPropKeys = fieldsProp.keySet();

        viewToControler(fieldsProp, fieldsPropKeys, controller);
        Object retorno = context.proceed();

        controlerToView(fieldsProp, fieldsPropKeys, controller);

        return retorno;
    }

    private void viewToControler(Map<Field, Field> fieldsProp , Set<Field> fieldsPropKeys, AbstractController controller) throws IllegalAccessException {
        for (Field fieldPropKey : fieldsPropKeys) {
            controller.getComponentsType(fieldPropKey.getType()).voewToControler(fieldPropKey,
                    fieldsProp.get(fieldPropKey), controller);
        }

        Map<String, Map<Field, BeanPathAdapter>> fieldsBean = controller.getFieldsBean();
        Map<Field, BeanPathAdapter> bind;
        Field field;
        BeanPathAdapter adapter;
        Object value;
        Object property;
        Set<String> keys = fieldsBean.keySet();
        for (String key : keys) {
            bind = fieldsBean.get(key);
            field = bind.keySet().iterator().next();
            value = field.get(controller);
            adapter = bind.get(field);
            if(value != null && adapter == null){
                adapter = new BeanPathAdapter(value);
                controller.binderListFieldsScene();
                bind.put(field, adapter);
            }
        }
    }

    private void controlerToView(Map<Field, Field> fieldsProp , Set<Field> fieldsPropKeys, AbstractController controller) throws IllegalAccessException {
        Map<String, Map<Field, BeanPathAdapter>> fieldsBean = controller.getFieldsBean();
        Map<Field, BeanPathAdapter> bind;
        Field field;
        BeanPathAdapter adapter;
        Object value;
        Object property;
        Set<String> keys = fieldsBean.keySet();
        for (String key : keys) {
            bind = fieldsBean.get(key);
            field = bind.keySet().iterator().next();
            value = field.get(controller);
            adapter = bind.get(field);
            if(value != null && adapter != null){
                adapter.setBean(value);
            }else if(value != null && adapter == null){
                adapter = new BeanPathAdapter(value);
                bind.put(field, adapter);
                controller.binderListFieldsScene();
            }

        }
        for (Field fieldPropKey : fieldsPropKeys) {
            property = controller.getValue(fieldsProp.get(fieldPropKey));
            if (property != null)
                controller.getComponentsType(fieldPropKey.getType()).binder(fieldPropKey, property, controller);
        }
    }

    static Class<?> getRealClass(Class<?> clazz) {

        return clazz.getClass().getSuperclass();
    }
}
