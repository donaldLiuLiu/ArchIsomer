package com.freshjuice.isomer.security.multi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractLoginParamResolver implements LoginParamResolver {

    private Logger log = LoggerFactory.getLogger(AbstractLoginParamResolver.class);
    protected LoginParamService loginParamService;

    public AbstractLoginParamResolver(LoginParamService loginParamService) {
        this.loginParamService = loginParamService;
    }

    public boolean supports(LoginParam loginParam) {
        if(loginParam == null) {
            log.warn("loginParam is null");
            return false;
        }
        return getSupportsTag().equals(loginParam.getType());
    }

    protected abstract String getSupportsTag();

}
