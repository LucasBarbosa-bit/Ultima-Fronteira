package jogo.ambientes;

import java.util.List;
import jogo.itens.Item;
import jogo.personagens.Personagem;

public class AmbienteCaverna extends Ambiente {

    public AmbienteCaverna(List<Item> recursos) {
        super("Caverna", "Ambiente escuro e úmido. Pode ser abrigo ou armadilha.", 4, recursos);
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("Explorando caverna... visibilidade baixa, use lanterna!");
        jogador.perderVida(2); // risco pequeno de ferimento
    }

    @Override
    public void gerarEvento() {
        System.out.println("⚠️ Você ouve sons estranhos ecoando pela caverna...");
    }

    @Override
    public void modificarClima() {
        System.out.println("💨 Correntes de ar mudam dentro da caverna.");
    }
}
