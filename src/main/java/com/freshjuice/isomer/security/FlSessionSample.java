package com.freshjuice.isomer.security;

public class FlSessionSample {

    /**
     *依赖
     * spring-session-data-redis
     *  spring-data-redis
     *  spring-session-core
     *
     */

    /**
     *auto-configuration
     * @see org.springframework.boot.autoconfigure.session.SessionAutoConfiguration
     * @see org.springframework.boot.autoconfigure.session.RedisSessionConfiguration
     * @see org.springframework.boot.autoconfigure.session.SessionRepositoryFilterConfiguration
     *configuration,annotation
     * @see org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
     * @see org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration
     * @see org.springframework.session.config.annotation.web.http.EnableSpringHttpSession
     * @see org.springframework.session.config.annotation.web.http.SpringHttpSessionConfiguration
     *filter and initializer
     * @see org.springframework.session.web.http.SessionRepositoryFilter
     * @see org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer
     *
     *
     *第一: SessionRepositoryFilter的注册
     *1、SpringHttpSessionConfiguration中创建SessionRepositoryFilter的bean
     * @Bean
     * public <S extends Session> SessionRepositoryFilter<? extends Session> springSessionRepositoryFilter(
     * 			SessionRepository<S> sessionRepository) {
     * 		SessionRepositoryFilter<S> sessionRepositoryFilter = new SessionRepositoryFilter<>(sessionRepository);
     * 		sessionRepositoryFilter.setServletContext(this.servletContext);
     * 		sessionRepositoryFilter.setHttpSessionIdResolver(this.httpSessionIdResolver);
     * 		return sessionRepositoryFilter;
     * }
     *2、SessionRepositoryFilterConfiguration中创建FilterRegistrationBean<SessionRepositoryFilter<?>>
     *  FilterRegistrationBean中设置了SessionRepositoryFilter<?>，会将SessionRepositoryFilter<?>写到Servlet Container中
     *  Order可以调用setOrder或者Filter的@Order，拦截路径在其基类的urlPatterns或者DEFAULT_URL_MAPPINGS
     *@Bean
     *FilterRegistrationBean<SessionRepositoryFilter<?>> sessionRepositoryFilterRegistration(SessionProperties sessionProperties,
     *                  SessionRepositoryFilter<?> filter) {
     *
     * 		FilterRegistrationBean<SessionRepositoryFilter<?>> registration = new FilterRegistrationBean<>(filter);
     * 		registration.setDispatcherTypes(getDispatcherTypes(sessionProperties));
     * 		registration.setOrder(sessionProperties.getServlet().getFilterOrder());
     * 		return registration;
     *}
     *3、增加配置
     *spring:
     *  session:
     *     servlet:
     *       ##去掉默认的ERROR,表示不拦截/error，原因@see FlBasicErrorController
     *       filter-dispatcher-types: ASYNC,REQUEST
     *
     *第二: RedisOperationsSessionRepository
     *
     *
     *
     *
     *第三: 与spring security
     * 1、SessionRegistry
     * 使用SpringSessionBackedSessionRegistry,替代SessionRegistryImpl {@link FlSecuritySample}/第十: session-management/4
     *     @Autowired
     *     private FindByIndexNameSessionRepository<S> sessionRepository;
     *     @Bean
     *     public SpringSessionBackedSessionRegistry<S> springSessionBackedSessionRegistry() {
     *         return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
     *     }
     *     sessionRegistry(springSessionBackedSessionRegistry())
     */



}
