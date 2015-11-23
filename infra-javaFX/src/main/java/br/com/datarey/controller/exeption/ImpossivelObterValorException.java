package br.com.datarey.controller.exeption;

import br.com.datarey.exception.BaseException;

public class ImpossivelObterValorException extends BaseException {

    private static final long serialVersionUID = 4157160139761968668L;

    public ImpossivelObterValorException() {
        super();
    }

    public ImpossivelObterValorException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ImpossivelObterValorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImpossivelObterValorException(String message) {
        super(message);
    }

    public ImpossivelObterValorException(Throwable cause) {
        super(cause);
    }
    
    
}
