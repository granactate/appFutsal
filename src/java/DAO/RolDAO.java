/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import DTO.DTO_Sistema.Rol_Sistema_DTO;
import util.BaseDeDatos;
import util.ManejoStringConsulta;
/**
 *
 * @author Mario(K y M)
 */
public class RolDAO {
    
    
    /**
     * metodo que permite listar todos los roles que hay en la base de datos
     * @return un arrayList de objetosRol solo  con  su  nombre cargado en ellos
     */
    public static ArrayList<Rol_Sistema_DTO> getAllRoll() {

        String sql = "SELECT nombre FROM rol";
        ArrayList<Rol_Sistema_DTO> miList = new ArrayList<Rol_Sistema_DTO>();

        if (!BaseDeDatos.hayConexion()) {

            BaseDeDatos.conectar();
            ArrayList<String> ref = BaseDeDatos.getConsultaSQL(sql);
            int i=0;
            for (String x : ref) {
                Rol_Sistema_DTO nuevo = new Rol_Sistema_DTO(ManejoStringConsulta.eliminarSeparador(ref.get(i)));
                miList.add(nuevo);
                i++;
            }
        }
        BaseDeDatos.desconectar();
        return miList;
    }

    /**
     * metodo que me permite Obtener atravez de el nombre de un rol su id En la base de datos
     * @param miRol
     * @return el mimsmo objeto Rol que recibio pero con la información del id_rol cargada
     */
    public static Rol_Sistema_DTO getRolPorNombre(Rol_Sistema_DTO miRol) {
        
        String sql="SELECT id "
                + "FROM rol"
                + " WHERE nombre= "+"'"+miRol.getNombre()+"'";  
        
        BaseDeDatos.conectar();
        ArrayList<String> miList=BaseDeDatos.getConsultaSQL(sql);
        BaseDeDatos.desconectar();
        
        if(miList==null||miList.isEmpty()){
            return null;
        }
        String resp=ManejoStringConsulta.eliminarSeparador(miList.get(0));
        
        if(resp==null||resp.isEmpty()){
            return null;
        }
        miRol.setIdentificador(Integer.parseInt(resp));      
               
        return  miRol;
    }
    
    /**
     * metodo que se encarga de crear el rol dentro de la base de datos 
     * @param nuevo
     * @return un entero con el resultado de la operacion<html><br><b>0</b>.si pudo realizar el registro con exito
     * <br><b>1</b>.si el nombre del rol ya existe en el sistema<br><b>2</b>.si no pudo realizar la conexion con la base de datos</html>
     */
    public static int addRol(Rol_Sistema_DTO nuevo) {     
        
        BaseDeDatos.conectar();
        if(RolDAO.buscarRolNombre(nuevo)){
            return 1;
        }
        int idRol=RolDAO.getNextIdROl();
        if(idRol<0){
            return 2;
        }
        
        nuevo.setIdentificador(idRol);
        String sql="INSERT INTO rol("
                + " id, nombre)"
                + " VALUES ("+nuevo.getIdentificador()+", '"+nuevo.getNombre()+"')";
        if(!BaseDeDatos.ejecutarActualizacionSQL(sql)){
            return 2;
        }
        
       return 0;
    }
    
    /**
     * metodo que se encarga de buscar si existe un modulo dependiendo del nombre <html><br><i>requiere que se inicie antes la conexion con la base de datos</html>
     * @param nuevo
     * @return true si lo encontro y false en caso contrario
     */
    private static boolean buscarRolNombre(Rol_Sistema_DTO nuevo){
        String sql="SELECT nombre"
                + " FROM"
                + " rol WHERE nombre = '"+nuevo.getNombre()+"'";
        ArrayList<String> busqueda= BaseDeDatos.getConsultaSQL(sql);
        return (busqueda==null||busqueda.isEmpty())?false:true;
    }
    
    
    /**
     * metodo que se encarga de buscar el id_rol para el nuevo rol<html><br><i>requiere que se inicie antes la conexion con la base de datos</html>
     * @return el entero que lo identificara
     */
    private static int getNextIdROl(){
        String sql="SELECT id "
                + "FROM rol "
                + "ORDER BY id;";
        
        ArrayList<String> consulta2= BaseDeDatos.getConsultaSQL(sql);
        if(consulta2==null||consulta2.isEmpty()){
            return -2;
        }
        
        ArrayList<String> consultaProcesada=ManejoStringConsulta.splitSeparador(consulta2);
        int ultimo=Integer.parseInt(consultaProcesada.get(consultaProcesada.size()-1));        
        ultimo++;
        
        return ultimo;
    }
}
