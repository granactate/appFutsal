/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Date;

/**
 *
 * @author Mario(K y M)
 */
public class ManejoDate {
    
    /**
     * metodo que permite obtener el mes de un Date 
     * @param fecha
     * @return 
     */
    public static int getMesDate(Date fecha){
        return fecha.getMonth()+1;
    }
    
    public static int getDiaDate(Date fecha){
        return fecha.getDate();
    }
    
    public static int getAnyoDAte(Date fecha){
        return (fecha.getYear()<1000)?
                fecha.getYear()+1900:
                fecha.getYear();
    }
    
    
    public static void setMesDate(int mes,Date fecha){
        fecha.setMonth(mes-1);        
    }
    
    public static void setDiaDate(int dia,Date fecha){
        fecha.setDate(dia);
    }
    
    public static  void setAnyoDate(int anyo,Date fecha){
        fecha.setYear(anyo);
    }
    
    
    public static Date inicializarDate(int dia, int mes, int anyo){
        Date fecha= new Date();
        fecha.setDate(dia);
        fecha.setMonth(mes-1);
        fecha.setYear(anyo);
        return fecha;
    }
    
    
    public static Date getDate(String fecha){
        
        String f[]=fecha.split("-");
        
        int dia=Integer.parseInt(f[2]);
        int mes=Integer.parseInt(f[1])-1;
        int anyo=(Integer.parseInt(f[0])<1000)?(Integer.parseInt(f[2])+1900):
                Integer.parseInt(f[0]);
        
        return new Date(anyo, mes, dia);
    }
    
    public static Date nextDate(Date fecha){
        if(fecha==null){
            return null;
        }
        
        int dia=fecha.getDate();
        dia++;        
        int mes=fecha.getMonth();
        int anyo=fecha.getYear();
        int diasMes=getDiasMes(mes, anyo);
        if(diasMes<dia){
            dia=1;
            mes++;
            if(mes>11){
                mes=0;
                anyo++;
            }
        }
        return new Date(anyo,mes,dia);        
    }
    
    
    private static int getDiasFebrero(int anyo){
        
    if ((anyo % 4 == 0) && ((anyo % 100 != 0) || (anyo % 400 == 0))){
        return 29;
    }
    else{
        return 28;
    }
    
    }
    
    
    private static int getDiasMes(int mes,int anyo){
        switch(mes){
            case 3:
            case 5:
            case 8:
            case 10:return 30;
                
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:return 31;     
                
            case 1:return getDiasFebrero(anyo);
       }
        return -1;
    }
    
    public static String getDate(Date fecha){
        String dia=fecha.getDate()+"";
        String mes=(fecha.getMonth()+1)+"";
        String anyio=(fecha.getYear()<1000)?
                (fecha.getYear()+1900)+""
                :fecha.getYear()+"";
        return anyio+"-"+mes+"-"+dia;
    }
    
    
    
    public static boolean A_esMenor_B(Date fechaA,Date fechaB){
        if(fechaA.getYear()<fechaB.getYear()){
            return true;
        }
        if(fechaA.getYear()>fechaB.getYear()){
            return false;
        }
        
        if(fechaA.getMonth()<fechaB.getMonth()){
            return true;
        }
        if(fechaA.getMonth()>fechaB.getMonth()){
            return false;
        }
                
        if(fechaA.getDate()<fechaB.getDate()){
            return true;
        }        
        if(fechaA.getDate()>fechaB.getDate()){
            return false;            
        }
        
        return true;
    }
    
    
    public static Date getFechaActual(){
        Date resp= new Date();  
        
        resp.setYear(resp.getYear()+1900);
        return resp;
    }
        
}
