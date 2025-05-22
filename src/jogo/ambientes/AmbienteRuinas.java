package jogo.ambientes;

import java.util.List;
import jogo.itens.Item;
import jogo.personagens.Personagem;

public class AmbienteRuinas extends Ambiente {

    public AmbienteRuinas(List<Item> recursos) {
        super("Ruínas", "Estruturas antigas com itens valiosos, mas instáveis.", 3, recursos);
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("Explorando as ruínas... riscos de desabamento.");
        jogador.perderVida(3);
    }

    @Override
    public void gerarEvento() {
        System.out.println("Algo se move entre os escombros... aliado ou inimigo?");
    }

    @Override
    public void modificarClima() {
        System.out.println("As ruínas oferecem abrigo contra o clima.");
    }
}
