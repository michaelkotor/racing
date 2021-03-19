package com.example.racing.messages;

import com.example.racing.model.internal.Event;

public interface EventReceiver {
    void receiveMessage(Event event) throws InterruptedException;
}
