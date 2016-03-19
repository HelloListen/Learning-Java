package com.springinaction.springidol;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangbin on 16/3/19.
 */
@Aspect
@Component
public class Audience1 {
    @Pointcut("execution(* com.springinaction.springidol.Performer.perform(..))")
    public void performance() {
    }

//    @Before("performance()")
//    public void takeSeats() {
//        System.out.println("The audience is taking their seats.");
//    }
//
//    @Before("performance()")
//    public void turnOffCellPhones() {
//        System.out.println("The audience is turning of their cellphones");
//    }
//
//    @After("performance()")
//    public void applaud() {
//        System.out.println("CLAP CLAP CLAP CLAP CLAP");
//    }
//
//    @AfterThrowing("performance()")
//    public void demandRefund() {
//        System.out.println("Boo! We want our money back!");
//    }

    @Around("performance()")
    public void watchPerformance(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("The audience is taking their seats.");
            System.out.println("The audience is turning off their cellphones.");
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            TimeUnit.MILLISECONDS.sleep(10);
            long end = System.currentTimeMillis();
            System.out.println("CLAP CLAP CLAP CLAP CLAP");
            System.out.println("The performance took " + (end - start) + " milliseconds.");
        } catch (Throwable t) {
            System.out.println("Boo! We want out money back!");
        }
    }
}
