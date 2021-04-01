package element;

public class Credit {
	
	private int id_transact;
	private int idmvt;
	private double credit;
	
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) throws Exception {
		if(credit <= 0) throw new Exception("Credit invalide");
		this.credit = credit;
	}
	public int getId_transact() {
		return id_transact;
	}
	public void setId_transact(int id_transact) {
		this.id_transact = id_transact;
	}
	public int getIdmvt() {
		return idmvt;
	}
	public void setIdmvt(int idmvt) {
		this.idmvt = idmvt;
	}
	
	public Credit(int id_transact, int idmvt) {
		super();
		this.id_transact = id_transact;
		this.idmvt = idmvt;
	}
	
	public Credit(double credit) throws Exception {
		super();
		this.setCredit(credit);
	}	
	
	public Credit() {
		super();
	}	
}
