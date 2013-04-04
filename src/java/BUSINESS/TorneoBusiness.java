/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUSINESS;

import DAO.EquipoDAO;
import DAO.TorneoDAO;
import DAO.Equipo_has_TorneoDAO;
import DTO.DTO_Negocio.OrganizadorTorneoDTO;
import DTO.DTO_Negocio.PersonaDTO;
import DAO.UsuarioDAO;
import DTO.DTO_Negocio.CanchaDTO;
import DTO.DTO_Negocio.DirectorTecnicoDTO;
import DTO.DTO_Negocio.EquipoDTO;
import DTO.DTO_Negocio.JugadorDTO;
import DTO.DTO_Negocio.TorneoDTO;
import DAO.Cancha_has_TorneoDAO;
import DTO.DTO_Negocio.FechaDTO;
import DTO.DTO_Negocio.HoraDTO;
import DTO.DTO_Negocio.HorarioDTO;
import java.util.ArrayList;
import java.util.Date;
import util.ManejoDate;
import util.ManejoStringConsulta;

/**
 *
 * @author Mario(K y M)
 */
class TorneoBusiness {

     
    /**
     * metodo que permite agregar un torneo nuevo al sistema con la información correspondiente
     * @param nombreTorneo
     * @param fechaLimiteInscripcion
     * @param fechaSorteoGrupos
     * @param fechaSorteoPartidos
     * @param fechaInicioTorneo fecha en la que se iniciaran los partido
     * @param usernameOrganizadorT
     * @return 
     */
    public static String addTorneo(String nombreTorneo, String usernameOrganizadorT) {
       
        OrganizadorTorneoDTO miOrganizador= OrganizadorTorneoBusiness.getOrganizadorTorneoUsername(usernameOrganizadorT);
        TorneoDTO torneoNuevo=new TorneoDTO(nombreTorneo);
        torneoNuevo.setMyOrganizador(miOrganizador);
       int resultado= TorneoDAO.addTorneo(torneoNuevo);
       
       switch(resultado){
           case 1:return "se ha creado un  nuevo torneo con el nombre: "+nombreTorneo+" Organizador asignado: "+usernameOrganizadorT;
           case 2:return "no se ha podido realizar la conexion con la base de datos";
           case 3:return "no se pudo crear el torneo "+nombreTorneo+" ya existe un torneo con este nombre";
           default :return "no se pudo terminar la operacion";
       }
       
    }

    /**
     * metodo que se permite obtener una lista de los torneos activos en el sistema
     * @return un ArrayList<String> con los torneos existentes en el sistema
     */
    public static ArrayList<String> getTorneosActivos() {
        ArrayList<TorneoDTO> misTorneos= new ArrayList<TorneoDTO>();
        misTorneos=TorneoDAO.getTorneosActivos();
        
        if(misTorneos==null){
            return null;
        }
        if(misTorneos.isEmpty()){
                return new ArrayList<String>();
        }
        
        ArrayList<String> nombreTorneos=new ArrayList<String>();
        for(TorneoDTO miTorneo:misTorneos){
            nombreTorneos.add(miTorneo.getNombre());
        }
            return nombreTorneos;
        
    }
    
    /**
     * metodo que se encarga de crear a los jugadores segun un listado de información de los jugadores
     * @param datosJugadores
     * @return 
     */
    private static ArrayList<JugadorDTO> crearJugadores(ArrayList<String> datosJugadores){
         ArrayList<JugadorDTO> misJugadores= new ArrayList<JugadorDTO>();
        
      
        for (String datosJugador : datosJugadores) {

            String infoJugador[] = datosJugador.split("/");
            String nombre = infoJugador[0];
            String apellidos = infoJugador[1];
            String identificacion = infoJugador[2];

            
            Date fechaNcto= ManejoDate.getDate(infoJugador[3]);
            
            int estatura = Integer.parseInt(infoJugador[4]);
            float peso = Float.parseFloat(infoJugador[5]);
            JugadorDTO nuevo = new JugadorDTO(nombre, apellidos, identificacion, fechaNcto, estatura, peso);
            misJugadores.add(nuevo);
        }
        return misJugadores;
    }

