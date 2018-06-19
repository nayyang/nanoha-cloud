package com.cason.demo.intercept.auth;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 要做登录令牌校验时，加上这个注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface Authenticate {

}
