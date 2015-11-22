package br.com.datarey.frame.dialog;

import br.com.datarey.service.type.Regra;

public class ItemTipoPesquisa {

    private String propriedade; 
    private Regra regra; 
   
    

    public ItemTipoPesquisa(String propriedade, Regra regra) {
        super();
        this.propriedade = propriedade;
        this.regra = regra;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public Regra getRegra() {
        return regra;
    }

    public void setRegra(Regra regra) {
        this.regra = regra;
    }

  
    
}
