package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class TypeOffreDaoService {
	public ArrayList<TypeOffre> getTypeOffre() throws Exception {
		ArrayList<TypeOffre> to = new ArrayList<>();
		PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM type_offre";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	to.add(new TypeOffre(rs.getInt("id_type_offre"), rs.getString("nom_type_offre")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }						
		return to;
	}
}
