package br.com.datarey.component.input;

import br.com.datarey.model.type.FatorConversao;

public class FatorConversaoInput extends StdDecimalInput<FatorConversao> {
    
    public FatorConversaoInput() {
        super();
        
        this.focusedProperty().addListener((ov, t, t1) -> {
            if (!t1) {
                try {
                    getValueProperty().set(
                            new FatorConversao(getText().replace(",", ".")));
                    setText(getValueProperty().get().toString());
                } catch (Exception e) {
                    setText("");
                }
            } 

         });

        this.getStyleClass().add("fatorConversaoInput");
    }
}