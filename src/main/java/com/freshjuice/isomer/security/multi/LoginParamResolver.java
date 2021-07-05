package com.freshjuice.isomer.security.multi;

import org.springframework.security.core.AuthenticationException;

public interface LoginParamResolver {
    LoginParam resolve(LoginParam loginParam) throws AuthenticationException;
}
