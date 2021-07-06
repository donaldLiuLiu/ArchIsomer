package com.freshjuice.isomer.security.multi.adapter;

import com.freshjuice.isomer.security.multi.adapter.LoginParamAdapter;
import org.springframework.security.core.AuthenticationException;

public interface LoginParamAdapterResolver {
    LoginParamAdapter resolve(LoginParamAdapter loginParam) throws AuthenticationException;
}
