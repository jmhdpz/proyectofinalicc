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
        pasos = rng.nextInt(1,100);
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

    private boolean darPasos() {
        int acc = 0;
        int indice = 0;   
        while (acc < 99) { 
            int pasosAcc = pasos;
            while (pasosAcc > 0) {
                if (sillas[indice]) { 
                    pasosAcc--;  
                }
                indice = (indice + 1) % 100;
            }
            indice = (indice - 1 + 100) % 100;
            if (sillas[indice]) {
                sillas[indice] = false;
                acc++;
                System.out.println("La silla num: " + (indice + 1) + " ha sido eliminada");
            }
        }
        return verificarGanador(); 
    }
    
    private int buscarTrue(){
        if (!verificarGanador()) return 0;
        for (int i = 0 ; i < 100; i++){
            if (sillas[i]) return i;
        }
        return 0;
    }

    public boolean jugar(int n) throws ExcepcionSillaInvalida {
        if (n < 1 || n > 100) throw new ExcepcionSillaInvalida();
        while (!verificarGanador()) {
            darPasos();
        }
        return (n - 1) == buscarTrue(); 
    }
    
}
