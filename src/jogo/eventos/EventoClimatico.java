package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

public class EventoClimatico extends Evento {
    private String tipoClima;
    private int duracao;

    public EventoClimatico(String nome, String descricao, double probabilidade, String impacto,
                           String condicaoAtivacao, String tipoClima, int duracao) {
        super(nome, descricao, probabilidade, impacto, condicaoAtivacao);
        this.tipoClima = tipoClima;
        this.duracao = duracao;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento Climático: " + tipoClima + " afeta o ambiente!");
        // Exemplo: gastar energia extra
        jogador.descansar(); // só um efeito placeholder
    }
}
