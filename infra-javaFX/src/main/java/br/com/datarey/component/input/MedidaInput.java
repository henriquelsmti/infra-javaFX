package br.com.datarey.component.input;

import br.com.datarey.model.type.Medida;

public class MedidaInput extends StdDecimalInput<Medida> {
    
    public MedidaInput() {
        super();
        
        this.focusedProperty().addListener((ov, t, t1) -> {
            if (!t1) {
                try {
                    getValueProperty().set(
                            new Medida(getText().replace(",", ".")));
                    setText(getValueProperty().get().toString());
                } catch (Exception e) {
                    setText("");
                }
            } 

         });
        this.getStyleClass().add("medidaInput");
    }
}