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
        
        String sql = "SELECT * FROM V_RESTE_ACHAT_OFFRE where id_client=? ORDER BY dateachat DESC";
        try{
            conn = new Helper().getConnexionPsql();
            //new Token().deleteToken(conn);
        	pst = conn.prepareStatement(sql);
        	pst.setInt(1, idclient);
            rs = pst.executeQuery();
            while(rs.next()){
            	achatOffreList.add(new Achatoffre(rs.getInt("id_client"), rs.getInt("id_achat_offre"), rs.getInt("id_offre"), rs.getInt("id_client_num"), rs.getString("dateachat"), rs.getString("date_expir"), rs.getString("nom_offre"), rs.getString("nom_type_offre"), rs.getDouble("valeur"), rs.getDouble("reste"), rs.getString("appel")));
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
	
	public void save(Achatoffre achatOffre, Connection conn) throws Exception {
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
        		throw new Exception("Credit insuffisant");
        	}
        	int id_client_num = new Clientnum().getId_client_numById_client(id_client, conn);
        	achatOffre.setId_client_num(id_client_num);
        	this.save(achatOffre, conn);
        	conn.commit();
        } catch(Exception ex) {
        	conn.rollback();
        	throw ex;
        } finally {
            if(conn!=null) conn.close();        
        }			
	}
	
	public ArrayList<Achatoffre> getResteAchatOffre(int ID_CLIENT_NUM, String dateDeCheck, String typeOffre, Connection conn) throws Exception {
		
		ArrayList<Achatoffre> achatOffreList = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "SELECT \r\n"
        		+ "	v_reste_achat_offre.*, offre_and_type.id_offre_and_type \r\n"
        		+ "FROM \r\n"
        		+ "	v_reste_achat_offre \r\n"
        		+ "	JOIN offre ON v_reste_achat_offre.id_offre=offre.id_offre \r\n"
        		+ "	join offre_and_type ON offre.id_offre=offre_and_type.id_offre \r\n"
        		+ "	JOIN type_offre ON type_offre.id_type_offre=offre_and_type.id_type_offre \r\n"
        		+ "WHERE \r\n"
        		+ "	v_reste_achat_offre.ID_CLIENT_NUM = ? \r\n"
        		+ "	AND v_reste_achat_offre.DATE_EXPIR > ?::timestamp(0) \r\n"
        		+ "	AND type_offre.NOM_TYPE_OFFRE LIKE '%"+typeOffre+"%' \r\n"
        		+ "ORDER BY offre.duree_valide ASC;";
        try{
        	pst = conn.prepareStatement(sql);
        	pst.setInt(1, ID_CLIENT_NUM);
        	pst.setString(2, dateDeCheck);
            System.out.println(pst);
        	rs = pst.executeQuery();
            while(rs.next()){
            	achatOffreList.add(new Achatoffre(rs.getInt("id_client"), rs.getInt("id_achat_offre"), rs.getInt("id_offre"), rs.getInt("id_client_num"), rs.getString("dateachat"), rs.getString("date_expir"), rs.getString("nom_offre"), rs.getString("nom_type_offre"), rs.getDouble("valeur"), rs.getDouble("reste"), rs.getString("appel"), rs.getInt("id_offre_and_type")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }						
		return achatOffreList;
	}
}
