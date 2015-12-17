package br.com.datarey.component.input;


public class LongInput extends NumberInput<Long> {

    public LongInput() {
        super();
        
        this.focusedProperty().addListener((ov, t, t1) -> {
            if (!t1) {
                try {
                    getValueProperty().set(
                            Long.parseLong(getText().replace(",", ".")));
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
                Long.parseLong(getText().replace(",", "."));
                return true;
            } catch (Exception e) {
               return false;
            } 
        }
        return false;
    }
    
    
}
