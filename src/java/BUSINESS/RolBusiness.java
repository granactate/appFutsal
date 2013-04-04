/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;
import DAO.RolDAO;
import DTO.DTO_Sistema.Rol_Sistema_DTO;
import java.util.ArrayList;
/**
 *
 * @author Mario(K y M)
 */
public class RolBusiness {
  
    
    /**
     * metodo que retorna todos los roles registrados en la base de datos
     * @return un ArrayList con los nombres de dichos roles
     */
    public static  ArrayList<String> getAllRoll(){
        
        ArrayList<Rol_Sistema_DTO> miList = RolDAO.getAllRoll();
        
        ArrayList<String> resp = new ArrayList<String>();

        if (miList.isEmpty()) {
            resp.add("no hay roles registrados");
            return resp;
        }

        for (Rol_Sistema_DTO rol : miList) {
            resp.add(rol.getNombre());
        }
        return resp;
    }

    /**
     * metodo que retorna  un Rol con el id_rol cargado desde la base de  datos
     * segun el nombre del rol
     * @param user
     * @return un objeto rol con  el id_rol cargado
     */
    public static Rol_Sistema_DTO getRolPorNombre(String rol) {
        Rol_Sistema_DTO miRol= new Rol_Sistema_DTO(rol);
        
        Rol_Sistema_DTO resp=RolDAO.getRolPorNombre(miRol);
        
        
        return resp;
    }     

    /**
     * metodo que permite crear un rol en nuevo en el sistema apartir de su nombre
     * @param nombre
     * @return un entero con el resultado de la operacion
     */
    public static Rol_Sistema_DTO addRol(String nombre) {
         Rol_Sistema_DTO nuevo= new Rol_Sistema_DTO(nombre);
         int resp=RolDAO.addRol(nuevo);
         
        return nuevo;
    }
    
   
}
