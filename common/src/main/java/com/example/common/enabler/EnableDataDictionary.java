package com.example.common.enabler;

import com.example.common.datadictionary.application.DataDictionaryAppService;
import com.example.common.datadictionary.controller.DataDictionaryController;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DataDictionaryAppService.class, DataDictionaryController.class})
public @interface EnableDataDictionary {}
