package test;

import datos.PersonaJDBC;
import domain.Persona;
import java.util.List;

public class ManejoPersonas {

    public static void main(String[] args) {

        PersonaJDBC personaJDBC = new PersonaJDBC();

        //Prueba SELECT
        List<Persona> personas = personaJDBC.select();
        for (Persona persona : personas) {
            System.out.println("Persona: " + persona);
            System.out.println("\n");
        }
        //Prueba insert
        //        Persona persona = new Persona();
        //        persona.setNombre("Pknuu");
        //        persona.setApellido("Rodriguez");
        //        persona.setEmail("Pknuu@ihih.com");
        //        persona.setTelefono("09090990");
        //
        //        personaJDBC.insert(persona);

        //Prueba update
        //        Persona persona=new Persona();
        //        persona.setId_persona(3);
        //        persona.setNombre("Rodrigo");
        //        persona.setApellido("Lara");
        //        persona.setEmail("MR@ijiji.com");
        //        persona.setTelefono("878787");
        //        
        //        personaJDBC.update(persona);

        //Eliminar un registro
        Persona persona =new Persona();
        persona.setId_persona(3);
        personaJDBC.delete(persona);

    }
}
