package jogo.itens;

import jogo.personagens.Personagem;

public abstract class Item {
    protected String nome;
    protected double peso;
    protected int durabilidade;

    public Item(String nome, double peso, int durabilidade) {
        this.nome = nome;
        this.peso = peso;
        this.durabilidade = durabilidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void reduzirDurabilidade(int valor) {
        durabilidade -= valor;
        if (durabilidade < 0) {
            durabilidade = 0;
            System.out.println("O item " + nome + " acabou.");
        }
    }

    public abstract void usar(Personagem jogador); // polimórfico

}
