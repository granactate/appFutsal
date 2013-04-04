/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DAO.EquipoDAO;
import DTO.DTO_Negocio.EquipoDTO;
import DTO.DTO_Negocio.TorneoDTO;

/**
 *
 * @author Mario(K y M)
 */
class EquipoBusiness {

//   
//    /**
//     * metodo que  permite obtener un Equipo atravez del nombre
//     * @param nombreEquipo
//     * @return EquipoDTO con los datos cargados<hmtl><br>el id que maneja la base de datos<br>elgrupo alque pertenece
//     */
//   public  static EquipoDTO getEquipoPorNombre(String nombreEquipo) {
//        EquipoDTO miEquipo=new EquipoDTO(nombreEquipo);
//        EquipoDTO equipoCargado=EquipoDAO.getEquipoPorNombreTorneo(miEquipo);
//        return equipoCargado;
//    }

   /**
    * metodo que  permite obtener un equipo de un torneo en base a  su nombre
    * @param torneoValidado
    * @return EquipoDTO con los datos cargados<hmtl><br>el id que maneja la base de datos<br>elgrupo alque pertenece
    */
    public static EquipoDTO getEquipoTorneoPorNombre(TorneoDTO torneoValidado,String nombreEquipo) {
        EquipoDTO miEquipo=new EquipoDTO(nombreEquipo);
        EquipoDTO equipoCargado=EquipoDAO.getEquipoPorNombreTorneo(torneoValidado, miEquipo,false);
        return equipoCargado;
    }
    
}
