package br.com.datarey.component.input;

import br.com.datarey.model.type.Dinheiro3;

public class Dinheiro3Input extends StdDecimalInput<Dinheiro3> {

    

    public Dinheiro3Input() {
        super();
        
        this.focusedProperty().addListener((ov, t, t1) -> {
            if (!t1) {
                try {
                    getValueProperty().set(
                            new Dinheiro3(getText().replace(",", ".")));
                    setText(getValueProperty().get().toString());
                } catch (Exception e) {
                    setText("");
                }
            } 

         });
        this.getStyleClass().add("dinheiroInput");
    }

}