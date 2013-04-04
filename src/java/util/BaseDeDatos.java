/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import java.sql.*;
import java.util.ArrayList;
//import java.util.Iterator;
//import javax.naming.*;
//import javax.sql.*;
//import java.util.ArrayList;
//import UTIL.JDBCMiddler;


/**
 *Clase que permite conectar con una Base De datos Mysql; sin embargo,
 * con solo cambiar el atributo controlador puede usarse para cualquier motor de base de datos
 * @author madarme
 */
public class BaseDeDatos {
private static String bd = "TorneoFutsalDB";
/**
private static  String login = "laptop";
private static  String password = "1234";
private static String url = "jdbc:mysql://192.168.0.13:3306/"+bd;
*/
private static  String login = "ufps_63";
private static  String password = "ufps_nm";
//private static String url = "jdbc:mysql://localhost:3306/"+bd;

private static  String url = "Jdbc:postgresql://sandbox2.ufps.edu.co:5432/"+bd;
//private static String controlador = "com.mysql.jdbc.Driver";
private static  String controlador="org.postgresql.Driver";

private static   JDBCMiddler jdbc;



public static boolean hayConexion()
{
        return (jdbc!=null && jdbc.hayConexion());
}

    
 public static  void conectar()
    {
        jdbc = new JDBCMiddler(controlador,url,login,password);
        try{
        jdbc.conectar();
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

public static boolean ejecutarActualizacionSQL(String comandoSQL)
{
    try{
        return (jdbc.ejecutarActualizacionSQL(comandoSQL));
    }catch(Exception e)
    {
            System.err.println("SQL Error:"+e.getMessage());
            return (false);
    }
}

public  static void desconectar()
{
try{
        jdbc.desconectar();
    }catch(Exception e)
    {
            System.err.println("SQL Error:"+e.getMessage());
        
    }
}


public  static String getTablaHTML(String sql)
{
try{
        return (jdbc.getHTML(sql));
    }catch(Exception e)
    {
            System.err.println("SQL Error:"+e.getMessage());
            return ("No se pudo crear la tabla");
    }


}


public  static ResultSet ejecutarSQL(String consultaSQL)
{
    try{
        return (jdbc.ejecutarSQL(consultaSQL));
    }catch(Exception e)
    {
            System.err.println("SQL Error:"+e.getMessage());
            return (null);
    }

}


public static java.util.ArrayList<String>  getConsultaSQL(String sql){
try{
        return (jdbc.getSQL(sql));
    }catch(Exception e)
    {
            System.err.println("SQL Error:"+e.getMessage());
            return (null);
    }
    

}

public static void main(String args[])
{
if(BaseDeDatos.hayConexion())
    System.out.println("Ya hay conexion");
else
{
       System.out.println("Intentando conectar con la base de datos...");
    BaseDeDatos.conectar();
    if(BaseDeDatos.hayConexion()) System.out.println("Base de datos conectada.");
}
String resp="";
ArrayList<String> prueba= BaseDeDatos.getConsultaSQL
        ("SELECT id_rol, nombre FROM rol WHERE nombre = 'Director Tecnico'");
ArrayList<String>  resultado=ManejoStringConsulta.splitSeparador(prueba);
for(String x: resultado){
    
    resp+=x +"\n";
}
System.out.println(resp);
System.err.println(resultado.size());
//System.out.println(BaseDeDatos.getTablaHTML("select nombre from estudiante"));


}




}
