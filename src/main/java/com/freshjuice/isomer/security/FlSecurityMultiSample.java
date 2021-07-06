package com.freshjuice.isomer.security;

public class FlSecurityMultiSample {

    /**
     * 1、基于浏览器访问的后端系统
     * 2、前后端分离
     * 3、后端系统可单机，可集群部署
     * 4、多种认证方式
     * 5、可配置的授权
     */

 //替换方案
       //定义拦截器处理认证，继承自AbstractAuthenticationProcessingFilter替换UsernamePasswordAuthenticationFilter
       //AbstractAuthenticationProcessingFilter中的组件，
       //相同的登录接口路径，如果进入到自定义的拦截器，需要处理认证，认证后请求不能转发到UsernamePasswordAuthenticationFilter
       //相同的认证代码，冗余


 //适配方案 定义一个UsernamePasswordAuthenticationFilter的前置拦截器
     //如果是JSON参数，则将参数等适配，然后将请求转发到UsernamePasswordAuthenticationFilter
     //如果不是JSON参数,此前置执行直接转发到UsernamePasswordAuthenticationFilter
    /**
     * @see com.freshjuice.isomer.security.multi.adapter.FlAuthenticationAdapterFilter
     * @see com.freshjuice.isomer.config.FlSecurityMultiAdapterConfig
     */



}
