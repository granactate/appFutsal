/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pckPrueba;

import BUSINESS.Facade;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import util.BaseDeDatos;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class prueba {
    
    private static ArrayList<String> nombres=new ArrayList();
    private static ArrayList<String> apellidos= new ArrayList();
    
    private static void llenarNombres(){
        nombres.add("mario");
        nombres.add("camilo");
        nombres.add("fernando");
        nombres.add("gerardo");
        nombres.add("antonio");
        nombres.add("arturo");
        nombres.add("marcos");
        nombres.add("alvaro");
        nombres.add("carlos");
        nombres.add("ivan");
        nombres.add("jesus");
        nombres.add("eduardo");
        nombres.add("johan");
        nombres.add("diego");
        nombres.add("gustavo");
        nombres.add("gustavo");
        nombres.add("gustavo");
        nombres.add("gustavo");
        nombres.add("jaime");
        nombres.add("eudardo");
        nombres.add("michael");
        nombres.add("antonio");
        nombres.add("raul");
        nombres.add("jairo");
        nombres.add("calisto");
        nombres.add("vladimir");
        nombres.add("vladimir");        
    }
    
    private static void llenarApellidos(){
        apellidos.add("nieto");
        apellidos.add("serrano");
        apellidos.add("carrascal");
        apellidos.add("aristisabal");
        apellidos.add("quintero");
        apellidos.add("maestre");
        apellidos.add("adarme");
        apellidos.add("fuentes");
        apellidos.add("rodriguez");
        apellidos.add("jaimes");
        apellidos.add("ardila");
        apellidos.add("lancheros");
        apellidos.add("flores");
        apellidos.add("medina");
        apellidos.add("villamizar");
    }
    
    
    private static void  pruebaCargarRoles(){
        ArrayList<String> aux = Facade.cargarRoles();
        String resp = "";
        if (aux == null || aux.isEmpty()) {
            System.err.print("no se estan cargando los roles\n");
            return;
        }
        for (String x : aux) {
            resp += x + "\n";
        }
        System.out.print(resp);
    }
    
    private static void pruebaRegistrarCancha(String nombreCancha,String direccion, String cedulaAdmin){
        System.out.println(Facade.registrarCancha(cedulaAdmin, nombreCancha, direccion));
    }
    
    
    
    private static void pruebaInicioSesion(String user,String pass, String rol){
        ArrayList<Integer> aux=Facade.validarUsuario(user, pass, rol);
        if(aux==null||aux.isEmpty()){
            System.err.println("datos incorrectos");
            return;
        }
        String resp="Los extendidos a los que puede acceder el usuario: " +user+", son:\n";
        for(Integer entero:aux){
            resp+="\n"+entero;
        }
        System.out.println(resp);
    }
    
    
    
    private static void pruebaRegistrarUsuario(String identificacion,String nombre,String apellidos
                                                ,String fecha,String telefono,String direccion,
                                                String correo, String username,String password,String rol){
        
        System.out.println(Facade.registrarUsuario(identificacion, nombre, apellidos, fecha, username, password, telefono, direccion, correo, rol));
    }
    
    private static void pruebaRegistroEquipo(String nombreEquipo,String usernameTecnico
                                            ,ArrayList<String>datosJugadores,String nombreTorneo){
        
        System.out.println(Facade.registrarEquipo(datosJugadores, nombreTorneo, usernameTecnico, nombreEquipo));
    }
    
    
    
    private static void pruebaCargarModulosConExtendidosPorRol(String nombreRol){
        ArrayList<String> resp = Facade.getModulosExtendidosPorRol(nombreRol);
        if (resp == null) {
            System.out.println("no se pudo realizar conexion en la base de datos\n o no se encontro el rol :(");
            return;
        }
        String val = "";
        for (String texto : resp) {
            String[] vec = texto.split("/");
            val += "************Modulo " + vec[0] + "************\n";
            for (int i = 1; i < vec.length; i++) {
                val += "\t" + i + " " + vec[i] + "\n";
            }
            val += "\n";
        }
        System.out.println(val);
    }
    
    
    
    private static void pruebaCargarModulosConExtendidos() {
        ArrayList<String> resp = Facade.getModulosExtendido();
        if (resp == null) {
            System.out.println("no se pudo realizar conexion en la base de datos");
            return;
        }
        String val = "";
        for (String texto : resp) {
            String[] vec = texto.split("/");
            val += "************Modulo " + vec[0] + "************\n";
            for (int i = 1; i < vec.length; i++) {
                val += "\t" + i + " " + vec[i] + "\n";
            }
            val += "\n";
        }
        System.out.println(val);
    }
    
    private static void pruebaListarEquiposSinValidarTorneo(String nombreTorneo){
        ArrayList<String> resp=Facade.getEquiposSinValidarTorneo(nombreTorneo);
        if(resp==null){
            System.out.println("no se pudo conectar con la base de datos");
            return;
        }
        if(resp.isEmpty()){
            System.out.println("no hay equipos sin el registro validado en el torneo");
            return;
        }
        System.out.println("Los equipos que no se le han validado el registro son:");
        for(String nombre:resp){
            System.out.println(nombre);
        }
    }
    
    
    private static void pruebaModificarPrivilegiosRol(String nombre,ArrayList<String> extendidos){
        System.out.println(Facade.modificarPrivilegiosRol(nombre, extendidos));
    }
    
    private static void pruebaCrearRol(String nombre,ArrayList<String> nombreExtensiones){
        System.out.println(Facade.addRol(nombre, nombreExtensiones));
    }
    
  
    private static void pruebaListarExtendidosPorModuloRol(String nombreModulo,String nombreRol){
        ArrayList<Integer> resp= Facade.getExtendidosPorModuloRol(nombreModulo, nombreRol);
        if(resp==null||resp.isEmpty()){
            System.out.println("no se pudo conectar con la base de datos");
            return ;
        }
        
        String val="Los extendidos del modulo: "+nombreModulo+" a los que podra acceder el "+nombreRol+"  son:\n";
        for(Integer entero:resp){
           val+=entero+"\n";
        }
        System.out.print(val);
    }
    
    private static void pruebaCrearTorneo(String nombreTorneo,String usernameOrganizador) {
        
        String resp=Facade.addTorneo(nombreTorneo, usernameOrganizador);
        System.out.print(resp);
    }
    
    private static void pruebaListarTorneosActivos(){
        ArrayList<String> resp= Facade.getTorneosActivos();
        if(resp==null){
            System.out.println("error al conectar con la base de  datos");
            return;
        }
        if(resp.isEmpty()){
            System.out.println("no hay torneos activos en el sistema");
            return;
        }
        
        String mensaje=" Los torneos activos en el sistema son:\n";
        for(String nombreTorneo:resp){
            mensaje+=nombreTorneo+"\n";
        }
        System.out.println(mensaje);
        
    }
    
    private static void pruebaValidarRegistroEquipo(String nombreEquipo,String nombreTorneo){
        System.out.println(Facade.aceptarEquipoTorneo(nombreEquipo, nombreTorneo));
    }
    
    private static void pruebaGenerarGrupos(String nombreTorneo){
        System.out.println(Facade.generarGruposTorneo(nombreTorneo));
    }
    
    private static void pruebaListarAdministradores(){
        ArrayList<String> resp= Facade.getAdministradoresCancha();
        if(resp==null){
            System.out.println("no se pudo conectar con la base de datos");
            return;
        }
        if(resp.isEmpty()){
            System.out.println("No hay Administradores Registrados ");
            return;
        }
        
        for(String nombre:resp ){
            String datos[]=nombre.split("/");
            System.out.println(datos[0]+"\tCedula: "+datos[1]);
        }
        
    }
    
    private static void pruebaListarCanchas(){
        ArrayList<String> resp = Facade.getCanchas();
        if (resp == null) {
            System.out.println("no se pudo conectar con la base de datos");
            return;
        }
        if (resp.isEmpty()) {
            System.out.println("no hay canchas registradas en el sistema");
            return;

        }
        for (String datosCancha : resp) {
            String datos[] = datosCancha.split("/");
            System.out.println(datos[0] + "\t" + datos[1]);

        }
    }
    
    private  static void pruebaModificarAdministrador(String infoCancha,String cedulaNewAdmin){
        System.out.println(Facade.modificarAdministradorCancha(infoCancha, cedulaNewAdmin));
    }
    
    private static void pruebaCrearCronogramaPlaneacion(String nombreTorneo,String fechaLimiteInscripcion,
                        String fechaSorteoGrupos,String fechaSorteoPartidos,String fechaInicioTorneo){
        
        System.out.println(Facade.crearCronogramaPlaneacionTorneo(nombreTorneo, fechaLimiteInscripcion, fechaSorteoGrupos, 
                fechaSorteoPartidos, fechaInicioTorneo));
    }
    
    private static void pruebaListarTorneosSinCronograma(String username){
        ArrayList<String> resp=Facade.listarTorneosSinCronograma(username);
        if(resp==null){
            System.out.println("no se pudo conectar con la base de datos");
            return;
        }
        if(resp.isEmpty()){
            System.out.println("No hay Torneos Sin Cronograma");
            return;
        }
        System.out.println("los torneos sin cronograma son:");
        for(String nombre:resp){
            System.out.println(nombre);
        }
    }
    
    private static void pruebaListarTorneosCronograma(String username){
        ArrayList<String> resp=Facade.listarTorneosCronograma(username);
        if(resp==null){
            System.out.println("no se pudo conectar con la base de  datos");
            return;
        }
        if(resp.isEmpty()){
            System.out.println("No hay ningun torneo con cronograma de planeación asignado");
            return;
        }
        System.out.println("los torneos con un cronograma de planeación son:");
        for(String nombre:resp){
            System.out.println(nombre);
        }
    }
    
    private static void pruebaGetCronogramaPlanificacionTorneo(String nombreTorneo){
        String resp=Facade.getCronogramaTorneo(nombreTorneo);
        if(resp.isEmpty()){
            System.out.println("no pudo conectar con la base de  datos");
            return;
        }
        
        String datos[]=resp.split("/");
        System.out.println("Nombre del Torneo: "+datos[0]);
        System.out.println("Fecha Limite Inscripción Equipos: "+datos[1]);
        System.out.println("Fecha Sorteo Grupos: "+datos[2]);
        System.out.println("Fecha Sorteo Partidos: "+datos[3]);
        System.out.println("Fecha Inicio Torneo: "+datos[4]);
    }
    
    private static void pruebaModificarCronograma(String nombreTorneo, String fechaLimiteInscripcion,
            String fechaSorteoGrupos,String fechaSorteoPartidos,String fechaInicioTorneo){
        
        String resp=Facade.modificarCronogramaPlaneacion(nombreTorneo, fechaLimiteInscripcion, 
                fechaSorteoGrupos, fechaSorteoPartidos, fechaInicioTorneo);
        if(resp.isEmpty()){
            System.out.println("No se pudo conectar con la base de  datos");
            return;
        }
        System.out.println(resp);
        
    }
    
    private static void preubaHabilitarCanchTorneo(String nombreTorneo,ArrayList<String> infoCancha){
        
        String resp=Facade.asignarCanchasTorneo(nombreTorneo, infoCancha);
        if(resp.isEmpty()){
            System.out.println("no se pudo conectar con la base de datos");
            return;
        }
        System.out.println(resp);
        
    }
    
    private static void pruebaListarCanchasDisponibles(){
        ArrayList<String> resp= Facade.getCanchasDisponibles();
        if(resp==null){
            System.out.println("no se pudo conectar con la base de datos");
            return;
        }
        if(resp.isEmpty()){
            System.out.println("no hay canchas disponibles");
        }
        
        for(String cancha:resp){
            String info[]=cancha.split("/");
            System.out.println(info[0]+"("+info[1]+")");
        }
    }
            
    private static void pruebaListarTorneosCronogramaSinCanchas(String username){
        ArrayList<String> resp= Facade.getTorneosOrganizador(username);
        
        if(resp==null){
            System.out.println("error no se pudo conectar con la base  de datos");
            return;
        }
        if(resp.isEmpty()){
            System.out.println("no hay Torneos  sin canchas asignas y con cronogramas creados");
            return;
        }
        
        System.out.println("Los torneos del Organizador: "+username+"\n que ya tienen creado un cronograma de planeación pero no tienen asignadas canchas son:\n");
        for(String torneo:resp){
            System.out.println(torneo);
        }
    }
    
    private static void pruebaListarTorneosDisponiblesInscripcion(){
        ArrayList<String> resp=Facade.getTorneosDisponiblesInscripcion();
        if(resp==null){
            System.out.println("error no se pudo conectar con la base de datos");
            return;
        }
        if(resp.isEmpty()){
            System.out.println("no se encontraron torneos disponibles");
            return;
        }
        
        System.out.println("Los torneos disponibles para inscripción son:\n");
        
       for(String torneo:resp){
           System.out.println(torneo);
       }
    }
    
    
    
    public static void main(String algo[]){
      
      ArrayList<String> jugadores =new ArrayList<String>();
      int opc=1;
      
      for(int i=9;i<19;i++){
          String nombre="mario";
          String apellido="nieto";
          String identificacion=i+"";
          String fechaNcto="1980-5-12";
          String estatura="190";
          String peso="80";
          String nuevoJugador=nombre+"/"+apellido+"/"+identificacion+"/"+fechaNcto+"/"+estatura+"/"+peso;
          jugadores.add(nuevoJugador);
      }
      
      String nombreEquipo="SuperCampeones2";
      String usernameTecnico="granactate";
      String nombreTorneo="Torneo3";
      
      pruebaRegistroEquipo(nombreEquipo, usernameTecnico, jugadores, nombreTorneo);
      
      
        
    //pruebaListarCanchasDisponibles();
//        pruebaListarTorneosActivos();
//        
//        
//        pruebaListarCanchasDisponibles();
//       
////      prueba.listarTorneosCronograma();
////      System.out.println("\n");
////      
//      String nombreTorneo=JOptionPane.showInputDialog("Digite el nombre del torneo");
//        
//        
//      int opc=3;
//      ArrayList<String> infoCancha=new ArrayList<String>();
//      
//      
//      while(opc!=2){
//          opc=Integer.parseInt(JOptionPane.showInputDialog( "Seleccione Accion\n1.Ingresar Info Cancha\n2.salir"));
//          switch(opc){
//              case 1:{
//                  String info=JOptionPane.showInputDialog("digite el nombre de la cancha y su direccion");
//                  infoCancha.add(info);
//              }break;
//              case 2:{
//                  break;
//              }
//                    
//          }
//      }
//      preubaHabilitarCanchTorneo(nombreTorneo, infoCancha);
//      
//      
//      pruebaGetCronogramaPlanificacionTorneo(nombreTorneo);
//      
//      System.out.println("\n");
//      System.out.println("\n");
//      
//      String fechaLimiteInscripcion=JOptionPane.showInputDialog("Digite la fehca limite de inscripción de equipos");
//      String fechaSorteoGrupos=JOptionPane.showInputDialog("Digite la fecha del sorteo de los grupos");
//      String fechaSorteoPartidos=JOptionPane.showInputDialog("Digite la fecha del sorteo de los partidos");
//      String fechaInicioTorneo=JOptionPane.showInputDialog("Digite fecha de inicio del torneo");
//      
//      pruebaModificarCronograma(nombreTorneo, fechaLimiteInscripcion, fechaSorteoGrupos, fechaSorteoPartidos, fechaInicioTorneo);
//       
       
           
    }
    
    

}