    /**
     * metodo que se encarga de realizar las validaciones para poder registrar un equipo a un torneo
     * @param datosJugadores<html><font color=purple><b><br>donde los datos del jugador deben estar guardados  nombre, apellidos, identificacion, fechaNcto, estatura, peso separado por un '/' entre cada dato  y la fecha debe usar como  separador '-' año-mes-dia <font color=black>
     * @param nombreTorneo
     * @param usernameTecnico
     * @param nombreEquipo
     * @return un Strign con el resultado de la operación
     */
    public static String registrarEquipoTorneo(ArrayList<String> datosJugadores, String nombreTorneo,
                                                        String usernameTecnico, String nombreEquipo) {
        
        DirectorTecnicoDTO miDirector= DirectorTecnicoBusiness.getDirectorTecnicoUsername(usernameTecnico);
        if(miDirector==null){
            return "no se puede conectar con la base de datos";
        }
        
        EquipoDTO miEquipo=DirectorTecnicoBusiness.getEquipoPorDirectorTecnico(miDirector);
        
        if(miEquipo!=null){
            return "el director tecnico ya tiene un equipo registrado ";
        }
        
        ArrayList<JugadorDTO> misJugadores=TorneoBusiness.crearJugadores(datosJugadores);        
        ArrayList<JugadorDTO> misJugadoresHabilitados=TorneoDAO.validarJugadoresTorneos(misJugadores);
        
        if(misJugadoresHabilitados==null){
            return "no se pudo realizar la conexion con la base de datos";
        }
        if(misJugadoresHabilitados.isEmpty()){
            return "los jugadores ingresados se encontraron en otros quipos o torneos registrados,"
                    + " un jugador no puede jugar en mas de un equipo al tiempo.";
        }
        
        if(misJugadoresHabilitados.size()<7){
            return "algunos jugadores no se pudieron registrar por encontrarse registrado en otro equipo,"+
                    "no cuenta con la cantidad de minima de jugadores para registrar un equipo.";
        }
        
        TorneoDTO torneoSeleccionado=new TorneoDTO(nombreTorneo);
        EquipoDTO nuevoEquipo= new EquipoDTO(nombreEquipo);
        
        
        EquipoDTO validado= EquipoDAO.getEquipoPorNombreTorneo(torneoSeleccionado,nuevoEquipo,true);
        if(validado!=null){
            return "el nombre del equipo ya se encuentra registrado a otro equipo";
        }
        
        TorneoDTO torneoCargado=TorneoDAO.getTorneoPorNombre(torneoSeleccionado);
        if(torneoCargado==null){
            return "no se pudo conectar con la base de datos";
        }
        
        nuevoEquipo= new EquipoDTO(nombreEquipo,misJugadoresHabilitados);
        
        nuevoEquipo.setMyDirectorTecnico(miDirector);
        
        torneoCargado.getMyEquipos().add(nuevoEquipo);
        
        int resp=EquipoDAO.addEquipo(torneoCargado);
        
        return (resp==2)?"no se pudo realizar la conexion con la base de datos":
                getResultadoAddEquipo(misJugadoresHabilitados, nombreTorneo, miDirector.getNombre()+" "+miDirector.getApellidos(), nombreEquipo);
    }
    
