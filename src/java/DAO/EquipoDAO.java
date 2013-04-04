/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Negocio.EquipoDTO;
import DTO.DTO_Negocio.JugadorDTO;
import DTO.DTO_Negocio.TorneoDTO;
import java.util.ArrayList;
import java.util.Date;
import util.BaseDeDatos;
import util.ManejoDate;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class EquipoDAO {

    /**
     * metodo que permite obtener un equipo de un torneo con el nombre del equipo y el nombre del torneo
     * @param torneoSeleccionado solo el nombre del torneo cargado para la busqueda
     * @param nuevoEquipo solo el nombre del equipo cargado para la busqueda
     * @param validado boolean si busca equipo validado o sin validar 
     * @return EquipoDTO que coincida con el nombre en el torneo especificado
     */
    public static EquipoDTO getEquipoPorNombreTorneo(TorneoDTO torneoSeleccionado, EquipoDTO nuevoEquipo,boolean validado) {
        String bool=(validado)?"t":"f";
        
        String sql = "select e.id, e.nombre,et.validado "
                + "from equipo as e, equipo_has_torneo as et, torneo as t "
                + "where e.nombre='"+nuevoEquipo.getNombre()+"' and e.id=et.fk_idequipo "
                + "and et.fk_idtorneo=t.id and t.nombre='"+torneoSeleccionado.getNombre()+"' and et.validado='"+bool+"'";
        
        if (!BaseDeDatos.hayConexion()) {
            BaseDeDatos.conectar();
        }

        ArrayList<String> consulta = BaseDeDatos.getConsultaSQL(sql);

        if (consulta == null || consulta.isEmpty()) {
            return null;
        }
        
        ArrayList<ArrayList<String>> datosCargados=ManejoStringConsulta.splitSeparadorMatriz(consulta);
        nuevoEquipo.setId(Integer.parseInt(datosCargados.get(0).get(0)));    
        BaseDeDatos.desconectar();
        return nuevoEquipo;
    }
    /**
     * metodo que permite crear un patido en la base de datos y asinarlo a  un torneo
     * @param torneoCargado
     * @return <html>1.si pudo realizar el registro<br>2.si presento un error en la base de datos
     */
    public static int addEquipo(TorneoDTO torneoCargado) {
        
        EquipoDTO equipoCargado=torneoCargado.getMyEquipos().get(0);
        int idEquipo=EquipoDAO.getNextIdEquipo();
        
        if(idEquipo<0){
            return 2;
        }
        equipoCargado.setId(idEquipo);
        
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        boolean addEquipo=EquipoDAO.addEquipo(equipoCargado);
        if(!addEquipo){
            return 2;
        }
        
        boolean asignarEquipoTorneo=EquipoDAO.asignarEquipoTorneo(torneoCargado, equipoCargado);
        if(!asignarEquipoTorneo){
            return 2;
        }
        
        
        for (JugadorDTO jugadorEquipo : equipoCargado.getMyJugadores()){
            
            boolean addJugador=EquipoDAO.addJugador(jugadorEquipo, equipoCargado);
            if(!addJugador){
                return 2;
            }            
            boolean asignaJugador=EquipoDAO.asignarEquipoJugador(equipoCargado, jugadorEquipo);            
            if(!asignaJugador){
                return 2;
            }            
            
        }
        
        
        return 1;
    }
    
    /**
     * metodo que permite crear un equipoEn la base de datos
     * @param miEquipo
     * @return un boolean con el resultado de la operacion
     */
    private static boolean addEquipo(EquipoDTO miEquipo){
        
        String sql="INSERT INTO equipo(id, nombre, grupo, fk_iddirectortecnico)"
                + " VALUES ('"+miEquipo.getId()+"', '"+miEquipo.getNombre()+"', '*'"
                +",'"+miEquipo.getMyDirectorTecnico().getIdentificacion()+"')";
            
            if(!BaseDeDatos.hayConexion()){
                BaseDeDatos.conectar();
            }
            
            boolean resp=BaseDeDatos.ejecutarActualizacionSQL(sql);
            BaseDeDatos.desconectar();
            return resp;
    }
    
    /**
     * metodo que se encarga de asignar en la base de datos un torneo a un equipo
     * @param miTorneo
     * @param miEquipo
     * @return boolean con el resultado de la operación
     */
    private static boolean asignarEquipoTorneo(TorneoDTO miTorneo,EquipoDTO miEquipo){
        
        int id=EquipoDAO.getNextIdEquipo();
        String sql="INSERT INTO equipo_has_torneo(id, fk_idtorneo, fk_idequipo,validado) "
                + "VALUES ('"+id+"', '"+miTorneo.getId()+"', '"+miEquipo.getId()+"',false)";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        boolean resp=BaseDeDatos.ejecutarActualizacionSQL(sql);
        BaseDeDatos.desconectar();
        return resp;
    }
    
    
    /**
     * metodo que permite obtener el id del proximo registro de la tabla equipo_has_torneo
     * @return int que sera el identificador 
     */
    private static int getNextIdEquipoTorneo(){
        String sql="SELECT id FROM equipo_has_torneo ORDER BY id";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        
        if(consulta==null){
            return -1;
        }
        
        if(consulta.isEmpty()){
            return 1;
        }
        
        return Integer.parseInt(ManejoStringConsulta.eliminarSeparador(consulta.get(consulta.size()-1)))+1;
    }
    
    /**
     * metodo que permite asignar un equipo a un jugador  en la base de datos
     * @param miEquipo
     * @param miTorneo
     * @return un boolean con el resultado de la operacion
     */
    private static boolean asignarEquipoJugador(EquipoDTO miEquipo, JugadorDTO miJugador){
        
        int id=getNextIdEquipoJugador();
        
        if(id<0){
            return false;
        }
        
        Date fechaAsignacion=ManejoDate.getFechaActual();
        
        String sql="INSERT INTO jugador_has_equipo(id, fk_idequipo, fk_idjugador,fechaasignacion) "
                + "VALUES ('"+id+"', '"+miEquipo.getId()+"', '"+miJugador.getIdentificacion()+"','"+ManejoStringConsulta.getFechaFormato(fechaAsignacion) +"')";
        
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        boolean val=BaseDeDatos.ejecutarActualizacionSQL(sql);
        BaseDeDatos.desconectar();
        return val;
    }
    
    /**
     * metodo que permite añadir un jugador en la base de datos
     * @param jugadorEquipo
     * @param equipoCargado
     * @return un boolean con el resultado de la operacion
     */
    private static boolean addJugador(JugadorDTO jugadorEquipo,EquipoDTO equipoCargado){
        int id=getNextIdJugador();
        if(id<0){
            return false;
        }
        String sql = "INSERT INTO jugador(nombre, apellidos, fechanacimiento, estatura, peso, id)"
                + " VALUES ('"+jugadorEquipo.getNombre()+"',"
                + "'"+jugadorEquipo.getApellidos()+"',"
                + "'"+ManejoDate.getDate(jugadorEquipo.getFechaNacimiento()) +"',"
                + "'"+jugadorEquipo.getEstatura()+"',"
                + "'"+jugadorEquipo.getPeso()+"',"
                + "'"+id+"' )";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        boolean resp=BaseDeDatos.ejecutarActualizacionSQL(sql);
        BaseDeDatos.desconectar();
        return resp;
    }
    
    
    /**
     * metodo que permite obtener el proximo identificador de nuevo jugador
     * @return int con el nuevo id<html><br>pueder retornar -1 si no pudo conectar con la base de datos
     */
    private static int getNextIdJugador(){
        String sql="select id from jugador order by id";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        
        BaseDeDatos.desconectar();
        
        if(consulta==null){
            return -1;
        }
        
        if(consulta.isEmpty()){
            return 1;
        }
        
        int resp=Integer.parseInt(ManejoStringConsulta.eliminarSeparador(consulta.get(consulta.size()-1)));
        resp++;
        
        return resp;
    }
    
    /**
     * metodo que permite obtener el proximo id del Equipo jugador de la base de datos
     * @return un entero que sera el id del nuevo registro
     */
    private static int getNextIdEquipoJugador(){
        String sql="select id"
                + " from jugador_has_equipo"
                + " order by id";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        ArrayList<String> resp=BaseDeDatos.getConsultaSQL(sql);
        BaseDeDatos.desconectar();
        if(resp==null){
            return -1;
        }
        if(resp.isEmpty()){
            return 1;
        }
        
        return Integer.parseInt(ManejoStringConsulta.eliminarSeparador(resp.get(resp.size()-1)))+1;
    }
    
    
    /**
     * metodo que permite obtener 
     * @return 
     */
    private static int getNextIdEquipo() {

        String sql = "select id from equipo order by id ";
        if (!BaseDeDatos.hayConexion()) {
            BaseDeDatos.conectar();
        }

        ArrayList<String> resp = BaseDeDatos.getConsultaSQL(sql);
        BaseDeDatos.desconectar();

        if (resp == null) {
            return -1;
        }

        if (resp.isEmpty()) {
            return 1;
        }

        return Integer.parseInt(ManejoStringConsulta.eliminarSeparador(resp.get(resp.size()-1))) + 1;
    }

    /**
     * metodo que permite  obtener un equipo conociendo su nombre unicamente 
     * @param miEquipo
     * @return un EquipoDTO con su informacion Correspondiente
     */
    public static EquipoDTO getEquipoPorNombreTorneo(EquipoDTO miEquipo) {
        String sql="SELECT e.id,e.nombre,e.nombre,e.grupo,equiTor.validado"
                + " from equipo as e, equipo_has_torneo as equiTor"
                + " where e.nombre= '"+miEquipo.getNombre()+"' and equiTor.fk_idequipo=e.id";
        if(!BaseDeDatos.hayConexion()){
        BaseDeDatos.conectar();
        }
        ArrayList<String> consulta= BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null||consulta.isEmpty()){
            return null;
        }
        ArrayList<ArrayList<String>> datosProcesados=ManejoStringConsulta.splitSeparadorMatriz(consulta);
        int id= Integer.parseInt(datosProcesados.get(0).get(0));
        char grupo=datosProcesados.get(0).get(2).charAt(0);
        
        miEquipo.setGrupo(grupo);
        miEquipo.setId(id);        
        
        BaseDeDatos.desconectar();
        
        return miEquipo;
        
    }

   
    
   
  
}
