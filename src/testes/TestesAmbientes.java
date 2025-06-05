package testes;

import jogo.ambientes.Localizador;
import jogo.eventos.GerenciadorDeEventos;
import jogo.itens.Agua;
import jogo.personagens.Personagem;

import javax.swing.*;

public class TestesAmbientes {
    public static void main(String[] args) {
        Personagem jogador = new Personagem("Zoiudo");
        System.out.println("Bem-vindo " + jogador.getNome() + ", " + jogador.getInventario().pesoAtual() + " kg de carga inicial.");

        Agua agua = new Agua(true, 1);

        Agua agua2 = new Agua(false, 10);

        jogador.getInventario().adicionarItem(agua2);

        jogador.getInventario().adicionarItem(agua);

        jogador.mostrarInventario();

        jogador.usarItem("Água");

        if (((Agua)jogador.getInventario().buscarItem("Água")).getPureza()){
            System.out.println("Pura");
        } else System.out.println("Contaminada");
    }
}
