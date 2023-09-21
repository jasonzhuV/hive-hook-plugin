package org.data.meta.hive.model.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.data.meta.hive.model.message.Message;
import org.data.meta.hive.model.record.QueryTable;

/**
 * @author : zhupeiwen
 * @date : 2023/7/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryTableInfo implements Message {
    private String database;
    private Long duration;
    private String engine;
    private String queryText;
    private String operationName;
    private Long timestamp;
    private String user;
    private String[] userGroupNames;
    private QueryTable queryTable;
}
