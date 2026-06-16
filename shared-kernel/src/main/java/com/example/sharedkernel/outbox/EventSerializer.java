package com.example.sharedkernel.outbox;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EventSerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String serialize(Object event) {
        try {
            return mapper.writeValueAsString(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object deserialize(String type, String payload) {
        try {
            return mapper.readValue(payload, Class.forName(type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
