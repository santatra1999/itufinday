package element;

public class Achatoffre {
	
	public int id_achat_offre;
	public int id_offre;
	int id_client_num;
	String dateachat;
	String date_expir;
	String nom_offre;
	double reste;
	int idclient;
	
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

	public Achatoffre(int id_achat_offre, int id_offre, int id_client_num, String dateachat, String date_expir,String nom_offre, double reste, int idclient) {
		super();
		this.setId_achat_offre(id_achat_offre);
		this.setId_offre(id_offre);
		this.setId_client_num(id_client_num);
		this.setDateachat(dateachat);
		this.setDate_expir(date_expir);
		this.setNom_offre(nom_offre);
		this.setReste(reste);
		this.setIdclient(idclient);
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
