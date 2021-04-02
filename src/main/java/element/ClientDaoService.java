package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import utils.Helper;

@Component
public class ClientDaoService extends Client {
	
	public Client connexion(Client clientInput,Connection conn) throws Exception {
		Client client = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM client where identif=? AND mdp=encode(digest(?, 'sha1'), 'hex')";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, clientInput.getIdentif());
            pst.setString(2, clientInput.getMdp());
            rs = pst.executeQuery();
            System.out.println(pst);
            while(rs.next()){
                client = new Client(rs.getInt("id_client"), rs.getString("nom"), rs.getString("datenaiss"), rs.getString("mdp"), rs.getString("identif"));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }		
		return client;
	}
	
	public Client connexion(Client clientInput) throws Exception {
        Client client = null;
		Connection conn = null;
		try{
            conn = new Helper().getConnexionPsql();
            client = this.connexion(clientInput,conn);
            if(client == null) {
            	return client;
            }
            this.saveToken(client.getIdentif(),client.getMdp(),conn); 
            conn.commit();
		}catch(Exception e){
			conn.rollback();
			throw e;
        }finally{
            if(conn!=null)conn.close();
        }		
		return client;		
	}

	public void saveToken(String identif,String mdp,Connection conn) throws Exception {
        PreparedStatement pst=null; 
        String sql = "INSERT INTO token "
        		+ "SELECT id_client,encode(digest(now() || ?, 'sha1'), 'hex'), now() + interval '8h' FROM client WHERE IDENTIF=? AND MDP=?";
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1, mdp);
            pst.setString(2, identif);
            pst.setString(3, mdp);
            System.out.println(pst);
            pst.executeUpdate();    
        } catch(Exception ex) {
            throw ex;
        } finally {
            if(pst!=null) pst.close();
        }   
	}
		
	public void saveClient(Client clientInput) throws Exception {
        PreparedStatement pst=null; 
        String sql = "INSERT INTO client VALUES(NEXTVAL('Client_sequence'),?,?,encode(digest(?, 'sha1'), 'hex'),?)";
        Connection conn = null;        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            conn = new Helper().getConnexionPsql();
        	pst=conn.prepareStatement(sql);
            pst.setString(1, clientInput.getNom());
            Date parsedDate = dateFormat.parse(clientInput.getDatenaiss());
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());            
            pst.setTimestamp(2, timestamp);
            pst.setString(3, clientInput.getMdp());
            pst.setString(4, clientInput.getIdentif());
            System.out.println(pst);            
            pst.executeUpdate();    
            this.saveToken(clientInput.getIdentif(),clientInput.getMdp(),conn); // TOKEN
            this.saveClientNum(clientInput, conn); // NUMERO
            
            conn.commit();
            //saveToken(int id_client,clientInput.getMdp(),conn);
        } catch(Exception ex) {
        	conn.rollback();
        	throw ex;
        } finally {
            if(pst!=null) pst.close();
            if(conn!=null) conn.close();
        }   
	}
	
	public void saveClientNum(Client client, Connection conn) throws Exception {
        PreparedStatement pst=null; 
        String sql = "INSERT INTO CLIENTNUM "
        		+ "SELECT NEXTVAL('ClientNum_sequence'),ID_CLIENT,?"
        		+ "FROM CLIENT WHERE IDENTIF=? AND MDP=encode(digest(?, 'sha1'), 'hex')";           
        try {
        	pst=conn.prepareStatement(sql);
            pst.setString(1, client.getNum());
            pst.setString(2, client.getIdentif());
            pst.setString(3, client.getMdp());
            pst.executeUpdate();    
        } catch(Exception ex) {
            throw ex;
        } finally {
            if(pst!=null) pst.close();
        }		
	}
	
	public ArrayList<Client> getChiffreAffaireClient() throws Exception {
		ArrayList<Client> listClient = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        
        String sql = "SELECT IDENTIF,NUM,SUM(CHaFF) AS CHaFF FROM V_CLIENT_CHFAFF GROUP BY IDENTIF,NUM";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
            	listClient.add(new Client(rs.getString("IDENTIF"), rs.getString("NUM"), rs.getDouble("CHaFF")));
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }			
		return listClient;
	}
	
	public String getClientnumById(int idclient, Connection conn) throws Exception {
		String numClient = "";
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "SELECT num FROM clientnum where id_client=?";
        try{
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, idclient);
        	rs = pst.executeQuery();
            while(rs.next()){
            	numClient = rs.getString("num");
            }
            System.out.println(pst);
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }			
		return numClient;
	}
	
	public double getCreditClient(int idclient, Connection conn) throws Exception {
		double credit = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try{
        	// new Token().deleteToken(conn);
        	String numero = this.getClientnumById(idclient, conn);
        	String sql = "SELECT (COALESCE((SELECT SUM(VALUE) FROM ENTREE_CREDIT WHERE num LIKE '%"+numero+"%'),0))-((COALESCE((SELECT SUM(VALEUR) FROM SORTIE_CREDIT_APPEL WHERE num LIKE '%"+numero+"%'),0))+(COALESCE((SELECT SUM(VALUE) FROM SORTIE_CREDIT_OFFRE WHERE num LIKE '%"+numero+"%'),0))) AS CREDIT";        	
        	pst = conn.prepareStatement(sql);        	
        	System.out.println(pst);
        	rs = pst.executeQuery();
            
        	while(rs.next()){
            	credit = rs.getDouble("CREDIT");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }			
		return credit;
	}

	public double getCreditClient(int idclient) throws Exception {
		double credit = 0;
        Connection conn = null;
        
        try{
        	conn = new Helper().getConnexionPsql();
        	// new Token().deleteToken(conn);
        	credit = this.getCreditClient(idclient, conn);     	
        }catch(Exception e){
            throw e;
        }finally{
            if(conn!=null)conn.close();
        }			
		return credit;
	}	
	
	public int getIdclient(String token) throws Exception {
		int idclient = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = "SELECT id_client FROM token where token=?";
        try{
        	conn = new Helper().getConnexionPsql();
            pst = conn.prepareStatement(sql);
            pst.setString(1, token);
            rs = pst.executeQuery();
            System.out.println(pst);
            while(rs.next()){
            	idclient = rs.getInt("id_client");
            }
            if(idclient == 0) {
            	throw new Exception("Veuillez vous connecter");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }		
	
		return idclient;
	}
	
	public void deconnexion(String token) throws Exception {
        PreparedStatement pst = null;
        Connection conn = null;
        String sql = "DELETE FROM token where token=?";
        try{
        	conn = new Helper().getConnexionPsql();
            pst = conn.prepareStatement(sql);
            pst.setString(1, token);
            pst.executeUpdate();
            conn.commit();
        }catch(Exception e){
        	conn.rollback();
        	throw e;
        }finally{
            if(pst!=null)pst.close();
            if(conn!=null)conn.close();
        }		
	}

	public double getSoldeMvola(int idclient, Connection conn) throws Exception {
		double solde = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "	SELECT \r\n"
        		+ "		SUM(VALUE)-SUM(FRAIS) AS VALUE_MOBILEMONEY \r\n"
        		+ "	FROM \r\n"
        		+ "		MVTMOBILEMONEY\r\n"
        		+ "	JOIN \r\n"
        		+ "		mobilemoney	      ON MVTMOBILEMONEY.ID_MOBILE_MONEY=mobilemoney.ID_MOBILE_MONEY\r\n"
        		+ "	JOIN\r\n"
        		+ "		clientnum ON clientnum.id_client_num=mobilemoney.id_client_num\r\n"
        		+ "	WHERE clientnum.id_client=? AND MVTMOBILEMONEY.validation=1";
        try{
        	// new Token().deleteToken(conn);
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, idclient);
            rs = pst.executeQuery();
            while(rs.next()){
            	solde = rs.getInt("value_mobilemoney");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }		
	
		return solde;
	}	

	public double getSoldeMvola(int idclient) throws Exception {
		double soldeMvola = 0;
		Connection conn = null;
		try {
			conn = new Helper().getConnexionPsql();
			soldeMvola = getSoldeMvola(idclient, conn);
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(conn!=null) conn.close();
		}
		return soldeMvola;
	}
	
	public Client getInformation(int idclient) throws Exception {
		Client client = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection conn = null;
        String sql = "SELECT * FROM V_DETAIL_CLIENT WHERE id_client=?";
        try{
            conn = new Helper().getConnexionPsql();
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, idclient);
            rs = pst.executeQuery();
            System.out.println(pst);
            while(rs.next()){
                client = new Client(rs.getInt("id_client"), rs.getString("nom"), rs.getString("datenaiss"), rs.getString("mdp"), rs.getString("identif"), rs.getString("num"));
            }
        } catch(Exception e) {
            throw e;
        } finally {
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
            if(conn!=null)conn.close();
        }			
		return client;
	}
}
