package jogo.ambientes;

import java.util.List;
import jogo.itens.Item;
import jogo.personagens.Personagem;

public class AmbienteFloresta extends Ambiente {

    public AmbienteFloresta(List<Item> recursos) {
        super("Floresta", "Vegetação densa e úmida, rica em recursos mas cheia de predadores.", 3, recursos);
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("Explorando a floresta... gasta energia extra devido à vegetação densa.");
        jogador.descansar(); // só como exemplo: pode ser mudar energia, encontrar item etc.
    }

    @Override
    public void gerarEvento() {
        System.out.println("Evento aleatório da floresta pode ocorrer aqui...");
    }

    @Override
    public void modificarClima() {
        System.out.println("Clima na floresta muda: começa a chover forte!");
    }
}
