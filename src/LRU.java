import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alison Barreiro
 */
public class LRU {

    private final int tamanhoRAM;
    private int miss_leitura;

    int contador = 0;
    
    private final String[] fila;

    private final List<Quadro> quadro = new ArrayList<>();

    public LRU(String[] fila) {

        this.tamanhoRAM = Integer.parseInt(fila[0]);
        this.miss_leitura = 0;
        this.fila = fila;

    }

    public void run() {

        for (int i = 1; i < fila.length; i++) {
            busca_na_memoria(Integer.parseInt(fila[i]));
        }

        System.out.println("LRU: " + miss_leitura);
    }

    public synchronized void busca_na_memoria(int data) {
        boolean achou = false;

        for (Quadro a : quadro) {
            if (a.getCelula() == data) {
                quadro.remove(a);
                quadro.add(new Quadro(data));
                achou = true;
                break;
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
