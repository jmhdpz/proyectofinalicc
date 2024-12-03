package src.Excepciones;

public class ExcepcionMovimientoIlegal extends Exception {
    public ExcepcionMovimientoIlegal(){
        super("El movimiento que intentas ejecutar no es valido. Por favor intenta de nuevo.");
    }
}
