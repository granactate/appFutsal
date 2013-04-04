/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DAO.UsuarioDAO;
import DTO.DTO_Negocio.DirectorTecnicoDTO;
import DTO.DTO_Negocio.EquipoDTO;
import DTO.DTO_Negocio.PersonaDTO;
import DAO.DirectorTecnicoDAO;
/**
 *
 * @author Mario(K y M)
 */
class DirectorTecnicoBusiness {

    /**
     * metodo que permite obtener un DirectorTecnico apartir de su Username
     * @param usernameTecnico
     * @return el DirectorTecnico con la información cargada de este 
     */
    public static DirectorTecnicoDTO getDirectorTecnicoUsername(String usernameTecnico) {
        PersonaDTO miDir=new PersonaDTO(usernameTecnico);
        PersonaDTO miDirector=UsuarioDAO.getUsuarioPorUsername(miDir);
        if(miDirector==null){
            return null;
        }
        
//        DirectorTecnicoDTO miDirectorTecnico=(DirectorTecnicoDTO)miDirector;
        
        DirectorTecnicoDTO miDirectorTecnico=new DirectorTecnicoDTO(miDirector.getNombre(), miDirector.getApellidos(),
        miDirector.getIdentificacion(), miDirector.getFechaNcto(),miDirector.getUsername() ,"", miDirector.getTelefono(),
         miDirector.getDireccion(), miDirector.getCorreo(), null);
        return miDirectorTecnico;
    }

    /**
     * metodo que permite obtener a partir de un director tecnico otenes el equipo que este dirige
     * @param miDirector
     * @return el EquipoDTO que dirige el tecnico o null si no encuentra ninguno
     */
    public static EquipoDTO getEquipoPorDirectorTecnico(DirectorTecnicoDTO miDirector) {
        return DirectorTecnicoDAO.getEquipoPorDirectorTecnico(miDirector);
    }
    
}
