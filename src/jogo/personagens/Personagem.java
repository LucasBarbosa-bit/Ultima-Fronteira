package jogo.personagens;

import jogo.ambientes.Localizador;
import jogo.eventos.GerenciadorDeEventos;
import jogo.itens.Arma;
import jogo.itens.Item;
import jogo.sistema.Inventario;

public class Personagem {
    private String nome;
    private int vida, fome, sede, energia, sanidade;
    private Inventario inventario;
    private Arma arma;
    private Localizador localizador;

    public Personagem(String nome) {
        this.nome = nome;
        this.vida = 100;
        this.fome = 100;
        this.sede = 100;
        this.energia = 100;
        this.sanidade = 100;
        this.inventario = new Inventario(20.0);// Exemplo de limite de peso
        this.localizador = new Localizador(new GerenciadorDeEventos());
    }

    public void comer(int valor) {
        fome += valor;
        if (fome > 100) fome = 100;
    }

    public void sede(int valor) {
        sede += valor;
        if (sede > 100) sede = 100;
    }

    public void descansar() {
        energia += 20;
        if (energia > 100) energia = 100;
    }

    public void equiparArma(Arma arma) {
        this.arma = arma;
        System.out.println("Equiando arma " + arma.getNome());
    }

    public void atacar(Personagem alvo) {
        if(this.arma != null) {
            this.arma.atacar(alvo);
        }

    }

    public void vida(int valor) {
        vida += valor;
        if (vida > 100) vida = 100;
    }

    public Localizador getLocalizador() { return this.localizador; }

    public void mover(String nomeAmbiente) {
        this.localizador.mudarAmbiente(nomeAmbiente);
    }


    public String localizacao(){
        return this.localizador.getAmbienteAtual().getNome();
    }

    public void usarItem(String nome) {
        Item item = inventario.buscarItem(nome);
        if (item != null) item.usar(this);
        else System.out.println("Item não foi encontrado.");

    }

    public Inventario getInventario() { return inventario; }

    public void adicionarItem(Item item) {
        inventario.adicionarItem(item);
        System.out.println("Item adicionado ao inventário: " + item.getNome());
    }
    public void removerItem(String nome) {
        inventario.removerItem(nome);
    }

    // Getters e outros métodos

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getFome() {return fome;}
    public int getSede() {return sede;}
    public int getEnergia() {return energia;}
    public int getSanidade() {return sanidade;}

}
