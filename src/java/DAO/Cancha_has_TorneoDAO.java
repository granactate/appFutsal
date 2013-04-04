/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Negocio.CanchaDTO;
import DTO.DTO_Negocio.TorneoDTO;
import java.util.ArrayList;
import util.BaseDeDatos;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class Cancha_has_TorneoDAO {

    /**
     * metodo que permite registrar  en la base  de datos una cancha al torneo
     * @param misCanchas
     * @param torneoCargado
     * @return boolean con el resultado de la operación
     */
    public static boolean addCanchasTorneos(ArrayList<CanchaDTO> misCanchas, TorneoDTO torneoCargado) {
        
        
        for(CanchaDTO cancha:misCanchas){
        
            int id=getNextId();
            if(id<0){
                return false;
            }
            
            String sql="INSERT INTO cancha_has_torneo(id, fk_idtorneo, fk_idcancha)"
                       + " VALUES ('"+id+"','"+torneoCargado.getId()+"', '"+cancha.getIdCancha()+"');";
            
            if(!BaseDeDatos.hayConexion()){
                BaseDeDatos.conectar();
            }
            
            boolean resp=BaseDeDatos.ejecutarActualizacionSQL(sql);
            if(!resp){
                return false;
            }
        }
        
        return true;
    }
    
    
    private static int getNextIdFecha(){
        return 0;
    }
    
    /**
     * metodo que permite conocer la id del proximo registro en la base  de datos
     * @return int con el identificador del proximo registro
     */
    private static int getNextId(){
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        String sql="select id "
                + "from cancha_has_torneo "
                + "order by id";
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null){
            return -1;
        }
        if(consulta.isEmpty()){
            return 1;
        }
        
        int id=Integer.parseInt(ManejoStringConsulta.eliminarSeparador(consulta.get(consulta.size()-1)));
        return 1+id;
    }
    
}