        /**
         * metodo que se encarga de  concatenar la información a mostrar despues de hacer el registro de un equipo
         * @param datosJugadores
         * @param nombreTorneo
         * @param usernameTecnico
         * @param nombreEquipo
         * @return Strin con una descripción sobre la operacion que se realizo en el sistema
         */
        private static String getResultadoAddEquipo(ArrayList<JugadorDTO> datosJugadores, String nombreTorneo,
                                                        String usernameTecnico, String nombreEquipo){
            
            String resp= "Se ha añadido el equipo: "+nombreEquipo+" al torneo "+nombreTorneo+"\n con los jugadores:\n";
            
            for(JugadorDTO jugador:datosJugadores){
               
                resp+= jugador.getNombre()+" "+jugador.getApellidos()+"\n";
            }
            resp+="Director Tecnico: "+usernameTecnico;
            return resp;
        }

//    public static String validarRegistroEquipo(String nombreEquipo, String nombreTorneo) {
//        
//        TorneoDTO miTorneo=new TorneoDTO(nombreTorneo);
//        TorneoDTO miTorneoCargado=TorneoDAO.getTorneoPorNombre(miTorneo);
//        
//        EquipoDTO miEquipo=EquipoBusiness.validarEquipo(nombreEquipo);
//        
//    }
    
        /**
         * metodo que se encarga de obtener los Equipos 
         * @param nombreTorneo
         * @return 
         */
    public static String generarGruposTorneo(String nombreTorneo) {
        TorneoDTO miTorneo=new TorneoDTO(nombreTorneo);
        TorneoDTO miTorneoCargado=TorneoDAO.getTorneoPorNombre(miTorneo);
        if(miTorneo==null){
            return "no su pudo realizar conexion con la base de  datos";
        }
        
        ArrayList<EquipoDTO> misEquipos=TorneoDAO.getEquiposAprovadosPorTorneo(miTorneo);
        if(misEquipos==null){
            return "error no pudo conectar con la base de datos";
        }
        
        if(misEquipos.isEmpty()){
            return "no hay equipos con el registro validado para torneo";
        }
        
        asignarGrupoAleatoriamente(misEquipos);
        String resp="";
        for(char c='A';c<'E';c++){
            resp+="Equipos del grupo "+c+":\n";
            for(EquipoDTO equipo:misEquipos){
                if(equipo.getGrupo()==c){
                    resp+=equipo.getNombre()+"\n";
                }
            }
        }
        if(!TorneoDAO.registrarGruposEquipos(misEquipos)){
            return "error al conectar con la base de  datos";
        }
        return resp;
    }
    
    private static void asignarGrupoAleatoriamente(ArrayList<EquipoDTO> misEquipos){
        ArrayList<Character> grupos= new ArrayList<Character>();        
        
        grupos.add('C');  grupos.add('A');
        grupos.add('D');  grupos.add('D');
        grupos.add('C');  grupos.add('C');        
        grupos.add('A');
        grupos.add('C');
        grupos.add('D');
        grupos.add('A');
        grupos.add('A');
        grupos.add('D');
        grupos.add('B');
        grupos.add('B');
        grupos.add('B');
        grupos.add('B');
      
        int i=0;
        while(!grupos.isEmpty()){
            int index=(int)((Math.random()*(Math.random()*100))%grupos.size());
            misEquipos.get(i).setGrupo(grupos.get(index));
            grupos.remove(index);
            i++;
        }
    }

    /**
     * metodo que se encarga de realizar las operaciones y validaciones necesarias para  validar el registrar un equipo
     * @param nombreEquipo
     * @param nombreTorneo
     * @return una cadena con el resultado de operacion
     */
    public static String validarRegistroEquipo(String nombreEquipo,String nombreTorneo) {
        
        TorneoDTO miTorneo= new TorneoDTO(nombreTorneo);
        TorneoDTO torneoValidado=TorneoDAO.getTorneoPorNombre(miTorneo);
        if(torneoValidado==null){
            return "no se pudo conectar con la base de datos";
        }
        EquipoDTO validoEquipo=EquipoBusiness.getEquipoTorneoPorNombre(torneoValidado,nombreEquipo);
        if(validoEquipo==null){
            return "no se puede conectar con la base de datos";
        }
        
        boolean resp=Equipo_has_TorneoDAO.validarEquipo(validoEquipo,torneoValidado);
        return (resp)?
                "Se ha validado la inscripcion del equipo":
                "no se pudo conectar con la base de  datos";
    }

