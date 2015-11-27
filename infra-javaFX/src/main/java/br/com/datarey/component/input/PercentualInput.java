package br.com.datarey.component.input;

import br.com.datarey.model.type.Percentual;

public class PercentualInput extends StdDecimalInput<Percentual> {
    
    public PercentualInput() {
        super();
        
        this.focusedProperty().addListener((ov, t, t1) -> {
            if (!t1) {
                try {
                    getValueProperty().set(
                            new Percentual(getText().replace(",", ".")));
                    setText(getValueProperty().get().toString());
                } catch (Exception e) {
                    setText("");
                }
            } 

         });
        this.getStyleClass().add("percentualInput");
    }
}