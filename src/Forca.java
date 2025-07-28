import java.util.Random; // Importa a classe Random para sorteios
import java.util.Scanner; // Importa a classe Scanner para entrada do usuário

/**
 * Classe responsável por gerenciar toda a lógica do jogo da Forca.
 * Controla o fluxo do jogo, manipula o estado dos jogadores, sorteia palavras e
 * executa a lógica principal de adivinhação, sempre reutilizando os objetos compartilhados.
 *
 * agora tem um loop simples que controla o fluxo do jogo, evitando o risco de stack overflow.
 * REFATORADO: Eliminados completamente os métodos recursivos que causavam stack overflow.
 * Agora usa um sistema de loop para controlar o fluxo sem recursão.
 * @author Kaléu Borges
 */
public class Forca {
  public final Scanner scanner; // Scanner compartilhado para entrada do usuário
  public final Jogador jogador; // Jogador compartilhado para armazenar dados dos jogadores
  public String nome; // Variável para nome do(s) jogador(es)
  private final Random random = new Random(); // Objeto Random para sorteios

 

  /**
   * Construtor da classe Forca. Recebe os objetos compartilhados Scanner e Jogador.
   *
   * @param scanner Scanner compartilhado para leitura de entrada do usuário
   * @param jogador Objeto Jogador compartilhado
   */
  public Forca(Scanner scanner, Jogador jogador) { // Construtor da classe
    this.scanner = scanner; // Inicializa o Scanner compartilhado
    this.jogador = jogador; // Inicializa o Jogador compartilhado
  }

  /**
   * Inicia uma nova partida, e retorna um booleano dependendo do método verMenuFinal.
   * se o menuFinal retornar true, continua o jogo, se retornar false, encerra.
   * REFATORADO: Agora usa um loop para controlar revanches sem recursão. previne problemas de stack overflow que poderiam acontecer depois de centenas de revanches/chamadas de menu
   *
   * @param multiplayer true para modo multiplayer, false para singleplayer
   * @return boolean indicando se o jogo deve continuar
   */
  public boolean iniciarJogo(boolean multiplayer) { // Inicia o jogo
    boolean continuarJogando = true;
    boolean modoAtual = multiplayer;

    // Loop para controlar revanches sem recursão - elimina completamente o risco de stack overflow
    while (continuarJogando) {
      adivinharPalavra(escolhaPalavra(modoAtual), modoAtual); // Escolhe a palavra e inicia a adivinhação
      // Obtém a decisão do menu final usando códigos de retorno
      int retornoMenuFinal = Menu.verMenuFinal(modoAtual, scanner, this);
      switch (retornoMenuFinal) {
        case 1: // Voltar para menu inicial
          return true;
        case 2: // Selecionar novo modo
          modoAtual = Menu.selecionarModo(scanner);
          break;
        case 3: // Revanche
          // Continua no loop com o mesmo modo. não precisa fazer nada aqui.
          System.out.println("REVANCHE!!!!!!!!!!!!!!!!!");
          Menu.pausa(1000); // Pausa de 1 segundo para dar tempo de ler a
          break;
        case 4: // Sair
          return false;
        default:
          continuarJogando = false;
          break;
      }
    }
    return true; // nunca deve chegar aqui. mas está aqui porque precisa dar no mínimo algum retorno.
  }

