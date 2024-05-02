package me.jnpmarques;

import java.util.Map;

import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.storage.OffsetStorageReader;

public interface RecordFactory<R, O> {
    public SourceRecord createRecord(R inputRecord);

    /**
     * 
     * @param offsetStorageReader
     * @return a map with the current offset for each partition
     */
    public Map<String , O> getOffsetMap(OffsetStorageReader offsetStorageReader);

    public void stop();
}
