package br.com.datarey.component.input;

import javafx.beans.property.ObjectProperty;

public interface CustomInput<T> {
    public ObjectProperty<T> getValueProperty();
}
