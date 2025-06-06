package jogo.itens;

import jogo.personagens.Personagem;

public class Agua extends Item {
    private Boolean pureza; // "potável =  True" ou "Contaminada = False"

    public Agua(Boolean pureza, double volume) {
        super("Água", volume, (int) Math.round(volume/0.3));
        this.pureza = pureza;
    }

    public void beber(Personagem jogador) {
        System.out.println("Você bebeu " + nome);
        reduzirDurabilidade(1);
        if (pureza) {
            System.out.println("A sede foi saciada em 20 pontos");

            jogador.sede(20);
            jogador.ganharvida(1);
        } else {
            System.out.println("Você bebeu " + nome + " e agora está contaminada.");
            jogador.perderVida(3);
        }
    }

    public void addDurabilidade(int durabilidade ) {
        this.durabilidade += durabilidade;
    }

    public int getDurabilidade() { return this.durabilidade; }
    public void setDurabilidade(int durabilidade) { this.durabilidade = durabilidade; }
    public boolean getPureza() { return this.pureza; }
    public void setPureza(boolean pureza) { this.pureza = pureza; }

    @Override
    public void usar(Personagem jogador) {
        beber(jogador);
    }
}
