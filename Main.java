import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import src.Misc.*;
public class Main {
    public static void main(String[] werfwerf) throws IOException {
        Scanner in = new Scanner(System.in); 
        ObjectInputStream lector = null;
        try {
            lector = new ObjectInputStream(new FileInputStream("jugadores.txt"));
            Jugador[] jugadores = null;
        try {
            jugadores = (Jugador[]) lector.readObject();
        } catch (Exception e) {}
        if (jugadores == null) jugadores = new Jugador[100];
        int numJugadores = 0;
        for (int i = 0 ; i < 100; i++) {
            if (jugadores[i] != null) numJugadores++;
        }

        int dia = 1;
        while (dia < 3){
            System.out.println("Bienvenido a la feria. Actualmente es el dia "+dia+". Elige una opcion:\n1. Registrar jugador.\n2. Ver mejores jugadores.\n3. Jugar. Juegos disponibles hoy: "+(dia == 1 ? "Cuadro magico (1 jugador), Conecta 4 (2 jugadores)." : "Salvados (1 jugador), Torres de Hanoi (1 jugador).")+"\n4. Ver a un jugador especifico.\n5. Avanzar dia\n6. Guardar y salir."); 
            try {
                int eleccion = in.nextInt();
                in.nextLine();
                switch (eleccion) {
                    case 1:
                        System.out.println("Como se llamara el jugador?");
                        boolean listo = false;
                        String nombre = null;
                        boolean nombreRegistrado = false;  
                        while (!listo) {
                            try {
                                nombre = in.nextLine();
                                nombreRegistrado = false; 
                                for (Jugador j : jugadores) {
                                    if (j != null && j.obtenerNombre().equals(nombre)) {
                                    nombreRegistrado = true;  
                                    System.out.println("Ya hay un jugador registrado con ese nombre. Regresaras al menu.");
                                    break; 
                                    }
                                }
                                listo = true;
                            } catch (Exception e) {
                                System.out.println("Hubo un error con tu entrada. Por favor intenta de nuevo");
                            }
                        }
                    if (!nombreRegistrado) {
                        if (nombre != null){
                            jugadores[numJugadores] = new Jugador(nombre);
                            numJugadores++;
                        }
                    }
                    break;
                    case 2:
                        try {
                            Jugador[] jugadoresTemp = new Jugador[numJugadores];
                            for (int i = 0 ; i < numJugadores ; i++){
                                jugadoresTemp[i] = jugadores[i];
                            }
                            OrdenamientoGenerico<Jugador> ordenamientoJugadores = new OrdenamientoGenerico<>(jugadoresTemp);
                            jugadoresTemp = ordenamientoJugadores.ordenar();
                            System.out.println("---------------------------------\nLos mejores jugadores son:");
                            for (int i = numJugadores - 1 ; i >= 0 ; i--){
                                if (numJugadores - i < 4) System.out.println("\n"+(numJugadores - i) + ". "+jugadoresTemp[i]);
                            }                            
                            System.out.println("---------------------------------");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Ocurrio un error inesperado.");
                        }
                        break;
                    case 3:
                        if (dia == 1){
                            DiaUno.main(werfwerf);
                        } else {
                            DiaDos.main(werfwerf);
                        }
                        break;
                    case 4:
                        String nombreBuscar = null;
                        boolean busqueda = false;
                        System.out.println("Introduce el nombre del jugador que deseas buscar: ");
                        while (!busqueda){
                            try {
                                nombreBuscar = in.nextLine();
                                busqueda = true;
                            } catch (Exception e) {
                                System.out.println("Hubo un error con tu entrada. Intenta de nuevo");
                            }
                        }
                        boolean encontrado = false;
                        for (int i = 0 ; i < numJugadores ; i++){
                            if (nombreBuscar == jugadores[i].obtenerNombre()){ 
                                System.out.println(jugadores[i]);
                                encontrado = true;
                            }
                        }
                        if (!encontrado) System.out.println("No se pudo encontrar el jugador que buscas.");
                        break;
                    case 5:
                        dia++;
                        break;
                    case 6:
                        ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream ("jugadores.txt"));
                        escritor.writeObject(jugadores);
                        return;
                    default:
                        System.out.println("Introduce un valor valido.");
                        in.nextLine();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduce un valor valido.");
                in.nextLine();
            }
        }
        in.close();
        if (lector != null) lector.close();
        } catch (Exception e) {}
    }
}
