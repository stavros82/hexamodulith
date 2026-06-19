// =======================================
// LISTENER AOP (@HandleEvent) WITH BACKOFF
// =======================================

package com.example.outbox.aop;

import com.example.outbox.service.OutboxService;
import com.example.sharedkernel.events.DomainEvent;
import com.example.sharedkernel.outbox.HandleEvent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class HandleEventAspect {

    private static final Logger logger = LoggerFactory.getLogger(HandleEventAspect.class);

    private final OutboxService outboxService;

    public HandleEventAspect(OutboxService outboxService) {
        this.outboxService = outboxService;
    }

    @Around("@annotation(handleEvent)")
    public Object aroundHandleEvent(ProceedingJoinPoint pjp, HandleEvent handleEvent) throws Throwable {

        DomainEvent event = (DomainEvent) pjp.getArgs()[0];

        logger.info("Handling event: {}", event);

        try {
            Object result = pjp.proceed();
            outboxService.markProcessed(event);
            logger.info("Event processed successfully: {}", event);
            return result;
        } catch (Throwable ex) {
            outboxService.markFailed(event);
            logger.error("Event processing failed: {}. Reason: {}", event, ex.getMessage(), ex);
            throw ex;
        }
    }
}