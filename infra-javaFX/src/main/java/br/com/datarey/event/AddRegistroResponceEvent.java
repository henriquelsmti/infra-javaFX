package br.com.datarey.event;

public class AddRegistroResponceEvent extends BaseEvent {

    private Object registro;
    
    public AddRegistroResponceEvent(Object target, Object registro) {
        super(target);
        this.registro = registro;
    }

    public Object getRegistro() {
        return this.registro;
    }

    public void setRegistro(Object registro) {
        this.registro = registro;
    }
    
    

}
