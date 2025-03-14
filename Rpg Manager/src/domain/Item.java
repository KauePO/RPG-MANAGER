package domain;

public class Item {
    String nome;
    String quantidade;
    String peso;
    String custo;

    // SETTERS
    void setNome(String nome) {
        this.nome = nome;
    }
    void setquantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    void setpeso(String peso) {
        this.peso = peso;
    }
    void setcusto(String custo) {
        this.custo = custo;
    }

    // GETTERS
    String getNome() {
        return nome;
    }
    String getquantidade() {
        return quantidade;
    }
    String getpeso() {
        return peso;
    }
    String getcusto() {
        return custo;
    }

}