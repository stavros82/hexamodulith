package com.example.outbox.relay;

import com.example.outbox.persistence.OutboxEntity;
import com.example.outbox.persistence.OutboxJpaRepository;
import com.example.sharedkernel.events.DomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component

public class OutboxRelay {

    private static final Logger log = LoggerFactory.getLogger(OutboxRelay.class);
    private final OutboxJpaRepository repo;
    private final ObjectMapper mapper;
    private final ApplicationEventPublisher publisher;

    // Max delay in seconds (π.χ. 60s)
    private static final int MAX_BACKOFF_SECONDS = 60;

    public OutboxRelay(OutboxJpaRepository repo, ObjectMapper mapper, ApplicationEventPublisher publisher) {
        this.repo = repo;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Scheduled(fixedDelay = 5000000)
    public void process() {

        List<OutboxEntity> rows =
                repo.findTop50ByProcessedFalseAndNextAttemptAtBeforeOrderByCreatedAtAsc(LocalDateTime.now());

        for (OutboxEntity row : rows) {

            try {
                // Deserialize event
                Class<?> type = Class.forName(row.getType());
                DomainEvent event = (DomainEvent) mapper.readValue(row.getPayload(), type);

                log.info(">>> [Relay] Publishing event id={} type={}", row.getId(), row.getType());

                // Publish event
                publisher.publishEvent(event);


                int attempts = row.getAttempts() == null ? 0 : row.getAttempts();
                int delay = (int) Math.min(MAX_BACKOFF_SECONDS, Math.pow(2, attempts));

                row.setAttempts(attempts + 1);
                row.setNextAttemptAt(LocalDateTime.now().plusSeconds(delay));
                row.setProcessed(false);
                repo.save(row);

                log.info(">>> [Relay] SUCCESS id={} published again", row.getEventId());

            } catch (Exception ex) {



                repo.save(row);

                log.warn(
                        ">>> [Relay] FAILURE id={} nextRetry={} ex={}",
                        row.getId(),

                        row.getNextAttemptAt(),
                        ex.toString()
                );
            }
        }
    }
}
