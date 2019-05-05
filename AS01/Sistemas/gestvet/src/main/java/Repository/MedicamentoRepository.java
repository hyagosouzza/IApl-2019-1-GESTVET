package Repository;

import Entidades.Clinica;
import Entidades.Medicamento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MedicamentoRepository {

    private Connection connection;

    public MedicamentoRepository()  throws ClassNotFoundException{
        this.connection = new Postgres().getConnection();
    }

    public ArrayList<Object> listarMedicamentos() {
        ArrayList<Object> medicamentos = new ArrayList<Object>();
        String sql = "SELECT * FROM medicamento";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medicamento medicamento = new Medicamento(rs.getInt("id"), rs.getString("nome"),
                        rs.getDouble("preco"), rs.getString("dosagem"));
                medicamentos.add(medicamento);
            }

            return medicamentos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarMedicamentosTxt() {

        try {
            FileReader reader = new FileReader("src/main/resources/novos-medicamentos.txt");
            BufferedReader leitor = new BufferedReader(reader);

            String line = leitor.readLine();

            while (line != null) {

                Medicamento medicamento = this.getMedicamentoFromLine(line);

                String array[] = line.split(";");

                String sql = "INSERT INTO medicamento (nome, preco, dosagem) VALUES (?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, medicamento.getNome());
                    stmt.setDouble(2, medicamento.getPreco());
                    stmt.setString(3, medicamento.getDosagem());
                    stmt.execute();
                    stmt.close();

                    System.out.println("Medicamento adicionado!");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                line = leitor.readLine();
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void adicionarMedicamentosJson() {

        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("src/main/resources/novos-medicamentos.json"));
            Clinica clinica = gson.fromJson(bufferedReader, Clinica.class);
            for (Medicamento medicamento : clinica.getMedicamentos()) {
                String sql = "INSERT INTO medicamento (nome, preco, dosagem) VALUES (?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, medicamento.getNome());
                    stmt.setDouble(2, medicamento.getPreco());
                    stmt.setString(3, medicamento.getDosagem());
                    stmt.execute();
                    stmt.close();

                    System.out.println("Medicamento adicionado!");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void adicionarMedicamentosXML(){
        try {
            File arquivo = new File("src/main/resources/novos-medicamentos.xml");
            SAXBuilder sb = new SAXBuilder();
            Document document = sb.build(arquivo);
            Element list = document.getRootElement();
            List medicamentos = list.getChildren();
            Iterator i = medicamentos.iterator();
            while (i.hasNext()){
                Element medicamento = (Element) i.next();
                String sql = "INSERT INTO medicamento (nome, preco, dosagem) VALUES (?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, medicamento.getChildText("nome"));
                    stmt.setDouble(2, Double.parseDouble(medicamento.getChildText("preco")));
                    stmt.setString(3, medicamento.getChildText("dosagem"));
                    stmt.execute();
                    stmt.close();

                    System.out.println("Medicamento adicionado!");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Medicamento getMedicamentoFromLine(String line) {
        Medicamento medicamento = new Medicamento();
        int index = 1;

        String nome = line.substring(index, index + Medicamento.LIMITENOME - 1).trim();
        index += Medicamento.LIMITENOME;
        double preco = Double.parseDouble(line.substring(index, index + Medicamento.LIMITEPRECO - 1));
        index += Medicamento.LIMITEPRECO;
        String dosagem = line.substring(index, line.length()).trim();

        medicamento.setNome(nome);
        medicamento.setPreco(preco);
        medicamento.setDosagem(dosagem);

        return medicamento;
    }
}
