package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class DetailoffreDaoService {
	
	public ArrayList<Detailoffre> getDetailOffre() throws Exception {
		ArrayList<Detailoffre> detailOffreList = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM V_DETAIL_OFFRE";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	detailOffreList.add(new Detailoffre(rs.getInt("id_offre"),rs.getInt("id_offre_and_type"),rs.getString("nom_offre"), rs.getString("nom_type_offre"), rs.getDouble("value"), rs.getString("duree_valide"), rs.getDouble("valeur"), rs.getInt("priorite"), rs.getString("appel"), rs.getInt("id_appelcout"), rs.getString("debut"), rs.getString("fin"), rs.getInt("MEME"), rs.getInt("DIFFERENT"), rs.getInt("INTERNATIONAL")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }						
		return detailOffreList;		
	}
}
