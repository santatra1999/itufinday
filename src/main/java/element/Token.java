package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.Helper;

public class Token {
	int id_client;
	String token;
	String date_expir;
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDate_expir() {
		return date_expir;
	}
	public void setDate_expir(String date_expir) {
		this.date_expir = date_expir;
	}
	public Token(int id_client, String token, String date_expir) {
		super();
		this.id_client = id_client;
		this.token = token;
		this.date_expir = date_expir;
	}
	public Token() {
		super();
	}
	
	public void deleteToken(Connection conn) throws Exception {
        PreparedStatement pst = null;
        
        String sql = "DELETE FROM TOKEN WHERE date_expir <= now()";
        try{
            pst = conn.prepareStatement(sql);
            int delete = pst.executeUpdate();
        	conn.commit();
        	if(delete == 1) {
        		throw new Exception("Veuiller vous connecter");
        	}
        }catch(Exception e){
        	conn.rollback();
        	throw e;
        }finally{
            if(pst!=null)pst.close();
        }			
	}
}
