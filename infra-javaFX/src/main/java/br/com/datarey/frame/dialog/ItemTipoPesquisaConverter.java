package br.com.datarey.frame.dialog;

import javafx.util.StringConverter;

public class ItemTipoPesquisaConverter extends StringConverter<ItemTipoPesquisa> {

    @Override
    public ItemTipoPesquisa fromString(String arg0) {
        return null;
    }

    @Override
    public String toString(ItemTipoPesquisa arg0) {
        return arg0.getLabel();
    }

}
