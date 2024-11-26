/**
 * Clase que modela un Cuadro mágico de 4x4.
 * @author José María Hernández Pérez y Aldo Enrique Yañez Ramirez
 * @version 1.0 
 * @date 27-Nov-2024 
 */

package src.Juegos;
import java.util.Random;
import src.Excepciones.*;

public class CuadroMagico {
    /**
     * Arreglo bidimensional representativo del tablero
     */
    protected int[][] cuadro;
    /**
     * Objeto random utilizado para generar los números iniciales
     */

    /**
     * Constructor por omisión. Crea un cuadro mágico con la primera fila, columna o diagonal generada aleatoriamente
     */
    public CuadroMagico(){
        cuadro = new int[4][4];
        Random rng = new Random();
        int fcd = rng.nextInt(3);
        boolean esValido = false;
        while(!esValido){
            switch (fcd) {
                case 0:
                    for (int i = 0 ; i < 3 ; i++){
                        do {
                            int candidatoAsignacion = rng.nextInt(1,16);
                            try{
                                if (!numeroColocado(candidatoAsignacion)) cuadro[0][i] = candidatoAsignacion;
                            } catch (ExcepcionNumeroInvalido e) {}
                        } while (cuadro[0][i] == 0);
                    }
                    cuadro[0][3] = 34 - cuadro[0][0] - cuadro[0][1] - cuadro[0][2];
                    if (cuadro[0][3] != cuadro[0][0] && cuadro[0][3] != cuadro[0][1] && cuadro[0][3] != cuadro[0][2] && cuadro[0][3] < 16 && cuadro[0][3] >	0) esValido = true;
                    break;
                case 1:
                    for (int i = 0 ; i < 3 ; i++){
                        do {
                            int candidatoAsignacion = rng.nextInt(1,16);
                            try{
                                if (!numeroColocado(candidatoAsignacion)) cuadro[i][0] = candidatoAsignacion;
                            } catch (ExcepcionNumeroInvalido e){}
                        } while (cuadro[i][0] == 0);
                    }
                    cuadro[3][0] = 34 - cuadro[0][0] - cuadro[1][0] - cuadro[2][0];
                    if (cuadro[3][0] != cuadro[0][0] && cuadro[3][0] != cuadro[1][0] && cuadro[3][0] != cuadro[2][0] && cuadro[3][0] < 16 && cuadro[3][0] >	0) esValido = true;
                    break;
                case 2:    
                    for (int i = 0 ; i < 3 ; i++){
                        do {
                            int candidatoAsignacion = rng.nextInt(1,16);
                            try{
                                if (!numeroColocado(candidatoAsignacion)) cuadro[i][i] = candidatoAsignacion;
                            } catch (ExcepcionNumeroInvalido e) {}
                        } while (cuadro[i][i] == 0);
                    }
                    cuadro[3][3] = 34 - cuadro[0][0] - cuadro[1][1] - cuadro[2][2];
                    if (cuadro[3][3] != cuadro[0][0] && cuadro[3][3] != cuadro[1][1] && cuadro[3][3] != cuadro[2][2] && cuadro[3][3] < 16 && cuadro[3][3] >	0) esValido = true;
                    break;
            }
            if (!esValido) cuadro = new int[4][4];
        }
    }

    /**
     * @param n - Número a evaluar.
     * @return boolean - Evalua si el número usado de parámetro se ha colocado ya, o no.
     * @throws ExcepcionNumeroInvalido - Si el número usado de argumento está fuera del rango 1-16
     */
    private boolean numeroColocado(int n) throws ExcepcionNumeroInvalido{
        if (n > 16 || n < 0) throw new ExcepcionNumeroInvalido();
        for (int i = 0 ; i < 4 ; i++){
            for (int j = 0 ; j < 4 ; j++){
                if (cuadro[i][j] == n) return true;
            }
        }
        return false;
    }

