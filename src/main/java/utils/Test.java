package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import element.Appel;
import element.AppelDaoService;

public class Test {

	public static void main(String[] args) throws Exception {
		//new Helper().getConnexion();
		//new ClientDaoService().connexion("CL1","SANTATRA");
		//new Client().setDatenaiss("1999-11-01");
		// new Clientnum().setNum("+1511");
		// new ().getCreditClient(1);
		/*ArrayList<Appel> appel = new AppelDaoService().getHistoriqueAppel(2);
		for(Appel app: appel) {
			System.out.println(app.getNumRecep());
		}*/
		double amount =20021656;
		Locale locale = new Locale("en", "US");      
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		System.out.println(currencyFormatter.format(amount));
	}

}
