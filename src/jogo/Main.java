package jogo;

import jogo.eventos.RegistroDeEventos;
import jogo.personagens.Personagem;

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

        Personagem jogador = new Personagem(nome);
        RegistroDeEventos.carregarEventos(jogador.getLocalizador().getGerenciadorDeEventos());

        System.out.println("Personagem criado!");
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

            System.out.println("\n📋 Escolha sua ação:");
            System.out.println("1. Explorar ambiente");
            System.out.println("2. Usar item");
            System.out.println("3. Mover para outro ambiente");
            System.out.println("4. Ver inventário");
            System.out.println("5. Remover item do inventário");
            System.out.println("6. Ver histórico de ambientes");
            System.out.println("7. Criar itens"); // Nova opção
            System.out.println("0. Sair do jogo");

            System.out.print(" --> Sua opção : ");

            int opcao = -1;
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Por favor, digite um número.");
            } finally {
                scanner.nextLine();
            }

            switch (opcao) {
                case 1:
                    jogador.explorar();
                    jogador.sede(-3);
                    jogador.fome(2);
                    break;
                case 2:
                    System.out.print("Digite o nome do item a usar: ");
                    String nomeItemUsar = scanner.nextLine();
                    jogador.usarItem(nomeItemUsar);
                    jogador.gastarEnergia(2);
                    break;
                case 3:
                    System.out.println("\nVocê está em: " + jogador.localizacao());
                    System.out.println();
                    jogador.getLocalizador().mostrarAmbientesDisponiveis();
                    System.out.println("Para qual ambiente você deseja ir?");
                    System.out.print("Digite o nº do ambiente: ");
                    int numAmbiente = scanner.nextInt();
                    scanner.nextLine();
                    switch (numAmbiente) {
                        case 1: jogador.mover("Ruínas"); break;
                        case 2: jogador.mover("Floresta"); break;
                        case 3: jogador.mover("Caverna"); break;
                        case 4: jogador.mover("Montanha"); break;
                        case 5: jogador.mover("Lago/Rio"); break;
                        default: System.out.println("Opção inválida!");
                    }
                    break;
                case 4:
                    jogador.mostrarInventario();
                    break;
                case 5:
                    System.out.print("Digite o nome do item a remover: ");
                    String nomeItemRemover = scanner.nextLine();
                    jogador.removerItem(nomeItemRemover);
                    break;
                case 6:
                    jogador.getLocalizador().mostrarHistorico();
                    break;
                case 7:
                    jogador.criarItem(); // Nova ação
                    jogador.gastarEnergia(15); // Crafting consome energia
                    break;
                case 0:
                    System.out.println("Saindo do jogo. Até a próxima!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção não reconhecida.");
                    break;
            }

            diasSobrevivendo++;
            if (diasSobrevivendo > DIAS_PARA_VENCER) {
                vitoria = true;
                break;
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
}