  /**
   * Solicita e define a palavra a ser adivinhada, de acordo com o modo de jogo.
   * No modo multiplayer, sorteia o jogador que escolhe a palavra.
   * No modo singleplayer, sorteia uma palavra aleatória.
   *
   * @param multiplayer true para modo multiplayer, false para singleplayer
   * @return Palavra escolhida para o jogo
   */
  public String escolhaPalavra(boolean multiplayer) { // Escolhe a palavra do jogo
    String palavra; // String que vai receber a palavra digitada ou sorteada
    if (multiplayer) { // Se for multiplayer
      for (int i = 0; i < jogador.numeroJogadores; i++) { // Para cada jogador
        System.out.print("Digite o nome do jogador " + (i + 1) + ": "); // Pede o nome
        nome = scanner.nextLine(); // Lê o nome
        jogador.setNome(nome, i); // Salva o nome
      }
      jogador.setWordPicker(random.nextInt(jogador.numeroJogadores)); // Sorteia quem escolhe a palavra
      switch (jogador.getWordpicker()) { // Define quem adivinha
        case 1: // Se o jogador 1 escolheu
          jogador.setGuesser(0); // Jogador 0 adivinha
          break;
        default:
          jogador.setGuesser(1); // Senão, jogador 1 adivinha
          break;
      }
      System.out.println(jogador.getNome(jogador.getWordpicker()) + ", Escolha uma palavra para " + jogador.getNome(jogador.getGuesser()) + " adivinhar: "); // Pede a palavra
      System.out.println(jogador.getNome(jogador.getGuesser()) + ", por favor, não olhe para a tela enquanto a palavra é digitada!"); // Alerta o adivinhador
      palavra = scanner.nextLine(); // Lê a palavra
      while (palavra.length() > 20) { // Se a palavra for muito grande
        System.out.print(palavra + " É grande demais. Digite uma palavra menor: "); // Pede outra
        palavra = scanner.nextLine(); // Lê novamente
      }
    } else { // Se for singleplayer
      jogador.setNome("Computador", 0); // Define nome do jogador 0
      System.out.println("A palavra será escolhida pelo computador."); // Informa ao usuário
      String palavraaleatoria[] = {"Java", "Ramon", "Análise de Sistemas", "São José", "IFSC", "Palhoça"}; // Palavras possíveis
      palavra = palavraaleatoria[random.nextInt(palavraaleatoria.length)]; // Sorteia uma palavra
      System.out.print("Digite seu nome: "); // Pede nome do jogador
      nome = scanner.nextLine(); // Lê o nome
      jogador.setNome(nome, 1); // Salva o nome
      jogador.setGuesser(1); // Jogador 1 será o adivinhador
    }
    Menu.limpaTela(); // Limpa a tela
    return palavra; // Retorna a palavra escolhida
  }

  /**
   * Executa o loop principal de adivinhação da palavra, processando tentativas do jogador
   * e controlando o fluxo de vitória/derrota.
   *
   * @param palavra     Palavra a ser adivinhada
   * @param multiplayer true se for modo multiplayer, false se singleplayer
   */
  public void adivinharPalavra(String palavra, boolean multiplayer) { // Lógica principal do jogo
    char[] letrasDescobertas = inicializarLetrasDescobertas(palavra); // Inicializa letras descobertas
    int erros = 0; // Contador de erros
    String palavra_minusculas = palavra.toLowerCase(); // Palavra em minúsculas
    StringBuilder estadoPalavra = new StringBuilder(new String(letrasDescobertas)); // Estado atual da palavra
    mostraEstado(estadoPalavra, erros); // Mostra o estado inicial
    boolean jogoAtivo = true; // Flag do loop principal
    int advinhador = jogador.getGuesser(); // Índice do jogador que adivinha

    while (jogoAtivo) { // Loop do jogo
      System.out.print(jogador.getNome(advinhador) + ", Digite uma letra ou a palavra completa: "); // Pede entrada
      String input = scanner.nextLine().toLowerCase().trim(); // Lê entrada e remove espaços extras
      while (input.isEmpty()) { // Se entrada vazia
        System.out.print("Erro: vazio. Digite novamente: "); // Pede novamente
        input = scanner.nextLine().toLowerCase().trim(); // Lê de novo e remove espaços extras
      }
      if (input.length() > 1) { // Se digitou mais de uma letra (tentou a palavra)
        if (input.equals(palavra_minusculas)) { // Se acertou a palavra
          estadoPalavra = new StringBuilder(palavra); // Revela a palavra
          mostraEstado(estadoPalavra, erros); // Mostra estado final
          System.out.println("Parabéns, " + jogador.getNome(advinhador) + "! Você adivinhou a palavra: " + palavra); // Mensagem de vitória
          Menu.pausa(2000); // Pausa
          break; // Sai do loop
        } else { // Se errou a palavra
          erros++; // Incrementa erros
          Boneco.exibirBoneco(erros); // Mostra boneco
          System.out.println("Palavra incorreta! Tente novamente."); // Mensagem de erro
        }
      } else { // Se digitou uma letra
        char letra = input.charAt(0); // Pega a letra
        boolean letraEncontrada = atualizarLetrasDescobertas(letra, palavra_minusculas, letrasDescobertas); // Atualiza letras
        if (!letraEncontrada) { // Se não encontrou
          erros++; // Incrementa erros
          Boneco.exibirBoneco(erros); // Mostra boneco
        } else { // Se encontrou
          Boneco.exibirBoneco(erros); // Mostra boneco
          estadoPalavra = new StringBuilder(new String(letrasDescobertas)); // Atualiza estado
        }
      }
      mostraEstado(estadoPalavra, erros); // Mostra estado atual
      if (verificarVitoria(estadoPalavra, palavra_minusculas, advinhador, palavra)) { // Se venceu
        jogoAtivo = false; // Sai do loop
      } else if (verificarDerrota(erros, palavra)) { // Se perdeu
        jogoAtivo = false; // Sai do loop
      }
    }
  }

