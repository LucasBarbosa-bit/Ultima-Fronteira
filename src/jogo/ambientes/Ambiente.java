package jogo.ambientes;

import java.util.*;
import jogo.itens.Item;
import jogo.personagens.Personagem;
import jogo.sistema.excecoes.InventarioCheioException;

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

    public void explorarComRecursos(Personagem jogador) {
        System.out.println("Você começa a explorar o ambiente: " + nome + "...");
        Random rand = new Random();

        // A quantidade de itens encontrados agora é afetada pela habilidade do Rastreador
        int quantidade = (int) Math.round((rand.nextInt(3) + 1) * jogador.getChanceEncontrarRecursoModifier());

        if (recursosDisponiveis.isEmpty()) {
            System.out.println("Nada foi encontrado aqui.");
            return;
        }

        Set<Item> encontrados = new HashSet<>();
        for (int i = 0; i < quantidade && i < recursosDisponiveis.size(); i++) {
            Item item = recursosDisponiveis.get(rand.nextInt(recursosDisponiveis.size()));
            encontrados.add(item);
        }

        if (encontrados.isEmpty()) {
            System.out.println("Nada foi encontrado desta vez.");
            return;
        }

        System.out.println("Você encontrou:");
        int i = 1;
        List<Item> lista = new ArrayList<>(encontrados);
        for (Item item : lista) {
            System.out.println(i + ". " + item.getNome() + " (peso: " + item.getPeso() + ")");
            i++;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite os números dos itens que deseja coletar (ex: 1 3), ou deixe em branco para nenhum:");
        String resposta = scanner.nextLine();

        if (resposta.isBlank()) {
            System.out.println("Você decidiu não coletar nada.");
            return;
        }

        String[] escolhas = resposta.trim().split(" ");
        for (String s : escolhas) {
            try {
                int escolha = Integer.parseInt(s);
                if (escolha > 0 && escolha <= lista.size()) {
                    Item item = lista.get(escolha - 1);
                    try {
                        jogador.getInventario().adicionarItem(item);
                        System.out.println("Você coletou: " + item.getNome());
                    } catch (InventarioCheioException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Número inválido: " + s);
            }
        }
    }

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