package Entidades;

public class Animal {

    public static final int LIMITENOME = 50;
    public static final int LIMITERACA = 25;
    public static final int LIMITEIDADE = 2;
    public static final int LIMITETIPO = 30;

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

    public Animal() { };

    public Animal(int id, String nome, String raca, int idade, String tipo) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.tipo = tipo;
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
                   contador++;
               }

               resultado = atributo + resultado;
               System.out.println(resultado);
               return resultado;
           }
        } else if (nomeAtributo.equals("raca")){
            if (atributo.length() < this.LIMITERACA){
                diferencaTamanho = this.LIMITERACA - atributo.length();
                contador = 0;
                while (contador < diferencaTamanho){
                    resultado += " ";
                    contador++;
                }

                resultado = atributo + resultado;
                System.out.println(resultado);
                return resultado;
            }
        } else if (nomeAtributo.equals("idade")){
            if (atributo.length() < this.LIMITEIDADE){
                diferencaTamanho = this.LIMITEIDADE - atributo.length();
                contador = 0;
                while (contador < diferencaTamanho){
                    resultado += "0";
                    contador++;
                }

                resultado = resultado + atributo;
                System.out.println(resultado);
                return resultado;
            }
        } else {
            if (atributo.length() < this.LIMITETIPO){
                diferencaTamanho = this.LIMITETIPO - atributo.length();
                contador = 0;
                while (contador < diferencaTamanho){
                    resultado += " ";
                    contador++;
                }

                resultado = atributo + resultado;
                System.out.println(resultado);
                return resultado;
            }
        }

        System.out.println(resultado);
        return resultado;
    }

    @Override
    public String toString() {
        String nomeFormatado = formatarAtributo(getNome(), "nome");
        String racaFormatado = formatarAtributo(getRaca(), "raca");
        String idadeFormatado = formatarAtributo(Integer.toString(getIdade()), "idade");
        String tipoFormatado = formatarAtributo(getTipo(), "tipo");
        return nomeFormatado+racaFormatado+idadeFormatado+tipoFormatado+getId();
    }
}
