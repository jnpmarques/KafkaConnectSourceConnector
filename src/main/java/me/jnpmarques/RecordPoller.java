package me.jnpmarques;

import java.util.List;
import java.util.Map;

public interface RecordPoller<R, O> {

    void setConfigs(Map<String, String> configs);

    void setOffsetMaps(Map<String, O> offsetMap);

    List<R> pollRecords();

    void stop();
}
