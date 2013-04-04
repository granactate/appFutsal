/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.DTO_Negocio;

/**
 *
 * @author Mario(K y M)
 */
public class CanchaDTO {
    private String nombre;
    private String direccion;
    private int idCancha;
    private AdministradorCanchaDTO myAdministrador;
    private HorarioDTO myHorario;
    
    //<editor-fold desc="Constructores">
    public CanchaDTO(int idCancha) {
        this.idCancha = idCancha;
    }

    public CanchaDTO() {
        this.idCancha=-1;
    }

    public CanchaDTO(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.idCancha=-1;
    }

    
    
    public CanchaDTO(AdministradorCanchaDTO myAdministrador) {
        this.myAdministrador = myAdministrador;
        this.idCancha=-1;
    }    

    public CanchaDTO(String nombre, String direccion, AdministradorCanchaDTO myAdministrador) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.myAdministrador = myAdministrador;
        this.idCancha=-1;
    }

    public CanchaDTO(String nombre, String direccion, int idCancha) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.idCancha = idCancha;
        this.idCancha=-1;
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
     * @return the idCancha
     */
    public int getIdCancha() {
        return idCancha;
    }

    /**
     * @param idCancha the idCancha to set
     */
    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    /**
     * @return the myAdministrador
     */
    public AdministradorCanchaDTO getMyAdministrador() {
        return myAdministrador;
    }

    /**
     * @param myAdministrador the myAdministrador to set
     */
    public void setMyAdministrador(AdministradorCanchaDTO myAdministrador) {
        this.myAdministrador = myAdministrador;
    }

    /**
     * @return the myHorario
     */
    public HorarioDTO getMyHorario() {
        return myHorario;
    }

    /**
     * @param myHorario the myHorario to set
     */
    public void setMyHorario(HorarioDTO myHorario) {
        this.myHorario = myHorario;
    }
    
    
    
    
    
    
}
