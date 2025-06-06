package jogo.ambientes;

import java.util.List;

import jogo.itens.*;
import jogo.personagens.Personagem;

public class AmbienteRuinas extends Ambiente {

    public AmbienteRuinas() {
        super("Ruínas", "Estruturas antigas com itens valiosos, mas instáveis.", 3);
        super.recursosDisponiveis.add(new Agua(true, 2));
        super.recursosDisponiveis.add(new Alimento("Cogumelo", 1.0,2, 5, true));
        super.recursosDisponiveis.add(new Alimento("Cebola", 0.1,1, 1, true));
        super.recursosDisponiveis.add(new Alimento("Tomate", 0.5,1, 5, false));
        super.recursosDisponiveis.add(new Alimento("Cereal", 0.1,5, 1, false));
        super.recursosDisponiveis.add(new Alimento("Cereal", 0.1,2, 5, true));

        super.recursosDisponiveis.add(new Remedio("Analgésico", 0.1, 20, "Análgésico", "Remove as dores"));
        super.recursosDisponiveis.add(new Remedio("Antibiótico", 0.1, 20, "Antibiótico", "Remove a doença"));
        super.recursosDisponiveis.add(new Remedio("Bandagem", 0.1, 20, "Bandagem", "Faz com que o corpo seja mais resistente"));

        super.recursosDisponiveis.add(new Ferramenta("Machado", 2.5, 100, "Machado", 50));
        super.recursosDisponiveis.add(new Ferramenta("Isqueiro", 0.1, 100, "Isqueiro", 100));
        super.recursosDisponiveis.add(new Ferramenta("Escudo", 2.0, 100, "Escudo", 100));

        super.recursosDisponiveis.add(new Arma("Espada", 3.0, 100, "Lamina", 10,10));
        super.recursosDisponiveis.add(new Arma("Machado de Batalha", 5.0, 100, "Lamina", 15, 8));

    }

    @Override
    public void explorar(Personagem jogador) {
        if(jogador.getEnergia() > 10){
            System.out.println("Explorando as ruínas... riscos de desabamento.");
            jogador.perderVida(2);
            jogador.gastarEnergia(10);

            explorarComRecursos(jogador);
        }

        // mais da lógica de exploração nesse ambiente
    }

    @Override
    public void gerarEvento() {
        System.out.println("Algo se move entre os escombros... aliado ou inimigo?");

        // futura implementação de eventos nesse ambiente
    }

    @Override
    public void modificarClima() {
        System.out.println("As ruínas oferecem abrigo contra o clima.");

        // evento de modificação do clima?

    }
}
