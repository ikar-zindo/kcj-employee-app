package com.kcj_employee_app.exception.list;

public class LogException extends RuntimeException {

    public LogException() {
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Exception cause) {
        super(message, cause);
    }
}
