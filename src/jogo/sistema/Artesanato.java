package jogo.sistema;

import jogo.itens.*;
import jogo.personagens.Mecanico;
import jogo.personagens.Personagem;
import jogo.sistema.excecoes.InventarioCheioException;

import java.util.*;

public class Artesanato {
    private List<Receita> receitasComuns;
    private List<Receita> receitasMecanico; // Receitas especiais

    public Artesanato() {
        this.receitasComuns = new ArrayList<>();
        this.receitasMecanico = new ArrayList<>();
        carregarReceitas();
    }

    private void carregarReceitas() {
        // --- Receitas Comuns ---
        Map<String, Integer> ingLanc = new HashMap<>();
        ingLanc.put("Galho Pontudo", 1);
        ingLanc.put("Cipó", 1);
        receitasComuns.add(new Receita(ingLanc, new Arma("Lança de Madeira", 0.8, 30, "Lança", 12, 12)));

        Map<String, Integer> ingMach = new HashMap<>();
        ingMach.put("Galho Pontudo", 1);
        ingMach.put("Rocha", 1);
        ingMach.put("Cipó", 1);
        receitasComuns.add(new Receita(ingMach, new Ferramenta("Machado Rústico", 2.5, 50, "Machado", 30)));

        // --- Receitas de Mecânico ---
        Map<String, Integer> ingEscudo = new HashMap<>();
        ingEscudo.put("Madeira", 2);
        ingEscudo.put("Cipó", 2);
        receitasMecanico.add(new Receita(ingEscudo, new Ferramenta("Escudo de Madeira", 3.0, 100, "Escudo", 100)));
    }

    public void mostrarInterfaceDeCriacao(Personagem jogador) {
        System.out.println("\n--- Criação de Itens ---");
        List<Receita> receitasPossiveis = new ArrayList<>();

        for (Receita receita : receitasComuns) {
            if (podeCriar(receita, jogador.getInventario())) {
                receitasPossiveis.add(receita);
            }
        }

        if (jogador instanceof Mecanico) {
            for (Receita receita : receitasMecanico) {
                if (podeCriar(receita, jogador.getInventario())) {
                    receitasPossiveis.add(receita);
                }
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
        Item itemCriado = receita.getResultado();
        Inventario inventario = jogador.getInventario();

        // 1. Verifica se há espaço ANTES de consumir os ingredientes
        if (inventario.pesoAtual() + itemCriado.getPeso() > inventario.getPesoMaximo()) {
            System.out.println("Falha na criação: Não há espaço suficiente no inventário para " + itemCriado.getNome() + ".");
            return;
        }

        // 2. Se houver espaço, consome os ingredientes
        for (Map.Entry<String, Integer> ingrediente : receita.getIngredientes().entrySet()) {
            Item item = inventario.buscarItem(ingrediente.getKey());
            item.reduzirDurabilidade(ingrediente.getValue());
            if (item.getDurabilidade() <= 0) {
                inventario.removerItem(item.getNome());
            }
        }

        // 3. Adiciona o item criado. Envolvemos em try-catch para o compilador.
        try {
            inventario.adicionarItem(itemCriado);
            System.out.println("Você criou: " + itemCriado.getNome() + "!");
        } catch (InventarioCheioException e) {
            // Esta exceção não deve mais acontecer aqui devido à verificação acima,
            // mas o tratamento é mantido por segurança.
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}