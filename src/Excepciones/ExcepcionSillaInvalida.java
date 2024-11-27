package src.Excepciones;

public class ExcepcionSillaInvalida extends Exception {
    public ExcepcionSillaInvalida(){
        super("La silla que intentaste elegir esta fuera del rango. Por favor intenta de nuevo");
    }
}
