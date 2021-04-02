package element;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Appelcredit {
	
	int id_appel;
	int id_client_num;
	double valeur;
	String date_depense;
	public int getId_appel() {
		return id_appel;
	}
	public void setId_appel(int id_appel) {
		this.id_appel = id_appel;
	}
	public int getId_client_num() {
		return id_client_num;
	}
	public void setId_client_num(int id_client_num) {
		this.id_client_num = id_client_num;
	}
	public double getValeur() {
		return valeur;
	}
	public void setValeur(double valeur) {
		this.valeur = valeur;
	}
	public String getDate_depense() {
		return date_depense;
	}
	public void setDate_depense(String date_depense) {
		this.date_depense = date_depense;
	}
	public Appelcredit(int id_appel, int id_client_num, double valeur, String date_depense) {
		super();
		this.setId_appel(id_appel);
		this.setId_client_num(id_client_num);
		this.setValeur(valeur);
		this.setDate_depense(date_depense);
	}
	public Appelcredit(int id_client_num, double valeur, String date_depense) {
		super();
		this.setId_client_num(id_client_num);
		this.setValeur(valeur);
		this.setDate_depense(date_depense);
	}	
	public Appelcredit() {
		super();
	}
	
	public void save(Connection conn) throws Exception {
		
		PreparedStatement pst=null; 
        String sql="INSERT INTO APPELCREDIT VALUES(NEXTVAL(APPELCREDIT_Sequence),?,coutAppel,dateDeCheck);";           
        
        try {        	
        	pst=conn.prepareStatement(sql);
            pst.setInt(1, this.getId_client_num());
            pst.setDouble(2, this.getValeur());
            pst.setString(3, this.getDate_depense());
            pst.executeUpdate(); 
            //conn.commit();
        } catch(Exception ex) {
        	//conn.rollback();
        	throw ex;
        } finally {
            if(pst!=null) pst.close();        
        }			
	}

}
