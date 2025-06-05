package jogo.sistema;

import jogo.itens.*;

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
        if (item instanceof Agua) {
            Agua aguaNova = (Agua) item;
            for (Item item1 : itens){
                if (item instanceof Agua){
                    Agua aguaInventario = (Agua) item1;
                    double pesoAdicional = aguaNova.getPeso();

                    if (pesoAtual() + pesoAdicional  < pesoMaximo){
                        double pesoTotal = aguaInventario.getPeso() + aguaNova.getPeso();
                        int novaDurabilidade = aguaInventario.getDurabilidade() + aguaNova.getDurabilidade();
                        aguaInventario.setPeso(pesoTotal);
                        aguaInventario.setDurabilidade(novaDurabilidade);
                    }
                    if (!aguaNova.getPureza() && (aguaNova.getDurabilidade() > aguaInventario.getDurabilidade())){
                        aguaNova.setPureza(false);
                    }
                    return true;
                }
            }
        }
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


    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }
        System.out.println("\n--- Inventário ---\n");
        for (Item item : itens) {
            System.out.println("- " + item.getNome() + " (Peso: " + item.getPeso() + ", Durabilidade: " + item.getDurabilidade() + ")");
        }
        System.out.println("\nPeso total: " + String.format("%.2f", pesoAtual()) + " / " + String.format("%.2f", pesoMaximo) + " kg");
        System.out.println("------------------\n");
    }

    public Arma getMelhorArma() {
        Arma melhor = null;
        for (Item item : itens) {
            if (item instanceof Arma arma) {
                if (melhor == null || arma.getDano() > melhor.getDano()) {
                    melhor = arma;
                }
            }
        }
        return melhor;
    }


    public double getPesoMaximo() {
        return pesoMaximo;
    }
}
