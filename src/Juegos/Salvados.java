/**
 * Clase que modela un juego de Salvados.
 * @author José María Hernández Pérez y Aldo Enrique Yañez Ramirez
 * @version 1.0 
 * @date 27-Nov-2024 
 */
package src.Juegos;
import src.Excepciones.*;
import java.util.Random;

public class Salvados {
    /**
     * Representa la cantidad de pasos que se recorrerán en cada salto
     */
    private int pasos;
    /**
     * Arreglo para representar cada una de las sillas. False significa que la silla es perdedora. True, que es ganadora.
     */
    public boolean[] sillas = new boolean [100];

    /**
     * Constructor por omisión. Inicializa un juego de salvados
     */
    public Salvados(){
        pasos = 0;
        for (int i = 0; i < 100; i++) {
            sillas[i] = true;
        }
    }

    /**
     * Método que asigna un valor aleatorio entre 1 y 100 al número de pasos
     */
    public void elegirPasos(){
        Random rng = new Random();
        int mcd = 1;
        do {
            pasos = rng.nextInt(1,100);
            for (int i = 1; i <= pasos && i <= 100; i++) {
                if (pasos % i == 0 && 100 % i == 0) mcd = i;
            }
        } while (mcd != 1);
        System.out.println("El numero de pasos para esta ronda sera: "+pasos+".");
    }

    /**
     * Método que permite verificar que quede solo un "true" en sillas
     */
    private boolean verificarGanador(){
        int acc = 0;
        for (boolean b : sillas) {
            if (!b) acc++;
        }
        if (acc == 99) return true;
        return false;
    }

    /**
     * Método encargado de dar los pasos para el juego
     * @return boolean - true si ya hay 99 sillas eliminadas. false en caso contrario
     */
    private boolean darPasos() {
        int acc = 0;
        int indice = 0;
        sillas[0] = false;
        System.out.println("1. La silla num: 1 ha sido eliminada");
        while (acc != 98) {
            indice += pasos;
            if (indice > 99) indice -= 100;
            sillas[indice] = false;
            acc++;
            System.out.println((acc+1)+". La silla num: " + (indice + 1) + " ha sido eliminada");
        }
        return verificarGanador();
    }
        
    /**
     * @return int - El indice de la silla ganadora. 0 si no se ha encontrado.
     */
    public int buscarTrue(){
        if (!verificarGanador()) return 0;
        int ganador = 0;
        for (int i = 0 ; i < 100; i++){
            if (sillas[i]) ganador = i;
        }
        return ganador;
    }

    /**
     * Método principal de la clase. Ejecuta la secuencia necesaria para jugar (menos el elegirPasos, para que se pueda llamar antes de elegir el número)
     * @param n - Silla a elegir
     * @return boolean - true si la silla elegida es ganadora, false en caso opuesto
     * @throws ExcepcionSillaInvalida - Si la silla está fuera del rango 1-100
     */
    public boolean jugar(int n) throws ExcepcionSillaInvalida {
        if (n < 1 || n > 100) throw new ExcepcionSillaInvalida();
        darPasos();
        return (n - 1) == buscarTrue(); 
    }   
}
