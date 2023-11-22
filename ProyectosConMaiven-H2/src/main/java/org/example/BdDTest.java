package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


        /*Tenemos que crear una conexión a la base de datos
        e insertar una fila paciente. Luego, abrir una
        transacción y asignar otro password con una sentencia
        update y, paso siguiente, generar una excepción (throw new Exception).
        Por último, corroborar con una consulta que el paciente existe
        y que el campo password mantuvo su valor inicial del punto 1.*/

public class BdDTest {
    public static void main(String[] args) throws Exception {
        // indicamos el driver correspondiente a la bd en memoria
        // carga dinámicamente la clase del controlador en memoria,
        // y newInstance() crea una instancia de esa clase.
        Class.forName("org.h2.Driver").newInstance();


        // con el metodo hetCoonnection de DriverManager
        // creamos un obj conexion con las configuraciones correspondientes a nuestra bd: url,usuario y pass
        Connection con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");


        //Obj para realizar consultas, instruccines SQL
        Statement stmt = con.createStatement();
        //Código para crear una tabla. Elimina la tabla si esta ya existe y la vuelve a crear
        //elimnarla antes de comenzar es una buena práctica en las bd en memoria se supone que no debe existir al levantarla
        String createSql = "DROP TABLE IF EXISTS TEST_PACIENTE; " +
                "CREATE TABLE TEST_PACIENTE(DNI INT PRIMARY KEY, NAME VARCHAR(50), APELLIDO VARCHAR(50)," +
                "                 DOMICILIO VARCHAR (50), FECHADEALTA DATE, USERNAME VARCHAR (50)," +
                "                  PASSWORD VARCHAR (50) );";


        //utilizamos el objeto statement para crear una tabla- execute ejecuta la instruccion SQL
        stmt.execute(createSql);

        /* EL prepareStatment es una subclase de statment, permite pasar parametros.

        String createStmt = "DROP TABLE IF EXISTS PACIENTE; " +
                "CREATE TABLE PACIENTE(DNI INT PRIMARY KEY,NOMBRE VARCHAR(50),APELLIDO VARCHAR(50),
                 DOMICILIO VARCHAR (50), );";
        PreparedStatement crearTabla = con.prepareStatement(createStmt);
        crearTabla.execute();*/

        String insertarRegistros="INSERT INTO TEST_PACIENTE VALUES(1, 'Juan');\n" +
                "INSERT INTO TEST_CLIENTE VALUES(2, 'Florencia');\n";

        //String insertarRegistros = "INSERT INTO CLIENTES VALUES(?, ?,?);";

        stmt.execute(insertarRegistros);
        //Codigo para consultar (query) todos los registros de la tabla TEST_CLIENTE
        String sql = "select * from TEST_CLIENTE";
        ResultSet res= stmt.executeQuery(sql); // como es una query va este método...


        // retorna un objeto de tipo ResultSet
        //Código para recorrer el resultado de la consulta
        while(res.next()) {
            System.out.println(res.getInt(1) + res.getString(2));
        }

        con.close();
    }

}
