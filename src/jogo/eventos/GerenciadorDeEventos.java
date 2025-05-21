package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorDeEventos {
    private List<Evento> eventosPossiveis;
    private Random random;

    public GerenciadorDeEventos() {
        this.eventosPossiveis = new ArrayList<>();
        this.random = new Random();
    }

    public void adicionarEvento(Evento evento) {
        eventosPossiveis.add(evento);
    }

    public void sortearEvento(Personagem jogador, Ambiente ambiente) {
        for (Evento evento : eventosPossiveis) {
            if (random.nextDouble() <= evento.probabilidade) {
                System.out.println("Evento sorteado: " + evento.getNome());
                evento.executar(jogador, ambiente);
                break;
            }
        }
    }
}
