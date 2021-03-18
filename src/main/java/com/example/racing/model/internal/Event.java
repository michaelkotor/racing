package com.example.racing.model.internal;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Event implements Serializable {

    private Enum<Operation> type;
    private Object operateOn;
    private LocalDateTime time;
    private UUID id;

    public Event(Operation type) {
        this.type = type;
        this.time = LocalDateTime.now();
        this.id = UUID.randomUUID();
    }
}
