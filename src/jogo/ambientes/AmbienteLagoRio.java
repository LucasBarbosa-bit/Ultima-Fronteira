package jogo.ambientes;

import java.util.List;
import jogo.itens.*;
import jogo.personagens.Personagem;

public class AmbienteLagoRio extends Ambiente {

    public AmbienteLagoRio() {
        super("Lago/Rio", "Fonte de água e alimento, mas com riscos escondidos.", 2);
        super.recursosDisponiveis.add(new Agua(true, 2.0));
        super.recursosDisponiveis.add(new Alimento("Peixe Cru", 0.5, 5, 15, false));
        super.recursosDisponiveis.add(new Alimento("Peixe Seco", 0.4, 15, 18, true));
        super.recursosDisponiveis.add(new Ferramenta("Linha de Pesca", 0.1, 20, "Ferramenta de Pesca", 20));
        super.recursosDisponiveis.add(new Material("Argila", 1.0, 50, "barro", 30));
        super.recursosDisponiveis.add(new Remedio("Folha Antisséptica", 0.1, 10, "Planta", "Cicatriza pequenos cortes"));
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("Explorando margem do rio... cuidado com o terreno lamacento.");
        jogador.descansar();
    }

    @Override
    public void gerarEvento() {
        System.out.println("A correnteza aumenta... ou algo se move na água...");
    }

    @Override
    public void modificarClima() {
        System.out.println("Chuva faz o nível da água subir.");
    }
}
