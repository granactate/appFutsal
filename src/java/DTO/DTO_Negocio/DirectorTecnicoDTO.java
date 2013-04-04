/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.DTO_Negocio;

import DTO.DTO_Sistema.Rol_Sistema_DTO;
import java.util.Date;

/**
 *
 * @author Mario(K y M)
 */
public class DirectorTecnicoDTO extends PersonaDTO{
    
  
    
    //<editor-fold desc="Constructores">
    public DirectorTecnicoDTO(String username, String password) {
        super(username, password);
    }
    
    public DirectorTecnicoDTO(String identificacion) {
        super(identificacion);
    }
    
    public DirectorTecnicoDTO(){
        
    }
    
    public DirectorTecnicoDTO(String nombre, String apellidos, String identificacion, Date fechaNcto, String username, String password, String telefono, String direccion, String correo, Rol_Sistema_DTO miRol) {
        super(nombre, apellidos, identificacion, fechaNcto, username, password, telefono, direccion, correo, miRol);
    }
    //</editor-fold>

    

      
}
