package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

public abstract class Evento {
    protected String nome;
    protected String descricao;
    protected double probabilidade;
    protected String impacto;
    protected String condicaoAtivacao;

    public Evento(String nome, String descricao, double probabilidade, String impacto, String condicaoAtivacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.probabilidade = probabilidade;
        this.impacto = impacto;
        this.condicaoAtivacao = condicaoAtivacao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract void executar(Personagem jogador, Ambiente local);
}
