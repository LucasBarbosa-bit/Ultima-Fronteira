package jogo.sistema;

import jogo.itens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventario {
    private List<Item> itens;
    private double pesoMaximo;

    public Inventario(double pesoMaximo) {
        this.itens = new ArrayList<>();
        this.pesoMaximo = pesoMaximo;
    }

    public boolean adicionarItem(Item item) {
        if (pesoAtual() + item.getPeso() > pesoMaximo) {
            return false; // Falha se o peso exceder o máximo
        }

        if (item instanceof Agua) {
            Agua aguaNova = (Agua) item;
            Optional<Item> aguaExistenteOpt = itens.stream()
                    .filter(i -> i instanceof Agua)
                    .findFirst();

            if (aguaExistenteOpt.isPresent()) {
                Agua aguaInventario = (Agua) aguaExistenteOpt.get();

                // Combina a água
                double pesoTotal = aguaInventario.getPeso() + aguaNova.getPeso();
                int novaDurabilidade = aguaInventario.getDurabilidade() + aguaNova.getDurabilidade();

                aguaInventario.setPeso(pesoTotal);
                aguaInventario.setDurabilidade(novaDurabilidade);

                // Se a água nova for impura, contamina toda a pilha
                if (!aguaNova.getPureza()) {
                    aguaInventario.setPureza(false);
                }
                return true;
            }
        }

        // Para outros itens ou se não houver água existente, adiciona normalmente
        itens.add(item);
        return true;
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
        System.out.println("\n--- Inventário ---");
        for (Item item : itens) {
            String detalhes = "- " + item.getNome() + " (Peso: " + String.format("%.2f", item.getPeso()) + ", Durabilidade: " + item.getDurabilidade();
            if (item instanceof Agua) {
                detalhes += ", Pureza: " + (((Agua) item).getPureza() ? "Potável" : "Contaminada");
            }
            detalhes += ")";
            System.out.println(detalhes);
        }
        System.out.println("\nPeso total: " + String.format("%.2f", pesoAtual()) + " / " + String.format("%.2f", pesoMaximo) + " kg");
        System.out.println("------------------\n");
    }

    public Arma getMelhorArma() {
        Arma melhor = null;
        for (Item item : itens) {
            if (item instanceof Arma) {
                Arma arma = (Arma) item;
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