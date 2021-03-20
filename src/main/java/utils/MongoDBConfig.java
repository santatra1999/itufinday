package utils;

import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConfig extends AbstractMongoClientConfiguration {

	@Override
	protected String getDatabaseName() {
		return "MOBILE";
	}
	
	public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/?readPreference=primary&ssl=false");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

}

