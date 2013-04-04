/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO.DTO_Negocio;

/**
 *
 * @author Mario(K y M)
 */
public class PartidoDTO {
    private String nombreCancha;
    private EquipoDTO myEquipoVisitante;
    private EquipoDTO myEquipoLocal;
    private ArbitroDTO myArbitro;
    private ArbitroDTO myArbitroAsistente;
    
    
    //<editor-fold desc="Constructores">
    
    
    public PartidoDTO(String nombreCancha) {
        this.nombreCancha = nombreCancha;
    }
    
    public PartidoDTO() {
    }
    
     public PartidoDTO(String nombreCancha, EquipoDTO myEquipoVisitante, EquipoDTO myEquipoLocal, ArbitroDTO myArbitro, ArbitroDTO myArbitroAsistente) {
        this.nombreCancha = nombreCancha;
        this.myEquipoVisitante = myEquipoVisitante;
        this.myEquipoLocal = myEquipoLocal;
        this.myArbitro = myArbitro;
        this.myArbitroAsistente = myArbitroAsistente;
    }

    
    //</editor-fold>

    /**
     * @return the nombreCancha
     */
    public String getNombreCancha() {
        return nombreCancha;
    }

    /**
     * @param nombreCancha the nombreCancha to set
     */
    public void setNombreCancha(String nombreCancha) {
        this.nombreCancha = nombreCancha;
    }

    /**
     * @return the myEquipoVisitante
     */
    public EquipoDTO getMyEquipoVisitante() {
        return myEquipoVisitante;
    }

    /**
     * @param myEquipoVisitante the myEquipoVisitante to set
     */
    public void setMyEquipoVisitante(EquipoDTO myEquipoVisitante) {
        this.myEquipoVisitante = myEquipoVisitante;
    }

    /**
     * @return the myEquipoLocal
     */
    public EquipoDTO getMyEquipoLocal() {
        return myEquipoLocal;
    }

    /**
     * @param myEquipoLocal the myEquipoLocal to set
     */
    public void setMyEquipoLocal(EquipoDTO myEquipoLocal) {
        this.myEquipoLocal = myEquipoLocal;
    }

    /**
     * @return the myArbitro
     */
    public ArbitroDTO getMyArbitro() {
        return myArbitro;
    }

    /**
     * @param myArbitro the myArbitro to set
     */
    public void setMyArbitro(ArbitroDTO myArbitro) {
        this.myArbitro = myArbitro;
    }

    /**
     * @return the myArbitroAsistente
     */
    public ArbitroDTO getMyArbitroAsistente() {
        return myArbitroAsistente;
    }

    /**
     * @param myArbitroAsistente the myArbitroAsistente to set
     */
    public void setMyArbitroAsistente(ArbitroDTO myArbitroAsistente) {
        this.myArbitroAsistente = myArbitroAsistente;
    }

   

    
}



