package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class OffreDaoService {
	public ArrayList<Offre> getOffreBeMpividy() throws Exception {
		ArrayList<Offre> offreList= new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM V_OFFRE_BE_MPIFIDY";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	offreList.add(new Offre(rs.getString("nom_offre"), rs.getInt("usage"), rs.getDouble("chaff")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }				
		return offreList;
	}
	
	public ArrayList<Offre> getOffre() throws Exception {
		ArrayList<Offre> offreList= new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM offre";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	offreList.add(new Offre(rs.getInt("id_offre"), rs.getString("nom_offre"), rs.getDouble("value"), rs.getInt("duree_valide"), rs.getInt("priorite")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }				
		return offreList;
	}

	public void saveOffre(HashMap<String, Object> formData) throws Exception {
        PreparedStatement pst=null; 
        String sql = "INSERT INTO OFFRE VALUES (NEXTVAL('Offre_sequence'),?)";
        Connection conn = null;    
        String nom_type_offre = formData.get("nom_type_offre").toString();
        if(nom_type_offre.compareTo("") == 0) {
        	throw new Exception("Nom type offre obligatoire");
        }
        try {
        	conn = new Helper().getConnexionPsql();
        	new Offre().controllerNomTypeOffre(nom_type_offre, conn);
        	pst=conn.prepareStatement(sql);
            pst.setString(1, nom_type_offre);       
            pst.executeUpdate();    
            conn.commit();
        } catch(Exception ex) {
        	conn.rollback();
        	throw ex;
        } finally {
            if(pst!=null) pst.close();
        }   
	}
	
	//public void saveTypeOffre(HashMap<String, Object> formData) throws Exception {	
}
