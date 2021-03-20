package element;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public void setNom_offre(String nom_offre) {
		this.nom_offre = nom_offre;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getDuree_valide() {
		return duree_valide;
	}
	public void setDuree_valide(int duree_valide) {
		this.duree_valide = duree_valide;
	}
	public int getPriorite() {
		return priorite;
	}
	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}
	public Offre(int id_offre, String nom_offre, double value, int duree_valide, int priorite) {
		super();
		this.setId_offre(id_offre);
		this.setNom_offre(nom_offre);
		this.setValue(value);
		this.setDuree_valide(duree_valide);
		this.setPriorite(priorite);
	}
	public Offre(String nom_offre, int usage, double chaff) {
		super();
		this.setNom_offre(nom_offre);
		this.setUsage(usage);
		this.setChaff(chaff);
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
	
	
}
