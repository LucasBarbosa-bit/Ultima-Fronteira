package jogo.personagens;

import jogo.ambientes.Localizador;
import jogo.eventos.GerenciadorDeEventos;
import jogo.itens.Arma;
import jogo.itens.Item;
import jogo.sistema.Inventario;

public class Personagem {
    private String nome;
    private int vida, alimentacao, sede, energia, sanidade;
    private Inventario inventario;
    private Arma arma;
    private Localizador localizador;

    public Personagem(String nome) {
        this.nome = nome;
        this.vida = 100;
        this.alimentacao = 100;
        this.sede = 100;
        this.energia = 100;
        this.sanidade = 100;
        this.inventario = new Inventario(20.0);// Exemplo de limite de peso
        this.localizador = new Localizador(new GerenciadorDeEventos(this));
    }

    public void comer(int valor) {
        alimentacao += valor;
        if (alimentacao > 100) alimentacao = 100;
    }

    public void fome(int valor) {
        alimentacao -= valor;
        if (alimentacao < 0) alimentacao = 0;
        if (alimentacao < 10) {
            System.out.println("\nVocê está com muita fome\n.");
            perderVida(10);
        }
        if (alimentacao > 100){
            alimentacao = 100;
        }
    }

    public void sede(int valor) {

        sede += valor;
        if (sede > 100) sede = 100;
        if (sede < 0) sede = 0;
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
    public void ganharvida(int valor) {
        if (vida >= 100 ) return;
        vida += valor;
        if (vida > 100) vida = 100;
        System.out.println("Vida ganha: " + valor);
    }

    public void perderVida(int valor){
        System.out.println("Vida perdida: " + valor);
        vida -= valor;
        if (vida > 100) vida = 100;
        if (vida < 0) vida = 0;
        if (vida == 0){
            // jogador.gameOver()
        }
    }

    public Localizador getLocalizador() { return this.localizador; }

    public void mostrarInventario() { inventario.listarItens(); }

    public void mover(String nomeAmbiente) {
        if (this.getEnergia() > 0){
            this.localizador.mudarAmbiente(nomeAmbiente);
            this.gastarEnergia(10);
            sede(-2);
            fome(2);
            gerarEvento();
        }
    }


    public String localizacao(){
        return this.localizador.getAmbienteAtual().getNome();
    }

    public boolean emLocalizacao(String nomeAmbiente) {
        return this.localizador.getAmbienteAtual().getNome() != null && this.localizador.getAmbienteAtual().getNome().equalsIgnoreCase(nomeAmbiente);
    }



    public void explorar(){
        this.localizador.getAmbienteAtual().explorar(this);
        gerarEvento();
    }

    public void gerarEvento() {
        this.localizador.gerarEventoAtual(this);
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

    public void gastarEnergia(int valor) {
        energia -= valor;
        if (energia < 0) energia = 0;
    }

    // Getters e outros métodos

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getAlimentacao() {return alimentacao;}
    public int getSede() {return sede;}
    public int getEnergia() {return energia;}
    public int getSanidade() {return sanidade;}

}
