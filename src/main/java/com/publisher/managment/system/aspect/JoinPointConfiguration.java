package com.publisher.managment.system.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class JoinPointConfiguration {

    @Pointcut("execution(* com.publisher.managment.system.controller.*.*(..)))")
    public void controllerLogging() {
    }

    @Pointcut(value = "execution(* com.publisher.managment.system.service.*.*(..))")
    public void logging() {
    }

    @Pointcut("within(com.publisher.managment.system..*)")
    public void controllerLogging2() {
    }

}
