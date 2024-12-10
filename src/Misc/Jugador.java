package src.Misc;

import java.io.Serializable;

import src.Excepciones.ExcepcionBalanceNegativo;

public class Jugador implements Comparable<Jugador>, Serializable {
    /**
     * Nombre del jugador.
     */
    private String nombre;
    /**
     * Creditos restantes, utilizados para jugar.
     */
    private int creditos;
    /**
     * Puntos, utilizados para comparar con otros jugadores.    
     */
    private int puntos;
    
    /**
     * Constructor. Recibe el nombre como parametro, e inicializa con 0 puntos y 100 creditos.
     * @param Nombre - El nombre del jugador.
     */
    public Jugador (String nombre){
        this.nombre = nombre;
        puntos = 0;
        creditos = 100;
    }

    /**
     * @return int - El puntaje actual del jugador.
     */
    public int obtenerPuntos() { return puntos; }

    /**
     * @return String - El nombre del jugador
     */
    public String obtenerNombre() { return nombre; }

    /**
     * @param cr - Los creditos a cobrar del jugador.
     * @throws ExcepcionBalanceNegativo - Si no se puede sustraer esa cantidad de creditos
     */
    public void cobrarCreditos(int cr) throws ExcepcionBalanceNegativo { 
        if (cr > creditos) throw new ExcepcionBalanceNegativo(nombre);
        creditos -= cr; 
    }

    /**
     * @param pts - Los puntos que se desean agregar al jugador.
     */
    public void agregarPuntos(int pts) {
        puntos += pts; 
    }

    /**
     * Metodo utilizado para comparar jugadores entre si, respecto a su puntaje
     * @return int - 1 si el jugador usado tiene mayor puntaje que el parametro, -1 si es lo contrario. 0 si tienen la misma.
     */
    public int compareTo(Jugador p1){
        if (this.obtenerPuntos() > p1.obtenerPuntos()) return 1;
        if (p1.obtenerPuntos() > this.obtenerPuntos()) return -1;
        else return 0;
    }

    /**
     * @return String - Una cadena representando los atributos del jugador
     */
    @Override
    public String toString(){
        return "Jugador " + nombre + ": Puntos: " + puntos + " Creditos: "+creditos;
    }
}
