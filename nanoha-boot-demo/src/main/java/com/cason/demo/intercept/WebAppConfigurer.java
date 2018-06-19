package com.cason.demo.intercept;

import com.cason.demo.intercept.auth.AuthenticateIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个
        registry.addInterceptor(new AuthenticateIntercept()).addPathPatterns("/**");
        // registry.addInterceptor(new PagedInterceptor()).addPathPatterns("/**");
    }


}
