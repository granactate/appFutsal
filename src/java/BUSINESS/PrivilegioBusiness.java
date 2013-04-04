/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;


import DAO.Extension_has_PrivilegioDAO;
import DAO.PrivilegioDAO;
import DTO.DTO_Sistema.Extendido_Sistema_DTO;
import DTO.DTO_Sistema.Privilegio_Sistema_DTO;
import DTO.DTO_Sistema.Rol_Sistema_DTO;
import java.util.ArrayList;

/**
 *
 * @author Mario(K y M)
 */
class PrivilegioBusiness {

    /**
     * metodo que permite los extendidos dependiendo del rol
     * @param miRol
     * @return retorna un ArrayList con los extendidos del rol indicado
     */
    public static ArrayList<Extendido_Sistema_DTO> getExtendidosPorRol(Rol_Sistema_DTO miRol) {
        if(miRol==null){
            return null;
        }
        Privilegio_Sistema_DTO miPrivilegio= new Privilegio_Sistema_DTO(miRol);        
        Privilegio_Sistema_DTO miPrivilegioResp=PrivilegioDAO.cargarDatosPrivilegio(miPrivilegio);
        if(miPrivilegioResp==null){
            return null;
        }
        
        ArrayList<Extendido_Sistema_DTO> miExtendidos=Extension_has_PrivilegioDAO.getTodasExtensiones(miPrivilegioResp);
        if(miExtendidos==null||miExtendidos.isEmpty()){
            return null;
        }
        return miExtendidos;
    }

    /**
     * metodo que permite crear un Privilegio nuevo en el sistema con todos sus datos correspondientes
     * @param misExtendidosCargados
     * @param nuevo
     * @return retorna privilegio que se acaba de crear con todos sus datos correspondientes
     */
    public static Privilegio_Sistema_DTO addPrivilegio(ArrayList<Extendido_Sistema_DTO> misExtendidosCargados, Rol_Sistema_DTO nuevo) {
        Privilegio_Sistema_DTO nuevoPrivilegio= new Privilegio_Sistema_DTO(misExtendidosCargados, nuevo);
        if(!PrivilegioDAO.addPrivilegio(nuevoPrivilegio)){
            return null;
        }
        return nuevoPrivilegio;
    }

    /**
     * metodo que se encarga modificar los privilegios del rol
     * @param nombre
     * @param nombreExtendidos
     * @return 
     */
    public static String modificarPrivilegioRol(String nombre, ArrayList<String> nombreExtendidos) {
        
        Rol_Sistema_DTO miRol=RolBusiness.getRolPorNombre(nombre);
        if(miRol==null){
            return "no se pudo conectar con la base de datos";
        }
        
        ArrayList<Extendido_Sistema_DTO>misExtendidos=ExtendidoBusiness.getExtendidoPorNombre(nombreExtendidos);
        if(misExtendidos==null||misExtendidos.isEmpty()){
            return "no se pudo conectar con la base de datos";
        }
        
        Privilegio_Sistema_DTO privilegio= new Privilegio_Sistema_DTO(miRol);
                
        Privilegio_Sistema_DTO privilegioCargado=PrivilegioDAO.cargarDatosPrivilegio(privilegio);
        
        if(privilegioCargado==null){
            return "no se pudo conectar con la base de  datos";
        }
        
        boolean eliminoExtendidos=Extension_has_PrivilegioDAO.elimarExtendidosPrivilegios(privilegioCargado);
        
        if(!eliminoExtendidos){
            return "no se pudo modificar los privilegios del rol";
        }
        
        privilegioCargado.setMyExtendidos(misExtendidos);
        
        boolean agregoNuevosExtendidos=Extension_has_PrivilegioDAO.crearExtendidos_Privilegios(privilegioCargado);
        
        if(!agregoNuevosExtendidos){
            return "no se puedo concluir el cambio en la base de datos";
        }
        
        String resp="se han cambiado los permisos al rol "+privilegioCargado.getMyRol().getNombre()+": \n";
        for(Extendido_Sistema_DTO extendido:privilegioCargado.getMyExtendidos()){
            resp+=extendido.getNombre()+"\n";
        }
             
        return resp;   
   }
    
}
