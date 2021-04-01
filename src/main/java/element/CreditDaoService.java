package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class CreditDaoService {
	
	public void save(Credit credit, Connection conn) throws SQLException, Exception {
		PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "INSERT INTO CREDIT VALUES (NEXTVAL('Credit_sequence'),?)";
        
        try{
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, credit.getIdmvt());
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
	
	public void achatCredit(int idclient, Credit value) throws Exception {
		Connection conn = null;
		
		try {
			conn = new Helper().getConnexionPsql();
			double solde = new ClientDaoService().getSoldeMvola(idclient, conn); 
			System.out.println("Credit: "+value.getCredit());
			if(solde < value.getCredit()) {
				throw new Exception("Solde insuffisant");
			}
			int id_mobile_money = new MvtmobilemoneyDaoService().getIdMobileMoneyByIdClient(idclient);
			new MvtmobilemoneyDaoService().retraitCredit(id_mobile_money, value.getCredit(), conn);
			Credit credit = new Credit();
			credit.setIdmvt(new MvtmobilemoneyDaoService().getIdMvtByIdMobileMoney(new MvtmobilemoneyDaoService().getIdMobileMoneyByIdClient(idclient)));
			this.save(credit, conn);
		} catch(Exception ex) {
			throw ex;
		} finally {
			if(conn!=null)conn.close();
		}
	}

	
}
