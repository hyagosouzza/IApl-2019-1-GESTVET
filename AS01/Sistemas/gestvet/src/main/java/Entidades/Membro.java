package Entidades;

public class Membro {

    private int id;
    private String nome;
    private String senha;
    private String user;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Membro() {}

    public Membro(int id, String nome, String senha, String user) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.user = user;
    }
}
