/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.DTO_Sistema;

/**
 *
 * @author Mario(K y M)
 */
public class Rol_Sistema_DTO {
    private String nombre;
    private int identificador;//numero que identifica al rol
    private Privilegio_Sistema_DTO myPrivilegios;
    
    //<editor-fold desc="Constructores">
    public Rol_Sistema_DTO(int identificador) {
        this.identificador = identificador;
    }
    
     public Rol_Sistema_DTO(String nombre) {
        this.nombre = nombre;
    }

    public Rol_Sistema_DTO(String nombre, int identificador, Privilegio_Sistema_DTO myPrivilegios) {
        this.nombre = nombre;
        this.identificador = identificador;
        this.myPrivilegios = myPrivilegios;
    }
      
     
     
     public Rol_Sistema_DTO(){
         
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
     * @return the identificador
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the myPrivilegios
     */
    public Privilegio_Sistema_DTO getMyPrivilegios() {
        return myPrivilegios;
    }

    /**
     * @param myPrivilegios the myPrivilegios to set
     */
    public void setMyPrivilegios(Privilegio_Sistema_DTO myPrivilegios) {
        this.myPrivilegios = myPrivilegios;
    }

   
     
   
}
