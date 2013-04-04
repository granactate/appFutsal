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
public class JugadorDTO {
    private String nombre;
    private String apellidos;
    private String identificacion;
    private Date fechaNacimiento;
    private int estatura;
    private float peso;
    

 
    
    //<editor-fold desc="Constructores">
    public JugadorDTO(String identificacion) {
        this.identificacion = identificacion;
    }

    public JugadorDTO(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    public JugadorDTO(){
        
    }

    public JugadorDTO(String nombre, String apellidos, String identificacion, Date fechaNacimiento, int estatura, float peso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.estatura = estatura;
        this.peso = peso;
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
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the estatura
     */
    public int getEstatura() {
        return estatura;
    }

    /**
     * @param estatura the estatura to set
     */
    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    /**
     * @return the peso
     */
    public float getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    
    
}
