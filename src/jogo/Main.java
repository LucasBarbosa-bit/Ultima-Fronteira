package jogo;

import jogo.ambientes.*;
import jogo.eventos.GerenciadorDeEventos;
import jogo.itens.Agua;
import jogo.itens.Item;
import jogo.personagens.Personagem;

import java.util.ArrayList;
import java.util.List;
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
        System.out.println("O peso de seu inventário é: " + jogador.getInventario().pesoAtual() + " kg de 20kg máximos.");
        System.out.println("Suas Condicoes de fome: " + jogador.getFome() + " /100");
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
        System.out.print("Digite o nome do ambiente: ");
        String nomeAmbiente = scanner.nextLine();
        scanner.close();

        jogador.mover(nomeAmbiente);


    }
}
