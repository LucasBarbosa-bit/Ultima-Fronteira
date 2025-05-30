package jogo.sistema;

import jogo.itens.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Item> itens;
    private double pesoMaximo;

    public Inventario(double pesoMaximo) {
        this.itens = new ArrayList<>();
        this.pesoMaximo = pesoMaximo;
    }

    public boolean adicionarItem(Item item) {
        if (pesoAtual() + item.getPeso() <= pesoMaximo) {
            itens.add(item);
            return true;
        }
        return false;
    }

    public boolean removerItem(String nome) {
        return itens.removeIf(item -> item.getNome().equalsIgnoreCase(nome));
    }

    public double pesoAtual() {
        return itens.stream().mapToDouble(Item::getPeso).sum();
    }

    public Item buscarItem(String nomeItem) {
        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                return item;
            }
        }
        return null;
    }

    public double getPesoMaximo() {
        return pesoMaximo;
    }
}
