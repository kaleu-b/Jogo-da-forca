import java.util.Scanner;
public class Main {

  /**
   * Classe principal do jogo da Forca.
   * Contém o ponto de entrada do programa e controla o fluxo do jogo.
   * REFATORADO: Agora usa um loop iterativo para evitar stack overflow.
   */

 /**
   * Ponto de entrada do programa - agora usa um loop simples para controlar o fluxo.
   * Cria os objetos compartilhados e inicia o menu principal.
   * REFATORADO: Agora completamente livre de stack overflow - usa apenas loops iterativos.
   *
   * @param args Argumentos de linha de comando (não utilizados)
   */
  public static void main(String[] args) { // Método principal
    Scanner scanner = new Scanner(System.in); // Cria o Scanner para entrada
    Jogador jogador = new Jogador(); // Cria o objeto Jogador
    Forca forca = new Forca(scanner, jogador); // Cria o objeto Forca com os objetos compartilhados

    /* Loop principal que controla todo o fluxo do jogo
     *  se menuinicial retornar true, continua o jogo.
     *  se retornar false, encerra o jogo.
     *  REFATORADO: Agora o controle de fluxo é completamente iterativo
     */
    boolean continuarJogo = true;
    while (continuarJogo) {
      continuarJogo = Menu.verMenuInicial(scanner, jogador, forca);
    }
    // mensagem final de agradecimento
    System.out.println("Obrigado por jogar!");
    scanner.close(); // Fecha o scanner
    System.exit(0); // encerra o programa
}
}