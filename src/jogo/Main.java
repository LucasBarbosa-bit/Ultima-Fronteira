package jogo;

import jogo.eventos.RegistroDeEventos;
import jogo.personagens.Mecanico;
import jogo.personagens.Personagem;
import jogo.personagens.Rastreador;
import jogo.personagens.SobreviventeNato;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nBem vindo ao Ultima Fronteira!");
        System.out.println("Iniciando o jogo...");
        System.out.println();

        System.out.println("Criação de Personagem...");
        System.out.print("Digite o nome do seu personagem: ");
        String nome = scanner.nextLine();
        System.out.println();

        Personagem jogador = null;
        while (jogador == null) {
            System.out.println("Escolha sua classe:");
            System.out.println("1. Sobrevivente Nato (Mais resistente à fome e sede)");
            System.out.println("2. Rastreador (Maior chance de encontrar recursos)");
            System.out.println("3. Mecânico (Capaz de criar itens avançados)");
            System.out.print("--> Sua opção: ");

            int classeEscolhida = -1;
            try {
                classeEscolhida = scanner.nextInt();
            } catch (InputMismatchException e) {
                // Apenas ignora
            } finally {
                scanner.nextLine();
            }

            switch (classeEscolhida) {
                case 1:
                    jogador = new SobreviventeNato(nome);
                    break;
                case 2:
                    jogador = new Rastreador(nome);
                    break;
                case 3:
                    jogador = new Mecanico(nome);
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma classe.");
            }
        }

        RegistroDeEventos.carregarEventos(jogador.getLocalizador().getGerenciadorDeEventos());

        System.out.println("\nPersonagem criado!");
        System.out.println("Seja Bem-vindo " + jogador.getNome() + "!");
        System.out.println();
        System.out.println("Você está em um(a) " + jogador.localizacao());

        int diasSobrevivendo = 1;
        final int DIAS_PARA_VENCER = 20;
        boolean vitoria = false;

        while (jogador.getVida() > 0 && jogador.getAlimentacao() > 0 && jogador.getSede() > 0 && jogador.getSanidade() > 0) {
            System.out.println("\n-------------------- Dia " + diasSobrevivendo + " --------------------");
            System.out.println("Vida: " + jogador.getVida() + " | Fome: " + jogador.getAlimentacao() +
                    " | Sede: " + jogador.getSede() + " | Energia: " + jogador.getEnergia() + " | Sanidade: " + jogador.getSanidade());

            System.out.println("\n--- Ações (Passam o dia) ---");
            System.out.println("1. Explorar ambiente");
            System.out.println("2. Mover para outro ambiente");
            System.out.println("3. Criar itens");
            System.out.println("4. Descansar");
            System.out.println("\n--- Ações Livres ---");
            System.out.println("5. Usar item");
            System.out.println("6. Ver inventário");
            System.out.println("7. Ver histórico de ambientes");
            System.out.println("8. Remover item do inventário");

            System.out.println("\n0. Sair do jogo");
            System.out.print(" --> Sua opção : ");


            int opcao = -1;
            boolean turnoPassou = false;

            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Por favor, digite um número.");
            } finally {
                scanner.nextLine();
            }

            switch (opcao) {
                case 1: // Explorar
                    jogador.explorar();
                    turnoPassou = true;
                    break;
                case 2: // Mover
                    boolean moveu = selecionarDestino(jogador, scanner);
                    turnoPassou = moveu;
                    break;
                case 3: // Criar Itens
                    // Adicionar lógica para verificar se a criação foi bem-sucedida antes de passar o turno
                    jogador.criarItem();
                    turnoPassou = true;
                    break;
                case 4: // Descansar
                    boolean descansou = jogador.tentarDescansar();
                    turnoPassou = descansou;
                    break;
                case 5: // Usar Item (Ação Livre)
                    jogador.mostrarMenuParaUsarItem();
                    break;
                case 6: // Ver Inventário
                    jogador.mostrarInventario();
                    break;
                case 7: // Ver Histórico
                    jogador.getLocalizador().mostrarHistorico();
                    break;
                case 8: // Remover Item
                    System.out.print("Digite o nome do item a remover: ");
                    String nomeItemRemover = scanner.nextLine();
                    jogador.removerItem(nomeItemRemover);
                    break;
                case 0:
                    System.out.println("Saindo do jogo. Até a próxima!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção não reconhecida.");
                    break;
            }

            if (turnoPassou) {
                diasSobrevivendo++;
                jogador.passarTurno();
                if (diasSobrevivendo > DIAS_PARA_VENCER) {
                    vitoria = true;
                    break;
                }
            }
        }

        System.out.println("\n--------------------");
        if (vitoria) {
            System.out.println("      VITÓRIA!");
            System.out.println("--------------------");
            System.out.println("Você sobreviveu por " + (diasSobrevivendo - 1) + " dias!");
            System.out.println("Contra todas as probabilidades, você dominou este ambiente hostil e provou ser um verdadeiro sobrevivente.");
        } else {
            System.out.println("      GAME OVER");
            System.out.println("--------------------");
            System.out.println("Você sobreviveu por " + (diasSobrevivendo - 1) + " dias.");
            if (jogador.getVida() <= 0) {
                System.out.println("Seus ferimentos foram graves demais.");
            } else if (jogador.getAlimentacao() <= 0) {
                System.out.println("A fome o consumiu.");
            } else if (jogador.getSede() <= 0) {
                System.out.println("A desidratação foi implacável.");
            } else if (jogador.getSanidade() <= 0) {
                System.out.println("Sua mente cedeu aos horrores da ilha.");
            }
        }
    }

    private static boolean selecionarDestino(Personagem jogador, Scanner scanner) {
        while (true) {
            System.out.println("\nVocê está em: " + jogador.localizacao());
            System.out.println();
            jogador.getLocalizador().mostrarAmbientesDisponiveis();
            System.out.println("Para qual ambiente você deseja ir? (Digite 0 para cancelar)");
            System.out.print("Digite o nº do ambiente: ");

            int numAmbiente = -1;
            try {
                numAmbiente = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
            } finally {
                scanner.nextLine();
            }

            if (numAmbiente == 0) {
                System.out.println("Movimento cancelado.");
                return false; // Retorna false para não passar o turno
            }

            switch (numAmbiente) {
                case 1: jogador.mover("Ruínas"); return true;
                case 2: jogador.mover("Floresta"); return true;
                case 3: jogador.mover("Caverna"); return true;
                case 4: jogador.mover("Montanha"); return true;
                case 5: jogador.mover("Lago/Rio"); return true;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}