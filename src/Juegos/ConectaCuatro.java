package src.Juegos;

import src.Excepciones.ExcepcionColumnaInvalida;
import src.Excepciones.ExcepcionColumnaLlena;

public class ConectaCuatro {
    /**
     * Cadena utilizada para la representacion grafica del tablero
     */
    private final String AMARILLO = "\u001B[33m";
    /**
     * Cadena utilizada para la representacion grafica del tablero
     */
    private final String ROJO = "\u001B[31m";
    /**
     * Cadena utilizada para la representacion grafica del tablero
     */
    private final String RESET = "\u001B[0m";
    /**
     * Arreglo que representa el tablero de juego.
     */
    private char[][] tablero;

    public ConectaCuatro() {
        tablero = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    /**
     * Método que permite colocar una ficha roja en el tablero.
     * @param col - La columna en la que se intenta colocar.
     * @throws ExcepcionColumnaInvalida - Si la columna que se intenta ocupar esta fuera del rango 1-7.
     * @throws ExcepcionColumnaLlena - Si la columna que se intenta ocupar esta llena.
     */
    public void colocarRojo(int col) throws ExcepcionColumnaInvalida, ExcepcionColumnaLlena{
        if (--col < 0 || col > 6) throw new ExcepcionColumnaInvalida();
        for (int i = 5 ; i >= 0 ; i--){
            if (tablero[i][col] == ' '){
                tablero[i][col] = 'r';
                return;
            }
        }
        throw new ExcepcionColumnaLlena();
    }

    /**
     * Método que permite colocar una ficha amarilla en el tablero.
     * @param col - La columna en la que se intenta colocar.
     * @throws ExcepcionColumnaInvalida - Si la columna que se intenta ocupar esta fuera del rango 1-7.
     * @throws ExcepcionColumnaLlena - Si la columna que se intenta ocupar esta llena.
     */
    public void colocarAmarillo(int col) throws ExcepcionColumnaInvalida, ExcepcionColumnaLlena{
        if (--col < 0 || col > 6) throw new ExcepcionColumnaInvalida();
        for (int i = 5 ; i >= 0 ; i--){
            if (tablero[i][col] == ' '){
                tablero[i][col] = 'a';
                return;
            }
        }
        throw new ExcepcionColumnaLlena();
    }

    public boolean empate(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (tablero[i][j] == ' ') return false;
            }
        }
        return true;
    }

    /**
     * @return char - 'r' si gano el jugador con las fichas rojas. 'a' si gano el jugador con las fichas amarillas. ' ' en caso opuesto
     */
    public char ganador() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (tablero[i][j] != ' ' && tablero[i][j] == tablero[i][j + 1] && tablero[i][j] == tablero[i][j + 2] && tablero[i][j] == tablero[i][j + 3]){
                    return tablero[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (tablero[i][j] != ' ' && tablero[i][j] == tablero[i + 1][j] && tablero[i][j] == tablero[i + 2][j] && tablero[i][j] == tablero[i + 3][j]){
                    return tablero[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (tablero[i][j] != ' ' && tablero[i][j] == tablero[i + 1][j + 1] && tablero[i][j] == tablero[i + 2][j + 2] && tablero[i][j] == tablero[i + 3][j + 3]){
                    return tablero[i][j];
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                if (tablero[i][j] != ' ' && tablero[i][j] == tablero[i + 1][j - 1] && tablero[i][j] == tablero[i + 2][j - 2] && tablero[i][j] == tablero[i + 3][j - 3]){
                    return tablero[i][j];
                }
            }
        }
        return ' ';
    }

    /**
     * Metodo que genera una representacion en cadena del tablero.
     * @return - Representacion en cadena del tablero.
     */
    @Override
    public String toString() {
        String[][] tab = new String[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (tablero[i][j] == ' ') tab[i][j] = " ";
                if (tablero[i][j] == 'r') tab[i][j] = ROJO + "O" + RESET;
                if (tablero[i][j] == 'a') tab[i][j] = AMARILLO + "O" + RESET;
            }
        }
        String ret = "";
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                ret = ret + "|" + tab[i][j];
            }
            ret = ret + "|\n"; 
        }
        ret = ret + " 1 2 3 4 5 6 7";
        return ret;
    }

    
}

