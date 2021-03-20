package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Helper {
    
    public Connection getConnexionPsql() throws Exception {
    	Connection connexion = null;
    	try {
            //Class.forName("org.postgresql.Driver");
            this.connexion = DriverManager
            .getConnection("postgres://bsalfegetstjgt:4a81fcf6648805e3e2ffe666ddce79672e8bfb6abd4e5473d47f5c23d98ce7d8@ec2-34-195-233-155.compute-1.amazonaws.com:5432/d9dgm3pbn81te3",
             "bsalfegetstjgt", 
             "4a81fcf6648805e3e2ffe666ddce79672e8bfb6abd4e5473d47f5c23d98ce7d8");
            //connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FINDAY", "adminFinday", "123456");
            connexion.setAutoCommit(false);
        } catch (SQLException e) {
            throw e;
        }
        System.out.println("Opened PSQL database successfully");
        return connexion;
    }

    public MongoDatabase getConnexionMongodb() throws Exception {
        String url = "mongodb://localhost:27017/?readPreference=primary&ssl=false";
        MongoClient mongo = null;
        MongoDatabase database = null;
        try {
        	mongo = MongoClients.create(url);
        	database = mongo.getDatabase("MOBILE");
        } catch(Exception ex) {
	    	throw ex;
	    }
        System.out.println("Opened MongoDb database successfully");
	    return database;
    }

}
