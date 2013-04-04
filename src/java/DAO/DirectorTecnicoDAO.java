/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Negocio.DirectorTecnicoDTO;
import DTO.DTO_Negocio.EquipoDTO;
import java.util.ArrayList;
import util.BaseDeDatos;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class DirectorTecnicoDAO {

    /**
     * metodo que se encarga de buscar un Equipo por Director Tecnico
     * @param miDirector
     * @return EquipoDTO que dirige el DirectorTecnico especificado
     */
    public static EquipoDTO getEquipoPorDirectorTecnico(DirectorTecnicoDTO miDirector) {
        String sql="select e.nombre,e.id,e.grupo,et.validado "
                + "from equipo as e, equipo_has_torneo as et "
                + "where e.fk_iddirectortecnico='"+miDirector.getIdentificacion()+"' and et.fk_idequipo=e.id";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta= BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null||consulta.isEmpty()){
            return null;
        }
        
        
        ArrayList<ArrayList<String>> consultaProcesada=ManejoStringConsulta.splitSeparadorMatriz(consulta);
        String nombre=consultaProcesada.get(0).get(0);
        int id=Integer.parseInt(consultaProcesada.get(0).get(1));
        char grupo=consultaProcesada.get(0).get(2).charAt(0);
        
        
        EquipoDTO equipo= new EquipoDTO(nombre, grupo, id, miDirector);   
        BaseDeDatos.desconectar();
        return equipo;
    }
    
}
