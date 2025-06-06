package jogo.sistema;

import jogo.itens.*;
import jogo.personagens.Personagem;

import java.util.*;

public class Artesanato {
    private List<Receita> receitas;

    public Artesanato() {
        this.receitas = new ArrayList<>();
        carregarReceitas();
    }

    private void carregarReceitas() {
        // Receita 1: Lança de Madeira
        Map<String, Integer> ingLanc = new HashMap<>();
        ingLanc.put("Galho Pontudo", 1);
        ingLanc.put("Cipó", 1);
        receitas.add(new Receita(ingLanc, new Arma("Lança de Madeira", 0.8, 30, "Lança", 12, 12)));

        // Receita 2: Machado Rústico
        Map<String, Integer> ingMach = new HashMap<>();
        ingMach.put("Galho Pontudo", 1);
        ingMach.put("Rocha", 1);
        ingMach.put("Cipó", 1);
        receitas.add(new Receita(ingMach, new Ferramenta("Machado Rústico", 2.5, 50, "Machado", 30)));
    }

    public void mostrarInterfaceDeCriacao(Personagem jogador) {
        System.out.println("\n--- Criação de Itens ---");
        List<Receita> receitasPossiveis = new ArrayList<>();

        for (Receita receita : receitas) {
            if (podeCriar(receita, jogador.getInventario())) {
                receitasPossiveis.add(receita);
            }
        }

        if (receitasPossiveis.isEmpty()) {
            System.out.println("Você não tem materiais suficientes para criar nada no momento.");
            return;
        }

        System.out.println("Você pode criar os seguintes itens:");
        for (int i = 0; i < receitasPossiveis.size(); i++) {
            System.out.println((i + 1) + ". " + receitasPossiveis.get(i).getResultado().getNome());
        }
        System.out.println("0. Voltar");
        System.out.print("O que você deseja criar? ");

        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= receitasPossiveis.size()) {
            criarItem(receitasPossiveis.get(escolha - 1), jogador);
        }
    }

    private boolean podeCriar(Receita receita, Inventario inventario) {
        for (Map.Entry<String, Integer> ingrediente : receita.getIngredientes().entrySet()) {
            Item itemNoInventario = inventario.buscarItem(ingrediente.getKey());
            if (itemNoInventario == null || itemNoInventario.getDurabilidade() < ingrediente.getValue()) {
                return false;
            }
        }
        return true;
    }

    private void criarItem(Receita receita, Personagem jogador) {
        // Remover ingredientes
        for (Map.Entry<String, Integer> ingrediente : receita.getIngredientes().entrySet()) {
            Item item = jogador.getInventario().buscarItem(ingrediente.getKey());
            item.reduzirDurabilidade(ingrediente.getValue());
            if (item.getDurabilidade() <= 0) {
                jogador.getInventario().removerItem(item.getNome());
            }
        }

        // Adicionar resultado
        Item itemCriado = receita.getResultado();
        jogador.getInventario().adicionarItem(itemCriado);
        System.out.println("Você criou: " + itemCriado.getNome() + "!");
    }
}