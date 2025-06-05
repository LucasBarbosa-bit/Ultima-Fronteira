package jogo.ambientes;

import java.util.*;

import jogo.eventos.GerenciadorDeEventos;
import jogo.personagens.Personagem;

public class Localizador {
    private List<Ambiente> ambientesDisponiveis;
    private List<String> historicoDeMovimentacao;
    private Ambiente ambienteAtual;
    private GerenciadorDeEventos gerenciadorDeEventos;

    public Localizador(GerenciadorDeEventos eventos) {
        this.ambientesDisponiveis = new ArrayList<>();

        this.ambientesDisponiveis.add(new AmbienteRuinas());
        this.ambientesDisponiveis.add(new AmbienteFloresta());
        this.ambientesDisponiveis.add(new AmbienteCaverna());
        this.ambientesDisponiveis.add(new AmbienteMontanha());
        this.ambientesDisponiveis.add(new AmbienteLagoRio());

        this.historicoDeMovimentacao = new ArrayList<>();
        this.gerenciadorDeEventos = eventos;

        Random random = new Random();
        this.ambienteAtual = ambientesDisponiveis.get(random.nextInt(ambientesDisponiveis.size()));


    }

    public void mudarAmbiente(String nomeNovoAmbiente) {
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
        int i = 1;
        for (Ambiente ambiente : ambientesDisponiveis) {
            System.out.println(i + ".  " + ambiente.getNome());
            i++;
        }
    }

    public void mostrarHistorico() {
        System.out.println("Histórico de movimentação:");
        for (String nome : historicoDeMovimentacao) {
            System.out.println("→ " + nome);
        }
    }

    public GerenciadorDeEventos getGerenciadorDeEventos() {
        return gerenciadorDeEventos;
    }
}
