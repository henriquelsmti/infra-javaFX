package br.com.datarey.event;

public abstract class BaseEvent {
    private Object target;
    
    
    public BaseEvent(Object target) {
        super();
        this.target = target;
    }

    public Object getTarget() {
        return this.target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
    
    
}
