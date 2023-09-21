package org.data.meta.hive.service.emitter;

import org.data.meta.hive.service.emitter.impl.RollingFileAndKafkaEmitterImpl;

public class EventEmitterFactory {
    private static final EventEmitter SINGLETON_EMITTER;

    public EventEmitterFactory() {
    }

    public static EventEmitter get() {
        return SINGLETON_EMITTER;
    }

    static {
        RollingFileAndKafkaEmitterImpl emitter = null;

        try {
            emitter = new RollingFileAndKafkaEmitterImpl();
        } catch (Throwable var2) {
            var2.printStackTrace();
        }

        SINGLETON_EMITTER = emitter;
    }
}
