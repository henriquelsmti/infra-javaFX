package br.com.datarey.component.input;

import org.apache.commons.lang3.StringUtils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public abstract class NumberInput <T extends Number> extends TextField implements CustomInput<T>{
    
    
    private final ObjectProperty<T> valueProperty = new SimpleObjectProperty<T>();
    
    
    public NumberInput(){
        this.setAlignment(Pos.CENTER_RIGHT);
        try{
            this.getStylesheets().add(this.getClass().getResource("../../css/input.css").toString());
        }catch(Exception e){
            e.printStackTrace();
        }

        valueProperty.addListener((event) ->{
            textProperty().set(valueProperty.getValue().toString());
        });
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
