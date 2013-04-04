/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mario(K y M)
 */
public class Facade {
    
    /**
     * <html><font color=green>no implementados<br>no implementados<br>no implementados</html>
     * @param nombreTorneo
     * @return 
     */
    public static ArrayList<String> getEquiposRegistrado(String nombreTorneo){
        return new ArrayList<String>();//solo para que  no marque error
        
    }
    /**
     * <html><font color=green>no implementados<br>no implementados<br>no implementados</html>
     * @param nombreEquipo
     * @param nombreTorneo
     * @return 
     */
    public static boolean darBajaEquipo(String nombreEquipo,String nombreTorneo){
        return false;//solo para que no marque error
    }
    
    
    /**
     * metodo que se encarga de retornar los roles existentes en el sistema
     * @return ArrayList<String> con el nombre de los roles
     */
    public static ArrayList<String> cargarRoles(){        
        return RolBusiness.getAllRoll();       
    }
    
    /**
     * metodo que permite Validar el inicio de sesion de un usario
     * @param user
     * @param pass
     * @param rol
     * @return ArrayList<Integer> con las extensiones a las que dicho usuario puede acceder
     */
    public static ArrayList<Integer> validarUsuario(String user, String pass, String rol){
        
        ArrayList<Integer> miList=PersonaBusiness.validarUsuario(user,pass,rol);
        return miList;
    }
    
    /**
     * metodo que permite crear un usuario con toda su información 
     * @param identificacion
     * @param nombre
     * @param apellidos
     * @param fechaNcto
     * @param username
     * @param passwor
     * @param telefono
     * @param direccion
     * @param correo
     * @return cadena con el resultado de la operación
     */
    public static String registrarUsuario(String identificacion, String nombre,
                                          String apellidos, String fechaNcto, String username,
                                          String passwor, String telefono, String direccion,
                                          String correo,String rol){
        
        return PersonaBusiness.registrarUsuario(identificacion, nombre, apellidos, fechaNcto, username, passwor, telefono, direccion, correo,rol);
    }
    
    /**
     * metodo que permite obtener los modulos con su respectivo extendido<html><font color=green><i><b>el primer String del arreglo sera el nombre del modulo<br>
     * y los siguientes String seran correspondientes a los extendidos de dicho modulo</html>
     * @return una matriz tridimensional con el nombre de los modulos con sus respectivas extensiones
     */
    public static ArrayList<String> getModulosExtendido(){
        return ModuloBusiness.getModulosConExtendidos();
    }
    
    
    /**
     * metodo que permite listar los torneos a los cuales ya se le ha creado un Cronograma de PLaneacion de un Organizador 
     * @param username
     * @return ArrayList<String> con los nombres de los torneos<html><br>puede retornar null si hay algun erro<br>puede retornar ArrayList vacion si no hay torneos disponibles
     */
    public static ArrayList<String> getTorneosOrganizador(String username){
        return TorneoBusiness.getTorneosOrganizador(username);
    }
    
    /**
     * metodo que permite añadir un nuevo rol, con su respectivo nombre y los extendidos  a los cuales podra acceder
     * @param nombre
     * @param nombreExtendidos
     * @return una cadena con el resultado de la operacion
     */    
    public static String addRol(String nombre,ArrayList<String> nombreExtendidos){
       return ExtendidoBusiness.crearRol(nombre,nombreExtendidos) ;
    }
    
    /**
     * metodo que permite modifiar los privilegios de un rol especifico
     * @param nombre
     * @param nombreExtendidos
     * @return una  cadena con el resultado de la operacion
     */
    public static String modificarPrivilegiosRol(String nombreRol, ArrayList<String> nombreExtendidos){
        return PrivilegioBusiness.modificarPrivilegioRol(nombreRol,nombreExtendidos);
    }  
    
    
    /**
     * metodo que permite listar los modulos que y extendidos a los que puede acceder un rol especifico
     * @param nombrRol
     * @return un ArrayList<String> donde <html><font color=green><i><b>el primer String del arreglo sera el nombre del modulo<br>
     * y los siguientes String seran correspondientes a los extendidos de dicho modulo</html>     * 
     */
    public static ArrayList<String> getModulosExtendidosPorRol(String nombreRol){
       try{
        return ModuloBusiness.getModulosExtendidosPorRol(nombreRol);
       }catch(Exception e){
           return null;
       }
    }
    
    /**
     * metodo que permite obtener los extendidos de un modulo especifico a los que podrea acceder un rol
     * @param nombreModulo
     * @param nombreRol
     * @return un ArrayList<Integer> con los identificadores de los extendidos a los que podra acceder
     */
    public static ArrayList<Integer> getExtendidosPorModuloRol(String nombreModulo,String nombreRol){
        return ExtendidoBusiness.getExtendidosPorModuloRol(nombreModulo,nombreRol);
    }
    
