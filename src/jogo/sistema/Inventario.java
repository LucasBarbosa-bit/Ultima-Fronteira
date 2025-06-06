package jogo.sistema;

import jogo.itens.*;
import jogo.sistema.excecoes.InventarioCheioException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventario {
    private List<Item> itens;
    private double pesoMaximo;

    // ... construtor ...
    public Inventario(double pesoMaximo) {
        this.itens = new ArrayList<>();
        this.pesoMaximo = pesoMaximo;
    }

    public List<Item> getItens() {
        return itens;
    }

    // ... resto da classe sem alterações ...
    public void adicionarItem(Item item) throws InventarioCheioException {
        if (pesoAtual() + item.getPeso() > pesoMaximo) {
            throw new InventarioCheioException("Não foi possível coletar " + item.getNome() + " (peso excedido).");
        }

        if (item instanceof Agua) {
            Optional<Item> aguaExistenteOpt = itens.stream()
                    .filter(i -> i instanceof Agua)
                    .findFirst();

            if (aguaExistenteOpt.isPresent()) {
                Agua aguaInventario = (Agua) aguaExistenteOpt.get();
                double pesoTotal = aguaInventario.getPeso() + item.getPeso();
                int novaDurabilidade = aguaInventario.getDurabilidade() + item.getDurabilidade();
                aguaInventario.setPeso(pesoTotal);
                aguaInventario.setDurabilidade(novaDurabilidade);
                if (!((Agua) item).getPureza()) {
                    aguaInventario.setPureza(false);
                }
                return;
            }
        }

        itens.add(item);
    }

    public boolean removerItem(String nome) {
        String nomeNormalizado = FerramentasDeTexto.normalizar(nome);
        return itens.removeIf(item -> FerramentasDeTexto.normalizar(item.getNome()).equals(nomeNormalizado));
    }

    public double pesoAtual() {
        return itens.stream().mapToDouble(Item::getPeso).sum();
    }

    public Item buscarItem(String nomeItem) {
        String nomeNormalizado = FerramentasDeTexto.normalizar(nomeItem);
        for (Item item : itens) {
            if (FerramentasDeTexto.normalizar(item.getNome()).equals(nomeNormalizado)) {
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