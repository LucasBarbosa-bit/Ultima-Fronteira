package jogo.itens;

public class Agua extends Item {
    private String pureza; // "potável", "contaminada"
    private double volume;

    public Agua(String nome, double peso, int durabilidade, String pureza, double volume) {
        super(nome, peso, durabilidade);
        this.pureza = pureza;
        this.volume = volume;
    }

    public void beber() {
        System.out.println("Você bebeu " + nome + ". Pureza: " + pureza);
        reduzirDurabilidade(1);
    }

    @Override
    public void usar() {
        beber();
    }
}
