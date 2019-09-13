
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alison Barreiro
 */
public class FIFO {

    private final int tamanhoRAM;
    private int miss_leitura;

    private final String[] fila;

    private final List<Quadro> quadro = new ArrayList<>();

    public FIFO(String[] fila) {

        this.tamanhoRAM = Integer.parseInt(fila[0]);
        this.miss_leitura = 0;
        this.fila = fila;

    }

    public void run() {

        for (int i = 1; i < fila.length; i++) {
            busca_na_memoria(Integer.parseInt(fila[i]));
        }

        System.out.println("FIFO: " + miss_leitura);
    }

    public synchronized void busca_na_memoria(int data) {
        boolean achou = false;
        for (Quadro a : quadro) {
            if (a.getCelula() == data) {
                achou = true;
            }
        }
        if (!achou) {
            miss_leitura++;
            if (quadro.size() == tamanhoRAM) {
                quadro.remove(0);
            }
            quadro.add(new Quadro(data));
        }

    }

}
