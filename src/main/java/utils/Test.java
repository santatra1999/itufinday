package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import element.Appel;
import element.AppelDaoService;
import element.Simulation;

public class Test {

	public static void main(String[] args) throws Exception {
		//new Helper().getConnexion();
		//new ClientDaoService().connexion("CL1","SANTATRA");
		//new Client().setDatenaiss("1999-11-01");
		// new Clientnum().setNum("+1511");
		// new ().getCreditClient(1);

		/*double amount =20021656;
		Locale locale = new Locale("en", "US");      
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		System.out.println(currencyFormatter.format(amount));*/
		//Appel appel = new Appel("+243324323454", "+261324323454", 12, "2021-04-15T09:04:00Z");
		//new AppelDaoService().save(appel);
		ArrayList<Appel> appelList = new AppelDaoService().getHistoriqueAppel(2);
		for(Appel app: appelList) {
			System.out.println(app.getDate());
		}
		//String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		//new Simulation().simulationAppel(1, "+261324323454", "+261344323454",date, 0);
		// String date = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS").format(new Date());
		//System.out.println(date);
	}

}
