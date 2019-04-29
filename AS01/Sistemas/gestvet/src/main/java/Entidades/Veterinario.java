package Entidades;

public class Veterinario extends Membro {

    public static final int LIMITEUSER = 50;
    public static final int LIMITESENHA = 20;
    public static final int LIMITENOME = 50;
    public static final int LIMITECRMV = 7;

    private int crmv;

    public Veterinario() {}

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

    private String formatarAtributo(String atributo, String nomeAtributo){
        String resultado = "";
        int diferencaTamanho, contador;
        if (nomeAtributo.equals("user")){
            if (atributo.length() < this.LIMITEUSER){
                diferencaTamanho = this.LIMITEUSER - atributo.length();
                contador = 0;
                while (contador < diferencaTamanho){
                    resultado += " ";
                    contador++;
                }

                resultado = atributo + resultado;
                return resultado;
            }
        } else if (nomeAtributo.equals("senha")){
            if (atributo.length() < this.LIMITESENHA){
                diferencaTamanho = this.LIMITESENHA - atributo.length();
                contador = 0;
                while (contador < diferencaTamanho){
                    resultado += " ";
                    contador++;
                }

                resultado = atributo + resultado;
                return resultado;
            }
        } else if (nomeAtributo.equals("nome")){
            if (atributo.length() < this.LIMITENOME){
                diferencaTamanho = this.LIMITENOME - atributo.length();
                contador = 0;
                while (contador < diferencaTamanho){
                    resultado += " ";
                    contador++;
                }

                resultado = atributo + resultado;
                return resultado;
            }
        } else {
            if (atributo.length() < this.crmv){
                diferencaTamanho = this.LIMITECRMV - atributo.length();
                contador = 0;
                while (contador < diferencaTamanho){
                    resultado += "0";
                    contador++;
                }

                resultado = atributo + resultado;
                return resultado;
            }
        }

        return resultado;
    }

    @Override
    public String toString() {
        String userFormatado = formatarAtributo(this.getUser(), "user");
        String senhaFormatado = formatarAtributo(getSenha(), "senha");
        String nomeFormatado = formatarAtributo(getNome(), "nome");
        String crmvFormatado = formatarAtributo(Integer.toString(getCrmv()), "crmv");

        return userFormatado+senhaFormatado+nomeFormatado+crmvFormatado;
    }
}
