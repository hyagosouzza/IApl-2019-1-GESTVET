package File;

import Entidades.Animal;
import Entidades.Medicamento;
import Entidades.Membro;
import Entidades.Veterinario;
import Repository.AnimalRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Write {

    public void writeTxt(String nome, List<Object> objects) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Public\\"+nome+".txt"));
        for (Object obj: objects) {
            bufferedWriter.write(obj.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        System.out.println("\nArquivo C:\\Users\\Public\\" + nome + ".txt criado.");

    }

    public void writeJson(String nome, List<Object> objects) throws IOException {
        JSONArray objectsJson = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        FileWriter writeFile = null;

        try{
            writeFile = new FileWriter("C:\\Users\\Public\\"+nome+".json");

            if(nome.equals("animais")) {
                for (Object obj: objects) {
                    jsonObject.put("nome", Animal.class.cast(obj).getNome());
                    jsonObject.put("idade", Animal.class.cast(obj).getIdade());
                    jsonObject.put("raca", Animal.class.cast(obj).getRaca());
                    jsonObject.put("tipo", Animal.class.cast(obj).getTipo());
                    objectsJson.put(jsonObject);
                    jsonObject = new JSONObject();
                }

                jsonObject.put("animais", objectsJson);
            } else if(nome.equals("medicamentos")) {
                for (Object obj: objects) {
                    jsonObject.put("nome", Medicamento.class.cast(obj).getNome());
                    jsonObject.put("preco", Medicamento.class.cast(obj).getPreco());
                    jsonObject.put("dosagem", Medicamento.class.cast(obj).getDosagem());
                    objectsJson.put(jsonObject);
                    jsonObject = new JSONObject();
                }

                jsonObject.put("medicamentos", objectsJson);
            } else {
                for (Object obj: objects) {
                    jsonObject.put("nome", Veterinario.class.cast(obj).getNome());
                    jsonObject.put("user", Veterinario.class.cast(obj).getUser());
                    jsonObject.put("senha", Veterinario.class.cast(obj).getSenha());
                    jsonObject.put("crmv", Veterinario.class.cast(obj).getCrmv());
                    objectsJson.put(jsonObject);
                    jsonObject = new JSONObject();
                }

                jsonObject.put("veterinarios", objectsJson);
            }


            writeFile.write(jsonObject.toString());

            writeFile.close();
        }
        catch(IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(jsonObject);
    }

}
