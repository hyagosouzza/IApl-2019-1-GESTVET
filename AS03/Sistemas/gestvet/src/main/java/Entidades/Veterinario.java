package Entidades;

public class Veterinario extends Membro {

    private int crmv;

    public Veterinario(int id, String nome, String senha, String user, int crmv) {
        super(id, nome, senha, user);
        this.crmv = crmv;
    }

    public int getCrmv() {
        return crmv;
    }

    public void setCrmv(int crmv) {
        this.crmv = crmv;
    }

    @Override
    public String toString() {
        return this.getUser()+";"+getSenha()+";"+getNome()+";"+getCrmv()+";"+getId();
    }
}
