package org.data.meta.hive.model.event;

import org.data.meta.hive.model.message.Message;

public interface Event<T> extends Message {
    String getEventType();

    String getId();

    String getType();

    Long getTimestamp();

    T getContent();
}
