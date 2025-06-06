package jogo.sistema;

import jogo.itens.Item;
import java.util.Map;

public class Receita {
    private Map<String, Integer> ingredientes; // Nome do item e quantidade necessária
    private Item resultado;

    public Receita(Map<String, Integer> ingredientes, Item resultado) {
        this.ingredientes = ingredientes;
        this.resultado = resultado;
    }

    public Map<String, Integer> getIngredientes() {
        return ingredientes;
    }

    public Item getResultado() {
        return resultado;
    }
}