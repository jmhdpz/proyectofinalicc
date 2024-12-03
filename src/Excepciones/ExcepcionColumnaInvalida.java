package src.Excepciones;

public class ExcepcionColumnaInvalida extends Exception {
    public ExcepcionColumnaInvalida(){
        super("La columna dada esta fuera del rango 1-7.");
    }
    
}
