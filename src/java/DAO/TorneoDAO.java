/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DTO_Negocio.EquipoDTO;
import DTO.DTO_Negocio.JugadorDTO;
import DTO.DTO_Negocio.OrganizadorTorneoDTO;
import DTO.DTO_Negocio.TorneoDTO;
import java.util.ArrayList;
import java.util.Date;
import util.BaseDeDatos;
import util.ManejoDate;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
public class TorneoDAO {
    
    /**
     * <html><font color=purple><b> no implementado
     * @param myTorneo
     * @return 
     */
    public static TorneoDTO getTorneo(TorneoDTO myTorneo){
        
        return null;
    }
    /**
     * <html><font color=purple><b> no implementado
     * @param myTorneo
     * @return 
     */
    public static TorneoDTO getEquiposRegistradosUltimaSemana(TorneoDTO myTorneo){
        return null;
    }
    
    /**
     * <html><font color=purple><b> no implementado
     * @param myTorneo
     * @param myEquipo
     * @return 
     */
    public static boolean darBajaEquipo(TorneoDTO myTorneo, EquipoDTO myEquipo){
        return false;
    }

    /**
     * metodo que permite almacenar un nuevo torneo en la base de datos
     * @param torneoNuevo
     * @return un entero de confirmación<html><br>
     * 1.si realizo el registro bien<br>
     * 2. no se pudo conectar con la base de datos <br>
     * 3. ya existe torneo con ese nombre
     * </html>
     */
    public static int addTorneo(TorneoDTO torneoNuevo) {
        int idNuevo=TorneoDAO.nextId();
        
        if(idNuevo<0){
            return  2;
        }
        
        if(TorneoDAO.buscarTorneoPorNombre(torneoNuevo)){
            return 3;
        }
        
        Date fechaActual=new Date();
        fechaActual.setMonth(fechaActual.getMonth()+1);
        fechaActual.setYear(fechaActual.getYear()+1900);
        String idOrganizador=torneoNuevo.getMyOrganizador().getIdentificacion();
        String fechaCreacion=ManejoStringConsulta.getFechaFormato(fechaActual);
        
        String sql="INSERT INTO torneo("
                + "id, nombre, fechacreacion,estaactivo, fk_idorganizadortorneo)"
                + " VALUES ('"+idNuevo+"', '"+torneoNuevo.getNombre()+"', '"+fechaCreacion+"'"
                + ", 'TRUE', '"+idOrganizador+"')";
        
        boolean resp=BaseDeDatos.ejecutarActualizacionSQL(sql);
        
        if(resp){
            return 1;
        }
        return 2;
    }

    /**
     * metodo que permite buscar si un torneo ya exite buscandolo por el nombre
     * @param miTorneo
     * @return true si existe o false en caso contrario
     */
    private static boolean buscarTorneoPorNombre(TorneoDTO miTorneo){
        String sql="SELECT  id " +
                " FROM torneo " +
                " WHERE nombre = '"+miTorneo.getNombre()+"'";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        
        if(consulta==null||consulta.isEmpty()){
            BaseDeDatos.desconectar();
            return false;
        }
        
        BaseDeDatos.desconectar();
        return true;
         
    }
    
    
    
   
    /**
     * metodo que permite obtener el id del proximo torneo
     * @return el entero que servira como id del nuevo torneo
     */
    private static int nextId(){
        String sql="SELECT id " +
                "FROM torneo " +
                "ORDER BY id";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null){
            return -1;
        }
        
        if(consulta.isEmpty()){
            return 0;
        }
        
        ArrayList<String> resultado=ManejoStringConsulta.splitSeparador(consulta);
        
