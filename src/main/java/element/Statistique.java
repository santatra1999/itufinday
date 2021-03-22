package element;

public class Statistique {
	
	private double chaff_jour;
	private String date_mvt_jour;
	private int frequence;
	private String date_mvt_frequence;
	private double chaff_total;
	public double getChaff_jour() {
		return chaff_jour;
	}
	public void setChaff_jour(double chaff_jour) {
		this.chaff_jour = chaff_jour;
	}
	public String getDate_mvt_jour() {
		return date_mvt_jour;
	}
	public void setDate_mvt_jour(String date_mvt_jour) {
		this.date_mvt_jour = date_mvt_jour;
	}
	public int getFrequence() {
		return frequence;
	}
	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}
	public String getDate_mvt_frequence() {
		return date_mvt_frequence;
	}
	public void setDate_mvt_frequence(String date_mvt_frequence) {
		this.date_mvt_frequence = date_mvt_frequence;
	}
	public double getChaff_total() {
		return chaff_total;
	}
	public void setChaff_total(double chaff_total) {
		this.chaff_total = chaff_total;
	}
	
	public Statistique(double chaff, String date_mvt) {
		super();
		this.setChaff_jour(chaff);
		this.setDate_mvt_jour(date_mvt);
	}
	
	public Statistique(int frequence, String date_mvt) {
		super();
		this.setFrequence(frequence);
		this.setDate_mvt_frequence(date_mvt);
	}
	
}
