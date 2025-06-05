package jogo.sistema;

import jogo.personagens.Criatura;
import jogo.personagens.Personagem;
import jogo.itens.Arma;

import java.util.Scanner;

public class Combate {
    public static void iniciar(Personagem jogador, Criatura inimigo) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nUm " + inimigo.getNome() + " apareceu! Prepare-se para lutar!");

        while (inimigo.estaViva() && jogador.getVida() > 0) {
            System.out.println("\n--- TURNO DO JOGADOR ---");
            System.out.println("1. Atacar com arma");
            System.out.println("2. Fugir");

            System.out.print("Escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

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
            } else if (escolha == 2) {
                System.out.println("Você foge do combate. O inimigo te atinge nas costas!");
                inimigo.atacar(jogador);
                jogador.gastarEnergia(20);
                break;
            }

            if (inimigo.estaViva()) {
                System.out.println("\n--- TURNO DO INIMIGO ---");
                inimigo.atacar(jogador);
            } else {
                System.out.println("Você derrotou o " + inimigo.getNome() + "!");
                jogador.descansar();
            }
        }

        if (jogador.getVida() <= 0) {
            System.out.println("Você foi derrotado em combate.");
            System.out.println("\n GAME OVER \n");
        }
    }
}
