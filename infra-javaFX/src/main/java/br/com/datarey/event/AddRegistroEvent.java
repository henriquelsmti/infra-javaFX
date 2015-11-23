package br.com.datarey.event;

public class AddRegistroEvent {
    private String nome;

    
    public AddRegistroEvent(String nome) {
        super();
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
