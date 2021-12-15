package com.mikola.accsys.exception;

public class AccountingSystemException extends Exception{
    public AccountingSystemException(String message) {
        super(message);
    }
//    public AccountingSystemException(Exception e) {
//        super(e);
//    }
    public AccountingSystemException(String message, Exception e) {
        super(message,e);
    }
}
