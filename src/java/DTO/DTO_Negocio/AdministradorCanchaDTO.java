/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.DTO_Negocio;

import java.util.Date;

/**
 *
 * @author Mario(K y M)
 */
public class AdministradorCanchaDTO extends PersonaDTO {

   

    //<editor-fold desc="Constructores">
    public AdministradorCanchaDTO() {
    }

    public AdministradorCanchaDTO(String identificacion) {
        super(identificacion);
    }

    public AdministradorCanchaDTO(String username, String password) {
        super(username, password);
    }
    
    public AdministradorCanchaDTO(String nombre, String apellidos, String identificacion, Date fechaNcto, String username, String password, String telefono, String direccion, String correo){
        super(nombre, apellidos, identificacion, fechaNcto, username, password, telefono, direccion, correo, null);
    }
    //</editor-fold>

       
}
