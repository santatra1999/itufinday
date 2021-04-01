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
        
        String sql = "SELECT * FROM V_DETAILCOUT ORDER BY id_appelcout";
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
	
	public void update(Detailcout detailC, int id_appelcout) throws Exception {
		
		PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = "UPDATE detailcout SET typeappel=?,coutsec=? WHERE id_appelcout=?";
        try{
        	conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, detailC.getTypeappel());
            pst.setDouble(2, detailC.getCousec());
            pst.setInt(3, id_appelcout);
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
	
	public void detailInsert(Detailcout detailC) throws Exception {
		
		PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = "INSERT INTO DETAILCOUT VALUES (NEXTVAL('DetailCout_Sequence'),?,?,?)";
        try {
        	conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, detailC.getId_offre_and_type());
            pst.setInt(2, detailC.getTypeappel());
            pst.setDouble(3, detailC.getCousec());
        	System.out.println(pst);
            pst.executeUpdate();
        	conn.commit();
        } catch(Exception e) {
        	conn.rollback();
        	throw e;
        } finally {
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }			
	}	
}
