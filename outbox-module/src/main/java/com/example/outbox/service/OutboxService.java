package com.example.outbox.service;

import com.example.outbox.persistence.OutboxEntity;
import com.example.outbox.persistence.OutboxJpaRepository;
import com.example.sharedkernel.events.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OutboxService {

    private static final Logger logger = LoggerFactory.getLogger(OutboxService.class);

    private final OutboxJpaRepository outbox;
    private final ObjectMapper mapper;

    public OutboxService(OutboxJpaRepository outbox, ObjectMapper mapper) {
        this.outbox = outbox;
        this.mapper = mapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markPublished(DomainEvent event) throws JsonProcessingException {
        logger.info("Publishing event: [{}] with id: [{}]", event.getClass().getName(), event.eventId());
        OutboxEntity row = new OutboxEntity();
        row.setType(event.getClass().getName());
        row.setPayload(mapper.writeValueAsString(event));
        row.setProcessed(false);
        row.setAttempts(0);
        row.setNextAttemptAt(LocalDateTime.now());
        row.setCreatedAt(LocalDateTime.now());
        row.setEventId(event.eventId());

        outbox.save(row);
        logger.debug("Saved OutboxEntity: {}", row);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markProcessed(DomainEvent domainEvent) {
        logger.info("Marking event [{}] as processed", domainEvent.eventId());
        OutboxEntity row = outbox.findByEventId(domainEvent.eventId());

        try {
            if (row != null) {
                row.setProcessed(true);
                row.setAttempts(0);
                row.setNextAttemptAt(LocalDateTime.now());
                outbox.save(row);
                logger.debug("Marked as processed: {}", row);
            } else {
                logger.warn("OutboxEntity not found for eventId: {}", domainEvent.eventId());
            }
        } catch (Exception ex) {
            logger.error("Error processing event [{}]: {}", domainEvent.eventId(), ex.getMessage(), ex);

            if (row != null) {
                int attempts = row.getAttempts() == null ? 0 : row.getAttempts();
                int delay = (int) Math.min(60, Math.pow(2, attempts));

                row.setAttempts(attempts + 1);
                row.setNextAttemptAt(LocalDateTime.now().plusSeconds(delay));
                row.setProcessed(false);

                outbox.save(row);
                logger.warn("Failed to mark processed, updated entity attempts and delay: {}", row);
            }

            throw ex;
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void markFailed(DomainEvent domainEvent) {
        logger.info("Marking event [{}] as failed", domainEvent.eventId());
        OutboxEntity row = outbox.findByEventId(domainEvent.eventId());
        if (row != null) {
            int attempts = row.getAttempts() == null ? 0 : row.getAttempts();
            int delay = (int) Math.min(60, Math.pow(2, attempts));

            row.setAttempts(attempts + 1);
            row.setNextAttemptAt(LocalDateTime.now().plusSeconds(delay));
            row.setProcessed(false);

            outbox.save(row);
            logger.warn("Marked as failed: {}, attempts: {}, nextAttemptIn: {} sec", row, attempts + 1, delay);
        } else {
            logger.warn("OutboxEntity not found for eventId: {}", domainEvent.eventId());
        }
    }
}