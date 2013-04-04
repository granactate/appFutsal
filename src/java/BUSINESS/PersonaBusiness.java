/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DAO.UsuarioDAO;
import DTO.DTO_Negocio.PersonaDTO;
import DTO.DTO_Sistema.Extendido_Sistema_DTO;
import DTO.DTO_Sistema.Rol_Sistema_DTO;
import java.util.ArrayList;
import java.util.Date;
import util.ManejoDate;
import util.ManejoStringConsulta;
/**
 *
 * @author Mario(K y M)
 */
public class PersonaBusiness {

    /**
     * metodo que se encarga de validar si el username, password, y el rol de un usuario coinciden
     * @param user
     * @param pass
     * @param rol
     * @return los modulos a los que  este tendra  acceso  al iniciar sesion 
     */
    public static ArrayList<Integer> validarUsuario(String user, String pass, String rol) {
        Rol_Sistema_DTO miRol=RolBusiness.getRolPorNombre(rol);
        if(miRol==null){
            return null;
        }
        PersonaDTO miPersona=new PersonaDTO(user,pass,miRol);
        PersonaDTO login=UsuarioDAO.validarPersona(miPersona);
        if(login==null){
            return null;
        }
        ArrayList<Extendido_Sistema_DTO> misExtendidos=PrivilegioBusiness.getExtendidosPorRol(miRol);
        if(misExtendidos==null||misExtendidos.isEmpty()){
            return null;
        }
        ArrayList<Integer> extendidosInt=new ArrayList<Integer>();
        for(Extendido_Sistema_DTO extSis:misExtendidos){
            extendidosInt.add(extSis.getIdentificador());
        }
        return extendidosInt;
    }
    
    /**
     * metodo que permite añadir un usuario nuevo al sistema
     * @param identificacion
     * @param nombre
     * @param apellidos
     * @param fechaNcto
     * @param username
     * @param passwor
     * @param telefono
     * @param direccion
     * @param correo
     * @param rol
     * @return una cadena con el resultado del registro
     */
    public static String registrarUsuario(String identificacion, String nombre, String apellidos, String fechaNcto, String username, String passwor, String telefono, String direccion, String correo,String rol) {
        Rol_Sistema_DTO miRol=RolBusiness.getRolPorNombre(rol);
        Date d=ManejoDate.getDate(fechaNcto);
        if(miRol==null){
            return "no se ha encontrado el rol del usuario";
        }
        PersonaDTO nueva= new PersonaDTO(nombre, apellidos, identificacion, d, username, passwor, telefono, direccion, correo,miRol);
        int resp=UsuarioDAO.addUsuario(nueva);
        return PersonaBusiness.mensajeRegistro(resp, nueva);
    }
    
    /**
     * metodo que se encarga de generar el mensaje para mostrar al usuario despues de realizar el registro
     * @param i
     * @param persona
     * @return un string con el mensaje indicado
     */
    private static String mensajeRegistro(int i,PersonaDTO persona){
        switch(i){
            case 0:return "la cedula ya esta registrada con otro usuario";
            case 1:return "el Username esta registrado con otro usuario";
            case 2:return "el usuario "+persona.getUsername()+" ha sido registrado con exito";
            case 3:return "NO SE HA PODIDO CONCLUIR EL REGISTRO";
            default:return "NO SE HA PODIDO CONCLUIR EL REGISTRO";
        }
    }
}
