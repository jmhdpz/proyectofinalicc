package src.Excepciones;

public class ExcepcionNumeroInvalido extends Exception {
    public ExcepcionNumeroInvalido(){
        super("El numero ya fue colocado, o esta fuera del rango permitido. Verifica tu entrada e intenta de nuevo.");
    }
}