        return Integer.parseInt(resultado.get(resultado.size()-1))+1;
        
    }

    /**
     * metodo que se encarga de realizar la busqueda en la base de datos de los torneos que se encuentran activos
     * @return un ArrayList<TorneoDTO> con los torneos activos
     */
    public static ArrayList<TorneoDTO> getTorneosActivos() {
        String sql="select id, nombre,fechacreacion,fechalimiteinscripcion,fechasorteogrupo,fechasorteopartidos,fechainiciotorneo"
                + " from torneo "
                + "where estaactivo='true'";
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        BaseDeDatos.desconectar();
        
        if(consulta==null){
            return null;
        }
        if(consulta.isEmpty()){
            return new ArrayList<TorneoDTO>();
        }
        
        ArrayList<ArrayList<String>> consultaProcesada= ManejoStringConsulta.splitSeparadorMatriz(consulta);
        
        if(consultaProcesada==null||consultaProcesada.isEmpty()){
            return null;
        }
        ArrayList<TorneoDTO> misTorneos= new ArrayList<TorneoDTO>();
        
        for(ArrayList<String> list:consultaProcesada){                   
            TorneoDTO unTorneo= TorneoDAO.crearTorneo(list);            
            misTorneos.add(unTorneo);
        }
        return misTorneos;
    }

    
    /**
     * metodo encargado buscar un listado de jugadores dentro de alguno de los torneos  activos en el sistema
     * @param misJugadores
     * @return un ArrayList<JugadorDTO> con los jugadores que no fueron encontrados en otros torneos activos
     */
    public static ArrayList<JugadorDTO> validarJugadoresTorneos(ArrayList<JugadorDTO> misJugadores) {
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<JugadorDTO> jugadoresHabilitados= new ArrayList<JugadorDTO>();
        
        for(int i=0;i<misJugadores.size();i++){
            
            JugadorDTO miJugador=misJugadores.get(i);
            String sql="select jug.nombre "
                    + "from jugador as jug, jugador_has_equipo as jugEqui,equipo as equ,"
                    + "  equipo_has_torneo as equiTorn, torneo as tor "
                    + "where jug.id='"+miJugador.getIdentificacion()+"' and jugEqui.fk_idjugador=jug.id and "
                    + "jugEqui.fk_idequipo=equ.id and equiTorn.fk_idequipo=equ.id and"
                    + " equiTorn.fk_idtorneo=tor.id and tor.estaactivo='true'";
            
            ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);

            if(consulta==null){
               return null;
            }
            if(consulta.isEmpty()){
                jugadoresHabilitados.add(miJugador);
            }
        }
        
        BaseDeDatos.desconectar();
        return jugadoresHabilitados;
        
        
    }

    /**
     * metodo que permite cargar la informacion en un torneo con el nombre de este
     * @param torneoSeleccionado
     * @return TorneoDTO con los datos cargados
     */
    public static TorneoDTO getTorneoPorNombre(TorneoDTO torneoSeleccionado) {
        String sql="select id,nombre,fechacreacion,fechalimiteinscripcion,fechasorteogrupo,"
                + "fechasorteopartidos,fechainiciotorneo,estaactivo "
                + "from torneo "
                + "where nombre='"+torneoSeleccionado.getNombre()+"' and estaactivo='true'";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        BaseDeDatos.desconectar();       
        
        if(consulta==null||consulta.isEmpty()){
            return null;
        }
        
        ArrayList<ArrayList<String>> consultaProcesada=ManejoStringConsulta.splitSeparadorMatriz(consulta);        
        torneoSeleccionado=TorneoDAO.crearTorneo(consultaProcesada.get(0));
        BaseDeDatos.desconectar();
        return torneoSeleccionado;        
    }
    
    /**
     * metodo que permite atravez de un ArrayList<String> con los datos cargados del torneo crear un nuevo torneo
     * @param datosTorneo
     * @return 
     */
    private static TorneoDTO crearTorneo(ArrayList<String> datosTorneo){
        if(datosTorneo==null||datosTorneo.isEmpty()){
            return null;
        }
          
            int id=Integer.parseInt(datosTorneo.get(0));            
            String nombre=datosTorneo.get(1);
            
            
            Date fechaCreacion=null;
            Date fechaLimiteInscripcion=null;
            Date fechaSorteoGrupos=null;
            Date fechaSorteoPartidos=null;
            Date fechaInicioTorneo=null;
            
         if (datosTorneo.size() > 2) {
            if (!datosTorneo.get(2).isEmpty()) {
                fechaCreacion = ManejoDate.getDate(datosTorneo.get(2));
            }
            if (!datosTorneo.get(3).equalsIgnoreCase("null")) {
                fechaLimiteInscripcion = ManejoDate.getDate(datosTorneo.get(3));
            }
            if (!datosTorneo.get(4).equalsIgnoreCase("null")) {
                fechaSorteoGrupos = ManejoDate.getDate(datosTorneo.get(4));
            }
            if (!datosTorneo.get(5).equalsIgnoreCase("null")) {
                fechaSorteoPartidos = ManejoDate.getDate(datosTorneo.get(5));
            }
            if (!datosTorneo.get(6).equalsIgnoreCase("null")) {
                fechaInicioTorneo = ManejoDate.getDate(datosTorneo.get(6));
            }

        }

                    
            TorneoDTO unTorneo= new TorneoDTO(nombre, fechaCreacion, fechaLimiteInscripcion,
                                    fechaSorteoGrupos, fechaSorteoPartidos, fechaInicioTorneo);
            unTorneo.setId(id);
            return unTorneo;
    }

    /**
     * metodo que se encarga de realizar la busqueda en la base  de datos de los equipos que pertenecen  a un torneo correspondiente
     * @param miTorneo
     * @return ArrayList<EquiposDTO> que hacen partey estan validados de dicho torneo
     */
    public static ArrayList<EquipoDTO> getEquiposAprovadosPorTorneo(TorneoDTO miTorneo) {
       String sql= "select e.nombre,e.aprobado,e.grupo,e.id "
               + "from equipo as e , equipo_has_torneo as equi, torneo as t "
               + "where t.id='"+miTorneo.getId()+"' and t.id=equi.fk_idtorneo and equi.fk_idequipo =e.id and e.aprobado='true'";
       
       if(!BaseDeDatos.hayConexion()){
           BaseDeDatos.conectar();
       }
       
       ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
       if(consulta==null||consulta.isEmpty()){
           return null;
       }
       
       ArrayList<EquipoDTO> misEquiposTorneo=new ArrayList<EquipoDTO>();
       ArrayList<ArrayList<String>> matrizDatos=ManejoStringConsulta.splitSeparadorMatriz(consulta);
       for(ArrayList<String> datosJugador:matrizDatos){
           EquipoDTO nuevo=crearEquipo(datosJugador);
           if(nuevo!=null){
               misEquiposTorneo.add(nuevo);
           }
       }
       
       return misEquiposTorneo;
    }
    
    /**
     * metodo que permite  con la informacion cargada de un torneo en una ArrayCrear el torneo
     * @param datos deben tener el siguiente formato 
     * @return 
     */
    private static EquipoDTO crearEquipo(ArrayList<String> datos){
       
        if(datos==null||datos.isEmpty()){
            return null;
        }
        EquipoDTO nuevoEquipo= new EquipoDTO(datos.get(0));
        nuevoEquipo.setAprovado(Boolean.parseBoolean(datos.get(1)));
        nuevoEquipo.setGrupo(datos.get(2).charAt(0));
        nuevoEquipo.setId(Integer.parseInt(datos.get(3)));
        
       return nuevoEquipo;
    }

    public static boolean registrarGruposEquipos(ArrayList<EquipoDTO> misEquipos) {
        
        if(!BaseDeDatos.hayConexion()){
                BaseDeDatos.conectar();
        }
        
        for(EquipoDTO equipo:misEquipos){
            String sql="UPDATE equipo set grupo='"+equipo.getGrupo()+"' where id='"+equipo.getId()+"'";
            
              boolean resp=BaseDeDatos.ejecutarActualizacionSQL(sql);
        }
        return true;
    }

    /**
     * metodo que permite obtener los equipos cuyo registro no ha sido validado
     * @param torneoCargado
     * @return el torneo con los equipos cargados
     */
    public static TorneoDTO getEquipoSinValidar(TorneoDTO torneoCargado) {
        String sql="select e.nombre, e.grupo, e.id  "
                + "from equipo as e , equipo_has_torneo as et, torneo as t "
                + "where t.nombre='"+torneoCargado.getNombre()+"' and et.fk_idtorneo=t.id and"
                + " et.validado='false' and et.fk_idequipo=e.id";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
         
        ArrayList<String> consulta= BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null){
            return null;
        }
        
        if(consulta.isEmpty()){
            return torneoCargado;
        }
        
        ArrayList<ArrayList<String>> consultaProcesada=ManejoStringConsulta.splitSeparadorMatriz(consulta);
        
        for(ArrayList<String> datos:consultaProcesada){
            EquipoDTO encontrado= new EquipoDTO(datos.get(0),datos.get(2).charAt(0),Integer.parseInt(datos.get(2)));
            torneoCargado.getMyEquipos().add(encontrado);
        }
        
        return torneoCargado;
        
    }

    /**
     * metodo que se encarga de realizar el cambio en las fechas del torneo
     * @param seleccionado<html><font color=green> debe tener cargada todas las fechas para crear el cronograma
     * @return 
     */
    public static boolean modificarCronogramaPlaneacion(TorneoDTO seleccionado) {
        
        String fechaLimiteInscripcion=ManejoDate.getDate(seleccionado.getFechaLimiteInscripcion());
        String fechaSorteoGrupo=ManejoDate.getDate(seleccionado.getFechaSorteoGrupo());
        String fechaSorteoPartido=ManejoDate.getDate(seleccionado.getFechaSorteoPartidos());
        String fechaInicioTorneo=ManejoDate.getDate(seleccionado.getInicioTorneo());
        
        String sql="UPDATE torneo "
                + "SET fechalimiteinscripcion='"+fechaLimiteInscripcion+"', fechasorteogrupo='"+fechaSorteoGrupo+"', "
                + "fechasorteopartidos='"+fechaSorteoPartido+"', fechainiciotorneo='"+fechaInicioTorneo+"' "
                + "WHERE id='"+seleccionado.getId()+"';";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        return BaseDeDatos.ejecutarActualizacionSQL(sql);
    }

    /**
     * metodo encargado de realizar la busqueda en la base  de datos de los torneos 
     * que no se les ha  asignado un cronograma de planeació
     * @return 
     */
    public static ArrayList<TorneoDTO> listarTorneosSinCronograma(OrganizadorTorneoDTO miOrganizador) {
        String sql="select id,nombre "
                + "from torneo "
                + "where fechalimiteinscripcion is null and "
                + "fechasorteogrupo is null and "
                + "fechasorteopartidos is null and "
                + "fechainiciotorneo is null and "
                + "fk_idorganizadortorneo='"+miOrganizador.getIdentificacion()+"'";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
                
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null){
            return null;
        }
        
        if(consulta.isEmpty()){
            return new ArrayList<TorneoDTO>();
        }
        ArrayList<TorneoDTO> misTorneos= new ArrayList<TorneoDTO>();
        ArrayList<ArrayList<String>> consultaProcesada=ManejoStringConsulta.splitSeparadorMatriz(consulta);
        for(ArrayList<String>datos:consultaProcesada){
            TorneoDTO miTorneo=crearTorneo(datos);
            misTorneos.add(miTorneo);
        }
        
        return misTorneos;
        
    }   

    /**
     * metodo que permite  consultar todos los torneos que ya tengan un cronograma especifico
     * @return ArrayList<TorneoDTO> con la información de los torneos
     */
    public static ArrayList<TorneoDTO> getTorneosCronograma(OrganizadorTorneoDTO miOrganizador) {
        
        
        String sql="select id, nombre  "
                + "from torneo "
                + "where not(fechalimiteinscripcion is null and "
                + "fechasorteogrupo is null and "
                + "fechasorteopartidos is null and "
                + "fechainiciotorneo is null) and "
                + "fk_idorganizadortorneo = '"+miOrganizador.getIdentificacion()+"'";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        
        ArrayList<String> consulta=BaseDeDatos.getConsultaSQL(sql);
        if(consulta==null){
            return null;
        }
        if(consulta.isEmpty()){
            return new ArrayList<TorneoDTO>();
        }
        
        ArrayList<ArrayList<String>> consultaProcesada=ManejoStringConsulta.splitSeparadorMatriz(consulta);
        ArrayList<TorneoDTO> misTorneos= new ArrayList<TorneoDTO>();
        for(ArrayList<String> datos:consultaProcesada){
            TorneoDTO miTorneo=crearTorneo(datos);
            misTorneos.add(miTorneo);
        }
        return misTorneos;                
    }

    /**
     * metodo encargado de realizar la busqueda en la base de datos de los torneos que tengan un cronograma de planeación creado , estan activos y sean de un Organizador Determinado
     * @param organizadorCargado
     * @return ArrayList<TorneoDTO> con los torneos que cumplan dichas condiciones, <html><br>puede retornar null si hay errores<br> puede retornar ArrayList vacio si no encontro ninguno
     */
    public static ArrayList<TorneoDTO> getTorneos(OrganizadorTorneoDTO organizadorCargado) {
        String sql="select id,nombre "
                + "from torneo "
                + "where id in("
                + " select id "
                + "from torneo "
                + "where fk_idorganizadortorneo='"+organizadorCargado.getIdentificacion()+"' and "
                + "estaactivo='true' and "
                + "not("
                + "fechalimiteinscripcion is null and "
                + "fechasorteogrupo is null and "
                + "fechasorteopartidos is null and "
                + "fechainiciotorneo is null "
                + ") "
                + "except "
                + "select ct.fk_idtorneo "
                + "from cancha_has_torneo as ct, torneo as t "
                + "where ct.fk_idtorneo=t.id and t.fk_idorganizadortorneo='"+organizadorCargado.getIdentificacion()+"' and t.estaactivo='true')";
        
        if(!BaseDeDatos.hayConexion()){
            BaseDeDatos.conectar();
        }
        ArrayList<String> consulta= BaseDeDatos.getConsultaSQL(sql);
        
        if(consulta==null){
            return null;
        }
        
        if(consulta.isEmpty()){
            return new ArrayList<TorneoDTO> ();
        }
        
        ArrayList<ArrayList<String>> consultaProcesada= ManejoStringConsulta.splitSeparadorMatriz(consulta);
        ArrayList<TorneoDTO> resp= new ArrayList<TorneoDTO>();
        
        for(ArrayList<String> datos:consultaProcesada){
            TorneoDTO nuevo= crearTorneo(datos);
            resp.add(nuevo);
        }
        return resp;
    }
    
}