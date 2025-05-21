package jogo.itens;

public class Alimento extends jogo.itens.Item {
    private int valorNutricional;
    private String tipo;
    private int prazoValidade;

    public Alimento(String nome, double peso, int durabilidade, int valorNutricional, String tipo, int prazoValidade) {
        super(nome, peso, durabilidade);
        this.valorNutricional = valorNutricional;
        this.tipo = tipo;
        this.prazoValidade = prazoValidade;
    }

    public void consumir() {
        System.out.println(nome + " consumido. Restaurou " + valorNutricional + " de fome.");
        reduzirDurabilidade(1);
    }

    @Override
    public void usar() {
        consumir();
    }
}
