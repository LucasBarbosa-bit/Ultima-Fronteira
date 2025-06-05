package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

public abstract class Evento {
    protected String nome;
    protected String descricao;
    protected double probabilidade;
    protected String impacto;
    protected Boolean condicaoAtivacao;
    private String localizacaoRequerida;

    public Evento(String nome, String descricao, double probabilidade, String impacto, Boolean condicaoAtivacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.probabilidade = probabilidade;
        this.impacto = impacto;
        this.condicaoAtivacao = condicaoAtivacao;
    }

    public void verificarEAtivar(Personagem jogador) {
        if (jogador.emLocalizacao(localizacaoRequerida)) {
            double sorteio = Math.random();
            if (sorteio <= probabilidade) {
                System.out.println("Evento ativado: " + nome);
                this.executar(jogador, jogador.getLocalizador().getAmbienteAtual());
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract void executar(Personagem jogador, Ambiente local);
}
