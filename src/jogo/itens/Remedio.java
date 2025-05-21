package jogo.itens;

public class Remedio extends Item {
    private String tipo; // bandagem, antibiótico, analgésico
    private String efeito;

    public Remedio(String nome, double peso, int durabilidade, String tipo, String efeito) {
        super(nome, peso, durabilidade);
        this.tipo = tipo;
        this.efeito = efeito;
    }

    @Override
    public void usar() {
        System.out.println("Usando " + tipo + ": " + efeito);
        reduzirDurabilidade(1);
    }
}
