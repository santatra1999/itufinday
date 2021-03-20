package element;

import java.util.HashMap;
import utils.Exceptionako;

public class Client {
	int id_client;
	String nom;
	String datenaiss;
	String mdp;
	String identif;
	String num;
	double chaff;
	
	public double getChaff() {
		return chaff;
	}

	public void setChaff(double chaff) {
		this.chaff = chaff;
	}

	public Client(String nom, String datenaiss, String mdp, String identif,String num) throws Exception {
		super();
		this.setNom(nom);
		this.setDatenaiss(datenaiss);
		this.setMdp(mdp);
		this.setIdentif(identif);
		this.setNum(num);
	}
	
	public Client(String identif,String num,double chaff) throws Exception {
		super();
		this.setIdentif(identif);
		this.setNum(num);
		this.setChaff(chaff);
	}	
	
	public Client(HashMap<String, Object> formData) throws Exception {
		super();
		this.setNom(formData.get("nom").toString());
		this.setDatenaiss(formData.get("datenaiss").toString());
		this.setMdp(formData.get("mdp").toString());
		this.setIdentif(formData.get("identif").toString());
		this.setNum(formData.get("num").toString());
	}
	
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) throws Exception {
		if(nom.compareTo("") == 0) {
			throw new Exception("Nom obligatoire");
		}
		this.nom = nom;
	}
	public String getDatenaiss() {
		return datenaiss;
	}
	public void setDatenaiss(String datenaiss) throws Exception {
		if(datenaiss.compareTo("") == 0) {
			throw new Exception("Date de naissance obligatoire");
		}
		new Exceptionako().checkTimestamp(datenaiss,"Date de naissance", "yyyy-MM-dd");
		this.datenaiss = datenaiss;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) throws Exception {
		if(mdp.compareTo("") == 0) {
			throw new Exception("Mot de passe obligatoire");
		}		
		this.mdp = mdp;
	}
	public String getIdentif() {
		return identif;
	}
	public void setIdentif(String identif) throws Exception {
		if(identif.compareTo("") == 0) {
			throw new Exception("Identifiant obligatoire");
		}		
		this.identif = identif;
	}

	public String getNum() {
		return num;
	}
	public void setNum(String num) throws Exception {
		if(num.compareTo("") == 0) {
			throw new Exception("Numéro obligatoire");
		}
		if(num.length() > 16 || num.length() < 6 || !num.startsWith("+")) {
			throw new Exception("Numéro invalide");
		}
		this.num = num;
	}	
	
	public Client(int id_client, String nom, String datenaiss, String mdp, String identif) throws Exception {
		super();
		this.setId_client(id_client);
		this.setNom(nom);
		this.setDatenaiss(datenaiss);
		this.setMdp(mdp);
		this.setIdentif(identif);
	}
	
	public Client() {
		super();
	}

	public Client(String identif, String mdp) throws Exception {
		super();
		this.setMdp(mdp);
		this.setIdentif(identif);		
	}	
	
}
