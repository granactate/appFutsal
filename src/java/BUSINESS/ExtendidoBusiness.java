/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DAO.ExtensionDAO;
import DTO.DTO_Sistema.Extendido_Sistema_DTO;
import DTO.DTO_Sistema.Modulo_Sistema_DTO;
import DTO.DTO_Sistema.Privilegio_Sistema_DTO;
import DTO.DTO_Sistema.Rol_Sistema_DTO;
import java.util.ArrayList;
/**
 *
 * @author Mario(K y M)
 */
class ExtendidoBusiness {

    /**
     * metodo que permite cargar los extendidos de los modulos que recibe en el array
     * @param misModulos
     * @return un ArrayList<Modulo_Sistema_DTO> con los extendiso de cada uno de los modulos cargados
     */
    public static ArrayList<Modulo_Sistema_DTO> getExtendidosModulo(ArrayList<Modulo_Sistema_DTO> misModulos) {
        if(misModulos==null||misModulos.isEmpty()){
            return null;
        }
        return ExtensionDAO.CargarExtendidosAModulo(misModulos);
    }

    /**
     * metodo que se encarga de crear un nuevo rol en el sistema con sus respectivos extendidos
     * @param nombre
     * @param nombreExtendidos
     * @return una cadena con el resultado de la operacion
     */
    public static String crearRol(String nombre, ArrayList<String> nombreExtendidos) {
        try{
        ArrayList<Extendido_Sistema_DTO> misExtendidos=new ArrayList<Extendido_Sistema_DTO>();
        for(String nombreDeExtension:nombreExtendidos){
            misExtendidos.add(new Extendido_Sistema_DTO(nombreDeExtension));
        }
        ArrayList<Extendido_Sistema_DTO>misExtendidosCargados=ExtensionDAO.cargarExtendidos(misExtendidos);
        
        if(nombreExtendidos==null|| nombreExtendidos.isEmpty()){
            return "se deben definir primero los extendidos del nuevo rol";
        }
        
        Rol_Sistema_DTO nuevo=RolBusiness.addRol(nombre);
        
        if(nuevo==null){
            return "El rol ya existe en el sistema";
        }
        
        Privilegio_Sistema_DTO  nuevoPrivilegio=PrivilegioBusiness.addPrivilegio(misExtendidosCargados,nuevo);
        
        if(nuevoPrivilegio==null){
            return "No se pudo crear el Privilegio";
        }
        String resp="";
        for(Extendido_Sistema_DTO ext:nuevoPrivilegio.getMyExtendidos()){
            resp+=ext.getNombre()+"\n";
        }
        return "Se ha creado con exito el nuevo rol: "+nuevo.getNombre()+"\n"+resp;
                
        }
        catch(Exception e){
            return "no se pudo establecer conexion con la base de datos";
        }
    }

    
    /**
     * metodo que permite cargar la información de una lista  de Extendidos son con el nombre de cada uno de ellos
     * @param nombreExtendidos
     * @return un ArrayList<Extendido_Sistema_DTO> con la información de cada uno de ellos cargados
     */
    public static ArrayList<Extendido_Sistema_DTO> getExtendidoPorNombre(ArrayList<String> nombreExtendidos) {
        ArrayList<Extendido_Sistema_DTO> extendido=new ArrayList<Extendido_Sistema_DTO>();
        for(String nombre:nombreExtendidos){
            extendido.add(new Extendido_Sistema_DTO(nombre));
        }
        ArrayList<Extendido_Sistema_DTO> extendidosCargados=ExtensionDAO.cargarExtendidos(extendido);
        return extendidosCargados;
    }

    /**
     * metodo que se encarga de obtener los extendidos de un modulo a los que podra acceder un rol
     * @param nombreModulo
     * @param nombreRol
     * @return un ArrayList<Integer> con los identificadores de las extensiones permitidas
     */
    public static ArrayList<Integer> getExtendidosPorModuloRol(String nombreModulo, String nombreRol) {
        Rol_Sistema_DTO miRol = RolBusiness.getRolPorNombre(nombreRol);
        if (miRol == null) {
            return null;
        }
        Modulo_Sistema_DTO miModulo = ModuloBusiness.getModuloPorNombre(nombreModulo);
        if (miModulo == null) {
            return null;
        }

        Modulo_Sistema_DTO miModuloCargado = ModuloBusiness.getExtendidoPorModuloRol(miRol, miModulo);

        if (miModuloCargado == null || miModuloCargado.getMyExtendidos() == null) {
            return null;
        }

        ArrayList<Integer> misExtendidosInt = new ArrayList<Integer>();

        for (Extendido_Sistema_DTO extendido : miModuloCargado.getMyExtendidos()) {
            misExtendidosInt.add(extendido.getIdentificador());
        }

        return misExtendidosInt;
    }

    
    
}
