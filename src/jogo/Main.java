package jogo;

import jogo.personagens.Personagem;

public class Main {
    public static void main(String[] args) {
        Personagem jogador = new Personagem("Lucas");
        System.out.println("Bem-vindo, " + jogador.getInventario().pesoAtual() + " kg de carga inicial.");
    }
}
