package jogo.ambientes;

import jogo.itens.*;
import jogo.personagens.Personagem;

public class AmbienteCaverna extends Ambiente {

    public AmbienteCaverna() {
        super("Caverna", "Ambiente escuro e úmido. Pode ser abrigo ou armadilha.", 4);

        super.recursosDisponiveis.add(new Agua(false, 1.0));
        super.recursosDisponiveis.add(new Alimento("Inseto Crocante", 0.1, 2, 2, false));
        super.recursosDisponiveis.add(new Material("Cristal Bruto", 3.0, 100, "mineral", 100));
        super.recursosDisponiveis.add(new Material("Rocha Escura", 2.5, 100, "pedra", 100));
        super.recursosDisponiveis.add(new Ferramenta("Tocha Improvisada", 1.5, 50, "Tocha", 15));
        super.recursosDisponiveis.add(new Remedio("Mofo Curativo", 0.1, 5, "Fungo", "Evita infecções"));
        super.recursosDisponiveis.add(new Arma("Adaga Rústica", 0.5, 30, "Lamina", 6, 1));
    }

    @Override
    public void explorar(Personagem jogador) {
        if (jogador.getEnergia() > 20){
            System.out.println("Explorando caverna... visibilidade baixa, a escuridão é sufocante.");
            jogador.perderSanidade(5); // Custo de sanidade por estar na escuridão
            jogador.gastarEnergia(20);

            explorarComRecursos(jogador);
        } else {
            System.out.println("Você está exausto demais para se aventurar na escuridão.");
        }
    }

    @Override
    public void gerarEvento() {
        System.out.println("Você ouve sons estranhos ecoando pela caverna...");
    }

    @Override
    public void modificarClima() {
        System.out.println("Correntes de ar mudam dentro da caverna.");
    }
}