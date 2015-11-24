package br.com.datarey.frame.dialog;

import br.com.datarey.service.type.Regra;

public class ItemTipoPesquisa {

    private String label;
    private String propriedade; 
    private Regra regra; 
   
    

    public ItemTipoPesquisa(String label, String propriedade, Regra regra) {
        super();
        this.label = label;
        this.propriedade = propriedade;
        this.regra = regra;
    }

    public String getLabel() {
        return this.label;
    }
    public void setLabel(String label) {
        this.label = label;
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
