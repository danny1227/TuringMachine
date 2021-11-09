package turingMachine;

import static turingMachine.TuringMachine.automata;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author J.Daniel Ramirez
 */

public class Validacion {
        
        public static boolean validacionInput(String input){
            boolean flag = true;
            
            for (int i = 0; i < input.length() ; i++) {
                if(Character.isDigit(input.charAt(i))|| Character.isLetter(input.charAt(i))){
                    System.out.println("La cadena es aceptada ~");
                    flag = true;
                }else{
                    flag = false;
                    System.out.println("La cadena NO es aceptada ~ /n Ingrese nuevamente una cadena valida");
                }
            }
            return flag;
        }
        
        public static boolean validacionNumero(String input){
            boolean flag = true;
            
            for (int i = 0; i < input.length(); i++) {
                if(Character.isDigit(input.charAt(i))){
                    flag = true; //Si es true son cadena de numeros
                }else if(Character.isLetter(input.charAt(i))){
                    flag = false; //Si es false son cadena de letras
                }
            }
            
            return flag;
        }
        
        public static boolean nombrarEstados(ArrayList<Object> states){
            Scanner input = new Scanner(System.in);

            boolean flag = true;
            boolean resp = true;
            String rest = "";
            System.out.print("¿Deseas nombrar sus estados? Q={} ");
            rest = input.nextLine();
            if (!rest.equals("Si") && !rest.equals("SI") && !rest.equals("si") && !rest.equals("S")
                    && !rest.equals("s")) {
                flag = false;
                for (int i = 0; i < states.size(); i++) {
                    states.set(i, "q" + String.valueOf((i+1)));
                }
            } else {
                for (int i = 0; i < states.size(); i++) {
                    System.out.print("Nombre del estado [q" + (i + 1) + ":] ");
                    rest = input.nextLine();
                    states.set(i, rest);
                }
            }            
            return flag;
        }
        
        public static void estados(int Efinal){
             Scanner input = new Scanner(System.in);

           
        }
        
        public static void menu(){
            System.out.println(" Emulador Maquina Turing \n "
                             + "Ingrese la opcion: \n");
            System.out.print(  " 1.Ingresa modelo  \n"
                             + " 2.Validar sentencia del lenguaje \n"
                             + " 3.Mostrar modelo registrado \n"
                             + " 4.Salir          :" );
        }
        
        
    public static void validarCadena(HashMap<Object, List<Object>> mapp, ArrayList<Object> cadena, int eFinal, ArrayList<Object> estadosQ, ArrayList<Object> keys){
        Scanner input = new Scanner(System.in);
        Object n1;
        String key="";
        do{
        System.out.print("Digite una entrada para el conjunto de simbolos L={} de su sentencia: ");
        n1 = input.nextLine();
        if (n1!=null) {
            cadena.add(n1);
        }
        System.out.print("¿Desea ingresar un simbolo más al conjunto L={}? ");
        key = input.nextLine();
        }while(key.toLowerCase().contains("si"));
        
        automata(mapp, cadena, eFinal, estadosQ,keys);
        
    }
    
    public static boolean allItems(ArrayList<Object> keys, ArrayList<Object> cadena){
        //checks if all elements of the cadena are recognized in the array of keys
        boolean flag = true;
            
            for (int i = 0; i < cadena.size(); i++) {
                //recorremos arreglo de setencia verificando que todos los elementos ingresados 
               //se encuentren dentro del arreglo de llaves
                if(keys.contains(cadena.get(i))){
                    flag = true;
                }else{
                    flag = false;
                    break;
                }                
            }            
        return flag;
    }
    
    public static boolean equalsIteams(ArrayList<Object> cadena){
        boolean flag = true;
           //verify if all Items of cadena are equals 
           // and if all items end in even number 
            if ((cadena.stream().distinct().count() <= 1) && cadena.size() % 2 == 0) {
                        return false;
                        //if the cadena is not valid
                }
        
        return flag;
    }
    
    public static boolean count(ArrayList<Object> cadena){
        
        boolean flag = false;
        boolean redFlag = false;
        
        List<Object> listDistinct = cadena.stream().distinct().collect(Collectors.toList());        
      
        for (Object object : listDistinct) {            
            if(numberValues(object, cadena)%2==0){
                redFlag = true;                    
            }else{
                flag = true;
            }     
            
        }
        
        if(redFlag==true&&flag == true){
            flag = true;            
        }else if(flag==true&&redFlag==false){
            flag = true;            
        }else{
            flag = false;            
        }
        
        return flag;
    }
    
    public static int numberValues(Object uniques, ArrayList<Object> cadena){
        int count = 0;
                          
            for (Object object1 : cadena) {                
                if(uniques.equals(object1)){
                    count++;
                }                
            }            
        
        
        return count;
    }
    
    public static ArrayList<Object> GetRange(ArrayList<Object> list,int inicio, int ultimo) {
        //Obtiene el rango del array del indice i al indice final
        ArrayList<Object> temp  = new ArrayList<Object>();

        for (int x = inicio; x <= ultimo; x++) {
            temp.add(list.get(x));
        }
        return temp;
    }
    
    public static ArrayList<Object> replace(ArrayList<Object> cadena, Object valor1, Object valor2){
        // X, Y
        int i = 0;
        for (Object valor : cadena) {
            if(cadena.get(i).equals(valor1)){
                cadena.set(i, "X");
            }else{
                cadena.set(i, "Y");
            }
            i++;
        }
        
        return cadena;
    }
    
}
