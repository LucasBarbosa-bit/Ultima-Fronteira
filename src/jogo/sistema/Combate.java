package jogo.sistema;

import jogo.personagens.Criatura;
import jogo.personagens.Personagem;
import jogo.itens.Arma;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Combate {
    public static void iniciar(Personagem jogador, Criatura inimigo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nUm " + inimigo.getNome() + " apareceu! Prepare-se para lutar!");
        jogador.perderSanidade(3);

        while (inimigo.estaViva() && jogador.getVida() > 0) {

            boolean acaoValida = false;
            while(!acaoValida) {
                System.out.println("\n--- TURNO DO JOGADOR ---");
                System.out.println("Vida: " + jogador.getVida() + " | Inimigo: " + inimigo.getVida());
                System.out.println("1. Atacar com arma");
                System.out.println("2. Fugir");

                System.out.print("Escolha: ");
                int escolha = -1;

                try {
                    escolha = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, digite um número.");
                } finally {
                    scanner.nextLine(); // Limpa o buffer do scanner
                }

                if (escolha == 1) {
                    Arma arma = jogador.getInventario().getMelhorArma();
                    if (arma == null) {
                        System.out.println("Você não tem nenhuma arma! Soco fraco (-1 de dano)");
                        inimigo.receberDano(1);
                    } else {
                        System.out.println("Você usa " + arma.getNome() + " para atacar!");
                        inimigo.receberDano(arma.getDano());
                        arma.reduzirDurabilidade(1);
                    }
                    acaoValida = true; // Ação foi válida, sai do loop de escolha
                } else if (escolha == 2) {
                    System.out.println("Você tenta fugir, mas o inimigo te atinge pelas costas!");
                    inimigo.atacar(jogador);
                    jogador.gastarEnergia(20);
                    // Sai do loop de combate inteiro
                    return;
                } else {
                    System.out.println("Opção inválida! Escolha 1 para atacar ou 2 para fugir.");
                }
            }


            if (inimigo.estaViva()) {
                System.out.println("\n--- TURNO DO INIMIGO ---");
                inimigo.atacar(jogador);
                jogador.perderSanidade(2);
            } else {
                System.out.println("Você derrotou o " + inimigo.getNome() + "!");
                jogador.descansar();
            }
        }

        if (jogador.getVida() <= 0) {
            System.out.println("Você foi derrotado em combate.");
        }
    }
}