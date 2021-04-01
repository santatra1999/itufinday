package element;

public class TransposeDetailOffreAppel {
	
	String nom_offre;
	int id_offre_and_type;
	int meme;
	int different;
	int international;
	public String getNom_offre() {
		return nom_offre;
	}
	public void setNom_offre(String nom_offre) {
		this.nom_offre = nom_offre;
	}
	public int getId_offre_and_type() {
		return id_offre_and_type;
	}
	public void setId_offre_and_type(int id_offre_and_type) {
		this.id_offre_and_type = id_offre_and_type;
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
	public TransposeDetailOffreAppel(String nom_offre, int id_offre_and_type, int meme, int different, int international) {
		super();
		this.nom_offre = nom_offre;
		this.id_offre_and_type = id_offre_and_type;
		this.meme = meme;
		this.different = different;
		this.international = international;
	}
	public TransposeDetailOffreAppel() {
		super();
	}
}
