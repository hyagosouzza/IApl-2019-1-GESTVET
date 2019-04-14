package Repository;

import Entidades.Medicamento;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicamentoRepository {

    private Connection connection;

    public MedicamentoRepository()  throws ClassNotFoundException{
        this.connection = new Postgres().getConnection();
    }

    public ArrayList<Object> listarMedicamentos() {
        ArrayList<Object> medicamentos = new ArrayList<Object>();
        String sql = "SELECT * FROM MEDICAMENTO";

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
    public void adicionarMedicamentos() {

        try {
            FileReader reader = new FileReader("src/main/resources/novos-medicamentos.txt");
            BufferedReader leitor = new BufferedReader(reader);

            String line = leitor.readLine();

            while (line != null) {

                String array[] = line.split(";");

                String sql = "INSERT INTO medicamento (nome, preco, dosagem) VALUES (?, ?, ?)";

                try {
                    PreparedStatement stmt = connection.prepareStatement(sql);

                    stmt.setString(1, array[0]);
                    stmt.setDouble(2, Double.parseDouble(array[1]));
                    stmt.setString(3, array[2]);
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

}
