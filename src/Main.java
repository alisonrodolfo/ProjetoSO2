import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;

/**
 *
 * @author Alison Barreiro
 */
public class Main {

    private static String[] mem_inst;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            Main proj = new Main();
            proj.run(args);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void run(String[] args) throws Exception {
        try {
            URL url = getClass().getResource("entrada.proj");
            int file_size = countLines(url.getPath()); //Conta o numero de linhas do arquivo

            FileReader file = new FileReader(url.getPath()); //Localizacao do arquivo
            BufferedReader arq = new BufferedReader(file);

            mem_inst = new String[file_size];

            /**
             * Carrega o arquivo na memoria*
             */
            memory_fetch(arq);
            
            FIFO fifo = new FIFO(mem_inst.clone());
            fifo.run();
            
            OTM otm = new OTM(mem_inst.clone());
            otm.run();
            
            LRU lru = new LRU(mem_inst.clone());
            lru.run();
            
            
            
            

            arq.close();
            file.close();

        } catch (FileNotFoundException fnf) {
            System.out.println("Arquivo nao encontrado");
        }
    }

    @SuppressWarnings("empty-statement")
    public static int countLines(String filename) throws IOException {
        LineNumberReader reader = new LineNumberReader(new FileReader(filename));
        while (reader.readLine() != null);
        //int cnt = reader.getLineNumber();
        reader.close();
        return reader.getLineNumber();
    }

    public static void memory_fetch(BufferedReader br) throws IOException {
        int i = 0, size = mem_inst.length;
        String line;

        while (i < size && br.ready()) {
            line = br.readLine();
            if (line.equals("")) {
                continue;
            }

            mem_inst[i] = line;
            i++;
        }
    }
    
}
