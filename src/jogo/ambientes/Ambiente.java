package jogo.ambientes;

import java.util.List;
import java.util.ArrayList;
import jogo.itens.Item;
import jogo.personagens.Personagem;

public abstract class Ambiente {
    protected String nome;
    protected String descricao;
    protected int dificuldadeExploracao;
    protected List<Item> recursosDisponiveis;

    public Ambiente(String nome, String descricao, int dificuldadeExploracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.dificuldadeExploracao = dificuldadeExploracao;
        this.recursosDisponiveis = new ArrayList<Item>();
    }

    public abstract void explorar(Personagem jogador);
    public abstract void gerarEvento();
    public abstract void modificarClima();

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Item> getRecursosDisponiveis() {
        return recursosDisponiveis;
    }
}
