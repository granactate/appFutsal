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
public class EquipoDTO {
    
    private String nombre;
    private char grupo;
    private ArrayList<JugadorDTO> myJugadores;
    private int id;
    private DirectorTecnicoDTO myDirectorTecnico;
    private boolean aprovado;
    
    
    
    //<editor-fold desc="Constructore">
    public EquipoDTO() {
    }

    public EquipoDTO(String nombre) {
        this.nombre = nombre;
    }

    public EquipoDTO(String nombre, ArrayList<JugadorDTO> myJugadores) {
        this.nombre = nombre;
        this.myJugadores = myJugadores;
    }

    public EquipoDTO(String nombre,char grupo,int id){
        this.nombre=nombre;
        this.grupo=grupo;
        this.id=id;
    }
    
    public EquipoDTO(String nombre, char grupo, int id, DirectorTecnicoDTO myDirectorTecnico) {
        this.nombre = nombre;
        this.grupo = grupo;
        this.id = id;
        this.myDirectorTecnico = myDirectorTecnico;
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
     * @return the grupo
     */
    public char getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the myJugadores
     */
    public ArrayList<JugadorDTO> getMyJugadores() {
        return myJugadores;
    }

    /**
     * @param myJugadores the myJugadores to set
     */
    public void setMyJugadores(ArrayList<JugadorDTO> myJugadores) {
        this.myJugadores = myJugadores;
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
     * @return the myDirectorTecnico
     */
    public DirectorTecnicoDTO getMyDirectorTecnico() {
        return myDirectorTecnico;
    }

    /**
     * @param myDirectorTecnico the myDirectorTecnico to set
     */
    public void setMyDirectorTecnico(DirectorTecnicoDTO myDirectorTecnico) {
        this.myDirectorTecnico = myDirectorTecnico;
    }

    /**
     * @return the aprovado
     */
    public boolean isAprovado() {
        return aprovado;
    }

    /**
     * @param aprovado the aprovado to set
     */
    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    
}
