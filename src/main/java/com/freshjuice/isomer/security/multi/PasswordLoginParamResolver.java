package com.freshjuice.isomer.security.multi;

import org.springframework.security.core.AuthenticationException;

public class PasswordLoginParamResolver extends AbstractLoginParamResolver {

    public PasswordLoginParamResolver(LoginParamService loginParamService) {
        super(loginParamService);
    }

    @Override
    protected String getSupportsTag() {
        return "PASSWORD";
    }

    @Override
    public LoginParam resolve(LoginParam loginParam) throws AuthenticationException {
        return loginParam;
    }
}
