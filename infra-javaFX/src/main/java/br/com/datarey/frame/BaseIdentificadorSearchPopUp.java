package br.com.datarey.frame;

import br.com.datarey.frame.dialog.BaseSearchPopUp;
import br.com.datarey.model.Identificador;

public class BaseIdentificadorSearchPopUp<E extends Identificador, C extends BaseIdentificadorSearchController<E, ?>>
        extends BaseSearchPopUp<E, C> {

    
    public BaseIdentificadorSearchPopUp() {
        super("Pesquisa");
    }


}
