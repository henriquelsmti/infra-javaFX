package br.com.datarey.util;

import java.lang.reflect.Field;

import br.com.datarey.dataBind.DataBind;

public class UtilDataBind {
    public static String getFieldsBeanNameFormated(Field field) {
        DataBind dataBind = (DataBind) field.getAnnotationsByType(DataBind.class)[0];
        if (dataBind.mappedBy().contains(".")) {
            return dataBind.mappedBy().split("\\.")[0];
        } else {
            return dataBind.mappedBy();
        }
    }
}
