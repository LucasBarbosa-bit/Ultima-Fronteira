package jogo.ambientes;

import java.util.List;
import jogo.itens.*;
import jogo.personagens.Personagem;

public class AmbienteFloresta extends Ambiente {

    public AmbienteFloresta() {
        super("Floresta", "Vegetação densa e úmida, rica em recursos mas cheia de predadores.", 3);

        super.recursosDisponiveis.add(new Agua(false, 1.5));
        super.recursosDisponiveis.add(new Alimento("Fruta Silvestre", 0.1, 2, 3, true));
        super.recursosDisponiveis.add(new Alimento("Cogumelo Cinzento", 0.2, 5, 3, false));
        super.recursosDisponiveis.add(new Alimento("Raiz Comestível", 0.3, 5, 2, true));
        super.recursosDisponiveis.add(new Arma("Galho Pontudo", 0.3, 20, "Lança Improvisada", 10, 10));
        super.recursosDisponiveis.add(new Material("Madeira", 2.0, 100, "madeira", 40));
        super.recursosDisponiveis.add(new Material("Cipó", 0.2, 30, "fibra vegetal", 20));
        super.recursosDisponiveis.add(new Remedio("Erva Medicinal", 0.1, 10, "Planta", "Reduz febre e alivia dor"));
    }

    @Override
    public void explorar(Personagem jogador) {
        if (jogador.getEnergia() > 10){
            System.out.println("Explorando a floresta... gasta energia extra devido à vegetação densa.");
            jogador.gastarEnergia(10);

            explorarComRecursos(jogador);
        }
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
