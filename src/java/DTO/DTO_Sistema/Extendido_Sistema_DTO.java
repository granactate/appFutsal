/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.DTO_Sistema;

import javax.swing.tree.DefaultTreeCellEditor;

/**
 *
 * @author Mario(K y M)
 */
public class Extendido_Sistema_DTO {
    
    private String nombre;
    private int identificador;//numero que identifica el extendido
    
    
    
    //<editor-fold desc="Constructores">
    public Extendido_Sistema_DTO(int identificador) {
        this.identificador = identificador;
    }

    public Extendido_Sistema_DTO(String nombre) {
        this.nombre = nombre;
    }

    public Extendido_Sistema_DTO(String nombre, int identificador) {
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
    
    
    
}
