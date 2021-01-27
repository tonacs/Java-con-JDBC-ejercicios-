package test;

import datos.Conexion;
import datos.PersonaJDBC;
import domain.Persona;
import java.sql.*;


public class ManejoPersonas {

    public static void main(String[] args) {
        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            PersonaJDBC personaJDBC = new PersonaJDBC(conexion);
            Persona cambioPersona = new Persona();
            cambioPersona.setId_persona(2);
            cambioPersona.setNombre("Vladimir");
            cambioPersona.setEmail("v@gmail.com");
            cambioPersona.setTelefono("776767676");
            personaJDBC.update(cambioPersona);

            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Alberto");
            //nuevaPersona.setApellido("Ramirezjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
            nuevaPersona.setApellido("Ramirez");
            personaJDBC.insert(nuevaPersona);

            conexion.commit();
            System.out.println("Se ha hecho commit de la transacción");

        } catch (SQLException ex) {
            try {
                ex.printStackTrace(System.out);
                System.out.println("Entramos al RollBack");
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }

        }

    }
}
