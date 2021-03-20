package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Exceptionako {
	
    public void checkTimestamp(String dateinput, String nomvariable, String formatIlaina) throws Exception {       
		DateFormat format = new SimpleDateFormat(formatIlaina);
		format.setLenient(false);
    	try {
    		format.parse(dateinput);    
        } catch (Exception ex) {
            throw new Exception(nomvariable+" incorrecte");
        }    
    }
}
