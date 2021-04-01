package element;

public class Detailcout {
	
	private int id_appelcout;
	private int id_offre_and_type;
	private int typeappel;
	private int cousec;
	
	public int getId_appelcout() {
		return id_appelcout;
	}
	public void setId_appelcout(int id_appelcout) {
		this.id_appelcout = id_appelcout;
	}
	public int getId_offre_and_type() {
		return id_offre_and_type;
	}
	public void setId_offre_and_type(int id_offre_and_type) {
		this.id_offre_and_type = id_offre_and_type;
	}
	public int getTypeappel() {
		return typeappel;
	}
	public void setTypeappel(int typeappel) {
		this.typeappel = typeappel;
	}
	public int getCousec() {
		return cousec;
	}
	public void setCousec(int cousec) {
		this.cousec = cousec;
	}
	public Detailcout(int id_appelcout, int id_offre_and_type, int typeappel, int cousec) {
		super();
		this.id_appelcout = id_appelcout;
		this.id_offre_and_type = id_offre_and_type;
		this.typeappel = typeappel;
		this.cousec = cousec;
	}
	public Detailcout() {
		super();
	}
	
	
}
