package element;

import java.util.ArrayList;
import static com.mongodb.client.model.Filters.*;
import java.util.List;

import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import utils.Helper;

@Component
public class AppelDaoService {
    public ArrayList<Appel> getHistoriqueAppel(String numClient) throws Exception {
    	ArrayList<Appel> appelList = new ArrayList<Appel>();
    	MongoDatabase database = null;
        MongoCursor<Document> cursor = null;
        List<Document> res = new ArrayList<>();
        
	    try {
        	database = new Helper().getConnexionMongodb();
	        MongoCollection<Document> collection = database.getCollection("Appel");
	        FindIterable<Document> iterDoc = collection.find(or(eq("numSender", numClient), eq("numRecep", numClient))).sort(new Document("date", +1));
	        cursor = iterDoc.cursor();
	        res = new ArrayList<>();
	        while(cursor.hasNext()){
	            res.add(cursor.next());
	        }
	        for(Document doc: res) {
	        	appelList.add(new Appel(doc.getObjectId("_id").toString(), doc.getString("numSender"), doc.getString("numRecep"), doc.getDouble("duree"), doc.getDate("date").toString()));
	        }
	    } catch(Exception ex) {
	    	throw ex;
	    } finally {
	    	if(cursor!=null) cursor.close();
	    }
	    
        return appelList;
    }
}
