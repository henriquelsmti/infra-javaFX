package br.com.datarey.frame.dialog;

import br.com.datarey.frame.Predicate;


public class ItemTipoPesquisa {

    private String label;
    private String propriedade; 
    private Predicate predicate; 
   
    

    public ItemTipoPesquisa(String label, String propriedade, Predicate predicate) {
        super();
        this.label = label;
        this.propriedade = propriedade;
        this.predicate = predicate;
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

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

  
    
}
