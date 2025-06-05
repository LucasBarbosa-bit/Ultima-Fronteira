package jogo;

import jogo.itens.Alimento;
import jogo.personagens.Personagem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Bem vindo ao Ultima Fronteira!");
        System.out.println("Iniciando o jogo...");
        System.out.println();

        System.out.println("Criação de Personagem...");
        System.out.print("Digite o nome do seu personagem: ");
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        System.out.println();

        Personagem jogador = new Personagem(nome);
        System.out.println("Personagem criado!");
        System.out.println("Seja Bem-vindo " + jogador.getNome() + "!");
        System.out.println();

        System.out.println("Suas condições de vida são: " + jogador.getVida() + " /100");
        System.out.println("Suas Condicoes de fome: " + jogador.getAlimentacao() + " /100");
        System.out.println("Suas Condicoes de sede: " + jogador.getSede() + " /100");
        System.out.println();

        System.out.println("Você acabou de ser jogado de um avião em uma ilha ...");
        System.out.println("Não parece haver ninguém aqui, mas você pode seguir em frente.");
        System.out.println("Um mapa pode te ajudar! Deve haver alguma coisa por aqui...");
        System.out.println("AQUI ESTÁ, encontramos um Baú!!!");
        System.out.println("Você pode encontrar no Baú algumas coisas para te ajudar a sobreviver.");
        System.out.println("Tem um mapa aqui... Por essas informações para que você está em um(a) " + jogador.localizacao());

        System.out.println();
        System.out.println("Parece que existe alguns lugares próximos: ");

        jogador.getLocalizador().mostrarAmbientesDisponiveis();

        System.out.println();
        System.out.println("Você pode escolher qual ambiente você quer ir.");
        System.out.print("Digite o nº do ambiente que você deseja ir: ");
        int nomeAmbiente1 = scanner.nextInt();
        scanner.nextLine();

        switch (nomeAmbiente1) {
            case 1: jogador.mover("Ruínas"); break;
            case 2: jogador.mover("Floresta"); break;
            case 3: jogador.mover("Caverna"); break;
            case 4: jogador.mover("Montanha"); break;
            case 5: jogador.mover("Lago e Rio"); break;
            default: System.out.println("Opção inválida!");
        }

       while (jogador.getVida() > 0 && jogador.getAlimentacao() > 0 && jogador.getSede() > 0) {
           System.out.println("\nVida: " + jogador.getVida() + " | Alimentação: " + jogador.getAlimentacao() +
                   " | Sede: " + jogador.getSede() + " | Energia: " + jogador.getEnergia());

           System.out.println("\n📋 Escolha sua ação:");
           System.out.println("1. Explorar ambiente e ver oque é possivel encontrar");
           System.out.println("2. Usar item que você deseja");
           System.out.println("3. Ir para um outro lugar");
           System.out.println("4. Ver inventário");
           System.out.println("5. Ver histórico de ambientes");
           System.out.println("0. Sair do jogo");

           System.out.print(" --> Sua opção : ");

           int opcao = scanner.nextInt();
           scanner.nextLine();

           switch (opcao) {
               case 1: jogador.explorar(); break;
               case 2:
                   System.out.print("Digite o nome do item a usar: ");
                   String nomeItem = scanner.nextLine();
                   jogador.usarItem(nomeItem);
                   break;
               case 3:
                   System.out.println("\nVocê está em: " + jogador.localizacao());
                   System.out.println();
                   jogador.getLocalizador().mostrarAmbientesDisponiveis();
                   System.out.println("Você pode escolher qual ambiente você quer ir.");
                   System.out.print("Digite o nº do ambiente que você deseja ir: ");
                   int nomeAmbiente = scanner.nextInt();
                   scanner.nextLine();
                   switch (nomeAmbiente1) {
                       case 1: jogador.mover("Ruínas"); break;
                       case 2: jogador.mover("Floresta"); break;
                       case 3: jogador.mover("Caverna"); break;
                       case 4: jogador.mover("Montanha"); break;
                       case 5: jogador.mover("Lago e Rio"); break;
                       default: System.out.println("Opção inválida!");
                   }

               case 4: jogador.mostrarInventario(); break;
               case 5: jogador.getLocalizador().mostrarHistorico(); break;
               case 0: System.exit(0);
           }
       }


    }
}
