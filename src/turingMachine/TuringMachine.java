package turingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author J.Daniel Ramirez
 * @Curso Automatas y lenguajes formales
 * @version TuringProyect final
 */

public class TuringMachine {

    Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        Map<Object, List<Object>> maps = new HashMap<Object, List<Object>>();
        ArrayList<Object> keys = new ArrayList<>();
        ArrayList<Object> arrayQ = new ArrayList<>();
        ArrayList<Object> cadenas = new ArrayList<>();
        boolean flag = true;
        int n1 =0;
        int eFinal = 0;
        
        Scanner input = new Scanner(System.in);
        do{
            Validacion.menu();
            do{
            try{
            
            n1 = Integer.parseInt(input.nextLine());
                    switch (n1) {
                        case 1:
                            //Ingresar máquina de turing
                            maps.clear();
                            keys.clear();
                            arrayQ.clear();
                            eFinal = inputCadena((HashMap) maps, keys,arrayQ);                            
                            break;
                        case 2:
                            cadenas.clear();
                            //Validar sentencia en máquina de truring
                            Validacion.validarCadena((HashMap) maps, cadenas, eFinal, arrayQ,keys);
                            break;
                        case 3:
                            //Mostrar máquina de turing registrada
                            mapear((HashMap) maps);
                            break;                            
                        case 4:
                            //Salir del sistema
                            System.exit(0);
                            break;
                        case 5:
                            //PRUEBA - TEST
                            mostrar((HashMap) maps, keys);
                            break;
                    }

                } catch (java.lang.NumberFormatException ex) {
                    flag = false;
                    System.out.println("Debe ingresar una opcion valida");
                    //System.out.println("Erro al ingresar caracter no valido " + ex);
                }
            }while (flag == false);           
        } while (n1 != 4);         
        
    }

    
    public static int inputCadena(HashMap mapp, ArrayList<Object> keys, ArrayList<Object> arrayQ){        
        List<Object> list = new ArrayList<Object>();    
        int eFinal = 0;
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        int n1 = 0,n2 = 0, n3=0;
        Object cadenas = null;
        Object key = null;
        
        do {
            flag = true;
            try {
                System.out.print("Cuantos simbolos{E} contiene su modelo: ");
                n1 = Integer.parseInt(input.nextLine());
            } catch (java.lang.NumberFormatException ex) {
                flag = false;
                //System.out.println("Erro al ingresar caracter no valido " + ex);
            }
        } while(Validacion.validacionNumero(String.valueOf(n1))==false || flag==false);
         
        do {
            flag = true;
            try {
                System.out.print("Cuantos estados{q} contiene su maquina de Turing: ");
                n2 = Integer.parseInt(input.nextLine());
                for (int i = 0; i < n2; i++)
                    arrayQ.add(i);  //Se crea el espacio de n objetos dentro del array de estados                              
                if(Validacion.nombrarEstados(arrayQ)){ 
                    //Si es resultado es true se nombran los estados de un contexto especifico
                    System.out.println("ESTADOS DEFINIDOS: ");
                    int j = 0;
                    for (Object state : arrayQ) {
                        System.out.println("[q"+(j+1)+"]:" + state);
                        j++;
                    }
                }else{ //Los estados se generan con nombres genericos
                    System.out.println("ESTADOS DEFINIDOS GENERICAMENTE");
                    for (Object state : arrayQ) {
                        System.out.println("["+state+"]: " + state);
                    }
                }
                
            } catch (java.lang.NumberFormatException ex) {
                flag = false;
                //System.out.println("Erro al ingresar caracter no valido " + ex);
            }
        } while(Validacion.validacionNumero(String.valueOf(n1))==false || flag==false);                                                                                 
        
        //Ingrese el indice del estado final    
        System.out.print("Ingrese el indice de estados finales de su modelo F={}: ");
        eFinal = Integer.parseInt(input.nextLine());
            
        for (int i = 0; i < n1; i++) {       
            List<Object> pl = new ArrayList<Object>();    
            key = null;
            cadenas = null;
            pl.clear();
            System.out.print("Ingrese su simbolo[" + (i+1) + "]: ");
            key = input.nextLine();
            keys.add(key);
            for (int j = 0; j < n2; j++) {
                System.out.print("ingrese el estado[ q"+(j+1)+"] para su simbolo [" + key.toString() + "] : ");
                cadenas = input.nextLine();
                pl.add(cadenas);
            }                        
            list = pl;
            mapp.put(key, list);
        }        
        mapear((HashMap) mapp);        
        return eFinal;
    }
    
    public static void mapear(HashMap mapp){    
        for (Object i : mapp.keySet()) {
            System.out.println("Simbolo [" + i + "] -> Estados: q" + mapp.get(i));            
        }
        System.out.println("\n");
    }    
    
    public static void mostrar(HashMap<Object, List<Object>> mapp, ArrayList<Object> array){
//        
        for (Object i : mapp.keySet()) {
            if (i.equals("a")) {
                System.out.println("Simbolo [" + i + "] -> Estados: q" + mapp.get(i).get(0));            
            }
        }
    }
    
    public static void automata(HashMap<Object, List<Object>> mapp,ArrayList<Object>  cadena, int eFinal, ArrayList<Object> estadosQ, ArrayList<Object> keys){
        boolean flag = false;
        boolean flagRed = false;
        ArrayList<Object> temp = new ArrayList<Object>();
        String[] dup;
        Object x = "0";
        Object y = "1";        
       
        String stdl;        
        String[] desc;
        
        int stateT;
        Object changeT;
        String  wayT;
        String process = "";
        int eActual = 0;
        int esFinal = eFinal -1; 
        int dato;
        int i =0; 
        int j =0; //inicio i para ciclos
//        cadena.add("S");
        
        //Do_ while, hasta que la cadenaArray<Objetos> se acabe || hasta que tenga flag = true
        do{                                 
            if (Validacion.allItems(keys, cadena)) { //todos los items son validos en las llaves                        
                        for (Object llave : mapp.keySet()) {
                            //compara cada objeto del *arrayList de llaves* con la llave ingresada                
                            if (cadena.get(i).equals(llave)) {
                                //si cuentra el dato ingresado dentro del ArrayList de llaves ingresamos
                                //F(estado inicial, cadena)                        
                                
                                stdl = (String) mapp.get(llave).get(eActual);
                                int cadenaLe = stdl.length();
                                if(cadenaLe>1 && stdl.contains("-")){
                                    
                                    desc = stdl.split("-"); //separamos la cadena ingresada por ejemplo 1-X-D  
                                    //compuesta por f() = (estado - intercambio - direccion)
                                    
                                    stateT = Integer.parseInt(String.valueOf(desc[0]));
                                    changeT = desc[1];
                                    wayT = (String) desc[2];
                                    
                                    cadena.set(i, changeT);
                                    
                                    System.out.println("δ( " + estadosQ.get(eActual) + " , " + llave + "): -> ( "+ changeT+", "+ wayT +", " + estadosQ.get( stateT - 1)+")   --> " + cadena.toString());                                    
                                    eActual = stateT - 1;
                                   
                                    if(wayT.toLowerCase().equals("d") && !(((String)changeT).equals("B")) ){
                                        i = i+1;
                                    }else if(wayT.toLowerCase().equals("i")){
                                        i = i-1;
                                    }else if(wayT.toLowerCase().equals("d") && (((String)changeT).equals("B"))){
                                        stdl = "S";
                                    }
                                    
                                    
                                }else if(cadenaLe ==1 && stdl.equals("S")){
                                    System.out.println("Estado final STOP");                                    
                                }else{
                                    System.out.println(" El estado es un estado imposible");
                                    flag = true;
                                }
                                    
                                if (esFinal == eActual && i == (cadena.size() - 1) && stdl.toLowerCase().equals("s")) {
                                    //Si llegamos al estado final
                                    process += cadena.toString() + "-> S \n";                                    
                                    System.out.println(process);
                                    System.out.println("COMPROBACION FINALIZADA");
//                                    System.out.println(" "+", B, " + estadosQ.get(eActual)  +" ->S");
                                    
                                    
                                    flag = true;
                                } else if (esFinal != eActual && i == (cadena.size() - 1) && stdl.toLowerCase().equals("s")) {
                                    System.out.println("COMPROBACION FINALIZADA / NO VALIDO / ESTADO IMPOSIBLE -");
                                    flag = true;
                                } else {
//                                    process += cadena.toString() + " \n";                                    
                                }
                                break;
                            }

                        }                                            

            }else{
                System.out.println("Hay valores dentro del la sentencia " + cadena.toString() + " que son valores NO reconocidos. ");
                flag=true;
            }         
            //            i++;
            j++;
        }while(flag==false);
        
        
        if(flag==false) System.out.println("NO ACEPTADO - VALORES NO RECONOCIDOS");
        if(flag==true && esFinal==eActual)System.out.println("RESPUESTA: CADENA "+ cadena.toString() +"  ES VALIDA - ESTADO FINAL ALCANZADO" + "\n");
        if(flag==true && esFinal!=eActual)System.out.println("RESPUESTA: CADENA "+ cadena.toString() +"NO ES VALIDA - ESTADO FINAL NO ALCANZADO");        
        
    }
    
    
}
