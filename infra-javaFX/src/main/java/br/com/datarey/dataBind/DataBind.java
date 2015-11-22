package br.com.datarey.dataBind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DataBind {
    String mappedBy();

    Class<?> typeField() default Object.class;

    Class<?> type() default Object.class;
}
