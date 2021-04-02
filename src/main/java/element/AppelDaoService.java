package element;

import java.sql.Connection;
import java.util.ArrayList;
import static com.mongodb.client.model.Filters.*;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import utils.Helper;

@Component
public class AppelDaoService {
	
    public ArrayList<Appel> getHistoriqueAppel(int idclient) throws Exception {
    	
    	ArrayList<Appel> appelList = new ArrayList<Appel>();
    	MongoDatabase database = null;
        MongoCursor<Document> cursor = null;
        //List<Document> res = new ArrayList<>();
        Connection conn = null;
	    try {
        	conn = new Helper().getConnexionPsql();
        	//new Token().deleteToken(conn);
        	database = new Helper().getConnexionMongodb();
	        MongoCollection<Document> collection = database.getCollection("Appel");
	        String numClient = new ClientDaoService().getClientnumById(idclient, conn);
	        FindIterable<Document> iterDoc = collection.find(or(eq("numSender", numClient), eq("numRecep", numClient))).sort(new Document("date", +1));
	        cursor = iterDoc.cursor();
	        //res = new ArrayList<>();
	        while(cursor.hasNext()) {
	        	Document d=cursor.next();
	        	//res.add(cursor.next());
	        	Appel appel = new Appel(d.getObjectId("_id"), d.getString("numSender"), d.getString("numRecep"), d.getDouble("duree"), d.get("date").toString());
	        	appelList.add(appel);
	        }
	        //for(Document doc: res) {
	        //	appelList.add(new Appel(doc.getObjectId("_id").toString(), doc.getString("numSender"), doc.getString("numRecep"), doc.getDouble("duree"), doc.getDate("date").toString()));
	        //}
	    } catch(Exception ex) {
	    	throw ex;
	    } finally {
	    	if(cursor!=null) cursor.close();
	    	if(conn!=null) conn.close();
	    }
	    
        return appelList;
    }
    
    public void save(Appel appel) throws Exception {
    	
    	MongoDatabase database = null;
    	ArrayList<Document> docs = new ArrayList<>();
    	MongoCollection<Document> collection = null;
    	
    	try {
    		database = new Helper().getConnexionMongodb();
	    	collection = database.getCollection("Appel");
	    	Document d1 = new Document();
	    	d1.append("numSender", appel.getNumSender());
	    	d1.append("numRecep", appel.getNumRecep());
	    	d1.append("duree", appel.getDuree());
	    	d1.append("date", appel.getDate());
	    	docs.add(d1); 
	    	collection.insertMany(docs);
	   } catch(Exception ex) {
		   database.getCollection("Appel").drop();
	   } 
    }
}
