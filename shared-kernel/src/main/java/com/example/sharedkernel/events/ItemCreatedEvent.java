
package com.example.sharedkernel.events;

import java.util.UUID;

public record ItemCreatedEvent(UUID eventId,Integer itemId) implements DomainEvent{

}
