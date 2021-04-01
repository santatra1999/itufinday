package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class MvtmobilemoneyDaoService {
	public ArrayList<Mvtmobilemoney> getDepotNonValide() throws Exception {
		ArrayList<Mvtmobilemoney> mvtmobilemoneyList = new ArrayList<>(); 
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM V_NON_VALIDATE_MVL";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	mvtmobilemoneyList.add(new Mvtmobilemoney(rs.getInt("id_client"),rs.getString("nom"),rs.getString("num"),rs.getInt("idmvt"), rs.getInt("id_mobile_money"), rs.getString("typemvt"), rs.getDouble("value"), rs.getString("date_mvt"),rs.getDouble("frais"), rs.getInt("validation")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }	
		return mvtmobilemoneyList;
	}

	public int getIdmvt(int idclient, String date_mvt, double valeur, String num, Connection conn) throws Exception {
		int idmvt = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "SELECT idmvt FROM V_NON_VALIDATE_MVL where id_client=? AND date_mvt=?::timestamp AND value=? AND validation=0 AND num=?";
        try{
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, idclient);
            pst.setString(2, date_mvt);
            pst.setDouble(3, valeur);
            pst.setString(4, num);
        	System.out.println(pst);
            rs = pst.executeQuery();
            while(rs.next()){
            	idmvt = rs.getInt("idmvt");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }			
		return idmvt;
	}

	public void updateDepot(int idmvt) throws Exception { 
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "UPDATE mvtmobilemoney SET validation = 1 WHERE idmvt = ?";
        try{
            conn = new Helper().getConnexionPsql();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idmvt);
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
	
	public void depot(Mvtmobilemoney mvtMobile) throws Exception {
		PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "INSERT INTO MVTMOBILEMONEY VALUES (NEXTVAL('MvtMobileMoney_sequence'),?,'E',?,NOW(),?,0)";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, mvtMobile.getId_mobile_money());
            pst.setDouble(2, mvtMobile.getValue());
            pst.setDouble(3, mvtMobile.getFrais());
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

	public void retrait(Mvtmobilemoney mvtMobile, Connection conn) throws Exception {
		PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO MVTMOBILEMONEY VALUES (NEXTVAL('MvtMobileMoney_sequence'),?,'S',?,NOW(),0,1)";
        try{
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, mvtMobile.getId_mobile_money());
            pst.setDouble(2, -1*mvtMobile.getValue());
        	System.out.println(pst);
            pst.executeUpdate();
        	conn.commit();
        }catch(Exception e){
        	conn.rollback();
        	throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }	
	}	
	
	/*public void saveMoneyClient(Mvtmobilemoney mvtMobileInput) throws Exception {
        Connection conn = null;
        try {
        	conn = new Helper().getConnexionPsql();	
            Mvtmobilemoney mvtMobile = new Mvtmobilemoney(mvtMobileInput.getId_mobile_money(),"E", mvtMobileInput.getValue(), "NOW()", mvtMobileInput.getFrais(), 0); 
            this.save(mvtMobile, conn);
        	conn.commit();
        } catch(Exception e) {
        	conn.rollback();
        	throw e;
        } finally {
            if(conn!=null)conn.close();
        }	
		
	}*/
	
	public int getIdMobileMoneyByIdClient(int idclient) throws Exception {
		int id_mobile_money = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT id_mobile_money FROM V_DETAIL_NUM WHERE id_client=?";
        Connection conn = null;
        
        try {
        	conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, idclient);
        	System.out.println(pst);
            rs = pst.executeQuery();
            while(rs.next()){
            	id_mobile_money = rs.getInt("id_mobile_money");
            }
        }catch(Exception e){
	        throw e;
	    }finally{
	        if(pst!=null)pst.close();
	        if(rs!=null)rs.close();
	        if(conn!=null)conn.close();
	    }	       
		return id_mobile_money;
	}
	
	public int getIdMvtByIdMobileMoney(int id_mobile_money) throws Exception {
		int idmvt = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT IDMVT FROM MVTMOBILEMONEY WHERE TYPEMVT LIKE 'S' AND ID_MOBILE_MONEY=? AND DATE_MVT=(SELECT MAX(DATE_MVT) FROM MVTMOBILEMONEY WHERE ID_MOBILE_MONEY=?)";
        Connection conn = null;
        
        try {
        	conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, id_mobile_money);
            pst.setInt(2, id_mobile_money);
        	System.out.println(pst);
            rs = pst.executeQuery();
            while(rs.next()){
            	idmvt = rs.getInt("IDMVT");
            }
        }catch(Exception e){
	        throw e;
	    }finally{
	        if(pst!=null)pst.close();
	        if(rs!=null)rs.close();
	        if(conn!=null)conn.close();
	    }	       
		return idmvt;
	}	
}
