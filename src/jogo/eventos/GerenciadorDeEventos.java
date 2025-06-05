package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorDeEventos {
    private List<Evento> eventosPossiveis;
    private Random random;
    private Personagem jogador;

    public GerenciadorDeEventos(Personagem joagdor) {
        this.eventosPossiveis = new ArrayList<>();
        this.random = new Random();
        this.jogador = joagdor;
    }

    public void adicionarEvento(Evento evento) {
        eventosPossiveis.add(evento);
    }

    public void sortearEvento(Personagem jogador, Ambiente ambiente) {
        for (Evento evento : eventosPossiveis) {
            if (random.nextDouble() <= evento.probabilidade) {
                if (evento.condicaoAtivacao){
                    System.out.println("\nEvento sorteado!!!");
                    evento.executar(jogador, ambiente);
                    break;
                }
            }
        }
    }

    public Personagem getJogador() { return jogador; }
}
