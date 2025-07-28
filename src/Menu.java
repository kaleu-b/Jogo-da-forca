import java.util.Scanner;

public class Menu {
  // Essa classe é responsável por exibir e tratar tudo relacionado a menus do jogo.

  /**
   * Exibe o menu principal do jogo da Forca, apresentando as opções iniciais ao usuário.
   * Permite iniciar o jogo, visualizar instruções, créditos ou sair do programa.
   * Controla o fluxo principal do programa de acordo com a escolha do jogador,
   * reutilizando os objetos compartilhados (Scanner, Jogador, Forca) durante toda a execução.
   *
   * usa retorno para indicar se o jogo deve continuar ou encerrar dependendo da opção escolhida.
   * @param input   Scanner compartilhado para leitura de entrada do usuário
   * @param jogador Objeto Jogador compartilhado
   * @param forca   Instância única de Forca compartilhada
   * @return boolean indicando se o jogo deve continuar (true) ou encerrar (false)
   */
  public static boolean verMenuInicial(Scanner input, Jogador jogador, Forca forca) {
    char opcao;
    do {
      limpaTela();
      System.out.println("---------------Bem-vindo ao jogo da Forca!---------------");
      pausa(1000);
      System.out.println("---------------MENU---------------");
      System.out.println("1 - Jogar");
      System.out.println("2 - Instruções");
      System.out.println("3 - Créditos");
      System.out.println("4 - Sair");
      System.out.println("---------------------------------");
      System.out.print("Escolha uma opção:");
      opcao = input.next().charAt(0);
      input.nextLine();
      switch (opcao) {
        case '1':
          boolean continuarAposJogo = menuModoJogo(input, jogador, forca);
          return continuarAposJogo;
        case '2':
          System.out.println("Instruções do jogo da Forca:");
          System.out.println("1. O objetivo é adivinhar a(s) palavra(s) secreta(s).");
          System.out.println("2. Você tem 6 tentativas.");
          System.out.println("3. A cada erro, uma parte do boneco é desenhada.");
          System.out.println("4. Se o boneco for completado, você perde.");
          pausa(5000);
          break;
        case '3':
          System.out.println("Créditos:");
          System.out.println("Desenvolvido por Kaléu Borges");
          pausa(2000);
          break;
        case '4':
          System.out.println("Saindo do jogo...");
          pausa(1500);
          return false;
        default:
          System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
          pausa(1000);
          break;
      }
    } while (opcao != '4' && opcao != '1');
    return true;
  }

  /**
   * Exibe o menu de seleção do modo de jogo (multiplayer ou singleplayer).
   * Agora reutiliza completamente o método selecionarModo() para evitar duplicação.
   *
   * @param input   Scanner compartilhado para leitura de entrada do usuário
   * @param jogador Objeto Jogador compartilhado
   * @param forca   Instância única de Forca compartilhada
   * @return boolean indicando se o jogo deve continuar (true) ou encerrar (false)
   */
  public static boolean menuModoJogo(Scanner input, Jogador jogador, Forca forca) {
    boolean multiplayer = selecionarModo(input);
    limpaTela();
    return forca.iniciarJogo(multiplayer);
  }

  /*
   * Retorna um número inteiro, de 1 a 4, dependendo da opção escolhida pelo usuário.
   * 1 - Voltar para o menu inicial
   * 2 - Selecionar modo de jogo
   * 3 - Revanche
   * 4 - Sair do jogo
   */
  public static int verMenuFinal(boolean multiplayer, Scanner input, Forca forca) {
    limpaTela();
    System.out.println("Fim do jogo! Obrigado por jogar.");
    pausa(1000);
    char opcao;
    do {
      limpaTela();
      System.out.println("---------------MENU FINAL---------------");
      System.out.println("1 - Voltar para o menu inicial");
      System.out.println("2 - Selecionar modo de jogo");
      System.out.println("3 - Revanche");
      System.out.println("4 - Sair");
      System.out.println("----------------------------------------");
      System.out.print("Escolha uma opção: ");
      String entrada = input.nextLine().trim();
      while (entrada.isEmpty()) {
        System.out.print("Entrada vazia. Digite novamente: ");
        entrada = input.nextLine().trim();
      }
      opcao = entrada.charAt(0);
      switch (opcao) {
        case '1': // caso 1:
          return 1; // retorna 1
        case '2': // caso 2:
          return 2; // retorna 2
        case '3': // caso 3:
          return 3; // retorna 3
        case '4': // caso 4:
          System.out.println("Saindo do jogo..."); // mostra uma mensagem de saída
          pausa(1500); // dá uma pause de 1.5 segundos
          return 4; // retorna 4
        default: // caso inválido:
          System.out.println("Opção inválida! Por favor, escolha uma opção válida."); // mensagem de erro
          pausa(1000);
          break; // volta pro loop
      }
    } while (true);
  }

  /**
   * Método unificado para seleção de modo de jogo.
   * Agora usado tanto no início do jogo quanto nas revanches.
   *
   * @param input Scanner para leitura de entrada
   * @return true para multiplayer, false para singleplayer
   */
  public static boolean selecionarModo(Scanner input) {
    char modojogo;
    do {
      limpaTela();
      System.out.println("Iniciando o jogo da Forca...");
      pausa(500);
      System.out.print("Selecione o modo:1 - Multiplayer (contra amigo) 2 - Singleplayer (contra computador)\nOpção: ");
      modojogo = input.next().charAt(0);
      input.nextLine();

      switch (modojogo) {
        case '1':
          System.out.println("Modo selecionado: Multiplayer");
          pausa(1000);
          return true;
        case '2':
          System.out.println("Modo selecionado: Singleplayer");
          pausa(1000);
          return false;
        default:
          System.out.println("Opção inválida! Por favor, escolha 1 ou 2.");
          pausa(1000);
          break;
      }
    } while (true);
  }

  /*esse método começa um processo de limpeza do terminal, dependendo do OS que o usuário estiver usando.
   *se for um windows, chama o cls.
   * se for linux/mac/unix/qualquer outro OS, usa o clear.
   */
  public static void limpaTela() {
   try { // tenta limpar a tela:
      // se o sistema operacional for um windows:
      if (System.getProperty("os.name").contains("Windows")) {
        // abre um processo do CLS no terminal do jogo, limpando a tela.
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        // se não for um windows:
      } else {
        // abre um processo do clear no terminal do jogo, também limpando a tela.
        new ProcessBuilder("clear").inheritIO().start().waitFor();
      }
    } catch (Exception e) { // e se der algum erro:
      for (int i = 0; i < 50; i++) { // imprime 50 linhas vazias em loop. não é exatamente uma "limpeza" pq dá pra simplesmente scrollar, mas melhor do que nada.
        System.out.println("");
      }
    }
  }

  /**
   * Pausa a execução do programa por um tempo determinado.
   * @param tempoPausa Tempo de pausa em milissegundos
   */
  public static void pausa(int tempoPausa) {
    try {
      Thread.sleep(tempoPausa); // Suspende a execução da thread atual
    } catch (InterruptedException e) {
      // Log da exceção caso a thread seja interrompida durante a pausa
      e.printStackTrace();
    }
  }
}