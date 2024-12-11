import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import src.Excepciones.*;
import src.Juegos.*;
import src.Misc.*;
public class Main {
    public static void main(String[] werfwerf) {
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
        ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream ("jugadores.txt"));

        int dia = 1;
        while (dia < 3){
            for (int i = 0 ; i < 100; i++) {
                if (jugadores[i] != null) numJugadores++;
            }
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
                                nombre = in.nextLine().trim();
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
                            if (numJugadores == 0) {
                                System.out.println("Actualmente no hay jugadores registrados");
                            } else {
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
                            }
                        } catch (Exception e) {
                            System.out.println("Ocurrio un error inesperado.");
                        }
                        break;
                    case 3:
                        if (dia == 1){
                            boolean run1 = true;
                            while (run1) {
                                try {
                                    System.out.println("Para jugar, elige:\n1. Cuadro magico\n2. Conecta 4\n3. Salir");
                                    int eleccion1 = in.nextInt();
                                    in.nextLine();
                                    switch (eleccion1) {
                                        case 1:
                                            System.out.println("Bienvenido al juego de Cuadro Magico. Se te presentara un cuadro de 4x4 con numeros del 1 al 16 generados en una fila, columna o diagonal. Tu mision sera colocar numeros en el resto del cuadro de modo que todas las filas, columnas y diagonales sumen lo mismo. Solo puedes usar numeros del 1 al 16, y no podras repetirlos, ni mover los ya colocados en ningun momento, por lo que cada movimientos sera crucial.\n\n Este juego cuesta 15 creditos.\nSi logras completar el cuadro, obtendras 10 puntos.");
                                            System.out.println("Quien va a jugar? Introduce el nombre del jugador.");
                                            boolean booleanoCM = false;
                                            String nombreCM = null;
                                            while (!booleanoCM) {
                                                try {
                                                    nombreCM = in.nextLine().trim();
                                                    booleanoCM = true;
                                                } catch (Exception e) {
                                                    System.out.println("Hubo un error con tu entrada. Por favor intenta de nuevo.");
                                                }
                                            }
                                            int jugadorCM = -1;
                                            for (int i = 0; i < numJugadores; i++) {
                                                if (jugadores[i].obtenerNombre().equals(nombreCM)) {
                                                    jugadorCM = i;
                                                    break;
                                                }
                                            }
                                            if (jugadorCM == -1) {
                                                System.out.println("No se encontro un jugador con ese nombre.");
                                                break;
                                            }
                                            try {
                                                jugadores[jugadorCM].cobrarCreditos(15);
                                            } catch (ExcepcionBalanceNegativo e) {
                                                System.out.println(e.getMessage());
                                                break;
                                            }  
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
                                                } catch (Exception e) {
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                            if (cm.ganador()) {
                                                System.out.println("Felicidades, lograste completar el cuadro correctamente.");
                                                jugadores[jugadorCM].agregarPuntos(10);
                                            } else {
                                                System.out.println("No se logro completar el cuadro, pues la disposicion actual no permite llegar a un cuadro valido.\n"+cm+"\nNo se te otorgaran puntos.");
                                            }
                                            break;
                                        case 2:
                                            if (numJugadores < 2){
                                                System.out.println("Para este juego se necesitan minimo dos jugadores. Intenta registrar mas.");
                                                break;
                                            }
                                            System.out.println("Bienvenidos al juego de Conecta Cuatro. En este juego se alternaran turnos para colocar fichas en las columnas que caeran hasta donde les sea posible. El objetivo es lograr conectar 4 fichas en fila, columna o diagonal.\n\nEste juego cuesta 15 creditos.\nEl ganador obtendra 10 puntos, y el perdedor 2. En caso de empate, se le otorgaran 5 puntos a cada uno.");
                                            System.out.println("Jugador uno. Introduce tu nombre:");
                                            String nombreJ1 = null;
                                            boolean booleanoJ1 = false;
                                            while (!booleanoJ1){
                                                try {
                                                nombreJ1 = in.nextLine().trim();
                                                booleanoJ1 = true;
                                                } catch (Exception e) {
                                                    System.out.println("Hubo un problema con tu entrada. Por favor intenta de nuevo.");
                                                }
                                            }
                                            int jugador1 = -1;
                                            for (int i = 0; i < numJugadores; i++) {
                                                if (jugadores[i].obtenerNombre().equals(nombreJ1)) {
                                                    jugador1 = i;
                                                    break;
                                                }
                                            }
                                            if (jugador1 == -1) {
                                                System.out.println("No se encontro un jugador con ese nombre.");
                                                break;
                                            }
                                            System.out.println("Jugador dos. Introduce tu nombre:");
                                            String nombreJ2 = null;
                                            boolean booleanoJ2 = false;
                                            while (!booleanoJ2){
                                                try {
                                                    nombreJ2 = in.nextLine().trim();
                                                    if (nombreJ2.equals(nombreJ1)) throw new IllegalArgumentException();
                                                    booleanoJ2 = true;
                                                } catch (IllegalArgumentException e) {
                                                    System.out.println("No puedes jugar contra ti mismo. Por favor intenta de nuevo.");
                                                } catch (Exception e) {
                                                    System.out.println("Hubo un problema con tu entrada. Por favor intenta de nuevo.");
                                                }
                                            }
                                            int jugador2 = -1;
                                            for (int i = 0; i < numJugadores; i++) {
                                                if (jugadores[i].obtenerNombre().equals(nombreJ2)) {
                                                    jugador2 = i;
                                                    break;
                                                }
                                            }
                                            if (jugador2 == -1) {
                                                System.out.println("No se encontro un jugador con ese nombre.");
                                                break;
                                            }
                                            try {
                                                jugadores[jugador1].cobrarCreditos(8);
                                                jugadores[jugador2].cobrarCreditos(8);
                                            } catch (ExcepcionBalanceNegativo e) {
                                                System.out.println(e.getMessage());
                                                break;
                                            }
                                            ConectaCuatro c4 = new ConectaCuatro();
                                            while (c4.ganador() == ' ') {
                                                try {
                                                    System.out.println(c4);
                                                    System.out.println("Jugador uno. Elige la columna donde deseas colocar una ficha:");
                                                    c4.colocarRojo(in.nextInt());
                                                    System.out.println(c4);
                                                    if (c4.ganador() != ' ') break;
                                                    System.out.println("Jugador dos. Elige la columna donde deseas colocar una ficha:");
                                                    c4.colocarAmarillo(in.nextInt());
                                                } catch (Exception e) {
                                                    System.out.println(e.getMessage());
                                                    in.nextLine().trim();
                                                }
                                            }
                                            if (c4.ganador() == 'r') {
                                                System.out.println("El ganador es "+jugadores[jugador1].obtenerNombre());
                                                jugadores[jugador1].agregarPuntos(10);
                                                jugadores[jugador2].agregarPuntos(2);
                                            } else if (c4.ganador() == 'a') {
                                                System.out.println("El ganador es "+jugadores[jugador2].obtenerNombre());
                                                jugadores[jugador2].agregarPuntos(10);
                                                jugadores[jugador1].agregarPuntos(2);
                                            } else {
                                                System.out.println("El juego termino en empate.");
                                                jugadores[jugador1].agregarPuntos(5);
                                                jugadores[jugador2].agregarPuntos(5);
                                            }
                                            break;
                                        case 3:
                                            run1 = false;
                                            break;
                                        default:
                                            System.out.println("Opcion no valida.");
                                            break;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Introduce un valor valido.");
                                    in.nextLine();
                                }
                            }
                        } else {
                            boolean run2 = true;
                            while (run2) {
                                try {
                                    System.out.println("Para jugar, elige:\n1. Salvados\n2. Torres de Hanoi\n3. Salir");
                                    int eleccion2 = in.nextInt();
                                    in.nextLine();
                                    switch (eleccion2) {
                                        case 1:
                                            System.out.println("Bienvenido al juego de salvados. En este juego, tendras 100 sillas para escoger. Al inicio de la ronda se te dira el numero de pasos que se dara cada vez (es decir, si el numero de pasos es 3, la primera silla en eliminarse es 3, y la segunda es 6, y asi sucesivamente.) para eliminar sillas, y con esta informacion, deberas elegir una silla, intentando predecir la ultima silla en ser eliminada, es decir, la ganadora. Ten en cuenta que si este numero de pasos causa que se repitan las sillas eliminadas (Por ejemplo, 50 causaria que se elimine 50, luego 100, y luego de vuelta a 50), este se volvera a generar aleatoriamente, pero tu eleccion ya no podra cambiarse.\n\nEste juego cuesta 15 creditos.\nSi adivinas la silla ganadora, obtendras 12 puntos. Si no, solo obtendras 2.");
                                            Salvados sv = new Salvados();   
                                            boolean terminado = false;
                                            boolean ganador = false;
                                            System.out.println("Quien va a jugar? Introduce el nombre del jugador.");
                                            String nombreSV = null;
                                            boolean booleanoSV = false;
                                            while (!booleanoSV){
                                                try {
                                                    nombreSV = in.nextLine().trim();
                                                    booleanoSV = true;
                                                } catch (Exception e) {
                                                    System.out.println("Hubo un error con tu entrada. Vuelve a intentar");
                                                }
                                            }
                                            int jugadorSV = -1;
                                            for (int i = 0; i < numJugadores; i++) {
                                                if (jugadores[i].obtenerNombre().equals(nombreSV)) {
                                                    jugadorSV = i;
                                                    break;
                                                }
                                            }
                                            if (jugadorSV == -1) {
                                                System.out.println("No se encontro un jugador con ese nombre.");
                                                break;
                                            }
                                            try {
                                                jugadores[jugadorSV].cobrarCreditos(15);
                                            } catch (ExcepcionBalanceNegativo e) {
                                                System.out.println(e.getMessage());
                                                break;
                                            }
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
                                                jugadores[jugadorSV].agregarPuntos(12);
                                            } else { 
                                                System.out.println("Se te otorgaran 2 puntos para este juego.");
                                                jugadores[jugadorSV].agregarPuntos(2);
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Bienvenido al juego de Torres de Hanoi. En este juego tendras 6 discos en un poste, con su tamaño representado por numeros (siendo 6 el mas grande, y 1 el mas pequeño), y tu objetivo sera llevar a todos al ultimo poste. Debes tener en cuenta que no puedes colocar un disco sobre otro mas pequeño, asi que deberas pensar bien cada movimiento.\n\nEste juego cuesta 15 creditos.\nSi logras conseguirlo en el minimo posible (63 movimientos), se te otorgaran 10 puntos.\nSi lo consigues con hasta 10 movimientos adicionales, se te otorgaran 5 puntos.\nEn caso contrario, se te otorgaran unicamente 2 puntos.");
                                            System.out.println("Quien va a jugar? Introduce el nombre del jugador.");
                                            boolean booleanoTH = false;
                                            String nombreTH = null;
                                            while (!booleanoTH) {
                                                try {
                                                    nombreTH = in.nextLine().trim();
                                                    booleanoTH = true;
                                                } catch (Exception e) {
                                                    System.out.println("Hubo un error con tu entrada. Por favor intenta de nuevo.");
                                                }
                                            }
                                            int jugadorTH = -1;
                                            for (int i = 0; i < numJugadores; i++) {
                                                if (jugadores[i].obtenerNombre().equals(nombreTH)) {
                                                    jugadorTH = i;
                                                    break;
                                                }
                                            }
                                            if (jugadorTH == -1) {
                                                System.out.println("No se encontro un jugador con ese nombre.");
                                                break;
                                            }
                                            try {
                                                jugadores[jugadorTH].cobrarCreditos(5);
                                            } catch (ExcepcionBalanceNegativo e) {
                                                System.out.println(e.getMessage());
                                                break;
                                            }
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
                                            jugadores[jugadorTH].agregarPuntos(puntosTH);
                                            break;
                                        case 3:
                                            run2 = false;
                                            break;
                                        default:
                                            break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("El valor que introdujiste no es valido.");
                                    in.next();
                                } catch (Exception e) {
                                    break; 
                                }
                            }
                        }
                        break;
                    case 4:
                        String nombreBuscar = null;
                        boolean busqueda = false;
                        System.out.println("Introduce el nombre del jugador que deseas buscar: ");
                        while (!busqueda){
                            try {
                                nombreBuscar = in.nextLine().trim();
                                busqueda = true;
                            } catch (Exception e) {
                                System.out.println("Hubo un error con tu entrada. Intenta de nuevo");
                            }
                        }
                        boolean encontrado = false;
                        for (int i = 0 ; i < numJugadores ; i++){
                            if (nombreBuscar.equals(jugadores[i].obtenerNombre())){ 
                                System.out.println(jugadores[i]);
                                encontrado = true;
                            }
                        }
                        if (!encontrado) System.out.println("No se pudo encontrar el jugador que buscas.");
                        break;
                    case 5:
                        dia++;
                        if (dia == 3){
                            in.close();
                            lector.close();
                        }
                        break;
                    case 6:
                        in.close();
                        if (lector != null) lector.close();
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
        escritor.close();
        } catch (Exception e) {e.printStackTrace();}
    }
}