package jogo.eventos;

import jogo.personagens.Personagem;
import jogo.ambientes.Ambiente;
import jogo.itens.Item;

public class EventoDescoberta extends Evento {
    private String tipoDescoberta;
    private Item recursoEncontrado;
    private boolean requerHabilidade;

    public EventoDescoberta(String nome, String descricao, double probabilidade, String impacto,
                            String condicaoAtivacao, String tipoDescoberta, Item recursoEncontrado,
                            boolean requerHabilidade) {
        super(nome, descricao, probabilidade, impacto, condicaoAtivacao);
        this.tipoDescoberta = tipoDescoberta;
        this.recursoEncontrado = recursoEncontrado;
        this.requerHabilidade = requerHabilidade;
    }

    @Override
    public void executar(Personagem jogador, Ambiente local) {
        System.out.println("🔎 Descoberta: " + tipoDescoberta + ". Você encontrou " + recursoEncontrado.getNome());
        jogador.getInventario().adicionarItem(recursoEncontrado);
    }
}
