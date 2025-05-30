package jogo.itens;

import jogo.personagens.Personagem;

public class Agua extends Item {
    private Boolean pureza; // "potável =  True" ou "Contaminada = False"

    public Agua(Boolean pureza, double volume) {
        super("Água", volume, (int) Math.round(volume/0.3));
        this.pureza = pureza;
    }

    public void beber(Personagem jogador) {
        System.out.println("Você bebeu " + nome + ". Pureza: " + pureza);
        reduzirDurabilidade(1);
        if (pureza) {
            System.out.println("A sede foi saciada com sucesso.");
            // implementar lógica da sede
            jogador.sede(-20);
            jogador.vida(2);
        } else {
            System.out.println("Você bebeu " + nome + " e agora está contaminada.");
            jogador.vida(-5);
        }
    }

    @Override
    public void usar(Personagem jogador) {
        beber(jogador);
    }
}
