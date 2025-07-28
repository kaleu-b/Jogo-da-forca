# 🎮 Jogo da Forca - Java (Terminal)

Este repositório contém um jogo da forca desenvolvido em Java, com foco em aprendizado de lógica de programação, estruturas de controle, entrada de dados e uso de orientação a objetos.

Projeto desenvolvido como atividade final da disciplina de **Pensamento Computacional e Algoritmos**, ministrada pelo professor **Ramon Mayor Martins**, no **IFSC - São José**.

---

## 📚 Objetivos

* Aplicar os conceitos fundamentais de programação;
* Exercitar o uso de estruturas de decisão, repetição e arrays;
* Praticar a orientação a objetos;
* Utilizar boas práticas de organização de código e modularização.

---

## ⚙️ Requisitos

* **Java 8** ou superior instalado no sistema.
  Para verificar a versão, execute no terminal:

  ```bash
  java -version
  ```

### Requisitos adicionais para Windows:

* **Git** (para rodar o `build.sh` via Git Bash):
  [https://git-scm.com/downloads/win](https://git-scm.com/downloads/win)

* **WSL** (Windows Subsystem for Linux – alternativa para usuários avançados):
  [https://learn.microsoft.com/en-us/windows/wsl/install](https://learn.microsoft.com/en-us/windows/wsl/install)

> ⚠️ Recomenda-se que pelo menos **Git** esteja instalado para facilitar a execução dos scripts `.sh` no Windows.

---

## 🗂️ Estrutura do Projeto

```text
Jogo-da-forca/
├── build.sh         # Script de compilação (gera os arquivos de execução .bat e .sh)
├── Forca.bat        # Arquivo gerado para execução no Windows
├── Forca.sh         # Arquivo gerado para execução no Linux/macOS
├── src/             # Contém os arquivos .java e .class
│   ├── Menu.java
│   ├── Forca.java
│   ├── Jogador.java
│   ├── Boneco.java
|   └── Main.java
└── src/jar/         # Local onde os scripts buscam os .class para execução
```

---

## 🖥️ Como Executar

### Windows 🪟

1. Baixe ou clone o repositório:

   ```bash
   git clone https://github.com/kaleu-b/Jogo-da-forca
   ```

2. Execute o arquivo `build.sh` utilizando o **Git Bash** ou **WSL**.

3. Dê um duplo clique no arquivo `Forca.bat` para iniciar o jogo via CMD.

> Se você não tiver o Git ou WSL, será necessário configurar manualmente a execução do jogo pelo terminal do Windows.

### Linux/macOS 🐧

1. Clone o repositório:

   ```bash
   git clone https://github.com/kaleu-b/Jogo-da-forca
   ```

2. Torne o `build.sh` executável:

   ```bash
   chmod +x build.sh
   ./build.sh
   ```

3. Execute o jogo com:

   ```bash
   ./Forca.sh
   ```


Ou com duplo clique no arquivo Forca.sh
---
## 📌 Como Jogar

O jogo é 100% em terminal (linha de comando). Ao iniciar, o menu principal será exibido com as seguintes opções:

1. Jogar
2. Instruções
3. Créditos
4. Sair

Ao selecionar **"Jogar"**, será solicitado que o usuário escolha entre:

* **1** – Jogar contra um amigo (modo multiplayer);
* **2** – Jogar contra o computador (modo singleplayer).

### 🎯 Regras do jogo:

* A palavra deve ter **no máximo 20 caracteres**.
* O jogador pode tentar adivinhar a **palavra inteira** ou uma **letra por vez**.
* O boneco será desenhado conforme os erros (até 6 erros).
* Ao final, o jogador poderá escolher entre:

  * **1** – Voltar pro menu inicial;
  * **2** – Selecionar modo de jogo;
  * **3** – Revanche;
  * **4** - Sair do jogo.

---

## 🧱 Estrutura do Código

### `Menu.java`

* Exibe o menu principal;
* Recebe e valida entradas do usuário;
* Inicia o jogo com base no modo selecionado;
* Exibe instruções e créditos;
* Usa `Scanner` para entrada de dados e métodos auxiliares como `pausa()` e `limpaTela()` para controle da experiência visual no terminal.

### `Forca.java`

* Gerencia o fluxo da partida (escolha da palavra, tentativas, vitórias e derrotas);
* Suporta dois modos: multiplayer e singleplayer;
* Utiliza:

  * `adivinharPalavra()` para gerenciar as jogadas;
  * `escolhaPalavra()` para decidir a palavra (usuário ou computador);
  * `inicializarLetrasDescobertas()` para gerar os "\_" da palavra;
  * `atualizarLetrasDescobertas()` para revelar letras corretas;
  * `verificarVitoria()` e `verificarDerrota()` para controlar o fim do jogo.

### `Jogador.java`

* Controla nomes dos jogadores;
* Define quem será o adivinhador e quem define a palavra;
* Suporte para até **2 jogadores**;
* Algumas funcionalidades extras (vidas e pontos) foram deixadas comentadas para possíveis futuras versões.

### `Boneco.java`

* Contém o método `exibirBoneco(int erros)` que desenha o boneco progressivamente com base na quantidade de erros (0 a 6).

---

## ✨ Créditos

Desenvolvido por **Kaléu Borges Augusto**
- Email acadêmico: `kaleu.b@aluno.ifsc.edu.br`
- Email pessoal: `kaleuborgesaugusto@gmail.com`
- GitHub: [kaleu-b](https://github.com/kaleu-b)

---

## Licença 🧾
Esse projeto é puramente educacional e não tem fins lucrativos. Sinta-se livre para usar.
