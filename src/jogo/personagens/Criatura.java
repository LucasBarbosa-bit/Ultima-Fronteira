package jogo.personagens;

public class Criatura {
    private String nome;
    private int vida;
    private int dano;

    public Criatura(String nome, int vida, int dano) {
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
    }

    public void atacar(Personagem jogador) {
        System.out.println("Criatura " + nome + " ataca! Causa " + dano + " de dano.");
        jogador.perderVida(dano);
    }

    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
        System.out.println(nome + " recebeu " + dano + " de dano. Vida restante: " + vida);
    }

    public boolean estaViva() {
        return vida > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getDano() {
        return dano;
    }
}
