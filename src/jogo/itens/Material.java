package jogo.itens;

public class Material extends Item {
    private String tipo; // madeira, pedra, metal
    private int resistencia;

    public Material(String nome, double peso, int durabilidade, String tipo, int resistencia) {
        super(nome, peso, durabilidade);
        this.tipo = tipo;
        this.resistencia = resistencia;
    }

    public Material combinar(Material outro) {
        System.out.println("Combinando " + this.nome + " com " + outro.nome);
        return new Material("Item Combinado", this.peso + outro.peso, 100, "composto", (this.resistencia + outro.resistencia) / 2);
    }

    @Override
    public void usar() {
        System.out.println("Material usado para fabricação.");
        reduzirDurabilidade(1);
    }
}
