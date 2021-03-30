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
            Class.forName("org.postgresql.Driver");
            //connexion = DriverManager
            //.getConnection("postgres://jiyomcuyrxqxyi:05a8287e0385101cbce0ca05adfdc883707c85abd1058f6a683c93fa048df115@ec2-3-221-49-44.compute-1.amazonaws.com:5432/d9pd7mk2lhhd2b",
             //"jiyomcuyrxqxyi", 
             //"05a8287e0385101cbce0ca05adfdc883707c85abd1058f6a683c93fa048df115");
            connexion = DriverManager.getConnection("jdbc:postgresql://postgresql-24249-0.cloudclusters.net:24249/FINDAY", "adminFinday", "12345678");
            //connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FINDAY", "adminFinday", "123456");
            connexion.setAutoCommit(false);
        } catch (SQLException e) {
            throw e;
        }
        System.out.println("Opened PSQL database successfully");
        return connexion;
    }

    public MongoDatabase getConnexionMongodb() throws Exception {
        //String url = "mongodb://localhost:27017/?readPreference=primary&ssl=false";
    	String url = "mongodb+srv://MOBILE:arminvanbuuren1999!@cluster0.i7ye3.mongodb.net/MOBILE";
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