    /**
     * metodo que permite listar los equipos que no  han sido validados en un torneo
     * @param nombreTorneo
     * @return ArrayList<String> con los nombre de los equipos sin validar
     */
    public static ArrayList<String> getEquipoSinValidar(String nombreTorneo) {
        TorneoDTO miTorneo= new TorneoDTO(nombreTorneo);
        TorneoDTO torneoCargado=TorneoDAO.getTorneoPorNombre(miTorneo);
        if(torneoCargado==null){
            return null;
        }
        TorneoDTO torneoConEquipos=TorneoDAO.getEquipoSinValidar(torneoCargado);
        if(torneoConEquipos==null){
            return null;
        }
        ArrayList<String> resp= new ArrayList<String>();
        for(EquipoDTO equipo:torneoConEquipos.getMyEquipos()){
            resp.add(equipo.getNombre());
        }
        return resp;
    }

    /**
     * metodo que permite realizar las validaciones correspondientes y crear el cronograma de planeacion de un torneo 
     * @param nombreTorneo
     * @param fechaLimiteInscripcion
     * @param fechaSorteoGrupos
     * @param fechaSorteoPartidos
     * @param fechaInicioTorneo
     * @return String con el resultado de la operacion
     */
    public static String crearCronogramaPlaneacion(String nombreTorneo, String fechaLimiteInscripcion, 
            String fechaSorteoGrupos, String fechaSorteoPartidos, String fechaInicioTorneo) {
        
        TorneoDTO seleccionado=new TorneoDTO(nombreTorneo);
        
        
        
        TorneoDTO torneoCargado=TorneoDAO.getTorneoPorNombre(seleccionado);
        
        if(torneoCargado==null){
            return "no se pudo conectar con la base de datos";
        }
        
        Date fechaLimiteInscripcionD=ManejoStringConsulta.getFechaFormatoDate(fechaLimiteInscripcion);
        Date fechaSorteoGruposD=ManejoStringConsulta.getFechaFormatoDate(fechaSorteoGrupos);
        Date fechaSorteoPartidosD=ManejoStringConsulta.getFechaFormatoDate(fechaSorteoPartidos);
        Date fechaInicioTorneoD=ManejoStringConsulta.getFechaFormatoDate(fechaInicioTorneo);
        
        torneoCargado.setFechaLimiteInscripcion(fechaLimiteInscripcionD);
        torneoCargado.setFechaSorteoGrupo(fechaSorteoGruposD);
        torneoCargado.setFechaSorteoPartidos(fechaSorteoPartidosD);
        torneoCargado.setInicioTorneo(fechaInicioTorneoD);
        
        boolean resp=TorneoDAO.modificarCronogramaPlaneacion(torneoCargado);
        
        String mensaje="no se pudo conectar con la base de datos";
        
        if(resp){
            mensaje="nombre del torneo: "+seleccionado.getNombre()+"\n"+
                    "fecha de inicio: "+ManejoStringConsulta.getMensajeFecha(fechaInicioTorneoD)+"\n"
                    + "fecha del sorteo de los grupos: "+ManejoStringConsulta.getMensajeFecha(fechaSorteoGruposD)+"\n"
                    + "fecha del sorteo de los equipos: "+ManejoStringConsulta.getMensajeFecha(fechaSorteoPartidosD)+"\n"
                    + "fecha de inicio del torneo: "+ManejoStringConsulta.getMensajeFecha(fechaInicioTorneoD);
        }
        return mensaje;
    }

