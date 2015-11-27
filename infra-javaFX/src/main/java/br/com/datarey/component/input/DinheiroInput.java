package br.com.datarey.component.input;

import br.com.datarey.model.type.Dinheiro;

public class DinheiroInput extends StdDecimalInput<Dinheiro> {
    
    public DinheiroInput() {
        super();
        
        this.focusedProperty().addListener((ov, t, t1) -> {
            if (!t1) {
                try {
                    getValueProperty().set(
                            new Dinheiro(getText().replace(",", ".")));
                    setText(getValueProperty().get().toString());
                } catch (Exception e) {
                    setText("");
                }
            } 

         });

        this.getStyleClass().add("dinheiroInput");
    }
}