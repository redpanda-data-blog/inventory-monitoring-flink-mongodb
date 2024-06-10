# Inventory Monitoring with Redpanda, MongoDB, and Apache Flink

This project demonstrates a sample inventory management system using real-time data technologies such as [Change Data Capture (CDC)](https://en.wikipedia.org/wiki/Change_data_capture) from MongoDB, Redpanda for [data streaming](https://en.wikipedia.org/wiki/Streaming_data), and real-time [data stream processing](https://en.wikipedia.org/wiki/Stream_processing) with [Apache Flink](https://flink.apache.org/).

![Architecture for Inventory Management and Monitoring System](https://i.imgur.com/is8od81.png)

## Build the Docker Images

### Run Redpanda

```bash
docker-compose -f ./compose-redpanda.yml up -d
```

### Run MongoDB

```bash
docker-compose -f ./compose-mongo.yml up -d
```

### Run Apache Flink

```bash
docker-compose -f ./compose-flink.yml up -d
```

## Run Your Flink Job

Build the project and create a JAR file:

```bash
cd FlinkIMS
mvn clean package
```

Deploy the JAR file to the Flink job manager:

```bash
docker cp ./target/FlinkIMS-1.0-SNAPSHOT.jar mongo-redpanda-flink-jobmanager-1:/job.jar
docker exec -it mongo-redpanda-flink-jobmanager-1 flink run -d /job.jar
```

## Test Your Application

To see the logs from the Flink task manager, connect to the Docker container:

```bash
docker logs -f mongo-redpanda-flink-taskmanager-1
```

Update and insert data into MongoDB to see the changes:

```bash
docker exec -it mongo-inventory mongosh

use e-inventory

db.products.insertMany([
  {
    itemId: "PER-00000001",
    itemName: "Bananas (Cavendish)",
    itemType: "Produce",
    supplier: {
      supplierId: "SUP-00001",
      name: "Tropical Fruitz Ltd",
      contact: {
        email: "supplier@tropicalfruitz.com",
        phone: "+1 (555) 555-5555"
      }
    },
    unitPrice: 2.5,
    unitOfMeasure: "kg",
    reorderPoint: 5,
    reorderQuantity: 50,
    perishable: true,
    batches: [
      {
        batchId: "PER-00001-A",
        purchaseDate: "2024-04-21",
        expiryDate: "2024-04-25",
        currentStock: 25,
        discount: {
          enabled: true,
          discountType: "percentage",
          discountValue: 0.0
        }
      }
    ]
  }
])
```

This setup will help you monitor inventory in real-time using CDC from MongoDB, data streaming with Redpanda, and stream processing with Apache Flink.
