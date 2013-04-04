/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Sistema.Extendido_Sistema_DTO;
import DTO.DTO_Sistema.Modulo_Sistema_DTO;
import java.util.ArrayList;
import util.BaseDeDatos;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class ExtensionDAO {

    /**
     * metodo que se encarga de cargar los extendidos al modulo
     * @param misModulos
     * @return un ArrayList<Modulo_Sistema_DTO> con todos sus modulos cargados
     */
    public static ArrayList<Modulo_Sistema_DTO> CargarExtendidosAModulo(ArrayList<Modulo_Sistema_DTO> misModulos) {
        if(misModulos==null||misModulos.isEmpty()){
            return null;
        }
        BaseDeDatos.conectar();
        for(Modulo_Sistema_DTO modulo:misModulos){
            String sql="SELECT id, nombre FROM extension WHERE fk_idmodulo = '"
                    +modulo.getIdentificador()+"'"; 
            
            ArrayList<ArrayList<String>> matriz=ManejoStringConsulta.splitSeparadorMatriz(
                    BaseDeDatos.getConsultaSQL(sql));
            
            ArrayList<Extendido_Sistema_DTO> misExtendidos= new ArrayList<Extendido_Sistema_DTO>();
            
            for(ArrayList<String> array:matriz){
                misExtendidos.add(new Extendido_Sistema_DTO(array.get(1),Integer.parseInt(array.get(0))));                        
            }
            
            modulo.setMyExtendidos(misExtendidos);
        }
        BaseDeDatos.desconectar();
        
        return misModulos;
                

    }
    
    /**
     * metodo que se encarga de buscar la información de los modulos en la base de datos con el nombre de los modulos cargado
     * @param misExtendidos
     * @return un ArrayList<Extendido_Sistema_DTO> con la información de los extendidos cargado en cada uno de ellos
     */
    public static ArrayList<Extendido_Sistema_DTO> cargarExtendidos(ArrayList<Extendido_Sistema_DTO> misExtendidos) {
        BaseDeDatos.conectar();
        
        for(Extendido_Sistema_DTO extendido:misExtendidos){
            String sql="SELECT id "
                    + "FROM extension "
                    + "WHERE nombre = '"+extendido.getNombre()+"'";
            
            ArrayList<String> consultar=BaseDeDatos.getConsultaSQL(sql);
            if(consultar==null||consultar.isEmpty()){
                return null;
            }
            
            String id_extendido=ManejoStringConsulta.eliminarSeparador(consultar.get(0));
            extendido.setIdentificador(Integer.parseInt(id_extendido));
        }
        BaseDeDatos.desconectar();
        return misExtendidos;
    }

   
    
}
