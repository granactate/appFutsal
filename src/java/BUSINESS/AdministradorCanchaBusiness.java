/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DTO.DTO_Negocio.AdministradorCanchaDTO;
import DAO.AdministradorCanchaDAO;
import java.util.ArrayList;

/**
 *
 * @author Mario(K y M)
 */
class AdministradorCanchaBusiness {

    /**
     * meotodo que permite listar los administradores de cancha a los cuales no se les ha asignado una cancha
     * @return Arraylist<Strign > con el nombre y cedula del administrador
     */
    public static ArrayList<String> getAdministradoresCancha() {
        
        ArrayList<AdministradorCanchaDTO> administradores=AdministradorCanchaDAO.getAdministradoresCancha();
        if(administradores==null){
            return null;
        }
        if(administradores.isEmpty()){
            return new ArrayList<String>();
        }
        ArrayList<String> resp= new ArrayList<String>();
        for(AdministradorCanchaDTO admin:administradores){
            resp.add(admin.getNombre()+"/"+admin.getIdentificacion());
        }
        return resp;
    }
    
}
