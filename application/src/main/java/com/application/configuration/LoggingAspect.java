package com.application.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger( LoggingAspect.class );

    @Before("execution(* com.application.*.*.*(..))")
    public void before(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().toShortString();
        log.info("Calling method {} with parameters {} ", methodName,joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.application.*.*.*(..))", returning = "response")
    public  void afterReturning(JoinPoint joinPoint,Object response){
        String methodName = joinPoint.getSignature().toShortString();
        log.info("Execution completed for {} and the result is {} ",methodName,response);
    }

    @Around("execution(* com.application.*.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = pjp.getSignature().toShortString();
        Object response = pjp.proceed();
        long endTime = System.currentTimeMillis();
        long totalTimeTakenInMS = endTime-startTime;
        log.info("Execution for {} took {} ms",methodName,totalTimeTakenInMS);
        return response;
    }
}
