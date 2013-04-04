/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.DTO_Negocio;

import java.util.ArrayList;

/**
 *
 * @author Mario(K y M)
 */
public class HorarioDTO {
    private int id;
    private ArrayList<FechaDTO> myFechas;
    
    //<editor-fold desc="Constructores">
    public HorarioDTO() {
    }

    public HorarioDTO(ArrayList<FechaDTO> myFechas) {
        this.myFechas = myFechas;
    }
    //</editor-fold> 

    /**
     * @return the myFechas
     */
    public ArrayList<FechaDTO> getMyFechas() {
        return myFechas;
    }

    /**
     * @param myFechas the myFechas to set
     */
    public void setMyFechas(ArrayList<FechaDTO> myFechas) {
        this.setMyFechas(myFechas);
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

  
   
    
    
}
