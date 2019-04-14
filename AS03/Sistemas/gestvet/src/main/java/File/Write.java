package File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Write {

    public Write(String nome, List<Object> objects) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Public\\"+nome+".txt"));
        for (Object obj: objects) {
            bufferedWriter.write(obj.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        System.out.println("\nArquivo C:\\Users\\Public\\" + nome + ".txt criado.");
    }
}
