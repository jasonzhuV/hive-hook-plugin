
# hive-hook-plugin


## Demo-Preview

安装本插件后，执行hive语句或者对HMS的api进行操作，会在`/tmp/hive`目录下产生日志。
![](http://image-picgo.test.upcdn.net/img/20210608144537.png)

## Table of contents
- [Demo-Preview](#Demo-Preview)
- [Usage](#Usage)
- [Installation](#Installation)
- [Development](#Development)

## Usage
[(Back to top)](#table-of-contents)
- 捕获HMS的操作
- 捕获对Hive表和库的操作
- 捕获读取数据的记录
- 捕获写出数据的记录
- 捕获字段级别数据血缘关系
- 形成日志文件

## Installation
[(Back to top)](#table-of-contents)
1. 将`hive-hook-plugin-1.0-SNAPSHOT.jar`复制到hive/lib目录内
2. 配置`hive-site.xml`文件
    ```shell script
    <property>
      <name>hive.metastore.event.listeners</name>
      <value>org.data.meta.hive.listener.MetaStoreListener</value>
    </property>
    <property>
      <name>hive.metastore.pre.event.listeners</name>
      <value>org.data.meta.hive.listener.MetaStorePreAuditListener</value>
    </property>
    <property>
      <name>hive.exec.post.hooks</name>
      <value>org.apache.hadoop.hive.ql.hooks.LineageLogger,org.data.meta.hive.hook.LineageLoggerHook</value>
    </property>
    ```
3. 打开hive cli 执行导数操作
```sql
insert into dw select id ,"999" from t;
```



# Development
[(Back to top)](#table-of-contents)

在项目根目录，执行mvn编译。
```shell script
mvn clean package
```
