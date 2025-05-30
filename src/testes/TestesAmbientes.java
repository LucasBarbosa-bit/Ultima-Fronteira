package testes;

import jogo.ambientes.Localizador;
import jogo.eventos.GerenciadorDeEventos;
import jogo.itens.Agua;
import jogo.personagens.Personagem;

public class TestesAmbientes {
    public static void main(String[] args) {
        Personagem jogador = new Personagem("Zoiudo");
        System.out.println("Bem-vindo " + jogador.getNome() + ", " + jogador.getInventario().pesoAtual() + " kg de carga inicial.");

        Agua agua = new Agua(true, 1);
        jogador.adicionarItem(agua);
        System.out.println(jogador.getInventario().pesoAtual() + " kg de carga atual, de " +  jogador.getInventario().getPesoMaximo() + " kg de carga máxima." );

        jogador.mover();

        System.out.println(jogador.localizacao());

        jogador.mover("Caverna");

        System.out.println(jogador.localizacao());
    }
}
