/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Negocio.PersonaDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import util.BaseDeDatos;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class UsuarioDAO {

    /**
     * metodo que se encarga de validar un usario por su password, username y rol ingresado
     * @param miPersona
     * @return un objeto de tipo persona que tiene la cedula y demas datos necesarios para la validación
     */
    public static PersonaDTO validarPersona(PersonaDTO miPersona) {
        if(miPersona==null){
            return null;
        }
       
        String sql="SELECT id"
                + " FROM usuario"
                + " WHERE password='"+miPersona.getPassword()+"' AND "
                + "username = '"+miPersona.getUsername()+"' AND "
                + "fk_idrol = '"+miPersona.getMyRol().getIdentificador()+"'";
        
        BaseDeDatos.conectar();        
        ArrayList<String> aux=BaseDeDatos.getConsultaSQL(sql);
        BaseDeDatos.desconectar();
        if(aux==null||aux.isEmpty()){
            return null;
        }
        ArrayList<String> resp=ManejoStringConsulta.splitSeparador(aux);
        if(resp!=null&&!resp.isEmpty()){
            miPersona.setIdentificacion(resp.get(0));
            //aqui cargo todos los datos necearios para retornar la persona hasta el momento solo cedula
            return miPersona;                    
        }
        return null;
    }

    /**
     * metodo que permite buscar una identificacion dentro de la base de datos 
     * @param identificacion
     * @return true si se encontro la identificacion y false en caso de contrario
     */
    private static boolean existeIdentificacion(String identificacion){
         String sql="SELECT id "
                + "FROM usuario WHERE "
                + "id = '"+identificacion+"'";
        BaseDeDatos.conectar();
        ArrayList<String> miResp=BaseDeDatos.getConsultaSQL(sql);
        BaseDeDatos.desconectar();
        return (miResp==null||miResp.isEmpty())?false:true;
    }
    
    /**
     * metodo que permite buscar si un username existe en la base de datos
     * @param username
     * @return 
     */
    private static boolean existeUsername(String username){
         String sql="SELECT id "
                + "FROM usuario WHERE "
                + "username = '"+username+"'";
        BaseDeDatos.conectar();
        ArrayList<String> miResp=BaseDeDatos.getConsultaSQL(sql);
        BaseDeDatos.desconectar();
        return (miResp==null||miResp.isEmpty())?false:true;
    }
    
    
    /**
     * metodo que añade un nuevo usuario a la base de datos con toda la información correspondiente
     * @param nueva
     * @return 0 si existe la cedula <html><br> 1 si existe el username<br>2 el registro se realizo con exito<br>3 no se pudo realizar el registro</html>
     * 
     */
    public static int addUsuario(PersonaDTO nueva) {
        try{
        if(UsuarioDAO.existeIdentificacion(nueva.getIdentificacion())){
            return 0;
        }
        if(UsuarioDAO.existeUsername(nueva.getUsername())){
            return 1;
        }
         String sql="INSERT INTO usuario( "
                 + "id, nombre, apellidos, username, password, fechanacimiento, correoelectronico, "
                 + "direccion, telefono, fk_idrol) "
                 + "VALUES ('"+nueva.getIdentificacion()+"','"+nueva.getNombre()+"','"+nueva.getApellidos()+"',"
                 + "'"+nueva.getUsername()+"','"+nueva.getPassword()+"','"+getDateFormato(nueva.getFechaNcto()) +"',"
                 + " '"+nueva.getCorreo()+"', '"+nueva.getDireccion()+"', '"+nueva.getTelefono()+"', '"+nueva.getMyRol().getIdentificador()+"')";
        
        if(!BaseDeDatos.hayConexion()){
        BaseDeDatos.conectar();        
        }
        
        boolean resultado=BaseDeDatos.ejecutarActualizacionSQL(sql);
        BaseDeDatos.desconectar();
        
        if(!resultado){
            return 3;
        }
        boolean propaga=propagarLLaveForanea(nueva);
        if(!propaga){
            return 3;
        }
        
            return 2;
        }catch(Exception e){
            return 3;
        }
    }
    /**
     * metodo que me permite subir a la base de  datos el date en el formato indicado 
     * @param fecha
     * @return un String con el formato de la cadena
     */
    private static String getDateFormato(Date fecha){
        return fecha.getYear()+"-"+fecha.getMonth()+"-"+fecha.getDate();        
    }
    
    
    /**
     * metodo que se encarga de propagar la llave foranea del nuevo usuario registrado a una de las tablas indicadas 
     * si es necesario <html><br><b>1.Organizador de torneo<br>2.Director Tecnico<br>3.Arbitro<br>4.Administrador Cacha</b>
     * @param nueva
     * @return un boolean con el resultado de la actualizacion
     */
    private static boolean propagarLLaveForanea(PersonaDTO nueva){
               
        String sql="";
        switch(nueva.getMyRol().getIdentificador()){
            case 1:{
                sql="INSERT INTO organizadortorneo(fk_idusuario) ";
                              
            }break;            
            case 2:{
             sql="INSERT INTO directortecnico(fk_idusuario) ";
                     
            }break;
            case 3:{
                sql="INSERT INTO arbitro(fk_idusuario) ";
            }break;
            case 4:{
                sql="INSERT INTO administradorcancha(fk_idusuario)";                        
            }break;
            default: return true;
        }
        
        sql+="VALUES ('"+nueva.getIdentificacion()+"')";
         if(!BaseDeDatos.hayConexion()){
                    BaseDeDatos.conectar();
        }
         return BaseDeDatos.ejecutarActualizacionSQL(sql);
    }
    
    /**
     * metodo que se encarga de cargar los datos de un usuario dado su username
     * @param miUsuario
     * @return un objeto de tipo usuario con toda la información del usuario cargada en él<html><br>
     * id<br>
     * nombre<br>
     * apellidos<br>
     * fechanacimiento<br>
     * correoelectronico<br>
     * direccion<br>
     * telefono<br>
     * </html>
     */
    public static PersonaDTO getUsuarioPorUsername(PersonaDTO miUsuario){
        String sql ="SELECT id, nombre, apellidos, fechanacimiento, correoelectronico, "
                + "direccion, telefono"
                + " FROM usuario"
                + " WHERE username= '"+miUsuario.getUsername()+"'";
        
        if (!BaseDeDatos.hayConexion()) {
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
        
        
        miUsuario.setIdentificacion(consultaProcesada.get(0).get(0));
        miUsuario.setNombre(consultaProcesada.get(0).get(1));
        miUsuario.setApellidos(consultaProcesada.get(0).get(2));
        
        
        String fecha[]=consultaProcesada.get(0).get(3).split("-");        
        Date fechaNacimiento = new Date(Integer.parseInt(fecha[0])-1900,
                                        Integer.parseInt(fecha[1])-1,
                                        Integer.parseInt(fecha[2]));
        
        
        miUsuario.setFechaNcto(fechaNacimiento);
        miUsuario.setCorreo(consultaProcesada.get(0).get(4));
        miUsuario.setDireccion(consultaProcesada.get(0).get(5));
        miUsuario.setTelefono(consultaProcesada.get(0).get(6));
        
        BaseDeDatos.desconectar();
        return miUsuario;
    }

    
}
