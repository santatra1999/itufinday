package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.bson.Document;

import element.AppelDaoService;
import element.Client;
import element.Clientnum;

public class Test {

	public static void main(String[] args) throws Exception {
		//new Helper().getConnexion();
		//new ClientDaoService().connexion("CL1","SANTATRA");
		//new Client().setDatenaiss("1999-11-01");
		// new Clientnum().setNum("+1511");
		// new ().getCreditClient(1);
		List<Document> appel = new AppelDaoService().getHistoriqueAppel("+261324323454");
		System.out.println(appel.size());
	}

}
