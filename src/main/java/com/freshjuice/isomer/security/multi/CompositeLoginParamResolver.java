package com.freshjuice.isomer.security.multi;

import org.springframework.security.core.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

public class CompositeLoginParamResolver implements LoginParamResolver {
    private final List<AbstractLoginParamResolver> resolvers = new ArrayList<>();
    private final DefaultLoginParamResolver defaultLoginParamResolver = new DefaultLoginParamResolver(null);

    @Override
    public LoginParam resolve(LoginParam loginParam) {
        AbstractLoginParamResolver resolverToUse = resolvers.stream().filter(resolver -> resolver.supports(loginParam)).findFirst().orElse(defaultLoginParamResolver);
        return resolverToUse.resolve(loginParam);
    }

    public void addResolver(AbstractLoginParamResolver resolver) {
        resolvers.add(resolver);
    }

    private static class DefaultLoginParamResolver extends AbstractLoginParamResolver {

        public DefaultLoginParamResolver(LoginParamService loginParamService) {
            super(loginParamService);
        }

        public boolean supports(LoginParam loginParam) {
            return true;
        }

        @Override
        protected String getSupportsTag() {
            return "";
        }

        @Override
        public LoginParam resolve(LoginParam loginParam) throws AuthenticationException {
            throw new LoginParamAuthenticationException("登录失败");
        }
    }
}
