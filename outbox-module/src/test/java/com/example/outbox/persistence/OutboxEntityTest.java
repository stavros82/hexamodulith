package com.example.outbox.persistence;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.UUID;

class OutboxEntityTest {

    @Test
    void initializesFieldsCorrectly() {
        OutboxEntity entity = new OutboxEntity();

        assert entity.getProcessed().equals(false);
        assert entity.getAttempts().equals(0);
        assert entity.getCreatedAt() != null;
        assert entity.getNextAttemptAt() != null;
    }

    @Test
    void setsAndGetsFields() {
        OutboxEntity entity = new OutboxEntity();
        Long id = 1L;
        String type = "TestType";
        String payload = "TestPayload";
        UUID eventId = UUID.randomUUID();
        LocalDateTime dateTime = LocalDateTime.now();

        entity.setId(id);
        entity.setType(type);
        entity.setPayload(payload);
        entity.setEventId(eventId);
        entity.setProcessed(true);
        entity.setAttempts(5);
        entity.setCreatedAt(dateTime);
        entity.setNextAttemptAt(dateTime);

        assert entity.getId().equals(id);
        assert entity.getType().equals(type);
        assert entity.getPayload().equals(payload);
        assert entity.getEventId().equals(eventId);
        assert entity.getProcessed();
        assert entity.getAttempts().equals(5);
        assert entity.getCreatedAt().equals(dateTime);
        assert entity.getNextAttemptAt().equals(dateTime);
    }
}
