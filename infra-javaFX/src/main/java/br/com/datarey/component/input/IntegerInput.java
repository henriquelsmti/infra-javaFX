package br.com.datarey.component.input;


public class IntegerInput extends NumberInput<Integer> {

    public IntegerInput() {
        super();
        
        this.focusedProperty().addListener((ov, t, t1) -> {
            if (!t1) {
                try {
                    getValueProperty().set(
                            Integer.parseInt(getText()));
                    setText(String.valueOf(getValueProperty().get()));
                } catch (Exception e) {
                    setText("");
                }
            } 

         });
    }
    
    

    @Override
    protected boolean validate(String text) {
        if("".equals(text) || text.matches("[0-9]")){
            try {
                Integer.parseInt(getText());
                return true;
            } catch (Exception e) {
               return false;
            } 
        }
        return false;
    }
    
    
}
