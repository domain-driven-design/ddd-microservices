package com.example.common.enabler;

import com.example.common.auth.AuthService;
import com.example.common.auth.FilterConfig;
import com.example.common.error.ExceptionAdvice;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        ExceptionAdvice.class,
})
public @interface EnableCommonFeatures {
}
