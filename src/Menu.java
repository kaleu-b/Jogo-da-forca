
import java.util.Scanner;

public class Menu {
  // Essa classe é responsável por exibir e tratar tudo relacionado a menus do jogo.

  /**
   * Exibe o menu principal do jogo da Forca, apresentando as opções iniciais ao usuário.
   * Permite iniciar o jogo, visualizar instruções, créditos ou sair do programa.
   * Controla o fluxo principal do programa de acordo com a escolha do jogador,
   * reutilizando os objetos compartilhados (Scanner, Jogador, Forca) durante toda a execução.
   *
   * @param input   Scanner compartilhado para leitura de entrada do usuário
   * @param jogador Objeto Jogador compartilhado
   * @param forca   Instância única de Forca compartilhada
   */
  public static void verMenuInicial(Scanner input, Jogador jogador, Forca forca) {
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
          menuModoJogo(input, jogador, forca);
          break;
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
          input.close();
          System.exit(0);
          break;
        default:
          System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
          pausa(1000);
          break;
      }
    } while (opcao != '4' && opcao != '1');
  }

  /**
   * Exibe o menu de seleção do modo de jogo (multiplayer ou singleplayer).
   * Permite ao usuário escolher entre jogar contra um amigo ou contra o computador.
   * Após a escolha, inicia o jogo com o modo selecionado reutilizando os objetos compartilhados.
   *
   * @param input   Scanner compartilhado para leitura de entrada do usuário
   * @param jogador Objeto Jogador compartilhado
   * @param forca   Instância única de Forca compartilhada
   */
  private static void menuModoJogo(Scanner input, Jogador jogador, Forca forca) {
    char modojogo;
    boolean multiplayer = true;
    do {
      limpaTela();
      System.out.println("Iniciando o jogo da Forca...");
      pausa(1000);
      System.out.print("Deseja jogar contra um amigo ou contra o computador? (1 - Amigo, 2 - Computador): ");
      modojogo = input.next().charAt(0);
      input.nextLine();
      switch (modojogo) {
        case '1':
          multiplayer = true;
          System.out.println("Modo selecionado: Jogar contra amigo");
          pausa(1000);
          break;
        case '2':
          multiplayer = false;
          System.out.println("Modo selecionado: Jogar contra computador");
          pausa(1000);
          break;
        default:
          System.out.println("Opção de modo inválida! Por favor, escolha 1 ou 2.");
          break;
      }
    } while (modojogo != '1' && modojogo != '2');
    limpaTela();
    forca.iniciarJogo(multiplayer);
  }
/*
 * esse método exibe o mennu final do jogo com algumas opções que o usuário pode escolher.
 * 1 - volta pro menu inicial
 * 2 - volta pro menu de seleção de modo de jogo
 * 3 - inicia uma revanche 
 * 4 - sai do jogo
 * 
 * obs : esse método usa o mesmo objeto scanner e forca que foram criados no início do jogo.
 * 
 */
  public static void verMenuFinal(boolean multiplayer, Scanner input, Forca forca) {
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
        case '1':
          verMenuInicial(input, forca.jogador, forca);
          break;
        case '2':
          menuModoJogo(input, forca.jogador, forca);
          break;
        case '3':
          forca.iniciarJogo(multiplayer);
          break;
        case '4':
          System.out.println("Obrigado por jogar! Até a próxima.");
          input.close();
          System.exit(0);
          break;
        default:
          System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
          pausa(1000);
          break;
      }
    } while (opcao != '4' && opcao != '1' && opcao != '2' && opcao != '3');
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
   * @param tempoPausa Tempo de pausa em milissegundos (ex: 1000 = 1 segundo)
   * interrompe a execução da thread atual.
   * essa pausa vai ser usada por duas razões:
   * 1. dar tempo pro usuário ler as mensagens do jogo antes da tela ser limpa;
   * 2. fluidez do jogo.
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