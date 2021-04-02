package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Forfaitusage {
	
	int id_forfait_usage;
	int id_achat_offre;
	String date_usage;
	double nihena;
	int id_type_offre;
	
	public int getId_forfait_usage() {
		return id_forfait_usage;
	}
	public void setId_forfait_usage(int id_forfait_usage) {
		this.id_forfait_usage = id_forfait_usage;
	}
	public int getId_achat_offre() {
		return id_achat_offre;
	}
	public void setId_achat_offre(int id_achat_offre) {
		this.id_achat_offre = id_achat_offre;
	}
	public String getDate_usage() {
		return date_usage;
	}
	public void setDate_usage(String date_usage) {
		this.date_usage = date_usage;
	}
	public double getNihena() {
		return nihena;
	}
	public void setNihena(double nihena) {
		this.nihena = nihena;
	}
	public int getId_type_offre() {
		return id_type_offre;
	}
	public void setId_type_offre(int id_type_offre) {
		this.id_type_offre = id_type_offre;
	}
	
	public Forfaitusage(int id_forfait_usage, int id_achat_offre, String date_usage, double nihena, int id_type_offre) {
		super();
		this.id_forfait_usage = id_forfait_usage;
		this.id_achat_offre = id_achat_offre;
		this.date_usage = date_usage;
		this.nihena = nihena;
		this.id_type_offre = id_type_offre;
	}
	
	public Forfaitusage() {
		super();
	}

	public void save(Forfaitusage fu, Connection conn) throws SQLException {
        
		PreparedStatement pst=null; 
        String sql="INSERT INTO FORFAITUSAGE VALUES(NEXTVAL('UsageForfait_Sequence'),?,?,?,?)";           
        
        try {        	
        	pst=conn.prepareStatement(sql);
            pst.setInt(1, fu.getId_achat_offre());
            pst.setString(2, fu.getDate_usage());
            pst.setDouble(3, fu.getNihena());
            pst.setInt(4, fu.getId_type_offre());
            pst.executeUpdate(); 
            conn.commit();
        } catch(Exception ex) {
        	conn.rollback();
        	throw ex;
        } finally {
            if(pst!=null) pst.close();        
        }			
	}
}
