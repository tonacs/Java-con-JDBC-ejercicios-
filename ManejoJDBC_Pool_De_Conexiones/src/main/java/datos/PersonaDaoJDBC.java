package datos;

import domain.PersonaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoJDBC implements PersonaDao{

    private Connection conecionTransaccional;
    private static final String SQL_SELECT = "select id_persona,nombre,apellido,email,telefono from persona";
    private static final String SQL_INSERT = "insert into persona(nombre,apellido,email,telefono) values(?,?,?,?)";
    private static final String SQL_UPDATE = "update persona set nombre=?,apellido=?,email=?,telefono=? where id_persona=?";
    private static final String SQL_DELETE = "delete from persona where id_persona=?";

    public PersonaDaoJDBC() {

    }

    public PersonaDaoJDBC(Connection conexionTransaccional) {
        this.conecionTransaccional = conexionTransaccional;
    }

    public List<PersonaDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
        try {
            conn = this.conecionTransaccional != null ? this.conecionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id_persona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                persona = new PersonaDTO();
                persona.setId_persona(id_persona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setEmail(email);
                persona.setTelefono(telefono);
                personas.add(persona);

            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conecionTransaccional == null) {
                Conexion.close(conn);

            }

        }
        return personas;
    }

    public int insert(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conecionTransaccional != null ? this.conecionTransaccional : Conexion.getConnection();

            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            System.out.println("Ejecutando query" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);

        }  finally {

            Conexion.close(stmt);
            if (this.conecionTransaccional == null) {
                Conexion.close(conn);

            }
        }
        return rows;
    }

    public int update(PersonaDTO persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conecionTransaccional != null ? this.conecionTransaccional : Conexion.getConnection();

            System.out.println("Ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getId_persona());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizados: " + rows);

        }  finally {
            Conexion.close(stmt);
            if (this.conecionTransaccional == null) {
                Conexion.close(conn);

            }
        }
        return rows;
    }

    public int delete(PersonaDTO persona)throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = this.conecionTransaccional != null ? this.conecionTransaccional : Conexion.getConnection();

            System.out.println("Ejecutando query: " + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getId_persona());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados: " + rows);
        }  finally {
            Conexion.close(stmt);
            if (this.conecionTransaccional == null) {
                Conexion.close(conn);

            }
        }
        return rows;
    }

}
