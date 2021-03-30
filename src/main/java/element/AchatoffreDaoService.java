package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class AchatoffreDaoService {
	public ArrayList<Achatoffre> getOffre(int idclient) throws Exception {
		ArrayList<Achatoffre> achatOffreList = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM V_RESTE_ACHAT_OFFRE where id_client=?";
        try{
            conn = new Helper().getConnexionPsql();
            new Token().deleteToken(conn);
        	pst = conn.prepareStatement(sql);
        	pst.setInt(1, idclient);
            rs = pst.executeQuery();
            while(rs.next()){
            	achatOffreList.add(new Achatoffre(rs.getInt("id_achat_offre"), rs.getInt("id_offre"), rs.getInt("id_client_num"), rs.getString("dateachat"), rs.getString("date_expir"), rs.getString("nom_offre"), rs.getDouble("reste"), rs.getInt("id_client")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }						
		return achatOffreList;
	}
}
