package me.jnpmarques;

import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;
import org.apache.kafka.connect.storage.OffsetStorageReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySourceConnectorTask extends SourceTask {
  static final Logger log = LoggerFactory.getLogger(MySourceConnectorTask.class);

  private RecordFactory<Object, Object> recordFactory;

  private RecordPoller<Object, Object> recordPoller;

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> configs) {
    // Implement and initialize the recordFactory
    // this.recordFactory = new RecordFactory();

    // Read Current Offsets from the OffsetReader
    var offsetMap = this.recordFactory.getOffsetMap(getOffsetStorageReader());

    // Implement and initialize the recordPoller
    // this.recordPoller = new RecordPoller(configs, offsetMap)
  }

  @Override
  public List<SourceRecord> poll() throws InterruptedException {
    List<SourceRecord> records = new ArrayList<>();
    
    log.info("Polling new Records");
    for (var inputRecord : this.recordPoller.pollRecords()) {
      records.add(this.recordFactory.createRecord(inputRecord));
    }
    log.info("Polled " + records.size() + " new records");
    return records;
  }

  @Override
  public void stop() {
    this.recordFactory.stop();
    this.recordPoller.stop();
  }

  /***
   * 
   * @return
   *         the reader class that provides access to offsets previously stored
   *         by the connector
   *         if the connector hasn't run before, this could return null
   */
  private OffsetStorageReader getOffsetStorageReader() {
    if (context == null) {
      log.debug("No context - assuming that this is the first time the Connector has run");
      return null;
    } else if (context.offsetStorageReader() == null) {
      log.debug("No offset reader - assuming that this is the first time the Connector has run");
      return null;
    } else {
      return context.offsetStorageReader();
    }
  }
}