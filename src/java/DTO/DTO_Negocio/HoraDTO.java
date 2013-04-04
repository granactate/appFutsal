/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.DTO_Negocio;

/**
 *
 * @author Mario(K y M)
 */
public class HoraDTO {

    private String estado;
    private int horaInicio;
    private int id;

    //<editor-fold desc="Constructores">

    public HoraDTO(int horaInicio, int id) {
        this.estado = "Libre";
        this.horaInicio = horaInicio;
        this.id = id;
    }

    public HoraDTO() {
        this.estado = "Libre";
    
    }

    public HoraDTO(int horaInicio) {
        this.horaInicio = horaInicio;
        this.estado = "Libre";
    }
    
    
    
    //<editor-fold/>
    
 
    /**
     * @return the horaInicio
     */
    public int getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
