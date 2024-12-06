import java.util.InputMismatchException;
import java.util.Scanner;
import src.Excepciones.*;
import src.Juegos.*;

public class DiaDos {
    @SuppressWarnings("resource") 
    public static void main(String[] werfwerf) {
        boolean run = true;
        Scanner in = new Scanner(System.in);
        while (run) {
            try {
                System.out.println("Para jugar, elige:\n1. Salvados\n2. Torres de Hanoi\n3. Salir");
                int eleccion = in.nextInt();
                switch (eleccion) {
                    case 1:
                        System.out.println("Bienvenido al juego de salvados. En este juego, tendras 100 sillas para escoger. Al inicio de la ronda se te dira el numero de pasos que se dara cada vez (es decir, si el numero de pasos es 3, la primera silla en eliminarse es 3, y la segunda es 6, y asi sucesivamente.) para eliminar sillas, y con esta informacion, deberas elegir una silla, intentando predecir la ultima silla en ser eliminada, es decir, la ganadora. Ten en cuenta que si este numero de pasos causa que se repitan las sillas eliminadas (Por ejemplo, 50 causaria que se elimine 50, luego 100, y luego de vuelta a 50), este se volvera a generar aleatoriamente, pero tu eleccion ya no podra cambiarse.\n\nSi adivinas la silla ganadora, obtendras 12 puntos. Si no, solo obtendras 2.");
                        Salvados sv = new Salvados();
                        boolean terminado = false;
                        boolean ganador = false;
                        do {
                            try {
                                sv.elegirPasos();
                                System.out.println("Elige la silla del 1 al 100 que creas que va a ganar.");
                                int silla = in.nextInt();
                                in.nextLine(); 
                                if (sv.jugar(silla)) {
                                    System.out.println("Felicidades, elegiste la silla ganadora.");
                                    ganador = true;
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
                        int puntosSV = ganador ? 12 : 2;
                        if (puntosSV == 12){
                            System.out.println("Se te otorgaran 12 puntos para este juego.");
                        } else { 
                            System.out.println("Se te otorgaran 2 puntos para este juego.");
                        }
                        break;
                    case 2:
                        System.out.println("Bienvenido al juego de Torres de Hanoi. En este juego tendras 6 discos en un poste, con su tamaño representado por numeros (siendo 6 el mas grande, y 1 el mas pequeño), y tu objetivo sera llevar a todos al ultimo poste. Debes tener en cuenta que no puedes colocar un disco sobre otro mas pequeño, asi que deberas pensar bien cada movimiento.\n\nSi logras conseguirlo en el minimo posible (63 movimientos), se te otorgaran 10 puntos.\nSi lo consigues con hasta 10 movimientos adicionales, se te otorgaran 5 puntos.\nEn caso contrario, se te otorgaran unicamente 2 puntos.");
                        TorresDeHanoi th = new TorresDeHanoi();
                        System.out.println(th);
                        while (!th.evaluarPosicion()){
                            try{ 
                                System.out.println("Elige el poste del que quieres mover un disco");
                                int de = in.nextInt();
                                System.out.println("Elige el poste al que quieres mover el disco");
                                int a = in.nextInt();
                                th.mover(de, a);
                                System.out.println(th);
                            } catch (InputMismatchException e){
                                System.out.println("La entrada que diste es invalida. Por favor reintenta tu movimiento.");
                                in.next();
                            } catch (ExcepcionPosteInvalido e){
                                System.out.println("Recuerda que los postes deben estar en el rango 1-3.");
                                in.next();
                            } catch (ExcepcionMovimientoIlegal e){
                                System.out.println("Recuerda que no puedes colocar un disco sobre otro mas pequeño que el.");
                                in.next();
                            }
                        }
                        System.out.println("Felicidades, conseguiste completar el juego en "+th.obtenerContador()+" movimientos.");
                        int puntosTH = th.obtenerContador() == 63 ? 10 : th.obtenerContador() <= 73 ? 5 : 2;
                        System.out.println("Por lo tanto, obtendras "+puntosTH+" puntos."); 
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
    }
}
