package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            	mvtmobilemoneyList.add(new Mvtmobilemoney(rs.getInt("idmvt"), rs.getInt("id_mobile_money"), rs.getString("typemvt"), rs.getDouble("value"), rs.getString("date_mvt"),rs.getDouble("frais"), rs.getInt("validation")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }	
		return mvtmobilemoneyList;
	}
}