  // Mostra o estado atual da palavra e o número de erros
  private void mostraEstado(StringBuilder estadoPalavra, int erros) { // Mostra o estado do jogo
    System.out.println("Palavra: " + estadoPalavra.toString()); // Mostra a palavra com letras descobertas
    System.out.println("Erros: " + erros); // Mostra o número de erros
  }

  /**
   * Inicializa o array de letras descobertas, preenchendo com '_' para letras e ' ' para espaços.
   * Isso garante que espaços na palavra original não sejam ocultados, mantendo a visualização fiel ao esperado.
   * Exemplo: palavra = "Pão de queijo" -> ___ __ ______
   *
   * @param palavra Palavra a ser adivinhada
   * @return Array de caracteres representando o estado inicial da palavra para o jogo
   */
  private char[] inicializarLetrasDescobertas(String palavra) { // Inicializa letras descobertas
    char[] letrasDescobertas = new char[palavra.length()]; // Cria o array de letras
    for (int i = 0; i < letrasDescobertas.length; i++) { // Para cada posição
      if (palavra.charAt(i) == ' ') { // Se for espaço
        letrasDescobertas[i] = ' '; // Revela o espaço
      } else {
        letrasDescobertas[i] = '_'; // Esconde a letra
      }
    }
    return letrasDescobertas; // Retorna o array
  }

  /**
   * Atualiza o array de letras descobertas com a letra digitada pelo jogador.
   * Para cada ocorrência da letra na palavra, revela a letra no array.
   *
   * @param letra              Letra digitada pelo jogador
   * @param palavra_minusculas Palavra original em minúsculas para comparação
   * @param letrasDescobertas  Array de estado atual das letras descobertas
   * @return true se a letra foi encontrada na palavra, false caso contrário
   */
  private boolean atualizarLetrasDescobertas(char letra, String palavra_minusculas, char[] letrasDescobertas) { // Atualiza letras descobertas
    boolean letraEncontrada = false; // Flag para saber se encontrou a letra. por padrão é false.
    for (int i = 0; i < palavra_minusculas.length(); i++) { // Para cada letra da palavra
      if (palavra_minusculas.charAt(i) == letra) { // Se encontrou a letra
        letrasDescobertas[i] = letra; // Revela a letra
        letraEncontrada = true; // Marca que encontrou
      }
    }
    return letraEncontrada; // Retorna se encontrou ou não
  }

  // Verifica se o jogador venceu
  private boolean verificarVitoria(StringBuilder estadoPalavra, String palavra_minusculas, int indiceJogador, String palavra) { // Verifica vitória
    if (estadoPalavra.toString().equals(palavra_minusculas)) { // Se todas as letras foram descobertas
      System.out.println("Parabéns, " + jogador.getNome(indiceJogador) + "! Você adivinhou a palavra: " + palavra); // Mensagem de vitória
      Menu.pausa(2000); // Pausa
      return true; // Retorna um valor true indicando vitória
    }
    return false; // Não venceu
  }

  // Verifica se o jogador perdeu ou não.
  private boolean verificarDerrota(int erros, String palavra) { // Verifica derrota
    if (erros >= 6) { // Se atingiu o limite de erros
      System.out.println("Você perdeu! A palavra era: " + palavra); // Mensagem de derrota
      Menu.pausa(2000); // Pausa
      return true; // Retorna um valor true indicando derrota
    }
    return false; // Não perdeu
  }
}