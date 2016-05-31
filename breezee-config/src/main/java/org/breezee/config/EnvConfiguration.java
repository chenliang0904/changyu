/*
 * Copyright (c) 2016 Breezee.org. All Rights Reserved.
 */

package org.breezee.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.TimeZone;

/**
 * 环境变量设置
 * Created by Silence on 2016/2/9.
 */
@Configuration
@ImportResource(value = {
        "classpath:/bean/bre-datasource.xml",
        "classpath:/bean/bre-dubbo.xml"
})
public class EnvConfiguration implements InitializingBean {

//    @Bean
//    public DispatcherServlet services() {
//        return new DispatcherServlet();
//    }
//
//    @Bean
//    public ServletContextInitializer servletContextInitializer() {
//        return new DubboServletContextInitializer();
//    }

    @Bean
    public MessageSource messageSource() {
        //        messageSource.setBasenames("messages","i18n");
        return new ResourceBundleMessageSource();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

//    @Bean
//    public DubboLocaleFilter dubboLocaleFilter() {
//        return new DubboLocaleFilter();
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("user.timezone", "Asia/Shanghai");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(this.getClass().getName() + ": user.timezone--->Asia/Shanghai");
    }
}
