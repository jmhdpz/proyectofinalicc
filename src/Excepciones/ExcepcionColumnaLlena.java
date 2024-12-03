package src.Excepciones;

public class ExcepcionColumnaLlena extends Exception {
    public ExcepcionColumnaLlena(){
        super("La columna en la que intentas colocar esta llena. Por favor intenta de nuevo.");
    }
}
