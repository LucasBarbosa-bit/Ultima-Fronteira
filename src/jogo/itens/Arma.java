package jogo.itens;

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

    public void atacar(String alvo) {
        System.out.println("Atacando " + alvo + " com " + nome + ", causando " + dano + " de dano.");
        reduzirDurabilidade(3);
    }

    @Override
    public void usar() {
        atacar("inimigo desconhecido");
    }
}

