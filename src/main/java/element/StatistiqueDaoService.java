package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class StatistiqueDaoService {
	public ArrayList<Statistique> getChaffJour() throws Exception {
		ArrayList<Statistique> listChaffJour = new ArrayList<Statistique>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM V_CHAFF_JOUR";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	listChaffJour.add(new Statistique(rs.getDouble("chaff"), rs.getString("date_mvt")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }		
		return listChaffJour;
	}
	
	public ArrayList<Statistique> getFrequenceUsage() throws Exception {
		ArrayList<Statistique> listChaffJour = new ArrayList<Statistique>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM V_FREQUENCE_USAGE";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	listChaffJour.add(new Statistique(rs.getInt("frequence"), rs.getString("date_mvt")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }		
		return listChaffJour;
	}
	
	public double getChiffreAffaire() throws Exception {
		double chaff = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM V_TOTAL_CHAFF";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	chaff = rs.getDouble("chaff");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }		
		return chaff;
	}	
	
	public double getCountClient() throws Exception {
		double isa = 0;
		PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM V_COUNT_CLIENT";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	isa = rs.getDouble("COUNT");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }				
		return isa;
	}
}
