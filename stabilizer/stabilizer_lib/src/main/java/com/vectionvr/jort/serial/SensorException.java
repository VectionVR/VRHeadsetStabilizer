package com.vectionvr.jort.serial;

public class SensorException extends RuntimeException {

    static enum ExceptionType{
        READ_TIMEOUT,UNABLE_TO_CONNECT,CANNOT_WRITE,CANNOT_VALIDATE,CANNOT_READ, NO_ANSWER;
    }
    private ExceptionType exceptionType;
    
    SensorException(String message, Throwable cause, ExceptionType exceptionType) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }

    SensorException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }
    
    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}
