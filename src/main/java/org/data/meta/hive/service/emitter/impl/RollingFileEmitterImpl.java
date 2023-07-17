package org.data.meta.hive.service.emitter.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.session.SessionState.LogHelper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.data.meta.hive.model.event.Event;
import org.data.meta.hive.model.message.Message;
import org.data.meta.hive.service.codec.EventCodecs;
import org.data.meta.hive.service.emitter.EventEmitter;

import java.io.IOException;
import java.util.Properties;

/**
 * @author jason
 */
public class RollingFileEmitterImpl implements EventEmitter {
    private static final RollingFileWriter rollingFileWriter = new RollingFileWriter(50000, "hook.event");

    private static final Properties prop = new Properties();

    private KafkaProducer<String, String> producer;


    public RollingFileEmitterImpl() {
//        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.19.161.128:9092,172.19.161.169:9092,172.19.161.181:9092");
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.41.4.87:9092,172.41.4.58:9092,172.41.4.71:9092");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("acks","all");
        prop.put("retries",0);
        prop.put("batch.size",16384);
        prop.put("linger.ms",1);
        prop.put("buffer.memory",33554432);
        producer = new KafkaProducer<>(prop);
    }

    public <T> void emit(Event<T> event) throws IOException {
        String message = new String(EventCodecs.encode(event));
        LogHelper console = SessionState.getConsole();
        if (console != null) {
            console.printInfo(message);
        }

        rollingFileWriter.writeLineWithLock(message);
    }


    @Override
    public <T> void sendKafka(Message message) {
        ObjectMapper om = new ObjectMapper();
        String topic ="hive-lineage-change";
        try {
            producer.send(new ProducerRecord<String, String>(topic, om.writeValueAsString(message)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
