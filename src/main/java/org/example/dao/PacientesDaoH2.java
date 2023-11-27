package org.example.dao;

import org.example.model.Paciente;

import java.sql.*;
import java.util.List;

public class PacientesDaoH2 implements IPacienteDao<Paciente>{

    private static final String DRIVER="org.h2.Driver";
    private static final String DRIVER_URL="jdbc:h2:~/PACIENTES";
    private static final String DRIVER_USER="USUARIO";
    private static final String DRIVER_PASS="CLAVE";


    // no tiene que ser estático no debn poder acceder x fuera de la clase
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        Connection con =DriverManager.getConnection(DRIVER_URL,DRIVER_USER,DRIVER_PASS);
        return con;
    }

    // private?
    @Override
    public Paciente buscar(Long id) {

        Connection con= null;
        Paciente paciente=null;
       try{
           con = getConnection();
           String buscar="SELECT * FROM PACIENTES WHERE DNI=?; " ;
           PreparedStatement pstm= con.prepareStatement(buscar);
           pstm.setLong(1,id);
           // trae el resultado
           ResultSet resultado=pstm.executeQuery();
           while( resultado.next()){
               Long idRetornado= resultado.getLong(1);
               String nombrePaciente= resultado.getString(2);
               String apellido= resultado.getString(3);

               paciente= new Paciente(nombrePaciente,apellido,idRetornado );
           }

           con.close();

           System.out.println(paciente);

       } catch(SQLException | ClassNotFoundException e){
           e.printStackTrace();
        }

        return paciente;
    }

    @Override
    public Paciente crear(Paciente paciente) {
        Connection con= null;

        try{
            con= getConnection();
            String consultaCreacion=" INSERT INTO PACIENTES VALUES(?,?,?);";
            PreparedStatement pstm= con.prepareStatement(consultaCreacion);
            pstm.setLong(1, paciente.getDni());
            pstm.setString(2, paciente.getNombre() );
            pstm.setString(3,paciente.getApellido());
            pstm.execute();


            buscar(paciente.getDni());
           // while(resultSet.next()){
              //  System.out.println(" el paciente creado es "+ resultSet);

            //}

            con.close();

        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return paciente;
    }

    @Override
    public void eliminar(Long id) {
        Connection con= null;
        try{
            con= getConnection();
            String eliminar=" DELETE FROM PACIENTES WHERE ID =?;";
            PreparedStatement pstm= con.prepareStatement(eliminar);
            pstm.setLong(1, 1);
            pstm.execute();

            con.close();

        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        Connection con= null;
        try{
            con= getConnection();
            String lista=" SELECT * FROM PACIENTES;";
            PreparedStatement pstm= con.prepareStatement(lista);
            pstm.execute();

            con.close();

        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }

}
