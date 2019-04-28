package Repository;

import Entidades.Animal;
import Entidades.Clinica;
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

public class AnimalRepository {

    private Connection connection;

    public AnimalRepository() throws ClassNotFoundException {
        this.connection = new Postgres().getConnection();
    }

    public List<Object> listarAnimais() {
        ArrayList<Object> animais = new ArrayList<Object>();
        String sql = "SELECT * FROM ANIMAL";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("raca"), rs.getInt("idade"),
                        rs.getString("tipo"));
                animais.add(animal);
            }

            return animais;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarAnimaisTxt() {

        try {
            FileReader reader = new FileReader("src/main/resources/novos-animais.txt");
            BufferedReader leitor = new BufferedReader(reader);

            String line = leitor.readLine();

            while (line != null) {
                int index = 0;
                Animal animal = this.getAnimalFromLine(line);

                String array[] = line.split(";");

                String sql = "INSERT INTO animal (nome, raca, idade, tipo) VALUES (?, ?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, animal.getNome());
                    stmt.setString(2, animal.getRaca());
                    stmt.setInt(3, animal.getIdade());
                    stmt.setString(4, animal.getTipo());
                    stmt.execute();
                    stmt.close();

                    System.out.println("Animal adicionado!");

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

    public void adicionarAnimaisJson() {

        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("src/main/resources/novos-animais.json"));
            Clinica clinica = gson.fromJson(bufferedReader, Clinica.class);
            for (Animal animal : clinica.getAnimais()) {
                String sql = "INSERT INTO animal (nome, raca, idade, tipo) VALUES (?, ?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, animal.getNome());
                    stmt.setString(2, animal.getRaca());
                    stmt.setInt(3, animal.getIdade());
                    stmt.setString(4, animal.getTipo());
                    stmt.execute();
                    stmt.close();

                    System.out.println("Animal adicionado!");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void adicionarAnimaisXML(){
        try {
            File arquivo = new File("src/main/resources/novos-animais.xml");
            SAXBuilder sb = new SAXBuilder();
            Document document = sb.build(arquivo);
            Element list = document.getRootElement();
            List animais = list.getChildren();
            Iterator i = animais.iterator();
            while (i.hasNext()){
                Element animal = (Element) i.next();
                String sql = "INSERT INTO animal (nome, raca, idade, tipo) VALUES (?, ?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, animal.getChildText("nome"));
                    stmt.setString(2, animal.getChildText("raca"));
                    stmt.setInt(3, Integer.parseInt(animal.getChildText("idade")));
                    stmt.setString(4, animal.getChildText("tipo"));
                    stmt.execute();
                    stmt.close();

                    System.out.println("Animal adicionado!");

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

    private Animal getAnimalFromLine(String line) {
        Animal animal = new Animal();
        int index = 0;

        String nome = line.substring(index, index + Animal.LIMITENOME).trim();
        index += Animal.LIMITENOME;
        String raca = line.substring(index, index + Animal.LIMITERACA).trim();
        index += Animal.LIMITERACA;
        int idade = Integer.parseInt(line.substring(index, index + Animal.LIMITEIDADE));
        index += Animal.LIMITEIDADE;
        String tipo = line.substring(index, index + Animal.LIMITETIPO).trim();

        animal.setNome(nome);
        animal.setRaca(raca);
        animal.setIdade(idade);
        animal.setTipo(tipo);

        return animal;
    }
}
