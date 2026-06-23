package com.example.outbox.service;

import com.example.outbox.persistence.OutboxEntity;
import com.example.outbox.persistence.OutboxJpaRepository;
import com.example.sharedkernel.events.DomainEvent;
import com.example.sharedkernel.events.ItemCreatedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OutboxServiceTest {

    @Mock
    private OutboxJpaRepository outboxJpaRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private OutboxService outboxService;

    @Test
    void markPublishedSavesEvent() throws Exception {
        UUID eventId = UUID.randomUUID();
        DomainEvent event = new ItemCreatedEvent(eventId, 1);

        when(objectMapper.writeValueAsString(event)).thenReturn("{\"eventId\":\"" + eventId + "\",\"itemId\":1}");

        outboxService.markPublished(event);

        verify(outboxJpaRepository, times(1)).save(any(OutboxEntity.class));
    }

    @Test
    void markProcessedUpdatesEntity() {
        UUID eventId = UUID.randomUUID();
        DomainEvent event = new ItemCreatedEvent(eventId, 1);
        OutboxEntity entity = new OutboxEntity();
        entity.setEventId(eventId);
        when(outboxJpaRepository.findByEventId(eventId)).thenReturn(entity);

        outboxService.markProcessed(event);

        assert entity.getProcessed();
        verify(outboxJpaRepository, times(1)).save(entity);
    }

    @Test
    void markFailedUpdatesAttempts() {
        UUID eventId = UUID.randomUUID();
        DomainEvent event = new ItemCreatedEvent(eventId, 1);
        OutboxEntity entity = new OutboxEntity();
        entity.setEventId(eventId);
        entity.setAttempts(0);
        when(outboxJpaRepository.findByEventId(eventId)).thenReturn(entity);

        outboxService.markFailed(event);

        assert entity.getAttempts() == 1;
        assert !entity.getProcessed();
        verify(outboxJpaRepository, times(1)).save(entity);
    }
}
