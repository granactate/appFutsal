/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Negocio.AdministradorCanchaDTO;
import java.util.ArrayList;
import java.util.Date;
import util.BaseDeDatos;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class AdministradorCanchaDAO {

    /**
     * metodo que se encarga de realizar las busquedas para encontrar los administradores de cancha sin registro        
     * @return ArrayList<AdministradorCanchaDTO> con los datos cargados
     */
    public static ArrayList<AdministradorCanchaDTO> getAdministradoresCancha() {
        String sql="select u.id, u.nombre, u.apellidos, u.fechanacimiento, u.correoelectronico,"
                + " u.direccion, u.telefono,  u.username, u.password "
                + "from administradorcancha as ac , usuario as u "
                + "where  ac.fk_idusuario=u.id";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta= BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null){
            return null;
        }
        
        if(consulta.isEmpty()){
            return new ArrayList<AdministradorCanchaDTO>();
        }
        
        ArrayList<AdministradorCanchaDTO> resp=new ArrayList<AdministradorCanchaDTO>();
        ArrayList<ArrayList<String>> consultaProcesada=ManejoStringConsulta.splitSeparadorMatriz(consulta);
        
        for(ArrayList<String> datos:consultaProcesada){
                       
            AdministradorCanchaDTO miAdmin=crearAdministrador(datos);
            if(miAdmin!=null){
            resp.add(miAdmin);
            }
        }
        return resp;
    }

    /**
     * metoodo que permite obtener un Administrador de una cancha con solo la identificacion
     * @param miAdmin <html><font color=green>solo requiere tener la identificacion cargada</font>
     * @return un AdministradorCanchaDTO con toda la informacion cargada
     */
    public static AdministradorCanchaDTO getAdministradorCancha(AdministradorCanchaDTO miAdmin) {
        String sql="select  u.id, u.nombre, u.apellidos, u.fechanacimiento,"
                + " u.correoelectronico, u.direccion, u.telefono,  u.username, u.password "
                + "from usuario as u , administradorcancha as ac "
                + "where ac.fk_idusuario=u.id and ac.fk_idusuario='"+miAdmin.getIdentificacion()+"'";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta= BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null||consulta.isEmpty()){
            return null;
        }
        
        ArrayList<ArrayList<String>> consultaProcesada=ManejoStringConsulta.splitSeparadorMatriz(consulta);
        
        return crearAdministrador(consultaProcesada.get(0));
    }
    
    /**
     * metodo encargado de crear un AdministradorCancha con un ArrayList<String> 
     * @param datos el Array debe tener el siguiente formato<html><br>id <br>nombre <br>apellidos <br>fechanacimiento 
     * <br>u.correoelectronico<br>direccion <br>telefono <br>username<br>password
     * @return el AdministradorCancha que  acaba de crear en caso de no poder hacerlo retornara null
     */
    private static AdministradorCanchaDTO crearAdministrador(ArrayList<String> datos){
        try {
            String nombre=datos.get(1);        
            String apellidos=datos.get(2);
            String identificacion= (datos.get(0));
            Date fecha=ManejoStringConsulta.getFechaFormatoDate(datos.get(3));
            String correo=datos.get(4);
            String direccion=datos.get(5);
            String telefono=datos.get(6);
            String username=datos.get(7);
            String password=datos.get(8);
            
            AdministradorCanchaDTO miAdmin= new AdministradorCanchaDTO(nombre, apellidos, identificacion, 
                                                    fecha, username, password, telefono, direccion, correo);
            return miAdmin;
            
        }catch(Exception e){
            return null;
        }
    }
    
}
