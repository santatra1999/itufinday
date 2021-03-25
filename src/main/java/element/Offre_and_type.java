package element;

public class Offre_and_type {
	private int id_offre_and_type;
	private int id_offre;
	private int id_type_offre;
	private double valeur;

	private String nom_offre;
	private String nom_type_offre;
	public int getId_offre_and_type() {
		return id_offre_and_type;
	}
	public void setId_offre_and_type(int id_offre_and_type) {
		this.id_offre_and_type = id_offre_and_type;
	}
	public int getId_offre() {
		return id_offre;
	}
	public void setId_offre(int id_offre) {
		this.id_offre = id_offre;
	}
	public int getId_type_offre() {
		return id_type_offre;
	}
	public void setId_type_offre(int id_type_offre) {
		this.id_type_offre = id_type_offre;
	}
	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
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
	public Offre_and_type(int id_offre_and_type, int id_offre, int id_type_offre, double valeur, String nom_offre,
			String nom_type_offre) {
		super();
		this.setId_offre_and_type(id_offre_and_type);
		this.setId_offre(id_offre);
		this.setId_type_offre(id_type_offre);
		this.setValeur(valeur);
		this.setNom_offre(nom_offre);
		this.setNom_type_offre(nom_type_offre);
	}
	public Offre_and_type(int id_offre, int id_type_offre, double valeur) {
		super();
		this.setId_offre(id_offre);
		this.setId_type_offre(id_type_offre);
		this.setValeur(valeur);
	}
	public Offre_and_type(String nom_offre, String nom_type_offre, double valeur) {
		super();
		this.setNom_offre(nom_offre);
		this.setNom_type_offre(nom_type_offre);
		this.setValeur(valeur);
	}	
	public Offre_and_type(double valeur) {
		super();
		this.setValeur(valeur);
	}
	public Offre_and_type() {
		super();
	}	
}
