package br.com.uniritter.tailine.models;

public class Frequencia {

    private String jogador;
    private int contaPresenca;
    private double compracimento;

    public Frequencia(String jogador, int contaPresenca, double compracimento) {
        this.jogador = jogador;
        this.contaPresenca = contaPresenca;
        this.compracimento = compracimento;
    }

    public Frequencia() { super(); }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public int getContaPresenca() {
        return contaPresenca;
    }

    public void setContaPresenca(int contaPresenca) {
        this.contaPresenca = contaPresenca;
    }

    public double getCompracimento() {
        return compracimento;
    }

    public void setCompracimento(double compracimento) {
        this.compracimento = compracimento;
    }

}
