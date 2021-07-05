package com.freshjuice.isomer.security.multi;

import org.springframework.security.core.AuthenticationException;

public class SmsCodeInvalidException extends AuthenticationException {
    public SmsCodeInvalidException(String msg, Throwable t) {
        super(msg, t);
    }

    public SmsCodeInvalidException(String msg) {
        super(msg);
    }
}
