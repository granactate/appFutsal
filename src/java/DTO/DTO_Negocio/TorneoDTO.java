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
public class TorneoDTO {
    private String nombre;
    private Date fechaCreacion;
    private Date fechaLimiteInscripcion;
    private Date fechaSorteoGrupo;
    private Date fechaSorteoPartidos;
    private Date inicioTorneo;
    private OrganizadorTorneoDTO myOrganizador;
    private ArrayList<EquipoDTO> myEquipos;
    private ArrayList<PartidoDTO> myPartidos;
    private boolean estaActivo;
    private int id;
            
    
    //<editor-fold desc="Constructores">
    public TorneoDTO(String nombre) {
        this.nombre = nombre;
        this.myEquipos= new ArrayList<EquipoDTO>();
        this.myPartidos= new ArrayList<PartidoDTO>();
    }

    public TorneoDTO(OrganizadorTorneoDTO myOrganizador) {
        this.myOrganizador = myOrganizador;
        this.myEquipos= new ArrayList<EquipoDTO>();
        this.myPartidos= new ArrayList<PartidoDTO>();
    }

    public TorneoDTO(String nombre, Date fechaCreacion, OrganizadorTorneoDTO myOrganizador) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.myOrganizador = myOrganizador;
        this.myEquipos= new ArrayList<EquipoDTO>();
        this.myPartidos= new ArrayList<PartidoDTO>();
    }    

    public TorneoDTO(String nombre, Date fechaLimiteInscripcion, Date fechaSorteoGrupo, Date fechaSorteoPartidos, Date inicioTorneo, OrganizadorTorneoDTO myOrganizador) {
        this.nombre = nombre;
        this.fechaLimiteInscripcion = fechaLimiteInscripcion;
        this.fechaSorteoGrupo = fechaSorteoGrupo;
        this.fechaSorteoPartidos = fechaSorteoPartidos;
        this.inicioTorneo = inicioTorneo;
        this.myOrganizador = myOrganizador;
        this.myEquipos= new ArrayList<EquipoDTO>();
        this.myPartidos= new ArrayList<PartidoDTO>();
    }

    public TorneoDTO(String nombre, Date fechaCreacion, Date fechaLimiteInscripcion, Date fechaSorteoGrupo, Date fechaSorteoPartidos, Date inicioTorneo) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimiteInscripcion = fechaLimiteInscripcion;
        this.fechaSorteoGrupo = fechaSorteoGrupo;
        this.fechaSorteoPartidos = fechaSorteoPartidos;
        this.inicioTorneo = inicioTorneo;
        this.myEquipos= new ArrayList<EquipoDTO>();
        this.myPartidos= new ArrayList<PartidoDTO>();
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
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaLimiteInscripcion
     */
    public Date getFechaLimiteInscripcion() {
        return fechaLimiteInscripcion;
    }

    /**
     * @param fechaLimiteInscripcion the fechaLimiteInscripcion to set
     */
    public void setFechaLimiteInscripcion(Date fechaLimiteInscripcion) {
        this.fechaLimiteInscripcion = fechaLimiteInscripcion;
    }

    /**
     * @return the fechaSorteoGrupo
     */
    public Date getFechaSorteoGrupo() {
        return fechaSorteoGrupo;
    }

    /**
     * @param fechaSorteoGrupo the fechaSorteoGrupo to set
     */
    public void setFechaSorteoGrupo(Date fechaSorteoGrupo) {
        this.fechaSorteoGrupo = fechaSorteoGrupo;
    }

    /**
     * @return the fechaSorteoPartidos
     */
    public Date getFechaSorteoPartidos() {
        return fechaSorteoPartidos;
    }

    /**
     * @param fechaSorteoPartidos the fechaSorteoPartidos to set
     */
    public void setFechaSorteoPartidos(Date fechaSorteoPartidos) {
        this.fechaSorteoPartidos = fechaSorteoPartidos;
    }

    /**
     * @return the inicioTorneo
     */
    public Date getInicioTorneo() {
        return inicioTorneo;
    }

    /**
     * @param inicioTorneo the inicioTorneo to set
     */
    public void setInicioTorneo(Date inicioTorneo) {
        this.inicioTorneo = inicioTorneo;
    }

    /**
     * @return the myOrganizador
     */
    public OrganizadorTorneoDTO getMyOrganizador() {
        return myOrganizador;
    }

    /**
     * @param myOrganizador the myOrganizador to set
     */
    public void setMyOrganizador(OrganizadorTorneoDTO myOrganizador) {
        this.myOrganizador = myOrganizador;
    }

    /**
     * @return the myEquipos
     */
    public ArrayList<EquipoDTO> getMyEquipos() {
        return myEquipos;
    }

    /**
     * @param myEquipos the myEquipos to set
     */
    public void setMyEquipos(ArrayList<EquipoDTO> myEquipos) {
        this.setMyEquipos(myEquipos);
    }

    /**
     * @return the myPartidos
     */
    public ArrayList<PartidoDTO> getMyPartidos() {
        return myPartidos;
    }

    /**
     * @param myPartidos the myPartidos to set
     */
    public void setMyPartidos(ArrayList<PartidoDTO> myPartidos) {
        this.setMyPartidos(myPartidos);
    }

    
    /**
     * @return the estaActivo
     */
    public boolean isEstaActivo() {
        return estaActivo;
    }

    /**
     * @param estaActivo the estaActivo to set
     */
    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
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