package src.Juegos;
import src.Excepciones.*;
/**
* Clase que modela un juego de Torres de Hanoi de 6 discos.
* @author José María Hernández Pérez y Aldo Enrique Yañez Ramirez
* @version 1.0 
* @date 15-Nov-2024 
*/
public class TorresDeHanoi {
    /**
     * Representacion en arreglos de las torres
     */
    private int[][] torres;
    /**
     * Contador de movimientos
     */
    private int acc;

    /**
     * Constructor por omisión. Inicializa un juego de torres de Hanoi en su estado inicial (todos los discos en el primer poste)
     */
    public TorresDeHanoi (){
        torres = new int[6][3];
        torres[5][0] = 6;
        torres[4][0] = 5;
        torres[3][0] = 4;
        torres[2][0] = 3;
        torres[1][0] = 2;
        torres[0][0] = 1;
        acc = 0;
    }

    /**
     * @param de - Poste origen del movimiento
     * @param a - Poste destino del movimiento
     * @throws ExcepcionPosteInvalido - Si el poste objetivo esta fuera del rango 1-3
     * @throws ExcepcionMovimientoIlegal - Si el movimiento no se puede completar
     */
    public void mover(int de, int a) throws ExcepcionPosteInvalido, ExcepcionMovimientoIlegal {
        de--; a--; 
        try {
            if (!movimientoValido(de, a)) throw new ExcepcionMovimientoIlegal();
        } catch (ExcepcionPosteInvalido e) {
            throw new ExcepcionMovimientoIlegal();
        }
        int discoMover = 0;
        for (int i = 0; i < 6; i++) { 
            if (torres[i][de] != 0) {
                discoMover = torres[i][de]; 
                torres[i][de] = 0;
                break;
            }
        }
        for (int i = 5; i >= 0; i--) { 
            if (torres[i][a] == 0) {   
                torres[i][a] = discoMover; 
                acc++;                     
                return;
            }
        }
        throw new ExcepcionMovimientoIlegal(); 
    }

    /**
     * @param de - Poste de origen del movimiento
     * @param a - Poste destino del movimiento
     * @return boolean - true si el movimiento es valido, false si no lo es
     * @throws ExcepcionPosteInvalido - Si alguno de los parametros esta fuera del rango 1-3.   
     */
    private boolean movimientoValido(int de, int a) throws ExcepcionPosteInvalido {
        if (a < 0 || de < 0 || de > 2 || a > 2) throw new ExcepcionPosteInvalido();
        int discoObjetivo = 0; 
        for (int i = 5; i >= 0; i--) {
            if (torres[i][a] != 0) {
                discoObjetivo = torres[i][a];
                break;
            }
        }
        int discoMover = 0;
        for (int i = 0; i < 6; i++) {
            if (torres[i][de] != 0) { 
                discoMover = torres[i][de];
                break;
            }
        }
        if (discoMover == 0) return false;
        if (discoObjetivo == 0) return true;
        if (discoMover < discoObjetivo) return true;
        return false;
    }

    public boolean evaluarPosicion(){
        for (int i = 0 ; i < 6 ; i++){
            for (int j = 0 ; j < 2 ; j++){
                if (torres[i][j] != 0) return false;
            }
        }
        for (int i = 1 ; i < 6 ; i++){
            if (torres[i-1][2] != i) return false;
        }
        return true;
    }

    /**
     * @return int - La cantidad de movimientos realizados hasta el momento.
     */
    public int obtenerContador(){ return acc; }

    /**
     * @return Una cadena que representa el juego en su estado actual.
     */
    @Override
    public String toString(){
        String[][] tab = new String[6][3];
        for (int i = 0 ; i < 6 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                tab[i][j] = (torres[i][j] == 0) ? "|" : Integer.toString(torres[i][j]) ;
            }
        }
        return " | | |\n "+tab[0][0]+" "+tab[0][1]+ " "+tab[0][2]+"\n "+tab[1][0]+" "+tab[1][1]+" "+tab[1][2]+"\n "+tab[2][0]+" "+tab[2][1]+ " "+tab[2][2]+"\n "+tab[3][0]+" "+tab[3][1]+ " "+tab[3][2]+"\n "+tab[4][0]+" "+tab[4][1]+ " "+tab[4][2]+"\n "+tab[5][0]+" "+tab[5][1]+ " "+tab[5][2]+"\nMovimientos realizados: "+acc;
    }
}
