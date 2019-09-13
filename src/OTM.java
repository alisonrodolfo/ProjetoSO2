import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alison Barreiro
 */
public class OTM {

    private final int tamanhoRAM;
    private int miss_leitura;

    private int instante;

    private final String[] fila;

    private final List<Quadro> quadro = new ArrayList<>();

    public OTM(String[] fila) {

        this.tamanhoRAM = Integer.parseInt(fila[0]);
        this.miss_leitura = 0;
        this.fila = fila;

    }

    public void run() {

        for (int i = 1; i < fila.length; i++) {
            instante = (i);
            busca_na_memoria(Integer.parseInt(fila[i]));
        }

        System.out.println("OTM: " + miss_leitura);
    }

    public synchronized void busca_na_memoria(int data) {
        boolean achou = false;

        for (Quadro a : quadro) {
            if (a.getCelula() == data) {
                achou = true;
                break;
            }

        }

        if (!achou) {
            miss_leitura++;
            if (quadro.size() == tamanhoRAM) {
                quadro.remove(supertime());
                
                
            }
            quadro.add(new Quadro(data));
        }

    }

    public int supertime() {

        int aux1, valor;
        int aux2 = 0, retorno = 0;

        // Verificar a proxima vez que ele vai ser chamado, incremetar ccontador e dá um break;
        // até encontrar com maior valor.
        for (Quadro a : quadro) {
            aux1 = 0;
            valor = a.getCelula();
            for (int i = instante; i < fila.length; i++) {
                if (valor == Integer.parseInt(fila[i])) {
                    break;
                }else{
                    aux1++;
                }
            }
            if(aux1>aux2){
                aux2 = aux1;
                retorno = quadro.indexOf(a);
                            
            }

        }   
        return retorno;
    }

}
