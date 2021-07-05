package com.freshjuice.isomer.security.multi;

import org.springframework.security.core.AuthenticationException;

public class LoginParamAuthenticationException extends AuthenticationException {
    public LoginParamAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public LoginParamAuthenticationException(String msg) {
        super(msg);
    }
}
