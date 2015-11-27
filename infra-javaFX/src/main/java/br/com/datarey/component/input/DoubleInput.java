package br.com.datarey.component.input;



public class DoubleInput extends NumberInput<Double> {

    public DoubleInput() {
        super();
        
        this.focusedProperty().addListener((ov, t, t1) -> {
            if (!t1) {
                try {
                    getValueProperty().set(
                            Double.parseDouble(getText().replace(",", ".")));
                    setText(String.valueOf(getValueProperty().get()));
                } catch (Exception e) {
                    setText("");
                }
            } 

         });
    }
    
    @Override
    protected boolean validate(String text) {
        if(isValid(text)){
            try {
                Double.parseDouble(getText().replace(",", "."));
                return true;
            } catch (Exception e) {
               return false;
            } 
        }
        return false;
    }
    
    
 
}
