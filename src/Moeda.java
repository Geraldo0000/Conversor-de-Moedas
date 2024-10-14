public class Moeda {
    private String codigo;
    private String simbolo;
    private String nome;
    private double taxaConversao;

    // Construtor
    public Moeda(String codigo, String simbolo, String nome, double taxaConversao) {
        this.codigo = codigo;
        this.simbolo = simbolo;
        this.nome = nome;
        this.taxaConversao = taxaConversao;
    }

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getNome() {
        return nome;
    }

    public double getTaxaConversao() {
        return taxaConversao;
    }

    // Método para converter um valor para a moeda base
    public double converterParaMoedaBase(double valor) {
        return valor * taxaConversao;
    }
}
