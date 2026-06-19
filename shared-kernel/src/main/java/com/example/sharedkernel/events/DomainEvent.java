package com.example.sharedkernel.events;


import java.util.UUID;

public interface DomainEvent {

    UUID eventId();
}
