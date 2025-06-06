package jogo.itens;

import jogo.personagens.Personagem;

public class Alimento extends jogo.itens.Item {
    private int valorNutricional;
    private boolean prazoValidade;

    public Alimento(String nome, double peso, int durabilidade, int valorNutricional, boolean prazoValidade) {
        super(nome, peso, durabilidade);
        this.prazoValidade = prazoValidade;
        if (prazoValidade) {
            this.valorNutricional = valorNutricional;
        } else {
            this.valorNutricional = valorNutricional / 2;
        }

    }

    public void consumir(Personagem jogador) {
        if (prazoValidade) {
            System.out.println(nome + " consumido. Restaurou " + valorNutricional + " de fome.");
            reduzirDurabilidade(1);
            jogador.comer(valorNutricional);
        } else {
            System.out.println(nome + " consumido, mas não parecia estar bom... Você se sente um pouco mal.");
            jogador.comer(valorNutricional);
            jogador.perderVida(5); // Penalidade por comer comida estragada
            reduzirDurabilidade(1);
        }

        if (this.durabilidade <= 0) {
            jogador.getInventario().removerItem(this.getNome());
        }
    }

    @Override
    public void usar(Personagem jogador) {
        consumir(jogador);
    }
}