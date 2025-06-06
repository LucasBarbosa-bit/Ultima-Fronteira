package jogo.personagens;

public class Rastreador extends Personagem {
    public Rastreador(String nome) {
        super(nome);
        // Habilidade: 25% mais chance de encontrar recursos
        this.chanceEncontrarRecursoModifier = 1.25;
    }
}