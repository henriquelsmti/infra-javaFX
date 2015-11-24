package br.com.datarey.event;

public class AddRegistroEvent extends BaseEvent{
    private String nome;

    
    public AddRegistroEvent(String nome, Object target) {
        super(target);
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
