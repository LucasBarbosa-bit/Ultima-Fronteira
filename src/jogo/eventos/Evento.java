package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;

public abstract class Evento {
    protected String nome;
    protected String descricao;
    protected double probabilidade;
    protected String impacto;
    protected String localizacaoRequerida; // Novo atributo

    public Evento(String nome, String descricao, double probabilidade, String impacto, String localizacaoRequerida) {
        this.nome = nome;
        this.descricao = descricao;
        this.probabilidade = probabilidade;
        this.impacto = impacto;
        this.localizacaoRequerida = localizacaoRequerida;
    }

    public boolean podeOcorrer(Ambiente ambienteAtual) {
        // Se a localização não for especificada, pode ocorrer em qualquer lugar.
        if (localizacaoRequerida == null) {
            return true;
        }
        // Verifica se o nome do ambiente atual corresponde ao requerido.
        return localizacaoRequerida.equalsIgnoreCase(ambienteAtual.getNome());
    }

    public double getProbabilidade() {
        return probabilidade;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract void executar(Personagem jogador, Ambiente local);
}