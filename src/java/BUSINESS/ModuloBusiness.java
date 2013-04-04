/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DAO.ModuloDAO;
import DTO.DTO_Sistema.Extendido_Sistema_DTO;
import DTO.DTO_Sistema.Modulo_Sistema_DTO;
import DTO.DTO_Sistema.Rol_Sistema_DTO;
import java.util.ArrayList;

/**
 *
 * @author <html>Mario Hernando Nieto Serrano</html>
 */
class ModuloBusiness {

    /**
     * metodo que permite obtener los modulos existentes en el sistema con su respectivo modulo
     * @return retorna una matriz tridimensional de string<html><br><font color=green><i><b>el primer String del arreglo sera el nombre del modulo<br>
     * y los siguientes String seran correspondientes a los extendidos de dicho modulo</html>
     */
    public static ArrayList<String> getModulosConExtendidos() {
        ArrayList<Modulo_Sistema_DTO> misModulos=ModuloDAO.getModulos();
        if(misModulos==null||misModulos.isEmpty()){
            return null;
        }
        
        ArrayList<Modulo_Sistema_DTO> misModulosCargados=ExtendidoBusiness.getExtendidosModulo(misModulos);                
        return ModuloBusiness.pasarAArray(misModulos);
    }
    
    /**
     * metodo que se encarga de copiar los datos de los modulos y sus extensiones un arreglo de String donde:<html><br><br>
     * <font color=green><i><b>el primer String del arreglo sera el nombre del modulo<br>
     * y los siguientes String seran correspondientes a los extendidos de dicho modulo
     * @param misModulos
     * @return una matriz tridimensional de String con los modulos y sus respectivos extendidos
     */
    private static  ArrayList<String> pasarAArray(ArrayList<Modulo_Sistema_DTO> misModulos){
        if(misModulos==null||misModulos.isEmpty()){
            return null;
        }
        ArrayList<String> resp= new ArrayList<String>();
        String val;
        for(Modulo_Sistema_DTO modulo:misModulos){
            val="";
            
            val+=modulo.getNombre()+"/";
            if(modulo.getMyExtendidos()==null||modulo.getMyExtendidos().isEmpty()){
                return null;
            }
            
            for(Extendido_Sistema_DTO extendido: modulo.getMyExtendidos()){
                val+=extendido.getNombre()+"/";
            }            
            resp.add(val);
        }
        return resp;
    }

    /**
     * metodo que permite listar los modulos y extendidos de un rol especifico
     * @param nombreRol
     * @return un ArrayList<String> con la información cargada donde el primero Strign sera el nombre del modulo
     * y el resto de los String seran los extendidos de determinado rol
     */
    public static ArrayList<String> getModulosExtendidosPorRol(String nombreRol) {
        
        Rol_Sistema_DTO miRol = RolBusiness.getRolPorNombre(nombreRol);
        ArrayList<Modulo_Sistema_DTO> misModulos = ModuloDAO.getModulosPorRol(miRol);
        if (misModulos == null || misModulos.isEmpty()) {
            return null;
        }

        ArrayList<String> arrayResp = new ArrayList<String>();
        String resp = "";


        for (Modulo_Sistema_DTO modulo : misModulos) {
            resp += modulo.getNombre() + "/";

            for (Extendido_Sistema_DTO extendido : modulo.getMyExtendidos()) {
                resp += extendido.getNombre() + "/";
            }

            arrayResp.add(resp);
            resp = "";
        }

        return arrayResp;        
    }
    
    /**
     * metodo que permite obtenre los modulos a los que podra acceder un rol determinado
     * @param miRol
     * @return un ArrayList<Modulo_Sistema_DTO> con los modulos a los que podra acceder dicho rol
     */
    public static ArrayList<Modulo_Sistema_DTO> getModulosPorRol(Rol_Sistema_DTO miRol){
        ArrayList<Modulo_Sistema_DTO> misModulos = ModuloDAO.getModulosPorRol(miRol);

        return misModulos;                
    }

    /**
     * metodo que permite obtener un modulo dependidendo de su nombre
     * @param nombreModulo
     * @return Modulo_Sistema_DTO con la información inicial Cargada<html><br>1.nombre<br>2.identificador
     */
    public static Modulo_Sistema_DTO getModuloPorNombre(String nombreModulo) {
        Modulo_Sistema_DTO miModulo= new Modulo_Sistema_DTO(nombreModulo);
        Modulo_Sistema_DTO miModuloCargado=ModuloDAO.getModuloPorNombre(miModulo);
        return miModuloCargado;
    }

    
    
    /**
     * metodo que se permite buscar los extendidos que pertenecen a un modulo y que podran ser accedidos por un rol determinado
     * @param miRol
     * @param miModulo
     * @return un modulo con las extensiones cargadas en el
     */
    public static Modulo_Sistema_DTO getExtendidoPorModuloRol(Rol_Sistema_DTO miRol, Modulo_Sistema_DTO miModulo) {
        
        return ModuloDAO.getExtendidosPorModuloRol(miRol,miModulo);
    }
    
    
}
