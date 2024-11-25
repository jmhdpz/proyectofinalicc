package src.Excepciones;

public class ExcepcionNumeroInvalido extends Exception {
    public ExcepcionNumeroInvalido(){
        super("El numero ya fue colocado. Por favor intenta de nuevo.");
    }
}
