package br.com.datarey.component.input;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public abstract class NumberInput <T extends Number> extends TextField implements CustomInput<T>{
    
    
    private final ObjectProperty<T> valueProperty = new SimpleObjectProperty<T>();
    
    private static final Logger LOGGER = Logger.getLogger(NumberInput.class);  
    
    public NumberInput(){
        this.setAlignment(Pos.CENTER_RIGHT);
        try{
            this.getStylesheets().add(this.getClass().getResource("../../css/input.css").toExternalForm());
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }
    }

    public ObjectProperty<T> getValueProperty() {
        return this.valueProperty;
    }
    
    @Override
    public void replaceText(int start, int end, String text)
    {
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text)
    {
        if (validate(text))
        {
            super.replaceSelection(text);
        }
    }

    protected boolean validate(String text) {
        return isValid(text);
    }
    
    protected final boolean isValid(String text){
        if(text.equals(",") || text.equals(".")){
            if(StringUtils.countMatches(getText(), ',') == 0 && StringUtils.countMatches(getText(), '.')  == 0){
                return true;
            }else{
                return false;
            }
        }else{
            return "".equals(text) || text.matches("[0-9]|,");
        }
    }
    

}
