/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Negocio.EquipoDTO;
import DTO.DTO_Negocio.TorneoDTO;
import util.BaseDeDatos;

/**
 *
 * @author Mario(K y M)
 */
public class Equipo_has_TorneoDAO {

    /**
     * metodo que permite validar un equipo determinado <html><br>requiere que se cargue el id
     * @param validoEquipo
     * @return boolean con el resultado de la operacion
     */
    public static boolean validarEquipo(EquipoDTO validoEquipo,TorneoDTO miTorneo) {
       String sql="UPDATE equipo_has_torneo"
               + " SET validado=true"
               + " WHERE fk_idequipo = '"+validoEquipo.getId()+"' and fk_idtorneo='"+miTorneo.getId()+"'";
       
       if(!BaseDeDatos.hayConexion()){
           BaseDeDatos.conectar();
       }
        return BaseDeDatos.ejecutarActualizacionSQL(sql);
           
    }
    
}
