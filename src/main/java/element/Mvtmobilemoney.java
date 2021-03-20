package element;

public class Mvtmobilemoney {
	int idmvt;
	int id_mobile_money;
	String typemvt;
	double value;
	String date_mvt;
	double frais;
	
	int validation;
	public int getIdmvt() {
		return idmvt;
	}
	public void setIdmvt(int idmvt) {
		this.idmvt = idmvt;
	}
	public int getId_mobile_money() {
		return id_mobile_money;
	}
	public void setId_mobile_money(int id_mobile_money) {
		this.id_mobile_money = id_mobile_money;
	}
	public String getTypemvt() {
		return typemvt;
	}
	public void setTypemvt(String typemvt) {
		this.typemvt = typemvt;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getDate_mvt() {
		return date_mvt;
	}
	public void setDate_mvt(String date_mvt) {
		this.date_mvt = date_mvt;
	}
	public double getFrais() {
		return frais;
	}
	public void setFrais(double frais) {
		this.frais = frais;
	}
	public int getValidation() {
		return validation;
	}
	public void setValidation(int validation) {
		this.validation = validation;
	}
	public Mvtmobilemoney(int idmvt, int id_mobile_money, String typemvt, double value, String date_mvt, double frais,
			int validation) {
		super();
		this.setIdmvt(idmvt);
		this.setId_mobile_money(id_mobile_money);
		this.setTypemvt(typemvt);
		this.setValue(value);
		this.setDate_mvt(date_mvt);
		this.setFrais(frais);
		this.setValidation(validation);
	}
	public Mvtmobilemoney() {
		super();
	}
	
	
}