    /**
     * @param fila - La fila que se desea evaluar
     * @param columna - La columna en la que se desea evaluar
     * @return boolean - Evalúa si la casilla en la que se intenta colocar, ya está ocupada, o no.
     * @throws ExcepcionCasilaInvalida - Si la casilla usada de parámetro está fuero del rango (0,0), (3,3).
     */
    private boolean casillaOcupada(int fila, int columna) throws ExcepcionCasillaInvalida{
        if (fila < 0 || columna < 0 || fila > 3 || columna > 3) throw new ExcepcionCasillaInvalida();
        if (cuadro[fila][columna] != 0) return true;
        return false;
    }

    /**
     * @param fila - La fila en la que se desea colocar el número
     * @param columna - La columna en la que se desea colocar el número
     * @param numero - El número que se desea colocar 
     * @throws ExcepcionColocacionNoExitosa - Si el numero ya se ha usado, o la casilla no es valida.
     */
    public void colocarNumero(char columna,int fila, int numero) throws ExcepcionColocacionNoExitosa{
        int columnaNum = 5;
        fila--; 
        switch (columna) {
            case 'A':
                columnaNum = 0;
                break;
            case 'B':
                columnaNum = 1;
                break;
            case 'C':
                columnaNum = 2;
                break;
            case 'D':
                columnaNum = 3;
                break;
            default:
                throw new ExcepcionColocacionNoExitosa("La casilla ingresada no es valida");
        }
        try {
            if(!numeroColocado(numero)){
                try {
                    if (!casillaOcupada(fila, columnaNum)){
                        cuadro[fila][columnaNum] = numero;
                    }
                } catch (Exception e) {
                    throw new ExcepcionColocacionNoExitosa(e.getMessage());
                }
            }
        } catch (Exception e) {
            throw new ExcepcionColocacionNoExitosa(e.getMessage());
        }
    }

    @Override
    /**
     * @return String - Representación en cadena del cuadro mágico, para ser mostrado en terminal.
     */
    public String toString(){
        //No se me ocurrió una forma más eficiente de hacer este proceso. Unicamente esta asignando una variable por casilla a cada valor, para facilitar el return.
        String A1 = cuadro[3][0] < 10 ? "0"+cuadro[3][0] : Integer.toString(cuadro[3][0]);
        String A2 = cuadro[2][0] < 10 ? "0"+cuadro[2][0] : Integer.toString(cuadro[2][0]);
        String A3 = cuadro[1][0] < 10 ? "0"+cuadro[1][0] : Integer.toString(cuadro[1][0]);
        String A4 = cuadro[0][0] < 10 ? "0"+cuadro[0][0] : Integer.toString(cuadro[0][0]);
        String B1 = cuadro[3][1] < 10 ? "0"+cuadro[3][1] : Integer.toString(cuadro[3][1]);
        String B2 = cuadro[2][1] < 10 ? "0"+cuadro[2][1] : Integer.toString(cuadro[2][1]);
        String B3 = cuadro[1][1] < 10 ? "0"+cuadro[1][1] : Integer.toString(cuadro[1][1]);
        String B4 = cuadro[0][1] < 10 ? "0"+cuadro[0][1] : Integer.toString(cuadro[0][1]);
        String C1 = cuadro[3][2] < 10 ? "0"+cuadro[3][2] : Integer.toString(cuadro[3][2]);
        String C2 = cuadro[2][2] < 10 ? "0"+cuadro[2][2] : Integer.toString(cuadro[2][2]);
        String C3 = cuadro[1][2] < 10 ? "0"+cuadro[1][2] : Integer.toString(cuadro[1][2]);
        String C4 = cuadro[0][2] < 10 ? "0"+cuadro[0][2] : Integer.toString(cuadro[0][2]);
        String D1 = cuadro[3][3] < 10 ? "0"+cuadro[3][3] : Integer.toString(cuadro[3][3]);
        String D2 = cuadro[2][3] < 10 ? "0"+cuadro[2][3] : Integer.toString(cuadro[2][3]);
        String D3 = cuadro[1][3] < 10 ? "0"+cuadro[1][3] : Integer.toString(cuadro[1][3]);
        String D4 = cuadro[0][3] < 10 ? "0"+cuadro[0][3] : Integer.toString(cuadro[0][3]);
        return "  ---------------------\n"+
        "4 | "+A1+" | "+B1+" | "+C1+" | "+D1+" |\n"+
        "  ---------------------\n"+
        "3 | "+A2+" | "+B2+" | "+C2+" | "+D2+" |\n"+
        "  ---------------------\n"+
        "2 | "+A3+" | "+B3+" | "+C3+" | "+D3+" |\n"+
        "  ---------------------\n"+
        "1 | "+A4+" | "+B4+" | "+C4+" | "+D4+" |\n"+
        "  ---------------------\n"+
        "    A    B    C    D";
    }

