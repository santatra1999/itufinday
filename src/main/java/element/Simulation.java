package element;

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
}
