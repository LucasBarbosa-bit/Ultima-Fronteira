package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;
import jogo.personagens.Criatura;
import jogo.sistema.Combate;

public class EventoCriatura extends Evento {
    private String tipoCriatura;
    private int nivelPerigo;

    public EventoCriatura(String nome, String descricao, double probabilidade, String impacto,
                          String localizacaoRequerida, String tipoCriatura, int nivelPerigo) {
        super(nome, descricao, probabilidade, impacto, localizacaoRequerida);
        this.tipoCriatura = tipoCriatura;
        this.nivelPerigo = nivelPerigo;
    }

    @Override
    public void executar(Personagem jogador, Ambiente ambiente) {
        System.out.println("Evento: Criatura hostil surge!");
        System.out.println(super.descricao);

        jogador.perderSanidade(10); // Perda de sanidade pelo susto

        Criatura criatura = new Criatura(this.tipoCriatura, 20, this.nivelPerigo);
        Combate.iniciar(jogador, criatura);
    }
}