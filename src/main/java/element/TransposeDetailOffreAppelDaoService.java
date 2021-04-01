package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class TransposeDetailOffreAppelDaoService {
	
	public ArrayList<TransposeDetailOffreAppel> getTranspose() throws Exception {
		
		ArrayList<TransposeDetailOffreAppel> tdoaList = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM v_transpose_detail_offre_appel";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	tdoaList.add(new TransposeDetailOffreAppel(rs.getString("nom_offre"),rs.getInt("id_offre_and_type"),rs.getInt("MEME"), rs.getInt("DIFFERENT"), rs.getInt("INTERNATIONAL")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }						
		return tdoaList;
	}
}
