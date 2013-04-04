/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Sistema.Extendido_Sistema_DTO;
import DTO.DTO_Sistema.Privilegio_Sistema_DTO;
import java.util.ArrayList;
import util.BaseDeDatos;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class Extension_has_PrivilegioDAO {
     
    /**
     * metodo que se encarga de consultar todas las extensiones de un privilegio
     * @param miPrivilegioResp
     * @return un ArrayList  de las extensiones
     */
    public static ArrayList<Extendido_Sistema_DTO> getTodasExtensiones(Privilegio_Sistema_DTO miPrivilegioResp) {
        String sql="select ext.id " +
                "from privilegio as p, extension_has_privilegio as extPriv, extension as ext " +
                " where p.id=extPriv.fk_idprivilegio and extPriv.fk_idextension=ext.id and p.id='"+miPrivilegioResp.getIdentificador()+"'";
        BaseDeDatos.conectar();
        ArrayList<String> miList=BaseDeDatos.getConsultaSQL(sql);
        BaseDeDatos.desconectar();
        if(miList==null||miList.isEmpty()){
            return null;
        }
        ArrayList<String>miResp=ManejoStringConsulta.splitSeparador(miList);
        if(miResp==null||miResp.isEmpty()){
            return null;
        }        
        ArrayList<Extendido_Sistema_DTO> misExtendidos=new ArrayList<Extendido_Sistema_DTO>();        
        for(String extension:miResp){
            Extendido_Sistema_DTO nueva= new Extendido_Sistema_DTO(Integer.parseInt(extension));
            misExtendidos.add(nueva);
        }
        return misExtendidos;
    }
    
     /**
     * metodo que se encarga de crear asignar en las base de datos los extendidos al privilegio de un rol determinado
     * @param nuevoPrivilegio
     * @return boolean si puedo concretar la operacion
     */
    public static boolean crearExtendidos_Privilegios(Privilegio_Sistema_DTO nuevoPrivilegio) {
        
        BaseDeDatos.conectar();
        int id_Ext_Pri=Extension_has_PrivilegioDAO.getNextId();
        
        for(Extendido_Sistema_DTO extendido:nuevoPrivilegio.getMyExtendidos()){
            String sql="INSERT INTO extension_has_privilegio( "
                    + "fk_idprivilegio, fk_idextension, id) "
                    + "VALUES ('"+nuevoPrivilegio.getIdentificador()+"', '"+extendido.getIdentificador()+"','"+id_Ext_Pri+"')";
            
            if(!BaseDeDatos.ejecutarActualizacionSQL(sql)){
                return false;
            }
            id_Ext_Pri++;
        }
        return true;
    }
    /**
     * metodo que permite obtener el id_extendido_privilegio del nuevo privilegio
     * @return el entero que identificara al extendido_privilegio
     */
    private static int getNextId(){
        String sql="SELECT id"
                + " FROM extension_has_privilegio"
                + " ORDER BY id";
        ArrayList<String> consulta= BaseDeDatos.getConsultaSQL(sql);
        ArrayList<String> procesada=ManejoStringConsulta.splitSeparador(consulta);
        
        return Integer.parseInt(procesada.get(procesada.size()-1))+1;
    }
    
     /**
     * metodo que elimina todos los Extendio_Privilegio de un Privilegio
     * @return un boolean con el resultado si pudo terminar la operación satisfactoriamente 
     */
    public static boolean elimarExtendidosPrivilegios(Privilegio_Sistema_DTO miPrivilegio){
        String sql="DELETE FROM extension_has_privilegio"
                + " WHERE fk_idprivilegio = '"+miPrivilegio.getIdentificador()+"'";
        
        if(!BaseDeDatos.hayConexion()){
        BaseDeDatos.conectar();
        }
        boolean resp= BaseDeDatos.ejecutarActualizacionSQL(sql);
        BaseDeDatos.desconectar();
        return resp;
    }
    
}
