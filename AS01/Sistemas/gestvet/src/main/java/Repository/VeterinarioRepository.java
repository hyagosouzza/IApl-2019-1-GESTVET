package Repository;

import Entidades.Clinica;
import Entidades.Membro;
import Entidades.Veterinario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

public class VeterinarioRepository {

    private Connection connection;

    public VeterinarioRepository()  throws ClassNotFoundException{
        this.connection = new Postgres().getConnection();
    }

    public List<Object> listarVeterinarios() {
        ArrayList<Object> veterinarios = new ArrayList<Object>();
        String sql = "SELECT * FROM membro";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Veterinario veterinario = new Veterinario(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("senha"), rs.getString("usuario"), rs.getInt("crmv"));
                veterinarios.add(veterinario);
            }

            return veterinarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarMembrosJson() {

        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("src/main/resources/novos-membros.json"));
            Clinica clinica = gson.fromJson(bufferedReader, Clinica.class);
            for (Veterinario veterinario : clinica.getVeterinarios()) {
                String sql = "INSERT INTO membro (usuario, senha, nome, crmv) VALUES (?, ?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, veterinario.getUser());
                    stmt.setString(2, veterinario.getSenha());
                    stmt.setString(3, veterinario.getNome());
                    stmt.setInt(4, veterinario.getCrmv());
                    stmt.execute();
                    stmt.close();

                    System.out.println("Membro adicionado!");

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void adicionarMembrosTxt() {

        try {
            FileReader reader = new FileReader("src/main/resources/novos-membros.txt");
            BufferedReader leitor = new BufferedReader(reader);

            String line = leitor.readLine();

            while (line != null) {

                String array[] = line.split(";");

                String sql = "INSERT INTO membro (usuario, senha, nome, crmv) VALUES (?, ?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, array[0]);
                    stmt.setString(2, array[1]);
                    stmt.setString(3, array[2]);
                    stmt.setInt(4, Integer.parseInt(array[3]));
                    stmt.execute();
                    stmt.close();

                    System.out.println("Membro adicionado!");

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
