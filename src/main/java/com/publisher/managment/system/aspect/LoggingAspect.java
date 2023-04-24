package com.publisher.managment.system.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before(value = "execution(* com.publisher.managment.system.controller.*.*(..)))")
    public void beforeAdviceController(JoinPoint joinPoint) {
        logger.info("Controller Method {} got triggered", joinPoint.getSignature().getName());
    }

    @Before(value = "execution(* com.publisher.managment.system.service.*.*(..)))")
    public void beforeAdviceService(JoinPoint joinPoint) {
        logger.info("Service Method {} got triggered", joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.publisher.managment.system.controller.*.*(..)))")
    public void afterReturningAdviceController(JoinPoint joinPoint) {
        logger.info("After Returning Controller Method {} got triggered", joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.publisher.managment.system.controller.*.*(..)))")
    public void afterReturningAdviceService(JoinPoint joinPoint) {
        logger.info("After Returning Service Method {} got triggered", joinPoint.getSignature().getName());
    }

    @AfterThrowing(value = "com.publisher.managment.system.aspect.JoinPointConfiguration.controllerLogging()", throwing = "exception")
    public void afterThrowingAdviceController(JoinPoint joinPoint, Object exception) {
        logger.info("After Throwing Controller Method {} got triggered ERROR : {}", joinPoint.getSignature().getName(), exception.toString());
    }

    @AfterThrowing(value = "com.publisher.managment.system.aspect.JoinPointConfiguration.logging()", throwing = "exception")
    public void afterThrowingAdviceService(JoinPoint joinPoint, Object exception) {
        logger.info("After Throwing Service Method {} got triggered ERROR : {}", joinPoint.getSignature().getName(), exception.toString());
    }


    @Around(value = "@annotation(com.publisher.managment.system.aspect.TrackExecutionTime)")
    public Object AroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object o = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        Long finalTime = endTime - startTime;
        logger.info("Method {} took {} mills to execute", proceedingJoinPoint.getSignature().getName(), finalTime);
        return o;
    }
}
