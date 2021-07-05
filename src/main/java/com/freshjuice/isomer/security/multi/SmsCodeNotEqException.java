package com.freshjuice.isomer.security.multi;

import org.springframework.security.core.AuthenticationException;

public class SmsCodeNotEqException extends AuthenticationException {
    public SmsCodeNotEqException(String msg, Throwable t) {
        super(msg, t);
    }

    public SmsCodeNotEqException(String msg) {
        super(msg);
    }
}
