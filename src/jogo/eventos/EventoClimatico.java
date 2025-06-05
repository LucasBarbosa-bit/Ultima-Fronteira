package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

public class EventoClimatico extends Evento {
    private String tipoClima;
    private int duracao;

    public EventoClimatico(String nome, String descricao, double probabilidade, String impacto,
                           Boolean condicaoAtivacao, String tipoClima, int duracao) {
        super(nome, descricao, probabilidade, impacto, condicaoAtivacao);
        this.tipoClima = tipoClima;
        this.duracao = duracao;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        if (condicaoAtivacao){
            System.out.println("Evento Climático: " + tipoClima + " afeta o ambiente!");
        }
    }
}
