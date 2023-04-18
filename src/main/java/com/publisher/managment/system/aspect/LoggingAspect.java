package com.publisher.managment.system.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

}