    /**
     * metodo que permite  listar los torneos que no tiene un cronograma de planeación asignado
     * @param username de un organizador de torneo en especifico
     * @return ArrayList<String> con el nombre de los torneos que no se les ha asignado un cronograma de planeación
     */
    public static ArrayList<String> getTorneosSinCronograma(String username) {
        
        OrganizadorTorneoDTO organizadorCargado=OrganizadorTorneoBusiness.getOrganizadorTorneoUsername(username);
        
        if(organizadorCargado==null){
            return null;
        }
        
        ArrayList<TorneoDTO> misTorneos=TorneoDAO.listarTorneosSinCronograma(organizadorCargado);
        if(misTorneos==null){
            return null;
        }
        
        if(misTorneos.isEmpty()){
            return new ArrayList<String>();
        }
        
        ArrayList<String> resp= new ArrayList<String>();
        for(TorneoDTO torneo:misTorneos){
            resp.add(torneo.getNombre());
        }
        return resp;
    }

    /**
     * metodo que permite obtener en un listado los Torneos que ya tiene asigando un cronograma de planeación
     * @return ArrayList<Stirng> con los nombre de los toneos
     */
    public static ArrayList<String> getTorneosCronograma(String username) {
        
        
        OrganizadorTorneoDTO organizadoCargado=OrganizadorTorneoBusiness.getOrganizadorTorneoUsername(username);
        
        if(organizadoCargado==null){
            return null;
        }
        
        ArrayList<TorneoDTO> misTorneos=TorneoDAO.getTorneosCronograma(organizadoCargado);
        if(misTorneos==null){
            return null;
        }
        if(misTorneos.isEmpty()){
            return new ArrayList<String>();
        }
        
        ArrayList<String> resp= new ArrayList<String>();
        for(TorneoDTO torneo:misTorneos){
            resp.add(torneo.getNombre());
        }
        return resp;
    }

    /**
     * metodo encargado de realizar las respectivas validaciones y obtener el 
     * @param nombreTorneo
     * @return String <html><br>con el siguiente formato
     * <br>nombre torneo
     * <br>fecha limite inscripción
     * <br>fecha sorteo grupos
     * <br>fecha sorteo partidos
     * <br>fecha inicion torneo
     */
    public static String getCronogramaTorneo(String nombreTorneo) {
        TorneoDTO miTorneo=new TorneoDTO(nombreTorneo);
        
        TorneoDTO torneoCargado=TorneoDAO.getTorneoPorNombre(miTorneo);
        if(torneoCargado==null){
            return "";
        }
        String fechaLimiteInscripcion=ManejoStringConsulta.getMensajeFecha(torneoCargado.getFechaLimiteInscripcion());
        String fechaSorteoGrupos=ManejoStringConsulta.getMensajeFecha(torneoCargado.getFechaSorteoGrupo());
        String fechaSorteoPartidos=ManejoStringConsulta.getMensajeFecha(torneoCargado.getFechaSorteoPartidos());
        String fechaInicioTorneo=ManejoStringConsulta.getMensajeFecha(torneoCargado.getInicioTorneo());
       
        return torneoCargado.getNombre()+"/"+fechaLimiteInscripcion+"/"+fechaSorteoGrupos+
                "/"+fechaSorteoPartidos+"/"+fechaInicioTorneo;
                       
    }

    
    /**
     * metodo que se encarga de hacer las validaciones necesarios para hacer los cambios en la planificación
     * @param nombreTorneo
     * @param fechaLimiteInscripcion
     * @param fechaSorteoGrupos
     * @param fechaSorteoPartidos
     * @param fechaInicioTorneo
     * @return String con el resultado de la operación
     */
    public static String modificarCronogramaPlaneacion(String nombreTorneo, String fechaLimiteInscripcion, 
            String fechaSorteoGrupos,String fechaSorteoPartidos, String fechaInicioTorneo) {
        
        try{
        Date fechaLimiteInscripcionD=ManejoStringConsulta.getFechaFormatoDate(fechaLimiteInscripcion);
        Date fechaSorteoGruposD=ManejoStringConsulta.getFechaFormatoDate(fechaSorteoGrupos);
        Date fechaSorteoPartidosD=ManejoStringConsulta.getFechaFormatoDate(fechaSorteoPartidos);
        Date fechaInicioTorneoD=ManejoStringConsulta.getFechaFormatoDate(fechaInicioTorneo);
        TorneoDTO miTorneo= new TorneoDTO(nombreTorneo, fechaInicioTorneoD, fechaLimiteInscripcionD,
                fechaSorteoGruposD, fechaSorteoPartidosD, fechaInicioTorneoD);
        
        boolean resp=TorneoDAO.modificarCronogramaPlaneacion(miTorneo);
        
        if(!resp){
            return  "";
        }
        
        return "Nombre del Torneo: "+nombreTorneo+"\n"
                + "Fecha Limite de Inscripción Equipos: "+ManejoStringConsulta.getMensajeFecha(fechaLimiteInscripcionD)+"\n"
                + "Fecha de Sorteo Grupos: "+ManejoStringConsulta.getMensajeFecha(fechaSorteoGruposD)+"\n"
                + "Fecha de Sorteo partidos: "+ManejoStringConsulta.getMensajeFecha(fechaSorteoPartidosD)+"\n"
                + "Fecha Inicio Sorteo: "+ManejoStringConsulta.getMensajeFecha(fechaInicioTorneoD);
        }catch(Exception e){
            
            return "";
        }
    }

