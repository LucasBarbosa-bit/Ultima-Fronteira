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

    public GerenciadorDeEventos(Personagem jogador) {
        this.eventosPossiveis = new ArrayList<>();
        this.random = new Random();
        this.jogador = jogador;
    }

    public void adicionarEvento(Evento evento) {
        eventosPossiveis.add(evento);
    }

    public void sortearEvento(Personagem jogador, Ambiente ambiente) {
        for (Evento evento : eventosPossiveis) {
            // Primeiro, verifica se o evento é compatível com o ambiente atual
            if (evento.podeOcorrer(ambiente)) {
                // Se for compatível, então verifica a probabilidade
                if (random.nextDouble() <= evento.getProbabilidade()) {
                    System.out.println("\nEvento sorteado: " + evento.getNome());
                    evento.executar(jogador, ambiente);
                    // Interrompe após o primeiro evento para não sobrecarregar o jogador
                    break;
                }
            }
        }
    }

    public Personagem getJogador() { return jogador; }
}