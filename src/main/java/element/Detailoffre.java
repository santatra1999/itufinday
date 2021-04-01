package element;

public class Detailoffre {
	
	int id_offre;
	int id_offre_and_type;
	String nom_offre;
	String nom_type_offre;
	double value;
	String duree_valide;
	double valeur;
	int priorite;
	String appel;
	int id_appelcout;
	String debut;
	String fin;
	int meme;
	int different;
	int international;
	public int getId_offre() {
		return id_offre;
	}
	public void setId_offre(int id_offre) {
		this.id_offre = id_offre;
	}
	public int getId_offre_and_type() {
		return id_offre_and_type;
	}
	public void setId_offre_and_type(int id_offre_and_type) {
		this.id_offre_and_type = id_offre_and_type;
	}
	public String getNom_offre() {
		return nom_offre;
	}
	public void setNom_offre(String nom_offre) {
		this.nom_offre = nom_offre;
	}
	public String getNom_type_offre() {
		return nom_type_offre;
	}
	public void setNom_type_offre(String nom_type_offre) {
		this.nom_type_offre = nom_type_offre;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getDuree_valide() {
		return duree_valide;
	}
	public void setDuree_valide(String duree_valide) {
		this.duree_valide = duree_valide;
	}
	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	public int getPriorite() {
		return priorite;
	}
	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}
	public String getAppel() {
		return appel;
	}
	public void setAppel(String appel) {
		this.appel = appel;
	}
	public int getId_appelcout() {
		return id_appelcout;
	}
	public void setId_appelcout(int id_appelcout) {
		this.id_appelcout = id_appelcout;
	}
	public String getDebut() {
		return debut;
	}
	public void setDebut(String debut) {
		this.debut = debut;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public int getMeme() {
		return meme;
	}
	public void setMeme(int meme) {
		this.meme = meme;
	}
	public int getDifferent() {
		return different;
	}
	public void setDifferent(int different) {
		this.different = different;
	}
	public int getInternational() {
		return international;
	}
	public void setInternational(int international) {
		this.international = international;
	}
	public Detailoffre(int id_offre, int id_offre_and_type, String nom_offre, String nom_type_offre, double value,
			String duree_valide, double valeur, int priorite, String appel, int id_appelcout, String debut, String fin,
			int meme, int different, int international) {
		super();
		this.id_offre = id_offre;
		this.id_offre_and_type = id_offre_and_type;
		this.nom_offre = nom_offre;
		this.nom_type_offre = nom_type_offre;
		this.value = value;
		this.duree_valide = duree_valide;
		this.valeur = valeur;
		this.priorite = priorite;
		this.appel = appel;
		this.id_appelcout = id_appelcout;
		this.debut = debut;
		this.fin = fin;
		this.meme = meme;
		this.different = different;
		this.international = international;
	}
	public Detailoffre() {
		super();
	}
	
}