    /**
     * metodo que se encarga de realizar las validaciones e invocaciones necesarias para poder asignar un listado de canchas a  un torneo
     * @param nombreTorneo
     * @param infoCanchas
     * @return String con el resultado de la operación<html><br> puede retornar "" si hay algun error
     */
    public static String asignarCanchasTorneo(String nombreTorneo, ArrayList<String> infoCanchas) {
        TorneoDTO miTorneo= new TorneoDTO(nombreTorneo);
        TorneoDTO torneoCargado=TorneoDAO.getTorneoPorNombre(miTorneo);
        
        if(torneoCargado==null){
            return "";
        }
        ArrayList<CanchaDTO> misCanchas=CanchaBusiness.getCanchas(infoCanchas);
        
        
        if(misCanchas==null||misCanchas.isEmpty()){
            return "";
        }
             
        
//        boolean resp=Cancha_has_TorneoDAO.addCanchasTorneos(misCanchas,torneoCargado);
        for(CanchaDTO cancha:misCanchas){
        ArrayList<FechaDTO> misFechas=getFechasCanchaTorneo(miTorneo.getInicioTorneo());
        HorarioDTO miHorario= new HorarioDTO(misFechas);
        cancha.setMyHorario(miHorario);
        }
        
//        
        boolean resp=Cancha_has_TorneoDAO.addCanchasTorneos(misCanchas,torneoCargado);
        
        if(!resp){
            return "";
        }
        
        String texto="Se han asigando al torneo: "+nombreTorneo+" las canchas: \n";
        for(CanchaDTO cancha:misCanchas){
            texto+=cancha.getNombre()+" ("+cancha.getDireccion()+")\n";
        }
        
        return texto;
    }
    
    /**
     * metodo que se encarga de crear las fechas para una cancha que se asigna a  un torneo 
     * @param fechaInicioTorneo
     * @return ArrayList<FechaDTO>
     */
    private static ArrayList<FechaDTO> getFechasCanchaTorneo(Date fechaInicioTorneo){
        ArrayList<FechaDTO> misFechas= new ArrayList<FechaDTO>();
        Date fecha=fechaInicioTorneo;
        for(int i=0;i<17;i++,fecha=ManejoDate.nextDate(fecha)){
            FechaDTO nueva= new FechaDTO(fecha);
            misFechas.add(nueva);
        }
        return misFechas;
    }

