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
public class PersonaDTO {
    private String nombre;
    private String apellidos;
    private String identificacion;
    private Date fechaNcto;
    private String username;
    private String password;
    private String telefono;
    private String direccion;
    private String correo;    
    private Rol_Sistema_DTO myRol;
    
    //<editor-fold desc="Constructores">

    public PersonaDTO(String username) {
        this.username = username;
    }

    public PersonaDTO(String nombre, String apellidos, String identificacion, Date fechaNcto, String username, String password, String telefono, String direccion, String correo,Rol_Sistema_DTO miRol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.fechaNcto = fechaNcto;
        this.username = username;
        this.password = password;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.myRol=miRol;
    }
    
    

    public PersonaDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public PersonaDTO(){        
    }

    public PersonaDTO(String username, String password, Rol_Sistema_DTO myRol) {
        this.username = username;
        this.password = password;
        this.myRol = myRol;
    }
    
    
    //</editor-fold>
    
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }    

    /**
     * @return the myRol
     */
    public Rol_Sistema_DTO getMyRol() {
        return myRol;
    }

    /**
     * @param myRol the myRol to set
     */
    public void setMyRol(Rol_Sistema_DTO myRol) {
        this.myRol = myRol;
    }

    /**
     * @return the fechaNcto
     */
    public Date getFechaNcto() {
        return fechaNcto;
    }

    /**
     * @param fechaNcto the fechaNcto to set
     */
    public void setFechaNcto(Date fechaNcto) {
        this.fechaNcto = fechaNcto;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
