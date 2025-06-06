# Jogo de Sobrevivência: Última Fronteira

## Descrição do Projeto

"Última Fronteira" é um jogo de sobrevivência em modo texto, baseado em eventos, onde o jogador deve tomar decisões estratégicas para sobreviver em um ambiente hostil.  Desenvolvido como projeto para a disciplina de Linguagem de Programação Orientada a Objetos, o jogo implementa conceitos como herança, polimorfismo e encapsulamento para criar uma experiência dinâmica e desafiadora.

O objetivo é sobreviver pelo maior tempo possível, gerenciando cuidadosamente os atributos do personagem, coletando recursos e enfrentando os perigos de uma terra desconhecida.

## Funcionalidades Implementadas

O jogo conta com um sistema robusto de mecânicas de sobrevivência, incluindo:

* **Atributos de Sobrevivência:** Gerenciamento de Vida, Fome, Sede, Energia e o perigoso atributo de Sanidade.
* **Exploração de Ambientes:** Cinco biomas únicos e exploráveis: Floresta, Montanha, Caverna, Lago/Rio e Ruínas Abandonadas, cada um com seus próprios recursos e perigos.
* **Sistema de Eventos Dinâmicos:** Eventos aleatórios, como encontros com criaturas, mudanças climáticas e descobertas, são sorteados com base no ambiente atual do jogador.
* **Combate por Turnos:** Enfrente criaturas hostis em um sistema de combate simples e tático.
* **Coleta e Inventário:** Um sistema de inventário com limite de peso para armazenar alimentos, água, materiais, ferramentas e armas.
* **Criação de Itens (Crafting):** Combine materiais encontrados no ambiente para criar novas ferramentas e armas.
* **Condições de Vitória e Derrota:** O jogo possui uma condição de vitória (sobreviver por 20 dias) e múltiplas condições de derrota (morte por fome, sede, ferimentos ou insanidade).

## Como Executar o Projeto

O projeto foi desenvolvido em Java e não requer dependências externas. Para compilar e executar, siga os passos:

1.  **Compilar o código:**
    Navegue até o diretório `src` do projeto pelo terminal e execute o comando de compilação.

    ```sh
    javac jogo/Main.java
    ```

2.  **Executar o jogo:**
    Ainda no diretório `src`, execute o seguinte comando para iniciar o jogo.

    ```sh
    java jogo.Main
    ```

## Estrutura do Projeto

O código-fonte está organizado em pacotes lógicos para refletir os diferentes componentes do jogo.

* `jogo/`: Contém a classe principal `Main`.
* `jogo/ambientes/`: Classes que definem os diferentes ambientes exploráveis.
* `jogo/eventos/`: Lógica para os eventos aleatórios e o gerenciador de eventos.
* `jogo/itens/`: Classes abstratas e concretas para todos os itens do jogo.
* `jogo/personagens/`: Classes para o personagem do jogador e as criaturas.
* `jogo/sistema/`: Classes que gerenciam sistemas complexos como Inventário, Combate e Artesanato.

---