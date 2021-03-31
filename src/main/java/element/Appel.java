package element;

public class Appel {

	String idappel;

	String numSender;

	String numRecep;

	double duree;

	String date;

	public String getIdappel() {
		return idappel;
	}

	public void setIdappel(String idappel) {
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

	public Appel(String idappel, String numSender, String numRecep, double duree, String date) {
		super();
		this.setNumSender(numSender);
		this.setNumRecep(numRecep);
		this.setDuree(duree);
		this.setDate(date);
		this.setIdappel(idappel);
	}

	public Appel() {
		super();
	}

}
