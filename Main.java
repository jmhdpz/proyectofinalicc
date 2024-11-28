import src.Excepciones.ExcepcionColocacionNoExitosa;
import src.Excepciones.ExcepcionSillaInvalida;
import src.Juegos.CuadroMagico;
import src.Juegos.Salvados;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] werfwerf) {
        Scanner in = new Scanner(System.in); 
        boolean run = true;
        System.out.println("Bienvenido.\nEste es un menu provisional, para probar los dos juegos funcionales actualmente. En el README se puede ver el avance que llevamos.");
        
        while (run) {
            System.out.println("Por favor, elige cual de los juegos quieres probar:\n1. Cuadro magico\n2. Salvados.\n3. Cerrar.");
            try {
                int eleccion = in.nextInt();
                in.nextLine(); 
                switch (eleccion) {
                    case 1:
                        System.out.println("Bienvenido al juego de cuadro magico. A continuacion se te presentara un cuadro de 4x4, con una fila, columna o diagonal ya generada. Tu objetivo es hacer que todas las filas, columnas y diagonales del cuadro sumen la misma cantidad, colocando un numero entre 1 y 16 a la vez. No puedes repetir numeros, ni moverlos una vez que los coloques. Por lo tanto, deberas pensar bien tus movimientos antes de llevarlos a cabo.");
                        CuadroMagico cm = new CuadroMagico();
                        while (!cm.juegoTerminado()) {
                            try {
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
                                System.out.println("Input inválido. Intenta de nuevo.");
                                in.nextLine(); 
                            } catch (ExcepcionColocacionNoExitosa e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        if (cm.ganador()) { 
                            System.out.println("Felicidades, lograste completar el cuadro correctamente."); 
                        } else {
                            System.out.println("Lamentablemente, tu cuadro no puede llegar a una posicion ganadora. Ahora volveras al menu.");
                        }
                        break;
                    case 2:
                        System.out.println("Bienvenido al juego de salvados. En este juego, tendras 100 sillas para escoger. Al inicio de la ronda se te dira el numero de pasos que se dara cada vez (es decir, si el numero de pasos es 3, la primera silla en eliminarse es 3, y la segunda es 6, y asi sucesivamente.) para eliminar sillas, y con esta informacion, deberas elegir una silla, intentando predecir la ultima silla en ser eliminada, es decir, la ganadora. Ten en cuenta que si este numero de pasos causa que se repitan las sillas eliminadas (Por ejemplo, 50 causaria que se elimine 50, luego 100, y luego de vuelta a 50), este se volvera a generar aleatoriamente, pero tu eleccion ya no podra cambiarse.");
                        Salvados sv = new Salvados();
                        boolean terminado = false;
                        do {
                            try {
                                sv.elegirPasos();
                                System.out.println("Elige la silla del 1 al 100 que creas que va a ganar.");
                                int silla = in.nextInt();
                                in.nextLine(); 
                                if (sv.jugar(silla)) {
                                    System.out.println("Felicidades, elegiste la silla ganadora.");
                                } else { 
                                    System.out.println("Has elegido la silla incorrecta. La silla ganadora era la numero " + sv.buscarTrue());
                                }
                                terminado = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Tu eleccion no es valida. El juego se reiniciara, por favor intenta de nuevo.");
                                in.nextLine(); 
                            } catch (ExcepcionSillaInvalida e) {
                                System.out.println("Recuerda que la silla a elegir debe estar entre 1 y 100. El juego se reiniciara, por favor intenta de nuevo.");
                            }
                        } while (!terminado);
                        break;
                    case 3:
                        System.out.println("Hasta luego.");
                        run = false;
                        break;
                    default:
                        System.out.println("Introduce un valor valido.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduce un valor valido.");
                in.nextLine(); 
            }
        }
        in.close(); 
    }
}
