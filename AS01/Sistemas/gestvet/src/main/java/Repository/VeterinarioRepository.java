package Repository;

import Entidades.Clinica;
import Entidades.Membro;
import Entidades.Veterinario;
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

                Veterinario veterinario = this.getVeterinarioFromLine(line);

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

                line = leitor.readLine();
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void adicionarVeterinarioXML(){
        try {
            File arquivo = new File("src/main/resources/novos-membros.xml");
            SAXBuilder sb = new SAXBuilder();
            Document document = sb.build(arquivo);
            Element list = document.getRootElement();
            List membros = list.getChildren();
            Iterator i = membros.iterator();
            while (i.hasNext()){
                Element veterinario = (Element) i.next();
                String sql = "INSERT INTO membro (usuario, senha, nome, crmv) VALUES (?, ?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, veterinario.getChildText("usuario"));
                    stmt.setString(2, veterinario.getChildText("senha"));
                    stmt.setString(3, veterinario.getChildText("nome"));
                    stmt.setInt(4, Integer.parseInt(veterinario.getChildText("crmv")));
                    stmt.execute();
                    stmt.close();

                    System.out.println("Membro adicionado!");

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

    private Veterinario getVeterinarioFromLine(String line) {
        Veterinario veterinario = new Veterinario();
        int index = 0;

        String usuario = line.substring(index, index + Veterinario.LIMITEUSER).trim();
        index += Veterinario.LIMITEUSER;
        String senha = line.substring(index, index + Veterinario.LIMITESENHA).trim();
        index += Veterinario.LIMITESENHA;
        String nome = line.substring(index, index + Veterinario.LIMITENOME).trim();
        index += Veterinario.LIMITENOME;
        int crmv = Integer.parseInt(line.substring(index, index + Veterinario.LIMITECRMV));

        veterinario.setUser(usuario);
        veterinario.setSenha(senha);
        veterinario.setNome(nome);
        veterinario.setCrmv(crmv);

        return veterinario;
    }
}
