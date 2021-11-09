package automatasfinitos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author J.Daniel Ramirez
 */

public class Xclass {
    
    
    public static void main(String[] args) {
                ArrayList<Object> cadena  = new ArrayList<Object>();
                
                cadena.add("2-Y-D");
                cadena.add("3-X-I");
                cadena.add("R");
                Object[] ob;
                ArrayList<Object> valores = new ArrayList<Object>();
                
                valores.add("B");
                valores.add("1");
                valores.add("0");
                
                String sig = (String) cadena.get(2);
                int cadenaLe = sig.length();
                
                if(cadenaLe>1 && sig.contains("-")){
                    ob = sig.split("-");
                    
                    valores.set(1, ob[1]);
                    valores.set(2, ob[1]);
                    
                }else if(cadenaLe ==1 && !sig.equals("S")){
                    System.out.println("Estado final STOP");
                }else{  
                    System.out.println(" El estado es un esatdo imposible");                
                }
                
            for (Object object : valores) {
                System.out.println(">" + object);
        }
        
    }
           
    
}
