package br.com.datarey.component.input;

import br.com.datarey.model.type.Dinheiro4;

public class Dinheiro4Input extends StdDecimalInput<Dinheiro4> {
    
    public Dinheiro4Input() {
        super();
        
        this.focusedProperty().addListener((ov, t, t1) -> {
            if (!t1) {
                try {
                    getValueProperty().set(
                            new Dinheiro4(getText().replace(",", ".")));
                    setText(getValueProperty().get().toString());
                } catch (Exception e) {
                    setText("");
                }
            } 

         });

        this.getStyleClass().add("dinheiroInput");
    }
}