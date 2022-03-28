import java.util.concurrent.Semaphore;

public class Controller extends Thread {
    private Semaphore semaforo;
    private int idCorredor;
    private static int chegadaPorta;
    private static int saidaPorta;

    public Controller(Semaphore semaforo, int idCorredor) {
        this.semaforo = semaforo;
        this.idCorredor = idCorredor;
    }

    public void run() {
        CorredorAndando();
        try {
            semaforo.acquire();
            CorredorPorta();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
            CorredorPodium();
        }
    }

    private void CorredorAndando() {
        int distanciaTotal = 200;
        int distanciaPercorrida = 0;
        int deslocamento = (int) ((Math.random() * 2) + 4);
        int tempo = 1000;
        while (distanciaPercorrida < distanciaTotal) {
            distanciaPercorrida += deslocamento;
            try {
                sleep(tempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (distanciaPercorrida > 200) {
                distanciaPercorrida = 200;
            }
            System.out.println("pessoa #" + idCorredor + " ja andou " + distanciaPercorrida + "m. ");
        }
        chegadaPorta++;
        System.out.println("# " + idCorredor + " foi o " + chegadaPorta + " a chegar");

    }

    private void CorredorPorta() {
        System.out.println("# " + idCorredor + " esta passando a porta");
        int tempo = (int) ((Math.random() * 1001) + 1000);
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void CorredorPodium() {
        saidaPorta++;
        System.out.println("# " + idCorredor + "foi o " + saidaPorta + " a passar a porta");
    }
}