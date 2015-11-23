package br.com.datarey.controller.exeption;

import br.com.datarey.exception.BaseException;

public class ImpossivelObterNovaInstanciaException extends BaseException {

    private static final long serialVersionUID = 4157160139761968668L;

    public ImpossivelObterNovaInstanciaException() {
        super();
    }

    public ImpossivelObterNovaInstanciaException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ImpossivelObterNovaInstanciaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImpossivelObterNovaInstanciaException(String message) {
        super(message);
    }

    public ImpossivelObterNovaInstanciaException(Throwable cause) {
        super(cause);
    }
    
    
}
