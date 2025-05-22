package jogo.ambientes;

import java.util.List;
import jogo.itens.Item;
import jogo.personagens.Personagem;

public class AmbienteMontanha extends Ambiente {

    public AmbienteMontanha(List<Item> recursos) {
        super("Montanha", "Região íngreme, clima instável e poucos alimentos naturais.", 5, recursos);
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("Explorando montanha... gasto alto de energia!");
        jogador.perderVida(5); // exemplo: esforço causa desgaste
    }

    @Override
    public void gerarEvento() {
        System.out.println("Nevasca ou deslizamento podem ocorrer nas montanhas...");
    }

    @Override
    public void modificarClima() {
        System.out.println("Nevasca repentina cobre a montanha.");
    }
}
