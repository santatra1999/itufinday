package element;

public class Detailcout {
	
	private int id_appelcout;
	private int id_offre_and_type;
	private int typeappel;
	private double coutsec;
	
	public double getCoutsec() {
		return coutsec;
	}
	public void setCoutsec(double coutsec) {
		this.coutsec = coutsec;
	}
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

	public Detailcout(int id_appelcout, int id_offre_and_type, int typeappel, double coutsec) {
		super();
		this.id_appelcout = id_appelcout;
		this.id_offre_and_type = id_offre_and_type;
		this.typeappel = typeappel;
		this.coutsec = coutsec;
	}
	public Detailcout() {
		super();
	}
	public Detailcout(int typeappel, double coutsec) {
		super();
		this.typeappel = typeappel;
		this.coutsec = coutsec;
	}
	
	public Detailcout(int id_offre_and_type,int typeappel, double coutsec) {
		super();
		this.id_offre_and_type = id_offre_and_type;
		this.typeappel = typeappel;
		this.coutsec = coutsec;
	}	
	
}
