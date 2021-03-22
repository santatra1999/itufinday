package element;

public class TypeOffre {
	private int id_type_offre;
	private String nom_type_offre;
	public int getId_type_offre() {
		return id_type_offre;
	}
	public void setId_type_offre(int id_type_offre) {
		this.id_type_offre = id_type_offre;
	}
	public String getNom_type_offre() {
		return nom_type_offre;
	}
	public void setNom_type_offre(String nom_type_offre) {
		this.nom_type_offre = nom_type_offre;
	}
	public TypeOffre(int id_type_offre, String nom_type_offre) {
		super();
		this.setId_type_offre(id_type_offre);
		this.setNom_type_offre(nom_type_offre);
	}
	
}
