package jogo.personagens;

import jogo.ambientes.Localizador;
import jogo.eventos.GerenciadorDeEventos;
import jogo.itens.Arma;
import jogo.itens.Item;
import jogo.sistema.Artesanato;
import jogo.sistema.Inventario;

public class Personagem {
    private String nome;
    private int vida, alimentacao, sede, energia, sanidade;
    private Inventario inventario;
    private Arma arma;
    private Localizador localizador;
    private Artesanato artesanato; // Novo atributo

    public Personagem(String nome) {
        this.nome = nome;
        this.vida = 100;
        this.alimentacao = 100;
        this.sede = 100;
        this.energia = 100;
        this.sanidade = 100;
        this.inventario = new Inventario(20.0);
        this.localizador = new Localizador(new GerenciadorDeEventos(this));
        this.artesanato = new Artesanato(); // Instancia o sistema de criação
    }

    public void comer(int valor) {
        alimentacao += valor;
        if (alimentacao > 100) alimentacao = 100;
    }

    public void fome(int valor) {
        alimentacao -= valor;
        if (alimentacao < 0) alimentacao = 0;
        if (alimentacao < 20) {
            System.out.println("\nVocê está com muita fome.");
            perderVida(5);
            perderSanidade(2);
        }
    }

    public void sede(int valor) {
        sede += valor;
        if (sede > 100) sede = 100;
        if (sede < 0) sede = 0;
        if (sede < 20) {
            System.out.println("\nVocê está morrendo de sede.");
            perderVida(5);
            perderSanidade(2);
        }
    }

    public void descansar() {
        energia += 20;
        if (energia > 100) energia = 100;
        ganharSanidade(5);
        System.out.println("Você descansa um pouco e recupera suas forças e sua mente.");
    }

    public void equiparArma(Arma arma) {
        this.arma = arma;
        System.out.println("Equipando arma " + arma.getNome());
    }

    public void atacar(Personagem alvo) {
        if(this.arma != null) {
            this.arma.atacar(alvo);
        }
    }

    public void ganharvida(int valor) {
        if (vida >= 100) return;
        vida += valor;
        if (vida > 100) vida = 100;
        System.out.println("Vida ganha: " + valor);
    }

    public void perderVida(int valor){
        System.out.println("Vida perdida: " + valor);
        vida -= valor;
        if (vida > 100) vida = 100;
        if (vida < 0) vida = 0;
    }

    public void ganharSanidade(int valor) {
        if (sanidade >= 100) return;
        sanidade += valor;
        if (sanidade > 100) sanidade = 100;
    }

    public void perderSanidade(int valor) {
        sanidade -= valor;
        System.out.println("Você sente sua mente vacilar... (Sanidade -" + valor + ")");
        if (sanidade < 0) sanidade = 0;
        if (sanidade < 20) {
            System.out.println("As sombras parecem se mover... sua percepção da realidade está se distorcendo.");
        }
    }

    public void criarItem() {
        artesanato.mostrarInterfaceDeCriacao(this);
    }

    public Localizador getLocalizador() { return this.localizador; }

    public void mostrarInventario() { inventario.listarItens(); }

    public void mover(String nomeAmbiente) {
        if (this.getEnergia() > 10){
            this.localizador.mudarAmbiente(nomeAmbiente);
            this.gastarEnergia(10);
            sede(-5);
            fome(5);
            gerarEvento();
        } else {
            System.out.println("Você está cansado demais para se mover.");
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
        boolean sucesso = inventario.adicionarItem(item);
        if (sucesso) {
            System.out.println("Item adicionado ao inventário: " + item.getNome());
        } else {
            System.out.println("Não foi possível coletar " + item.getNome() + " (peso excedido).");
        }
    }

    public void removerItem(String nome) {
        boolean sucesso = inventario.removerItem(nome);
        if (sucesso) {
            System.out.println(nome + " foi removido do inventário.");
        } else {
            System.out.println("Não foi possível encontrar " + nome + " no inventário.");
        }
    }

    public void gastarEnergia(int valor) {
        energia -= valor;
        if (energia < 0) energia = 0;
    }

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