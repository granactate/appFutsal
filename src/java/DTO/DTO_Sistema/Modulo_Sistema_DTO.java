/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.DTO_Sistema;

import java.util.ArrayList;

/**
 *
 * @author Mario(K y M)
 */
public class Modulo_Sistema_DTO {
    
    private String nombre;
    private int identificador;//numero que identifica el modulo
    private ArrayList<Extendido_Sistema_DTO> myExtendidos;
    
    
    //<editor-fold desc="Constructores">
    public Modulo_Sistema_DTO(int identificador) {
        this.identificador = identificador;
    }
    
    public Modulo_Sistema_DTO(String nombre) {
        this.nombre = nombre;
    }

    public Modulo_Sistema_DTO(String nombre, int identificador) {
        this.nombre = nombre;
        this.identificador = identificador;
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
     * @return the myExtendidos
     */
    public ArrayList<Extendido_Sistema_DTO> getMyExtendidos() {
        return myExtendidos;
    }

    /**
     * @param myExtendidos the myExtendidos to set
     */
    public void setMyExtendidos(ArrayList<Extendido_Sistema_DTO> myExtendidos) {
        this.myExtendidos = myExtendidos;
    }

    
    
}