    /**
     * metodo que permite listar los equipos que no han sido validados en un torneo
     * @param nombreTorneo
     * @return un listado con el nombre de cada uno de los equipos sin validar
     * <html><br>puede retornar null si no puede realizar la conexion con la base  de datos
     * <br> si el ArrayList<String> esta vacio es por que no existen equipos sin validar en dicho torneo
     */
    public static ArrayList<String> getEquiposSinValidarTorneo(String nombreTorneo){
        return TorneoBusiness.getEquipoSinValidar(nombreTorneo);
    }
    
    /**
     * metodo que permite crear un torneo en el sistema con los datos requeridos
     * @param nombreTorneo
     * @param fechaLimiteInscripcion
     * @param fechaSorteoGrupos
     * @param fechaSorteoPartidos
     * @param fechaInicioTorneo fecha en la que se empezaran a jugar los partidos del torneo
     * @param usernameOrganizadorT
     * @return una cadena con el resultado de la operación 
     */
    public static String addTorneo(String nombreTorneo,String usernameOrganizador){
        return TorneoBusiness.addTorneo(nombreTorneo,usernameOrganizador);
    }
    
    /**
     * metodo encargado de listar los torneos que se encuentran en el sistema activos
     * @return un ArrayList<Strin> con el nombre de los torneos activos en el sistema
     */
    public static ArrayList<String> getTorneosActivos(){
        return TorneoBusiness.getTorneosActivos();
    }
    
    
    /**
     * permite añadir un equipo a  un torneo determinado
     * @param datosJugadores <html><font color=purple><b><br>donde los datos del jugador deben estar guardados nombre, apellidos, identificacion, fechaNcto, estatura, peso,  separado por un '/' entre cada dato  y la fecha debe usar como separador '-' año-mes-dia <font color=black>
     * @param nombreTorneo
     * @param usernameTecnico
     * @param nombreEquipo 
     * @return un String con el resultado de la operacion
     */
    public static String registrarEquipo(ArrayList<String> datosJugadores, String nombreTorneo,
                                         String usernameTecnico,String nombreEquipo){
        
        return TorneoBusiness.registrarEquipoTorneo(datosJugadores,nombreTorneo,usernameTecnico,nombreEquipo);
        
    }
    
    /**
     * metodo que permite habilitar el registro  de un equipo a un torneo 
     * @param nombreEquipo
     * @param nombreTorneo
     * @return String con el resultado de la operación
     */
    public static String aceptarEquipoTorneo(String nombreEquipo,String nombreTorneo){
        return TorneoBusiness.validarRegistroEquipo(nombreEquipo,nombreTorneo);
    }
    
    
    /**
     * metodo del tercer previo permite generar la distribución por grupos de un torneo
     * @param nombreTorneo 
     * @return String con el resultado de la operación
     */
    public static String generarGruposTorneo(String nombreTorneo){
        return TorneoBusiness.generarGruposTorneo(nombreTorneo);
    }
    
    /**
     * metodo que permite verificar los administradores de cancha a los cuales no se les ha asignado una cancha
     * @return ArrayList<String> con el nombre de los Administradores<htmo><br>y su cedula separado por un '/'
     */
    public static ArrayList<String> getAdministradoresCancha(){
        return AdministradorCanchaBusiness.getAdministradoresCancha();
    }
    
    /**
     * metodo que permite añadir una nueva cancha al sistema <html><b> no es necesario especificar un torneo la añade al sistema</b>
     * @param CedulaAdmin
     * @param nomCancha
     * @param direccionCancha
     * @return String con el resultado de la operación
     */
    public static String registrarCancha(String cedulaAdmin, String nomCancha,String direccionCancha){
        return CanchaBusiness.registrarCancha(cedulaAdmin, nomCancha, direccionCancha);
    }
    
    /**
     * metodo que me permite listar todas que estan registradas en el sistema
     * @return ArrayList<Stirng> en el formato de <html><br>nombre/direccion<b><br> puede retornar 
     * <br>null si no conecto con la base de datos
     * <br>ArrayList<String> vacio, si no encontro ningun registro en la  base de datos
     * 
     */
    public static ArrayList<String> getCanchas(){
        return CanchaBusiness.getCanchas();
        
    }
    
