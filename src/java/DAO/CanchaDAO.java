/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Negocio.CanchaDTO;
import java.util.ArrayList;
import util.BaseDeDatos;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class CanchaDAO {

    /**
     * metodo que se encarga de añadir una nueva cancha al sistema asignandole un administrador
     * @param miCancha
     * @return boolean con el resultado de la operacion
     */
    public static boolean addCancha(CanchaDTO miCancha) {
                
        int id=CanchaDAO.getNextIdCancha();
        if(id<0){
            return false;
        }
        if(!validarRegistro(miCancha)){
            return false;
        }
        
        String sql="INSERT INTO cancha(id, nombre, direccion, fk_idadministradorcancha) "
                + "VALUES ('"+id+"', '"+miCancha.getNombre()+"', '"+miCancha.getDireccion()+"',"
                + " '"+miCancha.getMyAdministrador().getIdentificacion()+"')";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        return BaseDeDatos.ejecutarActualizacionSQL(sql);
    }
    
    /**
     * metodo que permite validar que no exista una cancha con el mismo nombre en la misma direccion 
     * @param miCancha
     * @return true si encontro una cancha con el mismo nombre en ls misma direccion
     */
    private static boolean validarRegistro(CanchaDTO miCancha){
        String sql="select id "
                + "from cancha "
                + "where direccion='"+miCancha.getDireccion()+"' and nombre='"+miCancha.getNombre()+"'";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        if(consulta!=null||consulta.isEmpty()){
            return true;
        }
        return false;
    }
    
    /**
     * metodo que se encarga de  generar el proximo identificador para la base de  datos
     * @return int con la nueva identificacion de la nueva cancha
     */
    private static int getNextIdCancha(){
        String sql="select id "
                + "from cancha "
                + "order by id";
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null){
            return -1;
        }
        if(consulta.isEmpty()){
            return 0;
        }
        return Integer.parseInt(ManejoStringConsulta.eliminarSeparador(consulta.get(consulta.size()-1)))+1;
    }

    /**
     * metodo que se encarga de realizar la busqueda de todas las canchas del sistema
     * @return ArrayList<CanchaDTO> con su informacion cargada:<html><br>id<br>nombre<br>direccion
     */
    public static ArrayList<CanchaDTO> getCanchas() {
        String sql="select id,nombre,direccion from cancha";
        
        if(!BaseDeDatos.hayConexion())
        {
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta= BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null){
            return null;
        }
        
        if(consulta.isEmpty()){
            return new ArrayList<CanchaDTO>();
        }
        
        ArrayList<ArrayList<String>> consultaProcesada=ManejoStringConsulta.splitSeparadorMatriz(consulta);
        ArrayList<CanchaDTO> misCanchas= new ArrayList<CanchaDTO>();
        for(ArrayList<String> datos:consultaProcesada){
            int id=Integer.parseInt(datos.get(0));
            String nombre=datos.get(1);
            String direccion=datos.get(2);
            
            misCanchas.add(new CanchaDTO(nombre, direccion, id));
        }
        return misCanchas;
    }

    /**
     * metodo que permite consultar la infromación de  una cancha en la base de datos con el nombre y la direccion de esta
     * @param miCancha
     * @return CanchaDTO con la información basica cargada
     */
    public static CanchaDTO getCancha(CanchaDTO miCancha) {
        String sql="select id "
                + "from cancha "
                + "where nombre='"+miCancha.getNombre()+"'and direccion='"+miCancha.getDireccion()+"'";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null){
            return null;
        }
        if(consulta.isEmpty()){
            return  miCancha;
        }
        int id=Integer.parseInt(ManejoStringConsulta.eliminarSeparador(consulta.get(0)));
        miCancha.setIdCancha(id);
        return miCancha;
    }

    /**
     * metodo que permite  cambiar el administrador de una cancha 
     * @param miCanchaCargada <html><color font=green> debe estar cargado el nuevo administrador
     * @return 
     */
    public static boolean asignarNuevoAdministador(CanchaDTO miCanchaCargada) {
        String sql="UPDATE cancha "
                + "SET fk_idadministradorcancha='"+miCanchaCargada.getMyAdministrador().getIdentificacion()+"' "
                + "WHERE id='"+miCanchaCargada.getIdCancha()+"'";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        return BaseDeDatos.ejecutarActualizacionSQL(sql);
            
    }

    /**
     * metodo que permite obtener un listado de canchas y cargar sus id
     * @param misCanchas deben tener cargado el nombre y direccion
     * @return ArrayList<CanchaDTO>  con el id de cada una cargada
     */
    public static ArrayList<CanchaDTO> getCanchas(ArrayList<CanchaDTO> misCanchas) {
        
        ArrayList<CanchaDTO> canchasCargadas= new ArrayList<CanchaDTO>();
        
        for(CanchaDTO cancha:misCanchas){
            String sql="select id "
                    + "from cancha "
                    + "where nombre='"+cancha.getNombre()+"' and direccion='"+cancha.getDireccion()+"'";
            
            if(!BaseDeDatos.hayConexion()){
                BaseDeDatos.conectar();
            }
            ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
            if(consulta==null){
                return null;
            }
            if(!consulta.isEmpty()){
                cancha.setIdCancha(Integer.parseInt(ManejoStringConsulta.eliminarSeparador(consulta.get(0))));
                canchasCargadas.add(cancha);
            }            
        }
        return canchasCargadas;
    }

    /**
     * metodo que de realizar la busqueda en la base  para obtener las canchas que no han sido asignados a ningun torneo
     * @return return null si encuentra un error<html><br> puede ArrayList vacio si no encuentra ninguna cancha disponible en el sistema
     */
    public static ArrayList<CanchaDTO> getCanchasDisponibles() {
        String sql="select id,nombre, direccion "
                + "from cancha "
                + "where id in("
                + " select id from cancha "
                + "except "
                + "select c.id "
                + "from cancha_has_torneo as ct, cancha as c "
                + "where ct.fk_idcancha=c.id)";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta= BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null){
            return null;
        }
        if(consulta.isEmpty()){
            return new ArrayList<CanchaDTO>();
        }
        
        ArrayList<CanchaDTO> misCanchas= new ArrayList<CanchaDTO>();
        ArrayList<ArrayList<String>>  consultaProcesada= ManejoStringConsulta.splitSeparadorMatriz(consulta);        
        for(ArrayList<String> datos:consultaProcesada){
            misCanchas.add(new CanchaDTO(datos.get(1),
                    datos.get(2), 
                    Integer.parseInt(datos.get(0))));
        }
        return misCanchas;
    }
    
}
