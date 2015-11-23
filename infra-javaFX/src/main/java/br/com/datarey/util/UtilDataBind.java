package br.com.datarey.util;

import java.lang.reflect.Field;

import br.com.datarey.databind.DataBind;

public class UtilDataBind {
    
    private UtilDataBind(){
        
    }
    
    public static String getFieldsBeanNameFormated(Field field) {
        DataBind dataBind = (DataBind) field.getAnnotationsByType(DataBind.class)[0];
        if (dataBind.mappedBy().contains(".")) {
            return dataBind.mappedBy().split("\\.")[0];
        } else {
            return dataBind.mappedBy();
        }
    }
}
