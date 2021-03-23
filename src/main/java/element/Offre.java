package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.Helper;

public class Offre {
	int id_offre;
	String nom_offre;
	double value;
	int duree_valide;
	int priorite;
	int usage;
	double chaff;
	double valeur_ot;
	String nom_type_offre;
	
	public String getNom_type_offre() {
		return nom_type_offre;
	}
	public void setNom_type_offre(String nom_type_offre) {
		this.nom_type_offre = nom_type_offre;
	}
	public Offre(String nom_offre, String nom_type_offre, double value, int duree_valide, double valeur_ot) throws Exception {
		super();
		this.setNom_offre(nom_offre);
		this.setValue(value);
		this.setDuree_valide(duree_valide);
		this.setValeur_ot(valeur_ot);
		this.setNom_type_offre(nom_type_offre);
	}
	public double getValeur_ot() {
		return valeur_ot;
	}
	public void setValeur_ot(double valeur_ot) throws Exception {
		if(Double.toString(valeur_ot).compareTo("") == 0) {
			throw new Exception("Valeur ot obligatoire");
		}
		this.valeur_ot = valeur_ot;
	}
	public double getChaff() {
		return chaff;
	}
	public void setChaff(double chaff) {
		this.chaff = chaff;
	}
	public int getUsage() {
		return usage;
	}
	public void setUsage(int usage) {
		this.usage = usage;
	}
	public int getId_offre() {
		return id_offre;
	}
	public void setId_offre(int id_offre) {
		this.id_offre = id_offre;
	}
	public String getNom_offre() {
		return nom_offre;
	}
	public void setNom_offre(String nom_offre) throws Exception {
		if(nom_offre.compareTo("" ) == 0) {
			throw new Exception("Nom offre obligatoire");
		}
		this.nom_offre = nom_offre;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) throws Exception {
		if(Double.toString(value).compareTo("") == 0) {
			throw new Exception("Valeur obligatoire");
		}
		this.value = value;
	}
	public int getDuree_valide() {
		return duree_valide;
	}
	public void setDuree_valide(int duree_valide) throws Exception {
		if(Integer.toString(duree_valide).compareTo("") == 0) {
			throw new Exception("Duree obligatoire");
		}
		this.duree_valide = duree_valide;
	}
	public int getPriorite() {
		return priorite;
	}
	public void setPriorite(int priorite) throws Exception {
		if(Integer.toString(priorite).compareTo("") == 0) {
			throw new Exception("Priorite obligatoire");
		}
		this.priorite = priorite;
	}
	public Offre(int id_offre, String nom_offre, double value, int duree_valide, int priorite) throws Exception {
		super();
		this.setId_offre(id_offre);
		this.setNom_offre(nom_offre);
		this.setValue(value);
		this.setDuree_valide(duree_valide);
		this.setPriorite(priorite);
	}
	public Offre(String nom_offre, int usage, double chaff) throws Exception {
		super();
		this.setNom_offre(nom_offre);
		this.setUsage(usage);
		this.setChaff(chaff);
	}
	public Offre(String nom_offre, double value,int duree_valide ,int priorite, double valeur_ot) throws Exception {
		super();
		this.setNom_offre(nom_offre);
		this.setValue(value);
		this.setDuree_valide(duree_valide);
		this.setPriorite(priorite);
		this.setValeur_ot(valeur_ot);
	}	
	public void controllerNomTypeOffre(String nom_type_offre, Connection conn) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM type_offre where nom_type_offre=?";
        try{
        	pst = conn.prepareStatement(sql);
        	pst.setString(1, nom_type_offre);
            rs = pst.executeQuery();
            while(rs.next()){
            	throw new Exception("Nom offre existant");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }				
	}	

	public void controllerNomOffre(String nom_offre, Connection conn) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM offre where nom_offre=?";
        try{
        	pst = conn.prepareStatement(sql);
        	pst.setString(1, nom_offre);
            rs = pst.executeQuery();
            while(rs.next()){
            	throw new Exception("Nom offre existant");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }				
	}	
	
	public Offre() {
		super();
	}
	
	public int getIdTypeByName(String nom_type, Connection conn) throws Exception {
		int idtype = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "select id_type_offre from type_offre where nom_type_offre=?";
        try{
        	pst = conn.prepareStatement(sql);
        	pst.setString(1, nom_type);
            System.out.println(pst);
        	rs = pst.executeQuery();
            while(rs.next()){
            	idtype = rs.getInt("id_type_offre");
            }
            if(idtype == 0) {
            	throw new Exception("Type offre invalide");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }				
		return idtype;
	}
	
	public int getIdOffreByName(String nom_offre, Connection conn) throws Exception {
		int idoffre = 0;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String sql = "select id_offre from offre where nom_offre=?";
        try{
        	pst = conn.prepareStatement(sql);
        	pst.setString(1, nom_offre);
        	System.out.println(pst);
        	rs = pst.executeQuery();
            while(rs.next()){
            	idoffre = rs.getInt("id_offre");
            }
        }catch(Exception e){
            throw e;
        }finally{
            if(pst!=null)pst.close();
            if(rs!=null)rs.close();
        }				
		return idoffre;
	}	
}
