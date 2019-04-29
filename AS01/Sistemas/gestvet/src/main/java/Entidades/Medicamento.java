package Entidades;

public class Medicamento {

    public static final int LIMITENOME = 50;
    public static final int LIMITEPRECO = 7;
    public static final int LIMITEDOSAGEM = 50;

    private int id;
    private String nome;
    private double preco;
    private String dosagem;

    public Medicamento() {}

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

    private String formatarAtributo(String atributo, String nomeAtributo){
        String resultado = "";
        int diferencaTamanho, contador;
        if (nomeAtributo.equals("nome")){
            if (atributo.length() < this.LIMITENOME){
                diferencaTamanho = this.LIMITENOME - atributo.length();
                contador = 0;
                while (contador < diferencaTamanho){
                    resultado += " ";
                }

                resultado = atributo + resultado;
                return resultado;
            }
        } else if (nomeAtributo.equals("preco")){
            if (atributo.length() < this.LIMITEPRECO){
                diferencaTamanho = this.LIMITEPRECO - atributo.length();
                contador = 0;
                while (contador < diferencaTamanho){
                    resultado += "0";
                }

                resultado = resultado + atributo;
                return resultado;
            }
        } else {
            if (atributo.length() < this.LIMITEDOSAGEM){
                diferencaTamanho = this.LIMITEDOSAGEM - atributo.length();
                contador = 0;
                while (contador < diferencaTamanho){
                    resultado += " ";
                }

                resultado = atributo + resultado;
                return resultado;
            }
        }

        return resultado;
    }

    @Override
    public String toString() {
        String nomeFormatado = formatarAtributo(getNome(), "nome");
        String precoFormatado = formatarAtributo(String.valueOf(getPreco()), "preco");
        String dosagemFormatado = formatarAtributo(getDosagem(), "dosagem");
        return nomeFormatado+precoFormatado+dosagemFormatado+getId();
    }
}
