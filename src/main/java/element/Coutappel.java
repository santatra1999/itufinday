package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.Helper;

public class Coutappel {
	private int typeappel;
	private int coutsec;
	
	public int getTypeappel() {
		return typeappel;
	}
	public void setTypeappel(int typeappel) {
		this.typeappel = typeappel;
	}
	public int getCoutsec() {
		return coutsec;
	}
	public void setCoutsec(int coutsec) {
		this.coutsec = coutsec;
	}
	public Coutappel(int typeappel, int coutsec) {
		super();
		this.typeappel = typeappel;
		this.coutsec = coutsec;
	}
	public Coutappel() {
		super();
	}
	
    public Coutappel getCoutAppel(int similarity, Connection conn) throws Exception {
        Coutappel coutAppel = null;
    	String sql = "SELECT * FROM COUTAPPEL WHERE TypeAppel=?";

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, similarity);
            rs = pst.executeQuery();
            System.out.println(pst);
            while(rs.next()){
            	coutAppel = new Coutappel(rs.getInt("typeappel"), rs.getInt("coutsec"));
            }
        } catch(Exception e) {
            throw e;
        } finally {
            if(pst!=null) pst.close();
            if(rs!=null) rs.close();
        }			    	
    	
    	return coutAppel;
    }
}
