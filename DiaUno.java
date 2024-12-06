import java.util.InputMismatchException;
import java.util.Scanner;
import src.Excepciones.*;
import src.Juegos.*;

public class DiaUno {
    public static void main(String[] werfwerf) {
        boolean run = true;
        Scanner in = new Scanner(System.in);
        while (run) {
            try {
                System.out.println("Para jugar, elige:\n1. Cuadro magico\n2. Conecta 4\n3. Salir");
                int eleccion = in.nextInt();
                switch (eleccion) {
                    case 1:
                        System.out.println("Bienvenido al juego de cuadro magico. A continuacion se te presentara un cuadro de 4x4, con una fila, columna o diagonal ya generada. Tu objetivo es hacer que todas las filas, columnas y diagonales del cuadro sumen la misma cantidad, colocando un numero entre 1 y 16 a la vez. No puedes repetir numeros, ni moverlos una vez que los coloques. Por lo tanto, deberas pensar bien tus movimientos antes de llevarlos a cabo.\n\nSi ganas, obtendras 10 puntos.");
                        CuadroMagico cm = new CuadroMagico();
                        while (!cm.juegoTerminado()) {
                            try {
                                in = null;
                                in = new Scanner(System.in);
                                //TODO: elegir jugador
                                System.out.println(cm);
                                System.out.println("Introduce la columna donde deseas colocar un numero.");
                                char col = in.nextLine().toUpperCase().charAt(0);
                                System.out.println("Introduce la fila donde deseas introducir un numero.");
                                int fila = in.nextInt();
                                System.out.println("Introduce el numero que deseas colocar.");
                                int num = in.nextInt();
                                in.nextLine(); 
                                cm.colocarNumero(col, fila, num);
                            } catch (InputMismatchException e) {
                                System.out.println("El valor que introdujiste no es valido. Intenta de nuevo.");
                                in.nextLine(); 
                            } catch (ExcepcionColocacionNoExitosa e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        int puntosCM = 0;
                        if (cm.ganador()) { 
                            System.out.println("Felicidades, lograste completar el cuadro correctamente."); 
                            puntosCM = 10;
                            System.out.println("Se te otorgaran 10 puntos por este juego.");
                        } else {
                            System.out.println("Lamentablemente, tu cuadro no puede llegar a una posicion ganadora. Ahora volveras al menu.");
                        }
                        break;
                    case 2:
                        //TODO: elegir jugadores
                        System.out.println("Bienvenido al juego de conecta cuatro. En este, se requiere tener dos jugadores, uno con las fichas rojas y otro con las fichas amarillas. Al colocar una ficha, esta caera hasta el fondo del tablero, hasta donde sea posible. Para ganar, uno de los dos jugadores debera lograr conectar 4 fichas ya sea vertical, horizontal, o diagonalmente. Si el tablero se llena antes de llegar a una posicion ganadora, el juego se considerara un empate.\n\nEl ganador obtendra 10 puntos, y el perdedor obtendra 2. En caso de empate, ambos jugadores recibiran 5 puntos.");
                        ConectaCuatro c4 = new ConectaCuatro();
                        System.out.println(c4);
                        while (c4.ganador() == ' '){
                            System.out.println("Jugador uno. Elige la columna en la que vas a colocar una ficha: ");
                            boolean turnoUno = false;
                            int uno = 0;
                            do {
                                try {
                                    uno = in.nextInt();
                                    c4.colocarRojo(uno);
                                    turnoUno = true;
                                } catch (ExcepcionColumnaInvalida e){
                                    System.out.println("Las columnas solo pueden estar en el rango 1-7.");
                                    in.nextLine();
                                } catch (ExcepcionColumnaLlena e) {
                                    System.out.println("La columna en la que intentas colocar esta llena. Vuelve a intentar.");
                                    in.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Tu entrada no fue valida. Vuelve a intentar.");
                                    in.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Hubo un error inesperado. Por favor, vuelve a intentar.");
                                    in.nextLine();
                                }
                            } while (!turnoUno);
                            System.out.println(c4);
                            System.out.println("Jugador dos. Elige la columna en la que vas a colocar una ficha: ");
                            boolean turnoDos = false;
                            int dos = 0;
                            do {
                                try {
                                    dos = in.nextInt();
                                    turnoDos = true;
                                    c4.colocarAmarillo(dos);
                                } catch (ExcepcionColumnaInvalida e){
                                    System.out.println("Las columnas solo pueden estar en el rango 1-7.");
                                    in.nextLine();
                                } catch (ExcepcionColumnaLlena e) {
                                    System.out.println("La columna en la que intentas colocar esta llena. Vuelve a intentar.");
                                    in.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Tu entrada no fue valida. Vuelve a intentar.");
                                    in.nextLine();
                                } catch (Exception e) {
                                    System.out.println("Hubo un error inesperado. Por favor, vuelve a intentar.");
                                    in.nextLine();
                                }
                            } while (!turnoDos);
                            System.out.println(c4);
                        }
                        if (c4.ganador() == 'e') {
                            System.out.println("El juego es un empate.");
                        } else {
                            System.out.println("El ganador es: "+(c4.ganador() == 'r' ? "rojo":"amarillo") );
                        }
                        break;
                    case 3:
                        run = false;
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("El valor que introdujiste no es valido.");
                in.next();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    in.close();
    }
}
