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
            //new Token().deleteToken(conn);
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
	
/*	public void save(Achatoffre achatOffre, Connection conn) throws Exception {
        PreparedStatement pst=null; 
        String sql = "INSERT INTO ACHATOFFRE VALUES(NEXTVAL('ID_OFFRE_Sequence'),?,?,NOW())";           
        
        try {        	
        	pst=conn.prepareStatement(sql);
            pst.setInt(1, achatOffre.getId_offre());
            pst.setInt(2, achatOffre.getId_client_num());
            pst.executeUpdate(); 
            //conn.commit();
        } catch(Exception ex) {
        	//conn.rollback();
        	throw ex;
        } finally {
            if(pst!=null) pst.close();        
        }		
	}
	
	public void achatOffre(Achatoffre achatOffre, int id_client) throws Exception {
		Connection conn = null;
        try {        	
        	conn = new Helper().getConnexionPsql();
        	double credit = new ClientDaoService().getCreditClient(id_client);
        	Offre offre = new OffreDaoService().getOffreByIdoffre(achatOffre.getId_offre());
        	if(credit < offre.getValue()) {
        		throw new Exception("Credit");
        	}
        	int id_client_num = new Clientnum().getId_client_numById_client(id_client, conn);
        	achatOffre.setId_client_num(id_client_num);
        	this.save(achatOffre, conn);
        } catch(Exception ex) {
            throw ex;
        } finally {
            if(conn!=null) conn.close();        
        }			
	}
	
	/*public ArrayList<Achatoffre> getResteAchatOffre(int ID_CLIENT_NUM, String dateDeCheck,String typeOffre) throws Exception {
		ArrayList<Achatoffre> achatOffreList = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT * FROM v_reste_achat_offre JOIN offre ON v_reste_achat_offre.id_offre=offre.id_offre WHERE v_reste_achat_offre.ID_CLIENT_NUM=? AND v_reste_achat_offre.DATE_EXPIR> ? AND v_reste_achat_offre.NOM_TYPE_OFFRE LIKE '%?%' ORDER BY DATEACHAT ASC";
        try{
            conn = new Helper().getConnexionPsql();
            //new Token().deleteToken(conn);
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
	}*/
}