    /**
     * @return boolean - true si el cuadro puede alcanzar una posición ganadora, false si no
     */
    public boolean distribucionValida(){
        for (int i = 0 ; i < 4 ; i++){
            if (cuadro[i][0] + cuadro[i][1] + cuadro[i][2] + cuadro[i][3] > 34) return false;
            if (cuadro[0][i] + cuadro[1][i] + cuadro[2][i] + cuadro[3][i] > 34) return false;
            for (int j = 16 ; j > 0 ; j--){
                try{
                    if (numeroColocado(j)){
                        if (cuadro[i][0] + cuadro[i][1] + cuadro[i][2] + cuadro[i][3] + j == 34 && cuadro[i][0] != j && cuadro[i][1] != j && cuadro[i][2] != j && cuadro[i][3] != j) return false;
                        if (cuadro[0][i] + cuadro[1][i] + cuadro[2][i] + cuadro[3][i] + j == 34 && cuadro[0][i] != j && cuadro[1][i] != j && cuadro[2][i] != j && cuadro[3][i] != j) return false;
                        if (cuadro[0][0] + cuadro[1][1] + cuadro[2][2] + cuadro[3][3] + j == 34 && cuadro[0][0] != j && cuadro[1][1] != j && cuadro[2][2] != j && cuadro[3][3] != j) return false;
                        if (cuadro[3][0] + cuadro[2][1] + cuadro[1][2] + cuadro[0][3] + j == 34 && cuadro[3][0] != j && cuadro[2][1] != j && cuadro[1][2] != j && cuadro[0][3] != j) return false;
                    }
                } catch (ExcepcionNumeroInvalido e){}
            }
        }
        if (cuadro [0][0] + cuadro[1][1] + cuadro[2][2] + cuadro[3][3] > 34) return false;
        if (cuadro [3][0] + cuadro[2][1] + cuadro[1][2] + cuadro[0][3] > 34) return false;
        return true;
    }

    /**
     * @return boolean - true si el cuadro es ganador, false si no lo es.
     */
    public boolean ganador(){
        for (int i = 0 ; i < 4 ; i++){
            if (cuadro[i][0] + cuadro[i][1] + cuadro[i][2] + cuadro[i][3] != 34) return false;
            if (cuadro[0][i] + cuadro[1][i] + cuadro[2][i] + cuadro[3][i] != 34) return false;
        }
        if (cuadro [0][0] + cuadro[1][1] + cuadro[2][2] + cuadro[3][3] != 34) return false;
        if (cuadro [3][0] + cuadro[2][1] + cuadro[1][2] + cuadro[0][3] != 34) return false;
        return true;
    }

    /**
     * @return boolean - true si el cuadro puede alcanzar una posición ganadora, o está en una. false si no.
     */
    public boolean juegoTerminado(){
        if (!distribucionValida()) return true;
        if (ganador()) return true;
        return false;
    }
}
