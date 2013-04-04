/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DAO.AdministradorCanchaDAO;
import DAO.CanchaDAO;
import DTO.DTO_Negocio.AdministradorCanchaDTO;
import DTO.DTO_Negocio.CanchaDTO;
import java.util.ArrayList;

/**
 *
 * @author Mario(K y M)
 */
class CanchaBusiness {

    /**
     * metodo que se encarga de realizar las verificaciones necesarias para añadir una cancha al sistema
     * @param CedulaAdmin
     * @param nomCancha
     * @param direccionCancha
     * @return String con el resultado de la operacion
     */
    public static String registrarCancha(String identificacion, String nomCancha, String direccionCancha) {
        AdministradorCanchaDTO miAdmin= new AdministradorCanchaDTO();
        miAdmin.setIdentificacion(identificacion);
        AdministradorCanchaDTO miAdminCargado=AdministradorCanchaDAO.getAdministradorCancha(miAdmin);
        
        if(miAdminCargado==null){
            return "No se pudo conectar con la base de datos";
        }
        CanchaDTO miCancha = new CanchaDTO(nomCancha,direccionCancha,miAdminCargado);
        boolean addCancha=CanchaDAO.addCancha(miCancha);
        
        return (addCancha)?
                "Se ha realizado el registro de la cancha: "+nomCancha+"\n Dirección: "+direccionCancha+"\n Administrador De la Cancha: "+miAdminCargado.getNombre():
                "No se pudo conectar con  la base de  datos";
                
    }

    /**
     * metodo que permite  listar todas las canchas exitentes en el sistema
     * @return ArrayList<String> con los nombres de las canchas y su direccion
     */
    public static ArrayList<String> getCanchas() {
        ArrayList<CanchaDTO> misCanchas=CanchaDAO.getCanchas();
        if(misCanchas==null){
            return null;
        }
        if(misCanchas.isEmpty()){
            return new ArrayList<String>();
        }
        ArrayList<String> resp= new ArrayList<String>();
        for(CanchaDTO miCancha:misCanchas){
            resp.add(miCancha.getNombre()+"/"+miCancha.getDireccion());
        }
        return resp;
    }

    /**
     * metodo que se  encarga de  realizar las diferentes validaciones para realizar el cambio del administrador
     * @param infoCancha
     * @param cedulaNuevoAdministrador
     * @return String con el resultado de la operación
     */
    public static String modificarAdministradorCancha(String infoCancha, String cedulaNuevoAdministrador) {
        String datosCancha[]=infoCancha.split("/");
        
        CanchaDTO  miCancha=new CanchaDTO(datosCancha[0], datosCancha[1]);
        AdministradorCanchaDTO miAdministrador=new AdministradorCanchaDTO();
        miAdministrador.setIdentificacion(cedulaNuevoAdministrador);
        
        CanchaDTO miCanchaCargada=CanchaDAO.getCancha(miCancha);
        if(miCanchaCargada==null){
            return "no se pudo conectar con la base de datos";
        }
        if(miCanchaCargada.getIdCancha()==-1){
            return "no se encontro la cancha en el sistema";
        }
        
        
        AdministradorCanchaDTO miAdministradorCargado=AdministradorCanchaDAO.getAdministradorCancha(miAdministrador);
        if(miAdministradorCargado==null){
            return "no se pudo conectar con la base  de datos";
        }
        
        miCanchaCargada.setMyAdministrador(miAdministradorCargado);
        
        boolean resp=CanchaDAO.asignarNuevoAdministador(miCanchaCargada);
        
        return(resp)?
                "el nuevo administrador de la cancha: "+miCanchaCargada.getNombre()+", en la dirección: " +
                miAdministradorCargado.getDireccion()+"\n tiene como nuevo administrador: "+miAdministradorCargado.getNombre()
                :"No se pudo conectar con la base de  datos";
    }

    /**
     * metodo que permite  obtener un listado  de canchas atravez de sus información identificativa
     * @param infoCanchas debe tener el formato nombreCancha/dirección
     * @return ArrayList<CanchaDTO> con la información basica de cada cancha<html><br>puede retornar null si hay algun error<br>puede retornar ArrayList vacio si no encontro los datos
     */
    public static ArrayList<CanchaDTO> getCanchas(ArrayList<String> infoCanchas) {
        ArrayList<CanchaDTO>misCanchas= new ArrayList<CanchaDTO>();
        for(String info:infoCanchas){
            String infoNuevaCancha[]=info.split("/");
            misCanchas.add(new CanchaDTO(infoNuevaCancha[0], infoNuevaCancha[1]));            
        }
        ArrayList<CanchaDTO> misCanchasCargadas=CanchaDAO.getCanchas(misCanchas);
        return misCanchasCargadas;
    }

    /**
     * metodo que permite listar todas las canchas que no han sido asignadas a ningun torneo 
     * @return ArrayList<String> con el info de las canchas<html><br>puede retornar null si hay errores<br>puede ArrayList vacio si no hay canchas disponibles
     */
    public static ArrayList<String> getCanchasDisponibles() {
        ArrayList<CanchaDTO> misCanchas=CanchaDAO.getCanchasDisponibles();
        
        if(misCanchas==null){
            return null;
        }
        if(misCanchas.isEmpty()){
            return new ArrayList<String>();
        }
        
        ArrayList<String> resp=new ArrayList<String>();
        
        for(CanchaDTO cancha:misCanchas){
            String mensaje=cancha.getNombre()+"/"+cancha.getDireccion();
            resp.add(mensaje);
        }
        return resp;
    }
    
}
