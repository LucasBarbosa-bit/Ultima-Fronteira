package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;
import jogo.personagens.Criatura;
import jogo.sistema.Combate;

public class EventoCriatura extends Evento {
    private String tipoCriatura;
    private int nivelPerigo;

    public EventoCriatura(String nome, String descricao, double probabilidade, String impacto,
                          Boolean condicaoAtivacao, String tipoCriatura, int nivelPerigo) {
        super(nome, descricao, probabilidade, impacto, condicaoAtivacao);
        this.tipoCriatura = tipoCriatura;
        this.nivelPerigo = nivelPerigo;
    }

    @Override
    public void executar(Personagem jogador, Ambiente ambiente) {
        if (condicaoAtivacao){
            System.out.println("Evento: Criatura hostil surge!");

            Criatura criatura = new Criatura("Lobo Selvagem", 20, 5);
            Combate.iniciar(jogador, criatura);
        }
    }

}
