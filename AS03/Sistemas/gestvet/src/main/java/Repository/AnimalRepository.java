package Repository;

import Entidades.Animal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public void adicionarAnimais() {

        try {
            FileReader reader = new FileReader("src/main/resources/novos-animais.txt");
            BufferedReader leitor = new BufferedReader(reader);

            String line = leitor.readLine();

            while (line != null) {

                String array[] = line.split(";");

                String sql = "INSERT INTO animal (nome, raca, idade, tipo) VALUES (?, ?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, array[0]);
                    stmt.setString(2, array[1]);
                    stmt.setInt(3, Integer.parseInt(array[2]));
                    stmt.setString(4, array[3]);
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
}
