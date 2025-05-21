package jogo.personagens;

import jogo.itens.Item;
import jogo.sistema.Inventario;

public class Personagem {
    private String nome;
    private int vida, fome, sede, energia, sanidade;
    private Inventario inventario;

    public Personagem(String nome) {
        this.nome = nome;
        this.vida = 100;
        this.fome = 100;
        this.sede = 100;
        this.energia = 100;
        this.sanidade = 100;
        this.inventario = new Inventario(50.0); // Exemplo de limite de peso
    }

    public void comer(int valor) {
        fome += valor;
        if (fome > 100) fome = 100;
    }

    public void beber(int valor) {
        sede += valor;
        if (sede > 100) sede = 100;
    }

    public void descansar() {
        energia += 20;
        if (energia > 100) energia = 100;
    }

    public void perderVida(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
    }

    public Inventario getInventario() {
        return inventario;
    }

    // Getters e outros métodos
}
