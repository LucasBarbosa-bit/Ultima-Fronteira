package jogo.personagens;

public class SobreviventeNato extends Personagem {
    public SobreviventeNato(String nome) {
        super(nome);
    }

    @Override
    public void fome(int valor) {
        // O Sobrevivente Nato sente menos os efeitos da fome
        super.fome(valor / 2);
    }

    @Override
    public void sede(int valor) {
        // O Sobrevivente Nato sente menos os efeitos da sede
        super.sede(valor / 2);
    }
}