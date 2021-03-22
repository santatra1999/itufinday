package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public void saveOffre_and_type(String nom_type_offre, String nom_offre, double valeur_ot) throws Exception {
		PreparedStatement pst=null; 
        String sql = "INSERT INTO offre_and_type VALUES (NEXTVAL('OffreAndType_Sequence'),?,?,?)";
        Connection conn = null;    
        try {
        	conn = new Helper().getConnexionPsql();
        	int id_offre =  new Offre().getIdOffreByName(nom_offre, conn);
        	int id_type_offre =  new Offre().getIdTypeByName(nom_type_offre, conn);
        	pst=conn.prepareStatement(sql);
            pst.setInt(1, id_offre);     
            pst.setInt(2, id_type_offre);     
            pst.setDouble(3, valeur_ot);     
            System.out.println(pst);
            pst.executeUpdate();    
            conn.commit();
        } catch(Exception ex) {
        	conn.rollback();
        	throw ex;
        } finally {
            if(pst!=null) pst.close();
        }   		
	}

	public void saveOffre (String nom_offre, double value, double duree_valide, int priorite) throws Exception {
        PreparedStatement pst=null; 
        String sql = "INSERT INTO OFFRE VALUES (NEXTVAL('Offre_sequence'),?,?,?,?)";
        Connection conn = null;    
        try {
        	conn = new Helper().getConnexionPsql();
        	pst=conn.prepareStatement(sql);
            pst.setString(1, nom_offre);
            pst.setDouble(2, value);
            pst.setDouble(3, duree_valide);
            pst.setInt(4, priorite);
            System.out.println(pst);
            pst.executeUpdate();    
            conn.commit();
        } catch(Exception ex) {
        	conn.rollback();
        	throw ex;
        } finally {
            if(pst!=null) pst.close();
        }   
	}		
	
	public void update(String nom_offre, Offre offre) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "UPDATE offre SET value=?,duree_valide=?,priorite=? WHERE nom_offre = ?";
        try{
            conn = new Helper().getConnexionPsql();
            //new Offre().controllerNomOffre(nom_offre, conn);
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, offre.getValue());
            pst.setInt(2, offre.getDuree_valide());
            pst.setInt(3, offre.getPriorite());
            pst.setString(4, nom_offre);
        	System.out.println(pst);
            pst.executeUpdate();
        	conn.commit();
        }catch(Exception e){
        	conn.rollback();
        	throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }	
	}
	
}
