package com.freshjuice.isomer.security.multi;

import com.freshjuice.isomer.security.entity.User;
import org.springframework.security.core.AuthenticationException;

public class PhoneLoginParamResolver extends AbstractLoginParamResolver {

    public PhoneLoginParamResolver(LoginParamService loginParamService) {
        super(loginParamService);
    }

    @Override
    protected String getSupportsTag() {
        return "PHONE";
    }

    @Override
    public LoginParam resolve(LoginParam loginParam) throws AuthenticationException {
        User user = loginParamService.getUserByPhone(loginParam.getPhone());
        if(user == null) throw new PhoneNotFoundException("手机号,["+loginParam.getPhone()+"]不存在");
        loginParamService.checkSmsCode(loginParam.getPhone(), loginParam.getSmsCode());
        loginParam.setUserName(user.getUserName());
        loginParam.setPassword(user.getPassword());
        return loginParam;
    }

}