    /**
     * metodo que permite hacer el cambio de un administrador  a una cancha
     * @param infoCancha <html><b>debe tener el formato nombre"/"direccion
     * @param cedulaNuevoAdministrador
     * @return String con el resultado de la operación
     */
    public static  String modificarAdministradorCancha(String infoCancha,String cedulaNuevoAdministrador){
        return CanchaBusiness.modificarAdministradorCancha(infoCancha,cedulaNuevoAdministrador);
    }
    
    
    /**
     * metodo que permite crear a un torneo existente el cronograma de planeacion
     * @param nombreTorneo
     * @param fechaLimiteInscripcion<html><color font=green> formato año-mes-dia
     * @param fechaSorteoGrupos<html><color font=green> formato año-mes-dia
     * @param fechaSorteoPartidos<html><color font=green> formato año-mes-dia
     * @param fechaInicioTorneo<html><color font=green> formato año-mes-dia
     * @return String con el resultado de la operacion
     */
    public static String crearCronogramaPlaneacionTorneo(String nombreTorneo,String fechaLimiteInscripcion,
                        String fechaSorteoGrupos,String fechaSorteoPartidos,String fechaInicioTorneo){
        
        return TorneoBusiness.crearCronogramaPlaneacion(nombreTorneo,fechaLimiteInscripcion,fechaSorteoGrupos,fechaSorteoPartidos,fechaInicioTorneo);
    }
    
    
    /**
     * metodo que permite listar los torneos activos que no se les ha creado un cronograma de planificación
     * @return ArrayList<String> con el nombre de los torneos que no tiene asignado un Cronograma de planeación<html><br>puede retornar nulo 
     * no conecto con la base de datos <br>puede retornar un ArrayList<String> vacio si no existe ningun torneo sin cronograma
     */
    public static ArrayList<String> listarTorneosSinCronograma(String username){
       return TorneoBusiness.getTorneosSinCronograma(username);
    }
    
    /**
     * metodo que permite obtener un listado con los nombres de los torneos que ya tienen asignados 
     * @param username del organizador torneo que se le listaran todos los torneos
     * @return un ArrayList con el nombre de los torneo que tienen asigando un cronograma  de planeacion<html><br>puede retornar nulo 
     * no conecto con la base de datos <br>puede retornar un ArrayList<String> vacio si no existe ningun torneo sin cronograma
     */
    public static ArrayList<String> listarTorneosCronograma(String username){
        return TorneoBusiness.getTorneosCronograma(username);
    }
    
    /**
     * metodo que permite obtener el cronograma de planeación de un torneo por su nombre
     * @param nombreTorneo
     * @return String con el resultado de la operación <html><br>con el siguiente formato
     * <br>nombre torneo
     * <br>fecha limite inscripción
     * <br>fecha sorteo grupos
     * <br>fecha sorteo partidos
     * <br>fecha inicion torneo<br> retorna vacio si hubo erro
     */
    public static String getCronogramaTorneo(String nombreTorneo){
        return TorneoBusiness.getCronogramaTorneo(nombreTorneo);
    }
    
    
    /**
     * metodo que permite cambiar el cronograma de planeación de un torneo especifico 
     * @param nombreTorneo
     * @param fechaLimiteInscripcion
     * @param fechaSorteoGrupos
     * @param fechaSorteoPartidos
     * @param fechaInicioTorneo
     * @return String con la notificación si pudo realizar el cambio <html><br>puede retornar "" si hay algun error
     */
    public static String modificarCronogramaPlaneacion(String nombreTorneo, String fechaLimiteInscripcion,
            String fechaSorteoGrupos,String fechaSorteoPartidos,String fechaInicioTorneo)
    {
        return TorneoBusiness.modificarCronogramaPlaneacion(nombreTorneo,fechaLimiteInscripcion,fechaSorteoGrupos,fechaSorteoPartidos,fechaInicioTorneo);
    }
    
    
    /**
     * metodo que permite al Organizador del torneo asignar una serie de canchas a un torneo
     * @param nombreTorneo
     * @param infoCanchas
     * @return String con el resultado de la operacion <hml><br>puede retornar "" si hay algun error
     */
    public static String asignarCanchasTorneo(String nombreTorneo, ArrayList<String> infoCanchas){
        return TorneoBusiness.asignarCanchasTorneo(nombreTorneo,infoCanchas);
    }
    
    /**
     * metodo que permite listar las canchas  que no han sido asignadas a  ningun torneo 
     * @return ArrayList<String> con el info de las canchas<hmlt><br>puede retornar null si hay algun error<br>puede retornar ArrayList vacio si no encuentra ninguna cancha disponible
     */
    public static ArrayList<String> getCanchasDisponibles(){
        return CanchaBusiness.getCanchasDisponibles();
    }
    
    /**
     * metodo que permite listar los torneos que tiene un organizador en el sistema sin importar el cronograma ni las canchas
     * @param username
     * @return ArrayList<String> con el nombre de los torneos <hmlt><br>puede retornar null si hay algun error<br>puede retornar ArrayList vacio si no encuentra ningun torneo disponible
     */
    public static ArrayList<String> getTorneosActivosOrganizador(String username){
        return TorneoBusiness.getTorneosActivosOrganizador(username);
    }
    
    /**
     * metodo que permite listar los torneo en los que se pueden registrar equipos
     * @return ArrayList<String>con el nombre de los torneos <hmlt><br>puede retornar null si hay algun error<br>puede retornar ArrayList vacio si no encuentra ningun torneo disponible
     */
    public static ArrayList<String> getTorneosDisponiblesInscripcion(){
        return TorneoBusiness.getTorneosDisponiblesInscripcion();
    }
}