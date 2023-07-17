package org.test;

import org.data.meta.hive.model.event.EventBase;
import org.data.meta.hive.model.lineage.LineageHookInfo;
import org.data.meta.hive.service.emitter.EventEmitterFactory;
import org.data.meta.hive.util.EventUtils;

import java.io.IOException;

/**
 * @author : zhupeiwen
 * @date : 2023/4/26
 */
public class TestWriter {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 3; i++) {
            final LineageHookInfo lhInfo = new LineageHookInfo();
            final EventBase<LineageHookInfo> event = new EventBase<LineageHookInfo>();
            event.setEventType("LINEAGE");
            event.setContent(lhInfo);
            event.setId(EventUtils.newId());
            event.setTimestamp(System.currentTimeMillis());
            event.setType("HIVE");
            EventEmitterFactory.get().emit(event);
        }
    }
}
