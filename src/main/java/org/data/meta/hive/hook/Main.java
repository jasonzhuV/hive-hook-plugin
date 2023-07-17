package org.data.meta.hive.hook;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.data.meta.hive.model.lineage.LineageHookInfo;
import org.data.meta.hive.service.emitter.EventEmitterFactory;

import java.io.IOException;

/**
 * @author : zhupeiwen
 * @date : 2023/6/15
 */
public class Main {

    public static void main(String[] args) throws IOException {

        String message =  "{\"database\":\"default\",\"duration\":9045,\"engine\":\"tez\",\"hash\":\"8e681d8c7db2c16396ea8436ba47a23d\",\"jobIds\":[],\"queryText\":\"insert overwrite table test.test_data select *from test.test_ingest_1\",\"operationName\":\"QUERY\",\"timestamp\":1686830483,\"user\":\"hive\",\"userGroupNames\":[\"hadoop\"],\"version\":\"1.0\",\"tableLineages\":[{\"srcDatabase\":\"test\",\"destDatabase\":\"test\",\"srcTable\":\"test_ingest_1\",\"destTable\":\"test_data\"}]}";

        ObjectMapper om = new ObjectMapper();

        LineageHookInfo lineageHookInfo = om.readValue(message, LineageHookInfo.class);


        System.out.println(om.writeValueAsString(lineageHookInfo));

//        EventEmitterFactory.get().sendKafka(lineageHookInfo);

    }

}
