package jogo.ambientes;

import java.util.*;

import jogo.eventos.GerenciadorDeEventos;
import jogo.personagens.Personagem;

public class GerenciadorDeAmbientes {
    private List<Ambiente> ambientesDisponiveis;
    private List<String> historicoDeMovimentacao;
    private Ambiente ambienteAtual;

    private GerenciadorDeEventos gerenciadorDeEventos;

    public GerenciadorDeAmbientes(List<Ambiente> ambientes, GerenciadorDeEventos eventos) {
        this.ambientesDisponiveis = ambientes;
        this.historicoDeMovimentacao = new ArrayList<>();
        this.gerenciadorDeEventos = eventos;

        if (!ambientes.isEmpty()) {
            this.ambienteAtual = ambientes.get(0); // começa no primeiro
            this.historicoDeMovimentacao.add(ambienteAtual.getNome());
        }
    }

    public void mudarAmbiente(Personagem jogador, String nomeNovoAmbiente) {
        for (Ambiente ambiente : ambientesDisponiveis) {
            if (ambiente.getNome().equalsIgnoreCase(nomeNovoAmbiente)) {
                this.ambienteAtual = ambiente;
                historicoDeMovimentacao.add(ambiente.getNome());
                System.out.println("Você se moveu para: " + ambiente.getNome());
                return;
            }
        }
        System.out.println("Ambiente não encontrado.");
    }

    public void gerarEventoAtual(Personagem jogador) {
        gerenciadorDeEventos.sortearEvento(jogador, ambienteAtual);
    }

    public void modificarRecursos() {
        System.out.println("Recursos em " + ambienteAtual.getNome() + " foram modificados");
        // implementar lógicas regeneração de recursos e escassez
    }

    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }

    public void mostrarAmbientesDisponiveis() {
        System.out.println("Ambientes disponíveis:");
        for (Ambiente ambiente : ambientesDisponiveis) {
            System.out.println("- " + ambiente.getNome());
        }
    }

    public void mostrarHistorico() {
        System.out.println("Histórico de movimentação:");
        for (String nome : historicoDeMovimentacao) {
            System.out.println("→ " + nome);
        }
    }
}
