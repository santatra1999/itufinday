package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class DetailcoutDaoService {
	public ArrayList<Detailcout> getDetailCout() throws Exception {
		ArrayList<Detailcout> detailCoutList = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM V_DETAILCOUT";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	detailCoutList.add(new Detailcout(rs.getInt("id_appelcout"),rs.getInt("id_offre_and_type"),rs.getInt("typeappel"), rs.getInt("coutsec")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }						
		return detailCoutList;
	}
}
