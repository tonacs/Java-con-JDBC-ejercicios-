package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;



public class Conexion {
    /**
     * Este es un comentario de JavaDoc
     */
    //Definir las variables para conectarnos a la base de datos
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "AQUÍ VA TU USUARIO";
    private static final String JDBC_PASSWORD = "AQUÍ VA TU CONTRASEÑA";
    
    public static DataSource getDataSource(){
        BasicDataSource ds=new BasicDataSource();
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASSWORD);
        //Definimos el tamaño incial del pool de conexiones
        ds.setInitialSize(5);
        return ds;
        
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();

    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        
    }
    
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
