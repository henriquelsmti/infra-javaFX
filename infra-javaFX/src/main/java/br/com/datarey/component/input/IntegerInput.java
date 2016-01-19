package br.com.datarey.component.input;


import org.apache.commons.lang3.StringUtils;

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
            if(getText().length() == 0){
                return true;
            }
            try {
                Integer.parseInt(getText() + text);
                return true;
            } catch (Exception e) {
               return false;
            } 
        }
        return false;
    }
    
    
}
