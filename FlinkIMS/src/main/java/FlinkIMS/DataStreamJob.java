package FlinkIMS;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class DataStreamJob {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Kafka broker addresses
        final String bootstrapServers = "redpanda-0:9092,redpanda-1:9092,redpanda-2:9092";
        final String productTopic = "e-inventory.products";

        KafkaSource<String> source = KafkaSource.<String>builder()
                .setBootstrapServers(bootstrapServers)
                .setTopics(productTopic)
                .setGroupId("e-ims-group")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .build();

        DataStream<String> productStream = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka source");

        productStream.print();

        env.execute("Flink Kafka Source Example");
    }
}





