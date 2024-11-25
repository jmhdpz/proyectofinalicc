package src.Excepciones;

public class ExcepcionCasillaInvalida extends Exception {
    public ExcepcionCasillaInvalida(){
        super("La casilla en la que se intenta colocar es invalida. Por favor volver a intentar.");
    }
}
