package jogo.eventos;

import jogo.ambientes.Ambiente;
import jogo.ambientes.AmbienteFloresta;
import jogo.itens.*;
import jogo.personagens.*;

public class RegistroDeEventos {

    public static void carregarEventos(GerenciadorDeEventos gerenciador) {

        // Lobo
        EventoCriatura lobo = new EventoCriatura(
                "Ataque de Lobo",
                "Um lobo faminto salta dos arbustos!",
                0.6,
                "vida",
                gerenciador.getJogador().emLocalizacao("Floresta"),
                "Lobo Selvagem", 5
        );

        // Urso
        EventoCriatura urso = new EventoCriatura(
                "Emboscada de Urso",
                "Você despertou um urso hibernando nas montanhas!",
                0.4,
                "vida",
                gerenciador.getJogador().emLocalizacao("Montanha"),
                "Urso Cinzento", 10
        );

        // Tempestade
        EventoClimatico chuva = new EventoClimatico(
                "Tempestade Forte",
                "Uma tempestade repentina alaga o terreno.",
                0.3,
                "energia0", true, "Frio e Umidade", 5) {
            @Override
            public void executar(Personagem jogador, Ambiente ambiente) {
                System.out.println("🌧️ A tempestade te desgasta. Você perde energia.");
                jogador.gastarEnergia(4);
            }
        };

        // Doença
        EventoDoencaFerimento intox = new EventoDoencaFerimento(
                "Intoxicação",
                "Você consumiu algo contaminado e se sente mal.",
                0.4,
                "vida",
                true,
                "Intoxicação Alimentar",
                20,
                10,
                true
        );

        // Ferramenta rara
        EventoDescoberta achado = new EventoDescoberta(
                "Achado Inesperado",
                "Você encontra um objeto abandonado em meio aos escombros.",
                0.5,
                "nenhum",
                gerenciador.getJogador().emLocalizacao("Ruínas"),
                "Ferramenta",
                new Ferramenta("Lanterna", 0.5, 50, "Iluminação", 30),
                false
        );

        //Registro no gerenciador
        gerenciador.adicionarEvento(lobo);
        gerenciador.adicionarEvento(urso);
        gerenciador.adicionarEvento(chuva);
        gerenciador.adicionarEvento(intox);
        gerenciador.adicionarEvento(achado);
    }
}
