package element;

public class Achatoffre {

	private int idclient;
	private int id_achat_offre;
	private int id_offre;
	private int id_client_num;
	private String dateachat;
	private String date_expir;
	private String nom_offre;
	private String nom_type_offre;
	private double valeur;
	private double reste;
	private String appel;
	
	public String getNom_type_offre() {
		return nom_type_offre;
	}
	public void setNom_type_offre(String nom_type_offre) {
		this.nom_type_offre = nom_type_offre;
	}
	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	public String getAppel() {
		return appel;
	}
	public void setAppel(String appel) {
		this.appel = appel;
	}
	public int getIdclient() {
		return idclient;
	}
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}
	public int getId_achat_offre() {
		return id_achat_offre;
	}
	public void setId_achat_offre(int id_achat_offre) {
		this.id_achat_offre = id_achat_offre;
	}
	public int getId_offre() {
		return id_offre;
	}
	public void setId_offre(int id_offre) {
		this.id_offre = id_offre;
	}
	public int getId_client_num() {
		return id_client_num;
	}
	public void setId_client_num(int id_client_num) {
		this.id_client_num = id_client_num;
	}
	public String getDateachat() {
		return dateachat;
	}
	public void setDateachat(String dateachat) {
		this.dateachat = dateachat;
	}
	public String getDate_expir() {
		return date_expir;
	}
	public void setDate_expir(String date_expir) {
		this.date_expir = date_expir;
	}
	public String getNom_offre() {
		return nom_offre;
	}
	public void setNom_offre(String nom_offre) {
		this.nom_offre = nom_offre;
	}
	public double getReste() {
		return reste;
	}
	public void setReste(double reste) {
		this.reste = reste;
	}

	public Achatoffre(int id_client,int id_achat_offre, int id_offre, int id_client_num, String dateachat, String date_expir,String nom_offre, String nom_type_offre, double valeur , double reste, String appel) {
		super();
		this.setId_achat_offre(id_achat_offre);
		this.setId_offre(id_offre);
		this.setId_client_num(id_client_num);
		this.setDateachat(dateachat);
		this.setDate_expir(date_expir);
		this.setNom_offre(nom_offre);
		this.setNom_type_offre(nom_type_offre);
		this.setReste(reste);
		this.setIdclient(id_client);
		this.setAppel(appel);
		this.setValeur(valeur);
	}
	public Achatoffre() {
		super();
	}
	public Achatoffre(int id_offre, int id_client_num) {
		super();
		this.setId_offre(id_offre);
		this.setId_client_num(id_client_num);
	}
	
}
