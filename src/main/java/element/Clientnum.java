package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.Helper;

public class Clientnum {
	int id_client_num;
	int id_client;
	String num;
	public Clientnum(int id_client_num, int id_client, String num) {
		super();
		this.id_client_num = id_client_num;
		this.id_client = id_client;
		this.num = num;
	}
	public Clientnum() {
		super();
	}
	public int getId_client_num() {
		return id_client_num;
	}
	public void setId_client_num(int id_client_num) {
		this.id_client_num = id_client_num;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) throws Exception {
		if(num.length() > 16 || num.length() < 6 || !num.startsWith("+")) {
			throw new Exception("Numéro invalide");
		}
		this.num = num;
	}
	
	public void save(Connection conn) throws Exception {
        PreparedStatement pst=null; 
        String sql = "INSERT INTO CLIENTNUM "
        		+ "SELECT NEXTVAL('ClientNum_sequence'),ID_CLIENT,?"
        		+ "FROM CLIENT WHERE NOM='Rakoto' AND MDP=encode(digest('123456', 'sha1'), 'hex') AND DATENAISS='1999-10-31'";           
        try {
        	pst=conn.prepareStatement(sql);
            pst.setInt(1, this.getId_client());
            pst.setString(2, this.getNum());
            pst.executeUpdate();    
        } catch(Exception ex) {
            throw ex;
        } finally {
            if(pst!=null) pst.close();
        }   		
	}
	
	public void save() throws Exception {
        Connection conn = null;     
        try {
        	conn = new Helper().getConnexionPsql();
        	this.save(conn);
        	conn.commit();
        } catch(Exception ex) {
        	conn.rollback();
            throw ex;
        } finally {
            if(conn!=null) conn.close();
        }   		
	}	
	
	public int getId_client_numById_client(int id_client, Connection conn) throws Exception {
		
		int id_client_num = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT id_client_num from clientnum WHERE id_client=?";
        
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id_client);
            rs = pst.executeQuery();
            System.out.println(pst);
            while(rs.next()) {
            	id_client_num  = rs.getInt("id_client_num");
            }
        } catch(Exception e) {
            throw e;
        } finally {
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }		
	
		return id_client_num ;
	}	
}
