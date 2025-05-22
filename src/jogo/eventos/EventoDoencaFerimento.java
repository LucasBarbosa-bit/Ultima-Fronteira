package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

public class EventoDoencaFerimento extends Evento {
    private String tipoCondicao;
    private int impactoVida;
    private int impactoEnergia;
    private boolean curaNecessaria;

    public EventoDoencaFerimento(String nome, String descricao, double probabilidade, String impacto,
                                 String condicaoAtivacao, String tipoCondicao, int impactoVida,
                                 int impactoEnergia, boolean curaNecessaria) {
        super(nome, descricao, probabilidade, impacto, condicaoAtivacao);
        this.tipoCondicao = tipoCondicao;
        this.impactoVida = impactoVida;
        this.impactoEnergia = impactoEnergia;
        this.curaNecessaria = curaNecessaria;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Evento de saúde: " + tipoCondicao);
        jogador.perderVida(impactoVida);
        // Simulação: perder energia também
        jogador.descansar(); // poderia ser uma penalização real
    }
}
