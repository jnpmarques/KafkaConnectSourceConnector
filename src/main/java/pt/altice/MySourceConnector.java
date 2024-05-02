package pt.altice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySourceConnector extends SourceConnector {
  private static Logger log = LoggerFactory.getLogger(MySourceConnector.class);
  private MySourceConnectorConfig config;

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> map) {
    config = new MySourceConnectorConfig(map);
    System.out.println("Starting Salesforce Connector. My setting is: " + config.getMy());
  }

  @Override
  public Class<? extends Task> taskClass() {
    return MySourceConnectorTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int maxTasks) {
    if (maxTasks > 1) {
      log.warn("Only one task is supported. Ignoring tasks.max which is set to {}", maxTasks);
    }
    List<Map<String, String>> taskConfigs = new ArrayList<>(1);
    taskConfigs.add(new HashMap<>());
    return taskConfigs;
  }

  @Override
  public void stop() {
    // TODO: Do things that are necessary to stop your connector.
  }

  @Override
  public ConfigDef config() {
    return MySourceConnectorConfig.conf();
  }
}
