package br.com.datarey.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import br.com.datarey.model.type.Dinheiro3;

public class Dinheiro3Input extends StdDecimalInput<Dinheiro3> {

    public Dinheiro3Input() {
        super();
        this.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                try {
                    getValueProperty().set(
                            new Dinheiro3(newValue.replace(",", ".")));
                    setText(getValueProperty().get().toString());
                } catch (Exception e) {
                    setText("");
                }
            }
        });
    }

    @Override
    public void replaceSelection(String text) {
        if (validate(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean validate(String text) {
        return ("".equals(text) || text.matches("[1-9]"));
    }

}