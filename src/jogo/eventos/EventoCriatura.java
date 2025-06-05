package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

public class EventoCriatura extends Evento {
    private String tipoCriatura;
    private int nivelPerigo;

    public EventoCriatura(String nome, String descricao, double probabilidade, String impacto,
                          String condicaoAtivacao, String tipoCriatura, int nivelPerigo) {
        super(nome, descricao, probabilidade, impacto, condicaoAtivacao);
        this.tipoCriatura = tipoCriatura;
        this.nivelPerigo = nivelPerigo;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("Ataque de " + tipoCriatura + "! Nível de perigo: " + nivelPerigo);
        jogador.perderVida(nivelPerigo * 10); // exemplo
    }
}
