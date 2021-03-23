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
            System.out.println(offre.getValeur());
            pst.setDouble(3, offre.getValeur());     
            System.out.println(pst);
            //pst.executeUpdate();    
            conn.commit();
        } catch(Exception ex) {
        	conn.rollback();
        	throw ex;
        } finally {
            if(pst!=null) pst.close();
            if(conn!=null) conn.close();
        }   		
	}
}
