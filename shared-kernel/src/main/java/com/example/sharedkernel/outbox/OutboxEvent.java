package com.example.sharedkernel.outbox;

import com.example.sharedkernel.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record OutboxEvent(
        UUID id,
        String type,
        String payload,
        Instant createdAt,
        boolean processed
) implements DomainEvent {}
