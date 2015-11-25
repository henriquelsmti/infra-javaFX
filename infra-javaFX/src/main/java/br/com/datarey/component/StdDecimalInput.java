package br.com.datarey.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;
import br.com.datarey.model.type.StdDecimal;

public class StdDecimalInput<T extends StdDecimal<T>> extends TextField {
    private ObjectProperty<T> valueProperty = new SimpleObjectProperty<T>();
    

    public ObjectProperty<T> getValueProperty() {
        return this.valueProperty;
    }
}
