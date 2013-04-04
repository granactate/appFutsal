/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Sistema.Privilegio_Sistema_DTO;
import java.util.ArrayList;
import util.BaseDeDatos;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class PrivilegioDAO {
    
    /**
     * metodo que se encarga de  buscar el id del privilegio en la base de datos
     * @param miPrivilegio
     * @return un objeto privilegio con su identificador cargado
     */
     public static Privilegio_Sistema_DTO cargarDatosPrivilegio(Privilegio_Sistema_DTO miPrivilegio) {
         String sql = "SELECT id "
                 + "FROM privilegio "
                 + "WHERE fk_idrol = '"+miPrivilegio.getMyRol().getIdentificador()+"'";
         
         BaseDeDatos.conectar();
         ArrayList<String> miList = BaseDeDatos.getConsultaSQL(sql);
         BaseDeDatos.desconectar();
         
         if (miList == null || miList.isEmpty()) {
             return null;
         }
         miPrivilegio.setIdentificador(Integer.parseInt(ManejoStringConsulta.eliminarSeparador(miList.get(0))));
         return miPrivilegio;
    }
     /**
      * metodo que permite crear un nuevo privilegio en la base de datos
      * @param nuevoPrivilegio
      * @return true si pudo realiza la creación con exito y falso en caso contrario
      */
    public static boolean addPrivilegio(Privilegio_Sistema_DTO nuevoPrivilegio) {
        BaseDeDatos.conectar();
        int id_Privilegio=PrivilegioDAO.getNextId();
        nuevoPrivilegio.setIdentificador(id_Privilegio);
        String sql="INSERT INTO privilegio("
                + " id, fk_idrol)"
                + " VALUES ('"+nuevoPrivilegio.getIdentificador()+"', '"
                +nuevoPrivilegio.getMyRol().getIdentificador()+"')";
        
        return BaseDeDatos.ejecutarActualizacionSQL(sql) &&Extension_has_PrivilegioDAO.crearExtendidos_Privilegios(nuevoPrivilegio);
    }
    
    /**
     * metodo que permite obtener el id_privilegio del nuevo privilegio<html><br><i>requiere que se inicie antes la conexion con la base de datos</html>
     * @return entero con el nuevo id 
     */
    private static int getNextId(){
        String sql="SELECT id"
                + " FROM privilegio"
                + " ORDER BY id";
        ArrayList<String>consulta=BaseDeDatos.getConsultaSQL(sql);
        ArrayList<String>procesada=ManejoStringConsulta.splitSeparador(consulta);
        return Integer.parseInt(procesada.get(procesada.size()-1))+1;
    }

    public static boolean elimarExtendidosPrivilegios(Privilegio_Sistema_DTO privilegio) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


    
   
}
