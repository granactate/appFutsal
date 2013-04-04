/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Sistema.Extendido_Sistema_DTO;
import DTO.DTO_Sistema.Modulo_Sistema_DTO;
import DTO.DTO_Sistema.Rol_Sistema_DTO;
import java.util.ArrayList;
import util.BaseDeDatos;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class ModuloDAO {

    /**
     * metodo que permite obtener los modulos existentes en el sistema
     * @return un ArrayList<String> con los modulos
     */
    public static ArrayList<Modulo_Sistema_DTO> getModulos() {
        String sql = "SELECT id, nombre FROM modulo";
        
        BaseDeDatos.conectar();
        ArrayList<String> busqueda = BaseDeDatos.getConsultaSQL(sql);
        BaseDeDatos.desconectar();
        
        if (busqueda == null || busqueda.isEmpty()) {
            return null;
        }
        
        ArrayList<ArrayList<String>> matrizDatos = ManejoStringConsulta.splitSeparadorMatriz(busqueda);
        if (matrizDatos == null || matrizDatos.isEmpty()) {
            return null;
        }
        
        ArrayList<Modulo_Sistema_DTO> miArray = new ArrayList<Modulo_Sistema_DTO>();
        for (int i = 0; i < matrizDatos.size(); i++) {
            Modulo_Sistema_DTO miModulo = new Modulo_Sistema_DTO(matrizDatos.get(i).get(1)
                                            , Integer.parseInt(matrizDatos.get(i).get(0)));
            miArray.add(miModulo);
        }

        return miArray;
    }

    

    public static ArrayList<Modulo_Sistema_DTO> getModulosPorRol(Rol_Sistema_DTO miRol) {
        
        String sql="SELECT id, nombre "
                + "FROM modulo "
                + "WHERE id IN ( "
                + "SELECT fk_idmodulo "
                + "FROM extension "
                + "WHERE id IN( "
                + "SELECT id "
                + "FROM extension_has_privilegio "
                + "WHERE fk_idprivilegio in("
                + " SELECT id "
                + "FROM privilegio "
                + "WHERE fk_idrol='"+miRol.getIdentificador()+"')))";
        
        BaseDeDatos.conectar();
        ArrayList<String> consulta= BaseDeDatos.getConsultaSQL(sql);
        
        if(consulta==null||consulta.isEmpty()){
            return null;
        }
        ArrayList<Modulo_Sistema_DTO> misModulos=new ArrayList<Modulo_Sistema_DTO>();
        ArrayList<ArrayList<String>> consultaProcesada= ManejoStringConsulta.splitSeparadorMatriz(consulta);
        for(ArrayList<String> array:consultaProcesada){
            
            misModulos.add(new Modulo_Sistema_DTO(array.get(1), Integer.parseInt(array.get(0))));           
            ArrayList<Extendido_Sistema_DTO> misExtendidos= ModuloDAO.getExtendidosPorIdModulo(array.get(0));
            misModulos.get(misModulos.size()-1).setMyExtendidos(misExtendidos);
        }
        BaseDeDatos.desconectar();
        return misModulos;
        
    }
    
    
    
    /**
     * metodo que permite obtener los Extendidos de un Moodulo con el id de este
     * @param idModulo
     * @return un ArrayList<Extendido_Sistema_DTO> con los extendido de dicho modulo
     */
    private static ArrayList<Extendido_Sistema_DTO> getExtendidosPorIdModulo(String idModulo){
        if(!BaseDeDatos.hayConexion()){
           BaseDeDatos.conectar(); 
        }
        
        String sql = "SELECT id, nombre"
                + " FROM extension"
                + " WHERE fk_idmodulo = '" + idModulo + "'";

        ArrayList<String> consulta = new ArrayList<String>();
        consulta = BaseDeDatos.getConsultaSQL(sql);
        ArrayList<ArrayList<String>> consultaProcesadaExtension = ManejoStringConsulta.splitSeparadorMatriz(consulta);

        if (consulta == null || consulta.isEmpty()) {
            return null;
        }
        ArrayList<Extendido_Sistema_DTO> misExtendidos = new ArrayList<Extendido_Sistema_DTO>();


        for (ArrayList<String> resp : consultaProcesadaExtension) {
            misExtendidos.add(new Extendido_Sistema_DTO(resp.get(1), Integer.parseInt(resp.get(0))));
        }
        return misExtendidos;
    }

    /**
     * metodo que permite cargar la información de un modulo con su nombre
     * @param miModulo
     * @return el mismo objeto Modulo_Sistema_DTO  pero con la información inicial cargada<html>
     * <br>1.nombre modulo<br>2.identificador modulo
     */
    public static Modulo_Sistema_DTO getModuloPorNombre(Modulo_Sistema_DTO miModulo) {
        String sql="SELECT id "
                + "FROM modulo "
                + "WHERE nombre='"+miModulo.getNombre()+"'";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);        
        BaseDeDatos.desconectar();
        if(consulta==null||consulta.isEmpty()){
            return null;
        }
        
        int identificador=Integer.parseInt(ManejoStringConsulta.eliminarSeparador(consulta.get(0)));
        miModulo.setIdentificador(identificador);
        
        return miModulo;
    }

    /**
     * metodo que se encarga de realiza la busqueda de los extendidos que podra acceder un rol 
     * @param miRol
     * @param miModulo
     * @return el modulo con los extendidos cargados en el
     */
    public static Modulo_Sistema_DTO getExtendidosPorModuloRol(Rol_Sistema_DTO miRol, Modulo_Sistema_DTO miModulo) {
        String sql="select ext.nombre,ext.id, m.nombre " +
                "from rol as r, privilegio as p, extension_has_privilegio as extPriv,extension as ext, modulo as m " +
                " where r.id= '"+miRol.getIdentificador()+"' "
                + "and r.id=p.fk_idrol"
                + " and p.id=extPriv.fk_idprivilegio"
                + " and extPriv.id=ext.id "
                + "and ext.fk_idmodulo=m.id "
                + "and m.id='"+miModulo.getIdentificador()+"'";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null||consulta.isEmpty()){
            return null;
        }
        
        
        ArrayList<ArrayList<String>> consultaProcesada=ManejoStringConsulta.splitSeparadorMatriz(consulta);
        if(consultaProcesada==null||consultaProcesada.isEmpty()){
            return null;
        }
        ArrayList<Extendido_Sistema_DTO> misExtensiones= new ArrayList<Extendido_Sistema_DTO>();
        
        for(ArrayList<String> list:consultaProcesada){
            Extendido_Sistema_DTO nuevoExtendido= new Extendido_Sistema_DTO(list.get(0), Integer.parseInt(list.get(1)));
            misExtensiones.add(nuevoExtendido);
        }
        
        if(misExtensiones.isEmpty()){
            return null;
        }
        miModulo.setMyExtendidos(misExtensiones);
        return miModulo;
    }
    
    
}
