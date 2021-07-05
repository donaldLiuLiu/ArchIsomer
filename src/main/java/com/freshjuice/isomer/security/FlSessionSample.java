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
     *  RedisHttpSessionConfiguration中创建RedisOperationsSessionRepository的bean
     *  @Bean
     *  public RedisOperationsSessionRepository sessionRepository() {
     * 		RedisTemplate<Object, Object> redisTemplate = createRedisTemplate();
     * 	    //TODO,使用RedisTemplate创建RedisOperationsSessionRepository
     * 		RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(redisTemplate);
     * 	    //TODO,设置ApplicationEventPublisher
     * 		sessionRepository.setApplicationEventPublisher(this.applicationEventPublisher);
     * 		if (this.defaultRedisSerializer != null) {
     * 			sessionRepository.setDefaultSerializer(this.defaultRedisSerializer);
     *      }
     *      //TODO,Session失效时间,将会设置成redis中spring:session:sessions:的失效时间
     * 		sessionRepository.setDefaultMaxInactiveInterval(this.maxInactiveIntervalInSeconds);
     * 		if (StringUtils.hasText(this.redisNamespace)) {
     * 			sessionRepository.setRedisKeyNamespace(this.redisNamespace);
     *      }
     * 		sessionRepository.setRedisFlushMode(this.redisFlushMode);
     * 		int database = resolveDatabase();
     * 		sessionRepository.setDatabase(database);
     * 		return sessionRepository;
     *  }
     *
     * //TODO??for what???
     *  @Bean
     *  public RedisMessageListenerContainer redisMessageListenerContainer() {
     * 		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
     * 		container.setConnectionFactory(this.redisConnectionFactory);
     * 		if (this.redisTaskExecutor != null) {
     * 			container.setTaskExecutor(this.redisTaskExecutor);
     *      }
     * 		if (this.redisSubscriptionExecutor != null) {
     * 			container.setSubscriptionExecutor(this.redisSubscriptionExecutor);
     *      }
     * 		container.addMessageListener(sessionRepository(), Arrays.asList(
     * 				new ChannelTopic(sessionRepository().getSessionDeletedChannel()),
     * 				new ChannelTopic(sessionRepository().getSessionExpiredChannel())));
     * 		container.addMessageListener(sessionRepository(),
     * 				Collections.singletonList(new PatternTopic(
     * 						sessionRepository().getSessionCreatedChannelPrefix() + "*")));
     * 		return container;
     *  }
     *
     *
     *  //TODO,定时调用RedisOperationsSessionRepository的cleanupExpiredSessions()
     *  @Override
     *  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
     * 		taskRegistrar.addCronTask(() -> sessionRepository().cleanupExpiredSessions(),
     * 				this.cleanupCron);
     *  }
     *
     *
     *第三: 与spring security
     * 1、SessionRegistry
     * 使用SpringSessionBackedSessionRegistry,替代SessionRegistryImpl {@link FlSecuritySample}/第十: session-management/4
     *     //TODO,这个Repository就是上述的RedisOperationsSessionRepository，所以Registry默认就是使用Repository
     *     @Autowired
     *     private FindByIndexNameSessionRepository<S> sessionRepository;
     *     @Bean
     *     public SpringSessionBackedSessionRegistry<S> springSessionBackedSessionRegistry() {
     *         return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
     *     }
     *     sessionRegistry(springSessionBackedSessionRegistry())
     *
     *TODO,内存泄漏问题,如果重复登录，并且携带上一次调用的s-token,HttpSession的id被修改成新id(相当于原来的HttpSession被删掉了),index中旧的sessionId没有被清理掉
     *TODO,重复登录，每次不携带s-token,新生成HttpSession,index中写入新的sessionId，原来的HttpSession还在，index中还在，打上过期标记。index中原来的sessionId不好清理
     *TODO,登录成功，返回s-token和remember-me，HttpSession and SessionRegistryImpl(false),此时不带s-token,使用remember-me访问非登录接口(将触发remember-me登录),新生成HttpSession,index中写入新的sessionId，原来的HttpSession还在，index中还在，打上过期标记。index中原来的sessionId不好清理
     *TODO,登录成功 ... spring:session:sessions过期后，index中对应的sessionId不会被自动清除
     *
     *
     */



}
