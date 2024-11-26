import src.Juegos.CuadroMagico;
import java.util.Scanner;
public class Main {
    public static void main(String[] werfwerf) {
        Scanner a = null;
        try{
            CuadroMagico tumama = new CuadroMagico();
            System.out.println(tumama);
            while (true){
                a = new Scanner(System.in);
                System.out.println("char");
                char col = a.nextLine().charAt(0);
                System.out.println("fila");
                int fila = a.nextInt();
                System.out.println("num");
                int num = a.nextInt();
                tumama.colocarNumero(col,fila, num);
                System.out.println(tumama);
                if (tumama.juegoTerminado()){
                    if (tumama.ganador()){
                        System.out.println("ganaste");
                    } else {
                        System.out.println("ya perdiste cabron");
                    }
                } else {
                    System.out.println("no has acabado pendejo inutil");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
