package src.Excepciones;

public class ExcepcionBalanceNegativo extends Exception {
    public ExcepcionBalanceNegativo(String nombre){
        super(nombre+" no tiene suficientes creditos para jugar.");
    }
}
