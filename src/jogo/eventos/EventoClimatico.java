package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

public abstract class EventoClimatico extends Evento {
    private String tipoClima;
    private int duracao;

    public EventoClimatico(String nome, String descricao, double probabilidade, String impacto,
                           String localizacaoRequerida, String tipoClima, int duracao) {
        super(nome, descricao, probabilidade, impacto, localizacaoRequerida);
        this.tipoClima = tipoClima;
        this.duracao = duracao;
    }

    @Override
    public abstract void executar(Personagem jogador, Ambiente local);
}