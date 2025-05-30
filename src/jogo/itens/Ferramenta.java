package jogo.itens;

import jogo.personagens.Personagem;

public class Ferramenta extends Item {
    private String tipo; // ex: "machado", "isqueiro"
    private int eficiencia;

    public Ferramenta(String nome, double peso, int durabilidade, String tipo, int eficiencia) {
        super(nome, peso, durabilidade);
        this.tipo = tipo;
        this.eficiencia = eficiencia;
    }

    @Override
    public void usar(Personagem personagem) {
        System.out.println("Usando ferramenta: " + tipo + ". Eficiência: " + eficiencia);
        reduzirDurabilidade(2);


    }
}
