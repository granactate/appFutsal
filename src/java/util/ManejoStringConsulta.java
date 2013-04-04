/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Mario(K y M)
 */
public class ManejoStringConsulta {

    
    public static String eliminarSeparador(String valor){
        return valor.substring(0, valor.length()-3);
    }
    
    /**
     * metodo que se encarga de tratar el ArrayList<String> generado por la consulta sql
     * @param consulta
     * @return un ArrayList<String> sin caracteres de separación
     */
    public static ArrayList<String> splitSeparador(ArrayList<String> consulta){        
        
        ArrayList<String> resp = new ArrayList<String>();
        for (String texto : consulta) {
            resp.add(ManejoStringConsulta.eliminarSeparador(texto));

        }
        return resp;       
    }
    /**
     * metodo que se encarga de tratar el ArrayList<String> generado por la consutla sql
     * @param consulta
     * @return una matriz bidimensional de String con cada uno de los campos separados organizados en forma de matriz
     */
    public static ArrayList<ArrayList<String>> splitSeparadorMatriz(ArrayList<String> consulta){
        ArrayList<ArrayList<String>> miMatriz=new ArrayList<ArrayList<String>>();
        for(int i=0;i<consulta.size();i++){
            miMatriz.add(ManejoStringConsulta.clonar(consulta.get(i)));
        }
        return miMatriz;
    }
    
    /**
     * metodo que se encarga de dividir una cadena eliminando los separadores 
     * @param array
     * @return un ArrayList<String> con las subcadenas cargadas en el
     */
    private static ArrayList<String> clonar(String array){
      String vec[]=array.split("---");
      ArrayList<String> aux= new ArrayList<String>();
      aux.addAll(Arrays.asList(vec));
      return aux;
    }
    
    private static boolean encontroSecuenciaSeparador(String palabra, int index){
        if (palabra.length() > index + 1 && 
                palabra.length() > index + 2 &&
                palabra.charAt(index + 1) == '-' &&
                palabra.charAt(index + 2) == '-') {
         return true;
        }
        return false;
    }
    
    
    /**
     * metodo que se encarga de  pasar una fecha de tipo Date al formato que almacena la base de datos
     * @param fecha
     * @return un String con el formato indicado
     */
    public static String getFechaFormato(Date fecha){
        
        return (fecha.getMonth()==0)? fecha.getYear()+"-"+12+"-"+fecha.getDate():
                +fecha.getYear()+"-"+fecha.getMonth()+"-"+fecha.getDate();
    }
    
    /**
     * metodo que permite tomar la fecha de la base de datos y procesarla al formato del Date de java
     * @param fecha
     * @return un objeto Date con la fecha cargada
     */
    public static Date getFechaFormatoDate(String fecha){
        String vecFecha[]=fecha.split("-");
        
        Date resp= new Date(Integer.parseInt(vecFecha[0]),
                        Integer.parseInt(vecFecha[1]),
                        Integer.parseInt(vecFecha[2]));
       
        return resp;
    }
    
    
    /**
     * metodo que permite obtener la fecha en  un mensaje 
     * @param fecha
     * @return String con el mensaje correspondiente
     */
    public static String getMensajeFecha(Date fecha){
        return fecha.getDate()+ " de "+getMesFecha(fecha)+" del "+fecha.getYear();
    }
    
    /**
     * metodo que permite obtener el mes en una cadena de un Date
     * @param fecha
     * @return el String correspondiente  a la fecha<html><br>puede retornar "" si hay algun erro
     */
    private static String getMesFecha(Date fecha){
        switch(fecha.getMonth()){
            case 1:return "Enero";
            case 2:return "Febrero";
            case 3:return "marzo";
            case 4:return "Abril";
            case 5:return "Mayo";
            case 6:return "Junio";
            case 7:return "Julio";
            case 8:return "Agosto";
            case 9:return "Septiembre";
            case 10:return "Octubre";
            case 11:return "Noviembre";
            case 0:
            case 12:return "diciembre";
            default:return "";
        }
    }
    
}
