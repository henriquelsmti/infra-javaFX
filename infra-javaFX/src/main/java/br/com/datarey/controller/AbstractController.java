package br.com.datarey.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.datarey.controller.exeption.ImpossivelObterNovaInstanciaException;
import br.com.datarey.controller.exeption.ImpossivelObterValorException;
import br.com.datarey.controller.type.ComponentsType;
import br.com.datarey.databind.Bindable;
import br.com.datarey.databind.DataBind;
import br.com.datarey.util.UtilDataBind;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jfxtras.labs.scene.control.BeanPathAdapter;

@Bindable
public abstract class AbstractController {

    @SuppressWarnings("rawtypes")
    private Map<String, Map<Field, BeanPathAdapter>> fieldsBean = new HashMap<>();
    private List<Map<String, Field>> listFieldsScene = new ArrayList<>();
    private Map<Field, Field> fieldsProp = new HashMap<>();

    private Map<Integer, Parent> navigator = new HashMap<>();

    @FXML
    protected void initialize() {
        // pegando super class por causa do Prox do CDI
        Class<?> clazz = this.getClass().getSuperclass();

        initFieldsScene(clazz);
        initFieldsBean(clazz);
        binder();
        init();
    }

    private void initFieldsBean(Class<?> clazz) {

        for (Map<String, Field> fieldsScene : listFieldsScene) {
            for (String fieldSceneName : fieldsScene.keySet()) {
                forFields(clazz, fieldsScene, fieldSceneName);
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void forFields(Class<?> clazz, Map<String, Field> fieldsScene, String fieldSceneName) {
        Map<Field, BeanPathAdapter> map;
        BeanPathAdapter beanPathAdapter;
        Object value;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equals(UtilDataBind.getFieldsBeanNameFormated(fieldsScene.get(fieldSceneName)))) {
                beanPathAdapter = null;
                value = getValue(field);
                if (value == null) {
                    value = createNewValue(field);
                }
                if (fieldSceneName.contains(".")) {
                    map = new HashMap<>();
                    beanPathAdapter = new BeanPathAdapter(value);
                    map.put(field, beanPathAdapter);
                    fieldsBean.put(field.getName(), map);
                } else {
                    fieldsProp.put(fieldsScene.get(fieldSceneName), field);
                }

            }
        }
    }

    Object createNewValue(Field field) {
        try {
            return field.getType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ImpossivelObterNovaInstanciaException(e);
        }
    }

    void initFieldsScene(Class<?> clazz) {
        Map<String, Field> fieldsScene;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(DataBind.class)) {
                fieldsScene = new HashMap<String, Field>();
                fieldsScene.put(getFieldsBeanName(field), field);
                listFieldsScene.add(fieldsScene);
            }
        }
    }

    private void binder() {
        binderListFieldsScene();
        binderFieldsProp();
    }

    @SuppressWarnings("rawtypes")
    private void binderListFieldsScene() {
        Map<Field, BeanPathAdapter> mapFieldsBean;
        BeanPathAdapter adapter;
        String beanName;
        Field fieldScene;
        DataBind dataBind;
        for (Map<String, Field> fieldsScene : listFieldsScene) {
            Set<String> fieldsSceneKeys = fieldsScene.keySet();
            for (String fieldsSceneKey : fieldsSceneKeys) {
                if (fieldsSceneKey.contains(".")) {
                    fieldScene = fieldsScene.get(fieldsSceneKey);
                    beanName = UtilDataBind.getFieldsBeanNameFormated(fieldScene);
                    mapFieldsBean = fieldsBean.get(beanName);
                    adapter = mapFieldsBean.get(mapFieldsBean.keySet().iterator().next());
                    dataBind = fieldScene.getDeclaredAnnotationsByType(DataBind.class)[0];
                    getComponentsType(fieldScene.getType()).binder(fieldScene, adapter, dataBind, this);
                }
            }
        }
    }

    private void binderFieldsProp() {
        Object property;
        Set<Field> fieldsPropKeys = fieldsProp.keySet();
        for (Field fieldPropKey : fieldsPropKeys) {
            property = getValue(fieldsProp.get(fieldPropKey));
            if (property != null)
                getComponentsType(fieldPropKey.getType()).binder(fieldPropKey, property, this);
        }
    }

    ComponentsType getComponentsType(Class<?> clazz) {
        for (ComponentsType componentsType : ComponentsType.values()) {
            if (componentsType.getClazz().equals(clazz)) {
                return componentsType;
            }
        }
        return null;
    }

    private String getFieldsBeanName(Field field) {
        return ((DataBind) field.getAnnotationsByType(DataBind.class)[0]).mappedBy();
    }

    Object getValue(Field field) {
        try {
            field.setAccessible(true);
            return field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new ImpossivelObterValorException(e);
        }
    }

    protected void init() {
    }

    protected void addEnterNavigator(Parent node) {
        Integer index = navigator.isEmpty() ? 0 : navigator.size();
        navigator.put(index, node);
        node.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER) || keyEvent.getCode().equals(KeyCode.TAB)) {
                Parent prox = navigator.get(index + 1);
                if (prox == null) {
                    prox = navigator.get(0);
                }
                prox.requestFocus();
            }
        });

    }

    @SuppressWarnings("rawtypes")
    Map<String, Map<Field, BeanPathAdapter>> getFieldsBean() {
        return this.fieldsBean;
    }

    Map<Field, Field> getFieldsProp() {
        return this.fieldsProp;
    }

}
