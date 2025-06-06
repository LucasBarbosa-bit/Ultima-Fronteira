package jogo.personagens;

import jogo.ambientes.Localizador;
import jogo.eventos.GerenciadorDeEventos;
import jogo.itens.Arma;
import jogo.itens.Item;
import jogo.sistema.Artesanato;
import jogo.sistema.Inventario;
import jogo.sistema.excecoes.InventarioCheioException;

import java.util.List;
import java.util.Scanner;

public abstract class Personagem {
    // ... atributos ...
    protected String nome;
    protected int vida, alimentacao, sede, energia, sanidade;
    protected Inventario inventario;
    protected Arma arma;
    protected Localizador localizador;
    protected Artesanato artesanato;
    private int turnosParaDescansarDeNovo = 0;
    protected double chanceEncontrarRecursoModifier = 1.0;

    // ... construtor ...
    public Personagem(String nome) {
        this.nome = nome;
        this.vida = 100;
        this.alimentacao = 100;
        this.sede = 100;
        this.energia = 100;
        this.sanidade = 100;
        this.inventario = new Inventario(20.0);
        this.localizador = new Localizador(new GerenciadorDeEventos(this));
        this.artesanato = new Artesanato();
    }

    public void passarTurno() {
        if (turnosParaDescansarDeNovo > 0) {
            turnosParaDescansarDeNovo--;
        }
        // Custo metabólico base para cada turno que passa
        fome(10);
        sede(-15);
    }

    public void fome(int valor) {
        alimentacao -= valor;
        if (alimentacao < 0) alimentacao = 0;
        if (alimentacao < 20) {
            System.out.println("\nSua barriga ronca de fome. Você se sente fraco.");
            perderVida(8);
            perderSanidade(4);
        }
    }

    public void sede(int valor) {
        sede += valor; // O valor passado é negativo para diminuir
        if (sede > 100) sede = 100;
        if (sede < 0) sede = 0;
        if (sede < 20) {
            System.out.println("\nVocê está morrendo de sede.");
            perderVida(5);
            perderSanidade(2);
        }
    }

    public void explorar(){
        gastarEnergia(15);
        this.localizador.getAmbienteAtual().explorar(this);
        gerarEvento();
    }

    public void mover(String nomeAmbiente) {
        if (this.getEnergia() > 20){ // Aumentei o requisito de energia para mover
            this.localizador.mudarAmbiente(nomeAmbiente);
            gastarEnergia(20);
            fome(4);   // Custo adicional pelo esforço
            sede(-4);  // Custo adicional pelo esforço
            gerarEvento();
        } else {
            System.out.println("Você está cansado demais para uma longa viagem.");
        }
    }

    public void descansar() {
        energia += 25;
        if (energia > 100) energia = 100;
        ganharSanidade(5);
    }

    public boolean tentarDescansar() {
        if (turnosParaDescansarDeNovo > 0) {
            System.out.println("Você ainda precisa se recuperar do último descanso. Continue em frente.");
            return false;
        }

        System.out.println("Você encontra um local relativamente seguro para descansar por um tempo...");
        descansar();
        System.out.println("O descanso recupera suas forças, mas consome suas reservas de comida e água.");
        fome(5);   // Custo adicional por um longo período parado
        sede(-5);  // Custo adicional por um longo período parado
        this.turnosParaDescansarDeNovo = 3;
        System.out.println("(Você precisará de 3 dias para poder descansar novamente)");
        gerarEvento();
        return true;
    }

    // ... O resto da classe permanece igual ...

    public boolean mostrarMenuParaUsarItem() {
        List<Item> itens = inventario.getItens();
        if (itens.isEmpty()) {
            System.out.println("Seu inventário está vazio.");
            return false;
        }

        System.out.println("\n--- Usar Item ---");
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + ". " + itens.get(i).getNome());
        }
        System.out.println("0. Cancelar");
        System.out.print("Qual item você deseja usar? ");

        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= itens.size()) {
            Item itemEscolhido = itens.get(escolha - 1);
            itemEscolhido.usar(this);
            return true;
        }

        System.out.println("Ação cancelada.");
        return false;
    }

    public void comer(int valor) {
        alimentacao += valor;
        if (alimentacao > 100) alimentacao = 100;
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

    public boolean criarItem() {
        return artesanato.mostrarInterfaceDeCriacao(this);
    }

    public Localizador getLocalizador() { return this.localizador; }

    public void mostrarInventario() { inventario.listarItens(); }

    public String localizacao(){
        return this.localizador.getAmbienteAtual().getNome();
    }

    public boolean emLocalizacao(String nomeAmbiente) {
        return this.localizador.getAmbienteAtual().getNome() != null && this.localizador.getAmbienteAtual().getNome().equalsIgnoreCase(nomeAmbiente);
    }

    public void gerarEvento() {
        this.localizador.gerarEventoAtual(this);
    }

    public Inventario getInventario() { return inventario; }

    public void adicionarItem(Item item) {
        try {
            inventario.adicionarItem(item);
            System.out.println("Item adicionado ao inventário: " + item.getNome());
        } catch (InventarioCheioException e) {
            System.out.println(e.getMessage());
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
    public double getChanceEncontrarRecursoModifier() { return chanceEncontrarRecursoModifier; }
}