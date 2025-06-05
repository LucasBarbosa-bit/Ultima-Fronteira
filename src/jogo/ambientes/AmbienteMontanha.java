package jogo.ambientes;

import java.util.List;
import jogo.itens.*;
import jogo.personagens.Personagem;

public class AmbienteMontanha extends Ambiente {

    public AmbienteMontanha() {
        super("Montanha", "Região íngreme, clima instável e poucos alimentos naturais.", 5);

        super.recursosDisponiveis.add(new Agua(true, 1.0)); //neve
        super.recursosDisponiveis.add(new Alimento("Raiz de Montanha", 0.3, 5, 10, true));
        super.recursosDisponiveis.add(new Remedio("Folha de Altitude", 0.1, 3, "Planta", "Aumenta resistência ao frio"));
        super.recursosDisponiveis.add(new Material("Rocha", 3.0, 100, "pedra", 100));
        super.recursosDisponiveis.add(new Material("Gelo", 1.0, 10, "gelo", 10));
        super.recursosDisponiveis.add(new Ferramenta("Pederneira", 0.2, 50, "Isqueiro Natural",40));
    }

    @Override
    public void explorar(Personagem jogador) {
        if(jogador.getEnergia() > 20){
            System.out.println("Explorando montanha... gasto alto de energia!");
            jogador.perderVida(5);
            jogador.gastarEnergia(20);
        }
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
