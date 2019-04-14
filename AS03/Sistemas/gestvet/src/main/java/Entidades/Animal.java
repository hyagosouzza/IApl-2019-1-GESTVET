package Entidades;

public class Animal {

    private int id;
    private String nome;
    private String raca;
    private int idade;
    private String tipo;

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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Animal(int id, String nome, String raca, int idade, String tipo) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return getNome()+";"+getRaca()+";"+getIdade()+";"+getTipo()+";"+getId();
    }
}
