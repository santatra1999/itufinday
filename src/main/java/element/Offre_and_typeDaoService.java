package element;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class Offre_and_typeDaoService {
	
	public void saveOffre_and_type(Offre_and_type offre) throws Exception {
		PreparedStatement pst=null; 
        String sql = "INSERT INTO offre_and_type VALUES (NEXTVAL('OffreAndType_Sequence'),?,?,?)";
        Connection conn = null;    
        try {
        	conn = new Helper().getConnexionPsql();
        	int id_offre =  new Offre().getIdOffreByName(offre.getNom_offre(), conn);
        	int id_type_offre =  new Offre().getIdTypeByName(offre.getNom_type_offre(), conn);
        	pst=conn.prepareStatement(sql);
            pst.setInt(1, id_offre);     
            pst.setInt(2, id_type_offre);  
            pst.setDouble(3, offre.getValeur());     
            System.out.println(pst);  
            conn.commit();
        } catch(Exception ex) {
        	conn.rollback();
        	throw ex;
        } finally {
            if(pst!=null) pst.close();
            if(conn!=null) conn.close();
        }   		
	}
	
	public void update(int id_offre_and_type, Offre_and_type o) throws Exception {
        PreparedStatement pst = null;
        Connection conn = null;
        
        String sql = "UPDATE offre_and_type SET valeur=? WHERE id_offre_and_type=?";
        try{
            conn = new Helper().getConnexionPsql();
            pst = conn.prepareStatement(sql);
            pst.setDouble(1, o.getValeur());
            pst.setInt(2, id_offre_and_type);
        	System.out.println(pst);
            pst.executeUpdate();
        	conn.commit();
        }catch(Exception e){
        	conn.rollback();
        	throw e;
        }finally{
            if(pst!=null)pst.close();
            if(conn!=null)conn.close();
        }	
	}	
	
	public void delete(int id_offre_and_type) throws Exception {
        PreparedStatement pst = null;
        Connection conn = null;
        
        String sql = "DELETE FROM offre_and_type WHERE id_offre_and_type=?";
        try{
            conn = new Helper().getConnexionPsql();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id_offre_and_type);
        	System.out.println(pst);
            pst.executeUpdate();
        	conn.commit();
        }catch(Exception e){
        	conn.rollback();
        	throw e;
        }finally{
            if(pst!=null)pst.close();
            if(conn!=null)conn.close();
        }	
	}		
}
