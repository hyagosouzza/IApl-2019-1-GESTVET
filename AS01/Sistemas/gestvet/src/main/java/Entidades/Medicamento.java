package Entidades;

public class Medicamento {

    private int id;
    private String nome;

    public Medicamento(int id, String nome, double preco, String dosagem) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.dosagem = dosagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    private double preco;
    private String dosagem;

    @Override
    public String toString() {
        return getNome()+";"+getPreco()+";"+getDosagem()+";"+getId();
    }
}
