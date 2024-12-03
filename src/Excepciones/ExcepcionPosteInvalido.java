package src.Excepciones;

public class ExcepcionPosteInvalido extends Exception {
    public ExcepcionPosteInvalido(){
        super("Los postes solo pueden estar en el rango 1-3.");
    }
}
