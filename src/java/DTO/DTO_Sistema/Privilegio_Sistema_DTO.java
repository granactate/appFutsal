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
public class Privilegio_Sistema_DTO {

    private ArrayList<Extendido_Sistema_DTO> myExtendidos;
    private Rol_Sistema_DTO myRol;
    private int identificador;

    //<editor-fold desc="Constructores">
    public Privilegio_Sistema_DTO() {
        myExtendidos = new ArrayList<Extendido_Sistema_DTO>();
    }

    public Privilegio_Sistema_DTO(Rol_Sistema_DTO miRol) {
        this.myRol = miRol;
        myExtendidos = new ArrayList<Extendido_Sistema_DTO>();
    }

    public Privilegio_Sistema_DTO(ArrayList<Extendido_Sistema_DTO> myExtendidos, Rol_Sistema_DTO myRol) {
        this.myExtendidos = myExtendidos;
        this.myRol = myRol;
    }
    
    

    //</editor-fold>
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
