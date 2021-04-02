package element;

import java.sql.Connection;
import java.util.ArrayList;

import utils.Helper;

public class Simulation {
    
	public int getDureeVitanyCredit(Double credit,Coutappel cout){ 
    	
		double dureeVitany=credit/cout.getCoutsec();
        return (int) dureeVitany;
    }

    public int getSimilarityOperateur(String appeleur,String appele){
//--    TypeAppel: 0 si meme operateur
//--    1 si operateurs diff
//--    2 si international 
        String num1=appeleur.substring(0,4);
        String num2=appele.substring(0,4);
//    	Check if meme nationalité
            
        if(num1.equals(num2)) {
            String num21=appeleur.substring(0,6);
            String num22=appele.substring(0,6);
            if(num21.equals(num22)) {
                return 0;
            } else {
                return 1;
            }
//    	Check if meme nationalité
        } else {
            return 2;
        }
    }	
    
    public int getDureeSimulationAppel(int ID_CLIENT_NUM, String appeleur, String appele, String dateDeCheck, int duree, Connection conn) throws Exception{
        
    	int dureeAppel = 0;
        String typeOffre = "APPEL";
        int similarity = this.getSimilarityOperateur(appeleur,appele);
        int i = 0;
        double coutAppel = 0.0;
        Coutappel cout = null;
        try {
	        int id_client = new Clientnum().getId_client_ById_client_num(ID_CLIENT_NUM, conn);
	        double credit = new ClientDaoService().getCreditClient(id_client, conn);
	        ArrayList <Achatoffre> offres = new AchatoffreDaoService().getResteAchatOffre(ID_CLIENT_NUM,dateDeCheck,typeOffre, conn);       
	 
	        cout=new Coutappel().getCoutAppel(similarity, conn);
	        int dureeMaxCredit= this.getDureeVitanyCredit(credit,cout);
	
	        
	        //Raha tsy misy offres
	        if(offres.isEmpty()) {
	            if(duree<=dureeMaxCredit) {
	                coutAppel=duree*cout.getCoutsec();
	                dureeAppel=duree;
	            } else {
	                coutAppel=dureeMaxCredit*cout.getCoutsec();
	                dureeAppel=dureeMaxCredit;
	            }
	           //INSERT INTO APPELCREDIT VALUES(NEXTVAL(APPELCREDIT_Sequence),ID_CLIENT_NUM,coutAppel,dateDeCheck);
	           
	           new Appelcredit(ID_CLIENT_NUM,coutAppel,dateDeCheck).save(conn);
	        } else {
	            coutAppel=duree*cout.getCoutsec();
	            double totalCout=0;
	            int e=0;
	            while(e!=2){
	                if(i>=offres.size()){
	                    e=1;
	                    break;
	                }
	                double tempSolde=offres.get(i).getReste();
	                if((tempSolde-coutAppel)>=0){
	//                    offres.get(i).setReste(tempSolde-coutAppel);
	                    dureeAppel=duree;
	                    e=2;
	                    //INSERT INTO FORFAITUSAGE VALUES(NEXTVAL('UsageForfait_Sequence'),offres.get(i).getIdAchatOffre(),dateDeCheck,coutAppel,offres.get(i).getID_OFFRE_AND_TYPE());
	                    new Forfaitusage(offres.get(i).getId_achat_offre(),dateDeCheck,coutAppel,offres.get(i).getId_offre_and_type()).save(conn);
	                }else{
	                    coutAppel=coutAppel-tempSolde;
	                    i++;
	                    //INSERT INTO FORFAITUSAGE VALUES(NEXTVAL('UsageForfait_Sequence'),offres.get(i).getIdAchatOffre(),dateDeCheck,tempSolde,offres.get(i).getID_OFFRE_AND_TYPE());
	                    new Forfaitusage(offres.get(i).getId_achat_offre(),dateDeCheck,tempSolde,offres.get(i).getId_offre_and_type()).save(conn);
	                }
	                totalCout+=offres.get(i).getReste();
	                
	            }
	//            Raha ohatra ka tsy mbola tapitra nefa lany ny offre
	            if(e==1){
	                if(credit-coutAppel>=0){
	//                    Mihena an'iny ny Credit
	                    dureeAppel=duree;
	                    //INSERT INTO APPELCREDIT VALUES(NEXTVAL(APPELCREDIT_Sequence),ID_CLIENT_NUM,credit-coutAppel,dateDeCheck);
	                    new Appelcredit(ID_CLIENT_NUM,credit-coutAppel,dateDeCheck).save(conn);
	                }else{
	                    int dureePdtForfait=this.getDureeVitanyCredit(totalCout,cout);
	                    dureeAppel=dureeMaxCredit+dureePdtForfait;
	                    //INSERT INTO APPELCREDIT VALUES(NEXTVAL(APPELCREDIT_Sequence),ID_CLIENT_NUM,credit,dateDeCheck);
	                    new Appelcredit(ID_CLIENT_NUM,credit,dateDeCheck).save(conn);
	                }
	            }
	        }
        } catch(Exception ex) {
        	throw ex;
        } 
        return dureeAppel;
        
    }   
    
    public void simulationAppel(int ID_CLIENT_NUM, String appeleur, String appele, String dateDeCheck, int duree) throws Exception {
    	
    	Connection conn = null;
    	try {
	    	conn = new Helper().getConnexionPsql();
    		int dureeSimulation = this.getDureeSimulationAppel(ID_CLIENT_NUM, appeleur, appele, dateDeCheck, duree, conn);
	    	Appel appel = new Appel(appeleur, appele, dureeSimulation, dateDeCheck);
	    	new AppelDaoService().save(appel);
	    	conn.commit();
    	} catch(Exception ex) {
    		conn.rollback();
    		throw ex;
    	} finally {
    		if(conn!=null) conn.close();
    	} 
    	
    }
}
