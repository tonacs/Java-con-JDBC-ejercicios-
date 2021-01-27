package test;

import datos.UsuarioJDBC;
import domain.Usuario;
import java.util.List;

public class ManejoUsuarios {
    public static void main(String[] args) {
        UsuarioJDBC usuarioJDBC=new UsuarioJDBC();
        
//Ejecutar el listado de usuarios 
    List<Usuario> usuarios=usuarioJDBC.select();
    for(Usuario usuario:usuarios){
        System.out.println("Usuario: "+usuario);
    }
        
    //Insertamos un nuevo usuario
//    Usuario usuario=new Usuario("Carlos.Juarez","123");
//    usuarioJDBC.insert(usuario);


       //Modificamos un usuario existente
//       Usuario usuario=new Usuario(3,"carlosSSS","456");
//       usuarioJDBC.update(usuario);
        
        usuarioJDBC.delete(new Usuario(3));
    
    
        
    }
}
