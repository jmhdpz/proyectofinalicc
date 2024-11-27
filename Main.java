import src.Juegos.CuadroMagico;
import src.Juegos.Salvados;
import java.util.Scanner;
public class Main {
    public static void main(String[] werfwerf) {
        Scanner a = null;
        try {
            a = new Scanner(System.in);
            Salvados eeee = new Salvados();
            eeee.elegirPasos(); 
            int pasos = a.nextInt();
            boolean pene = eeee.jugar(pasos);
            System.out.println(pene);
        } catch (Exception e) {
            // TODO: handle exception
        } finally{
            a.close();
        }
    }
}
