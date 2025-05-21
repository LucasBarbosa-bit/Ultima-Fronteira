package jogo.ambientes;

import java.util.List;
import jogo.itens.Item;
import jogo.personagens.Personagem;

public class AmbienteLagoRio extends Ambiente {

    public AmbienteLagoRio(List<Item> recursos) {
        super("Lago/Rio", "Fonte de água e alimento, mas com riscos escondidos.", 2, recursos);
    }

    @Override
    public void explorar(Personagem jogador) {
        System.out.println("Explorando margem do rio... cuidado com o terreno lamacento.");
        jogador.descansar(); // fácil acesso a água, pode recuperar energia
    }

    @Override
    public void gerarEvento() {
        System.out.println("🌊 A correnteza aumenta... ou algo se move na água...");
    }

    @Override
    public void modificarClima() {
        System.out.println("⛈️ Chuva faz o nível da água subir.");
    }
}
