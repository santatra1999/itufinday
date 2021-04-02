package element;

import org.bson.types.ObjectId;

public class Appel {

	ObjectId idappel;

	String numSender;

	String numRecep;

	double duree;

	String date;

	public ObjectId getIdappel() {
		return idappel;
	}

	public void setIdappel(ObjectId idappel) {
		this.idappel = idappel;
	}

	public String getNumSender() {
		return numSender;
	}

	public void setNumSender(String numSender) {
		this.numSender = numSender;
	}

	public String getNumRecep() {
		return numRecep;
	}

	public void setNumRecep(String numRecep) {
		this.numRecep = numRecep;
	}

	public double getDuree() {
		return duree;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Appel(ObjectId idappel, String numSender, String numRecep, double duree, String date) {
		super();
		this.setNumSender(numSender);
		this.setNumRecep(numRecep);
		this.setDuree(duree);
		this.setDate(date);
		this.setIdappel(idappel);
	}

	public Appel(String numSender, String numRecep, double duree, String date) {
		super();
		this.setNumSender(numSender);
		this.setNumRecep(numRecep);
		this.setDuree(duree);
		this.setDate(date);
	}	
	
	public Appel() {
		super();
	}

}
