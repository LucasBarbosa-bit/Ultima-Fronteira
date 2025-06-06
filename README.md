# Jogo de Sobrevivência: Última Fronteira

## Descrição do Projeto

"Última Fronteira" é um jogo de sobrevivência em modo texto, baseado em eventos, onde o jogador deve tomar decisões estratégicas para sobreviver em um ambiente hostil. Desenvolvido como projeto para a disciplina de Linguagem de Programação Orientada a Objetos, o jogo implementa conceitos como herança, polimorfismo e encapsulamento para criar uma experiência dinâmica e desafiadora.

O objetivo é sobreviver pelo maior tempo possível, gerenciando cuidadosamente os atributos do personagem, coletando recursos e enfrentando os perigos de uma terra desconhecida.

## Funcionalidades Implementadas

O jogo conta com um sistema robusto de mecânicas de sobrevivência, incluindo:

* **Atributos de Sobrevivência:** Gerenciamento de Vida, Fome, Sede, Energia e o perigoso atributo de Sanidade.
* **Sistema de Classes de Personagem:** Escolha entre 3 classes (Sobrevivente Nato, Rastreador, Mecânico), cada uma com habilidades únicas que alteram a jogabilidade.
* **Exploração de Ambientes:** Cinco biomas únicos e exploráveis: Floresta, Montanha, Caverna, Lago/Rio e Ruínas Abandonadas.
* **Sistema de Eventos Dinâmicos:** Eventos aleatórios, como encontros com criaturas e mudanças climáticas, são sorteados com base no ambiente atual do jogador.
* **Combate por Turnos:** Enfrente criaturas hostis em um sistema de combate simples e tático.
* **Coleta e Inventário:** Um sistema de inventário com limite de peso para armazenar alimentos, água, materiais, ferramentas e armas.
* **Criação de Itens (Crafting):** Combine materiais encontrados no ambiente para criar novas ferramentas e armas.
* **Ação de Descansar:** Recupere energia e sanidade ao descansar, mas cuidado com os riscos de ficar vulnerável.
* **Condições de Vitória e Derrota:** O jogo possui uma condição de vitória (sobreviver por 20 dias) e múltiplas condições de derrota.

## Classes de Personagem

No início do jogo, você pode escolher uma das três classes, cada uma com um estilo de jogo diferente:

* **Sobrevivente Nato:** Mais resistente, as penalidades por fome e sede são reduzidas, tornando a sobrevivência inicial mais fácil.
* **Rastreador:** Habilidoso em encontrar recursos, tem uma chance maior de achar mais itens ao explorar os ambientes.
* **Mecânico:** Um engenheiro improvisado, capaz de construir itens e ferramentas avançadas que outras classes não conseguem.

## Como Executar o Projeto

O projeto foi desenvolvido em Java e não requer dependências externas. Para compilar e executar, siga os passos:

1.  **Compilar o código:**
    Abra o terminal na pasta raiz do projeto (`Ultima-Fronteira/`) e execute o seguinte comando. Ele irá compilar todos os arquivos `.java` dentro da pasta `src` e colocar os `.class` na pasta `bin` (que será criada se não existir).

    ```sh
    javac -d bin src/jogo/Main.java
    ```

    *(O compilador `javac` irá automaticamente encontrar e compilar todas as classes dependentes).*

2.  **Executar o jogo:**
    Ainda na pasta raiz, execute o seguinte comando para iniciar o jogo, especificando que as classes estão no diretório `bin`.

    ```sh
    java -cp bin jogo.Main
    ```

## Estrutura do Projeto

O código-fonte está organizado em pacotes lógicos para refletir os diferentes componentes do jogo, seguindo as boas práticas de encapsulamento e organização.

* `jogo/`: Contém a classe principal `Main`.
* `jogo/ambientes/`: Classes que definem os diferentes ambientes exploráveis.
* `jogo/eventos/`: Lógica para os eventos aleatórios e o gerenciador de eventos.
* `jogo/itens/`: Classes abstratas e concretas para todos os itens do jogo.
* `jogo/personagens/`: A classe abstrata `Personagem` e suas subclasses (classes de personagem).
* `jogo/sistema/`: Classes que gerenciam sistemas complexos como Inventário, Combate e Artesanato.
* `jogo/sistema/excecoes/`: Contém as exceções personalizadas do jogo.
