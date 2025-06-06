package jogo.eventos;

import jogo.ambientes.Ambiente;
import jogo.itens.*;
import jogo.personagens.*;

public class RegistroDeEventos {

    public static void carregarEventos(GerenciadorDeEventos gerenciador) {

        // Lobo - Só pode ocorrer na Floresta
        EventoCriatura lobo = new EventoCriatura(
                "Ataque de Lobo",
                "Um lobo faminto salta dos arbustos!",
                0.6,
                "vida",
                "Floresta", // Localização requerida
                "Lobo Selvagem", 5
        );

        // Urso - Só pode ocorrer na Montanha
        EventoCriatura urso = new EventoCriatura(
                "Emboscada de Urso",
                "Você despertou um urso hibernando!",
                0.4,
                "vida",
                "Montanha", // Localização requerida
                "Urso Cinzento", 10
        );

        // Tempestade - Pode ocorrer em qualquer ambiente externo (localização null)
        EventoClimatico chuva = new EventoClimatico(
                "Tempestade Forte",
                "Uma tempestade repentina alaga o terreno.",
                0.3,
                "energia",
                null, // Pode ocorrer em qualquer lugar
                "Frio e Umidade", 5) {
            @Override
            public void executar(Personagem jogador, Ambiente ambiente) {
                System.out.println("A tempestade te desgasta. Você perde energia.");
                jogador.gastarEnergia(15);
                jogador.perderSanidade(5);
            }
        };

        // Doença - Pode ocorrer em qualquer lugar
        EventoDoencaFerimento intox = new EventoDoencaFerimento(
                "Intoxicação",
                "Você consumiu algo contaminado e se sente mal.",
                0.4,
                "vida",
                null, // Pode ocorrer em qualquer lugar
                "Intoxicação Alimentar",
                10,
                10,
                true
        );

        // Ferramenta rara - Só pode ocorrer nas Ruínas
        EventoDescoberta achado = new EventoDescoberta(
                "Achado Inesperado",
                "Você encontra um objeto abandonado em meio aos escombros.",
                0.5,
                "nenhum",
                "Ruínas", // Localização requerida
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