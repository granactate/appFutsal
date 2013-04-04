/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DAO.OrganizadorTorneoDAO;
import DAO.UsuarioDAO;
import DTO.DTO_Negocio.OrganizadorTorneoDTO;

/**
 *
 * @author Mario(K y M)
 */
class OrganizadorTorneoBusiness {

    /**
     * metodo que permite obtener el organizador de torneo dependiendo de su username
     * @param usernameOrganizadorT
     * @return el Organizador Torneo DTO con toda la información,  que corresponde con el username ingresado 
     */
    public static OrganizadorTorneoDTO getOrganizadorTorneoUsername(String usernameOrganizadorT) {
        
        OrganizadorTorneoDTO miOrganizador=new OrganizadorTorneoDTO(usernameOrganizadorT, "");
        OrganizadorTorneoDTO miOrganizadorActualizado=(OrganizadorTorneoDTO)UsuarioDAO.getUsuarioPorUsername(miOrganizador);
        
        return miOrganizadorActualizado;
    }
    
}
