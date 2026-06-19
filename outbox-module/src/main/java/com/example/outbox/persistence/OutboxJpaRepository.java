
package com.example.outbox.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OutboxJpaRepository extends JpaRepository<OutboxEntity, Long> {

    List<OutboxEntity> findTop50ByProcessedFalseAndNextAttemptAtBeforeOrderByCreatedAtAsc(
            LocalDateTime now
    );
    OutboxEntity findByEventId(UUID eventId);
}