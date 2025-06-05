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
            this.valorNutricional = valorNutricional/2;
        }

    }

    public void consumir(Personagem jogador) {
        if(prazoValidade) {
            System.out.println(nome + " consumido. Restaurou " + valorNutricional + " de fome.");
            reduzirDurabilidade(1);
            jogador.comer(valorNutricional);
        } else  {
            System.out.println(nome + " consumido.");
            jogador.comer(valorNutricional);
            jogador.perderVida(valorNutricional);
            reduzirDurabilidade(1);
        }
        if(this.durabilidade == 0){
            boolean b = jogador.getInventario().removerItem(this.getNome());

        }
    }

    @Override
    public void usar(Personagem jogador) {
        consumir(jogador);
    }
}
