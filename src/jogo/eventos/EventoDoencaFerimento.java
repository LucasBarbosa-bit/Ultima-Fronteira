package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

public class EventoDoencaFerimento extends Evento {
    private String tipoCondicao;
    private int impactoVida;
    private int impactoEnergia;
    private boolean curaNecessaria;

    public EventoDoencaFerimento(String nome, String descricao, double probabilidade, String impacto,
                                 String localizacaoRequerida, String tipoCondicao, int impactoVida,
                                 int impactoEnergia, boolean curaNecessaria) {
        super(nome, descricao, probabilidade, impacto, localizacaoRequerida);
        this.tipoCondicao = tipoCondicao;
        this.impactoVida = impactoVida;
        this.impactoEnergia = impactoEnergia;
        this.curaNecessaria = curaNecessaria;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println(super.descricao);
        jogador.perderVida(impactoVida);
        jogador.gastarEnergia(impactoEnergia);
    }
}