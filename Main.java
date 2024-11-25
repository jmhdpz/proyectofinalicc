import src.Juegos.CuadroMagico;
import java.util.Random;
public class Main {
    public static void main(String[] werfwerf) {
        try{
            Random rng = new Random();
            CuadroMagico tumama = new CuadroMagico();
            System.out.println(tumama);
            for (int i = 0 ; i < 16 ; i++){
                int col = rng.nextInt(1,4);
                char colu = ' ';
                switch (col){
                    case 1:colu='A';break;
                    case 2:colu='B';break;
                    case 3:colu='C';break;
                    case 4:colu='D';break;
                }
                tumama.colocarNumero(rng.nextInt(1,4), colu, rng.nextInt(1,16));
            }
            System.out.println(tumama);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