    /**
     * metodo que permite  obtener la fecha siguiente de un dia en especificada
     * @param fecha
     * @return Date con la fecha siguiente 
     */
    private static Date getSiguienteFecha(Date fecha){
        
        int dia=fecha.getDate();
        int mes=(fecha.getMonth()==0)?12:fecha.getMonth();
        int anyo=fecha.getYear();
        
        int diasMes=getDiasMes(fecha);
        dia++;
        
        if(dia>diasMes){
            mes++;            
            dia=1;
            if(mes>12){
                anyo++;
                mes=1;
                dia=1;
            }
        }
        
        return null;
        
        
        
    }
    
    /**
     * metodo que permite obtener los dias que tiene un mes 
     * @param fecha
     * @return int con el numero de dias que tiene el mes
     */
    private static int getDiasMes(Date fecha){
        int mes=fecha.getMonth();
        switch(mes){
            case 4:
            case 6:
            case 9:
            case 11: return 30;
            case 0:
            case 1:
            case 3:
            case 5:
            case 7:
            case 10:
            case 12: return 31;
            case 2:  return (esBiciesto(fecha.getYear()))?29:28;
            default:return -1;                
        }
        
    }
    
    /**
     * 
     * metodo que permite 
     * @param año
     * @return 
     */    
    private static boolean esBiciesto(int año){
        
     return ((año % 4 == 0) && ((año % 100 != 0) || (año % 400 == 0)))?
             true:
             false;
        
    }

    /**
     * metodo que realiza las validaciones necesarias para poder listar los torneos de una Organizador siempre que estos ya tengan creado un Cronograma de planeación
     * @param username
     * @return ArrayList<Stirng> con los nombre de los torneos <html><br>puede retornar null si hay errores<br>puede retornar ArrayList vacio si no encuentra torneos
     */
    public static ArrayList<String> getTorneosOrganizador(String username) {
        OrganizadorTorneoDTO miOrganizador=new OrganizadorTorneoDTO(username, "");
        OrganizadorTorneoDTO organizadorCargado=(OrganizadorTorneoDTO)UsuarioDAO.getUsuarioPorUsername(miOrganizador);
        
        if(organizadorCargado==null){
            return null;
        }
        
        ArrayList<TorneoDTO> misTorneos=TorneoDAO.getTorneos(organizadorCargado);
        
        if(misTorneos==null){
            return null;
        }
        if(misTorneos.isEmpty()){
            return new ArrayList<String>();
        }
        ArrayList<String> resp= new ArrayList<String>();
        for(TorneoDTO torneo:misTorneos){
            resp.add(torneo.getNombre());
        }
        return resp;
    }

    /**
     * metodo que se encarga de realizar las validaciones necesarias y listar  los torneos que pertencen a un organizador
     * @param username
     * @return Arraylist<String> con el nombre de los torneos del organizador que se encuentren activos<hmlt><br>puede retornar null si hay algun error<br>puede retornar ArrayList vacio si no encuentra ningun torneo
     */
    public static ArrayList<String> getTorneosActivosOrganizador(String username) {
        OrganizadorTorneoDTO organizadorTorneo=OrganizadorTorneoBusiness.getOrganizadorTorneoUsername(username);
        if(organizadorTorneo==null){
            return null;
        }
        return null;
    }

    /**
     * metodo que se encarga de listar los torneos disponibles para inscripcion
     * @return 
     */
    public static ArrayList<String> getTorneosDisponiblesInscripcion() {
        ArrayList<TorneoDTO> misTorneos=TorneoDAO.getTorneosActivos();
        if(misTorneos==null){
            return null;
        }
        if(misTorneos.isEmpty()){
            return new ArrayList<String>();
        }
        
        ArrayList<String>resp= new ArrayList<String>();
        
            for(TorneoDTO torneo:misTorneos){
                if(torneo.getFechaLimiteInscripcion()!=null){
                    if(ManejoDate.A_esMenor_B(ManejoDate.getFechaActual(), torneo.getFechaLimiteInscripcion())){
                        resp.add(torneo.getNombre());
                    }
                }
            }
        return resp;
    }
    
        
    
    
}
