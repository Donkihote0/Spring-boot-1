package com.donkihote.identity_service.exception;

public class AppException extends RuntimeException {

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public AppException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    
}
