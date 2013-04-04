/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.DTO_Negocio;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mario(K y M)
 */
public class FechaDTO {

    private ArrayList<HoraDTO> myHoras;
    private Date fecha;
    private PartidoDTO myPartido;
    private int id;

    //<editor-fold desc="Constructores">
    public FechaDTO(Date fecha) {
        this.fecha = fecha;
        myHoras= new ArrayList<HoraDTO>();
        this.crearHoras();
    }
    
    private void crearHoras(){
        int hora=8;
        
        for(int i=0;i<7;i++){
            HoraDTO nueva= new HoraDTO(hora);
            myHoras.add(nueva);
            hora+=2;
        }
    }

    public FechaDTO() {
        myHoras= new ArrayList<HoraDTO>();
        this.crearHoras();
    }

    public FechaDTO(PartidoDTO myPartido) {
        this.myPartido = myPartido;
        this.crearHoras();
    }
    
    //</editor-fold>

    
    /**
     * @return the myHoras
     */
    public ArrayList<HoraDTO> getMyHoras() {
        return myHoras;
    }

    /**
     * @param myHoras the myHoras to set
     */
    public void setMyHoras(ArrayList<HoraDTO> myHoras) {
        this.setMyHoras(myHoras);
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the myPartido
     */
    public PartidoDTO getMyPartido() {
        return myPartido;
    }

    /**
     * @param myPartido the myPartido to set
     */
    public void setMyPartido(PartidoDTO myPartido) {
        this.myPartido = myPartido;
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
