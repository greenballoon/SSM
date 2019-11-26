package com.shop.exception;

public class MyException extends Exception {
    private String errormsg;

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public MyException() {
        super();
    }

    public MyException(String errormsg) {
        super();
        this.errormsg = errormsg;
    }
}
