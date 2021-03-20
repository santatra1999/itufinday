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
    public List<Document> getHistoriqueAppel(String numClient) throws Exception {
        MongoDatabase database = null;
        MongoCursor<Document> cursor = null;
        List<Document> res = new ArrayList<>();
        
	    try {
        	database = new Helper().getConnexionMongodb();
	        MongoCollection<Document> collection = database.getCollection("Appel");
	        Document filter = new Document();
	        filter.append("numSender", "+261324323454");
	        filter.append("numRecep", "+261324323454");
	        FindIterable<Document> iterDoc = collection.find(filter);
	        cursor = iterDoc.cursor();
	        res = new ArrayList<>();
	        while(cursor.hasNext()){
	            res.add(cursor.next());
	        }
	    } catch(Exception ex) {
	    	throw ex;
	    } finally {
	    	if(cursor!=null) cursor.close();
	    }
	    
        return res;
    }
}
