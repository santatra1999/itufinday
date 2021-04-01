package element;

public class Credit {
	private int id_transact;
	private int idmvt;
	
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
	
	public Credit() {
		super();
	}
	
	
	
}
