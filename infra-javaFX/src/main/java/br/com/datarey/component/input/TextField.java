package br.com.datarey.component.input;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TextField extends javafx.scene.control.TextField implements CustomInput<String> {

    private ObjectProperty<String> valueProperty = new SimpleObjectProperty<>();

    public TextField() {
        super();
        bind();
    }

    public TextField(String text) {
        super(text);
        bind();
    }

    private void bind(){

        this.textProperty().bindBidirectional(valueProperty);

    }

    @Override
    public ObjectProperty<String> getValueProperty() {

        return valueProperty;
    }
}
