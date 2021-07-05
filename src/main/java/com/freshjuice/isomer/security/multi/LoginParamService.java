package com.freshjuice.isomer.security.multi;

import com.freshjuice.isomer.security.entity.User;

public interface LoginParamService {
    User getUserByPhone(String phone);
    public void checkSmsCode(String phone, String code);
}
