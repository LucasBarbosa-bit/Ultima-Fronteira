package jogo.itens;

import jogo.personagens.Personagem;

public class Arma extends Item {
    private String tipoDeArma; // corpo a corpo ou distância
    private int dano;
    private int alcance;

    public Arma(String nome, double peso, int durabilidade, String tipoDeArma, int dano, int alcance) {
        super(nome, peso, durabilidade);
        this.tipoDeArma = tipoDeArma;
        this.dano = dano;
        this.alcance = alcance;
    }

    public void atacar(Personagem alvo) {
        System.out.println("Atacando " + alvo.getNome() + " com " + nome + ", causando " + dano + " de dano.");
        reduzirDurabilidade(3);
        alvo.perderVida(dano);
    }

    @Override
    public void usar(Personagem jogador) {
        System.out.println("O personagem " + jogador.getNome() + " equipa " + super.nome);
    }
}

