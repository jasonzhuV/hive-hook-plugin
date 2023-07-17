package org.data.meta.hive.service.emitter;

import org.data.meta.hive.model.event.Event;
import org.data.meta.hive.model.message.Message;

import java.io.IOException;

public interface EventEmitter {
    <T> void emit(Event<T> event) throws IOException;

    /**
     * kafka producer
     * @param message
     * @param <T>
     * @throws IOException
     */
    <T> void sendKafka(Message message) throws IOException;
}
