FROM bitnami/kafka:3.4.1

# Copy MongoDB source connector plugin files
COPY ./mongo-connector /opt/connectors/mongo-connector

# Copy Connect instance configuration file
COPY ./connect-distributed.properties /opt/bitnami/kafka/config/connect-distributed.properties

# Start Kafka Connect instance in distributed mode with configuration file
CMD ["/opt/bitnami/kafka/bin/connect-distributed.sh", "/opt/bitnami/kafka/config/connect-distributed.properties"]
