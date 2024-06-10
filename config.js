
rsconf = {
          _id: "rs0",   // the name of the replica set
          members: [{ _id: 0, host: "mongo-inventory:27017", priority: 1.0 }], //adding this instance to the set
};

rs.initiate(rsconf);  //initiating the replica set configured above

rs.status();  // returning the current status of the replication set


db = db.getSiblingDB("e-inventory"); // Creating database
db.createCollection("products"); // creating collection for products inventory
db.createCollection("orders"); // creating collection for orders recieved
