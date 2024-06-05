package com.petclinic.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

@Aspect @Component
@Profile("testing")
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.petclinic.core..*Service.*(..))")
    public void logBefore() {
        logger.debug("Service function has been called");
    }

    @Around("execution(* com.petclinic.core..*Service.*(..))")
    public Object logAround(ProceedingJoinPoint processingJoinPoint) throws Throwable {
        logger.debug("Service function has been called");
        return processingJoinPoint.proceed();
    }

    @Around("execution(* com.petclinic.core..*VisitService.findByReferenceNumber*(..))")
    public Object mesureTime(ProceedingJoinPoint processingJoinPoint) throws Throwable {
        StopWatch stopWatch =  new StopWatch();
        stopWatch.start();
        Object result = processingJoinPoint.proceed();
        stopWatch.stop();
        logger.info("time spent: {} ms", stopWatch.getTotalTime(TimeUnit.MILLISECONDS));
        return result;
    }
}
