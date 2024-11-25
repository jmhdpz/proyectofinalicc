package src.Excepciones;

public class ExcepcionColocacionNoExitosa extends Exception{
    public ExcepcionColocacionNoExitosa(String mensaje){
        super("No se pudo colocar el numero:\n" +mensaje);
    }
}
