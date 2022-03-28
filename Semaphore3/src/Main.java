import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int permissoes = 1;
        Semaphore semaforo = new Semaphore(permissoes);
        for (int idCorredor = 0; idCorredor < 4; idCorredor++) {
            Thread tCorrida = new Controller(semaforo, idCorredor);
            tCorrida.start();
        }
    }
}