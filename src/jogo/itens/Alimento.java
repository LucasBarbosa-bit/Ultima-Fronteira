package jogo.itens;

import jogo.personagens.Personagem;

public class Alimento extends jogo.itens.Item {
    private int valorNutricional;
    private String tipo;
    private boolean prazoValidade;

    public Alimento(String nome, double peso, int durabilidade, int valorNutricional, boolean prazoValidade) {
        super(nome, peso, durabilidade);
        this.prazoValidade = prazoValidade;
        if (prazoValidade) {
            this.valorNutricional = valorNutricional;
        } else {
            this.valorNutricional = 0;
        }


    }

    public void consumir(Personagem jogador) {
        System.out.println(nome + " consumido. Restaurou " + valorNutricional + " de fome.");
        reduzirDurabilidade(1);
        jogador.comer(valorNutricional);
    }

    @Override
    public void usar(Personagem jogador) {
        consumir(jogador);
    }
}
