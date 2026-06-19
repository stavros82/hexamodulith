package com.example.outbox.aop;



import com.example.outbox.service.OutboxService;
import com.example.sharedkernel.events.DomainEvent;
import com.example.sharedkernel.outbox.OutboxEvent;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class OutboxProducerAspect {

    private final OutboxService outboxService;


    public OutboxProducerAspect(OutboxService outboxService) {
        this.outboxService = outboxService;
    }

    @Around("@annotation(outboxEvent)")
    public Object intercept(ProceedingJoinPoint pjp, OutboxEvent outboxEvent) throws Throwable {

        DomainEvent event = (DomainEvent)pjp.getArgs()[0];

       outboxService.markPublished(event);
         return pjp.proceed();
    }


}


