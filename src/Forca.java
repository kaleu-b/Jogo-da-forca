/*Now Playing:
 *
 * Candy Truck/You expected: LAB Your Result: GREEN - Bring Me The Horizon
 *             0:01  ❍─────── 4:28
 *            ↻     ⊲  Ⅱ  ⊳     ↺
 *           Volume: ▁▂▃▄▅▆▇ 100%
 */
import java.util.Random;
import java.util.Scanner;

public class Forca extends Menu {
    public Scanner scanner;
    public Jogador jogador = new Jogador();
    public String nome;
    private final Random random = new Random();

    public Forca(Scanner scanner) {
        this.scanner = scanner;
        this.jogador = new Jogador();
    }

    public void iniciarJogo(boolean multiplayer) {
        adivinharPalavra(escolhaPalavra(multiplayer), multiplayer);
    }

    public String escolhaPalavra(boolean multiplayer) {
        String palavra;
        if (multiplayer == true) {
            for (int i = 0; i < jogador.numeroJogadores; i++) {
                System.out.print("Digite o nome do jogador " + (i + 1) + ": ");
                nome = scanner.nextLine();
                jogador.setNome(nome, i);
            }

            jogador.setWordPicker(random.nextInt(jogador.numeroJogadores));
            
            switch (jogador.getWordpicker()) {
                case 1:
                    jogador.setGuesser(0);
                    break;
                default:
                    jogador.setGuesser(1);
                    break;
            }
            System.out.println(jogador.getNome(jogador.getWordpicker()) + ", Escolha uma palavra para " + jogador.getNome(jogador.getGuesser()) + " adivinhar:");
            palavra = scanner.nextLine();
            while (palavra.length() > 20) {
                System.out.println(palavra + " É grande demais. Digite uma palavra menor.");
                palavra = scanner.nextLine();
            }
        } else {
            jogador.setNome("Computador", 0);
            System.out.println("A palavra será escolhida pelo computador.");
            palavra = "exemplo"; //placeholder;
            System.out.print("Digite seu nome: ");
            nome = scanner.nextLine();
            jogador.setNome(nome, 1);
            jogador.setGuesser(1);
        }
        limpaTela();
        return palavra; 
    }

    public void adivinharPalavra(String palavra, boolean multiplayer) {
        char[] letrasDescobertas = inicializarLetrasDescobertas(palavra);
        int erros = 0;
        String palavra_minusculas = palavra.toLowerCase();
        StringBuilder estadoPalavra = new StringBuilder(new String(letrasDescobertas));
        mostraEstado(estadoPalavra, erros);
        boolean jogoAtivo = true;
        int advinhador = jogador.getGuesser();

        while (jogoAtivo) {
            System.out.print(jogador.getNome(advinhador) + ", Digite uma letra ou a palavra completa: ");
            String input = scanner.nextLine().toLowerCase();
            
            while (input.isEmpty()) {
            System.out.print("Erro: vazio. Digite novamente: ");
            String input = scanner.nextLine().toLowerCase();
            }

            if (input.length() > 1) { // If input is a word guess
                if (input.equals(palavra_minusculas)) {
                    // Player guessed the whole word correctly
                    estadoPalavra = new StringBuilder(palavra);
                    mostraEstado(estadoPalavra, erros);
                    System.out.println("Parabéns, " + jogador.getNome(advinhador) + "! Você adivinhou a palavra: " + palavra);
                    jogoAtivo = false;
                    break;
                } else {
                    // Wrong word guess - count as error
                    erros++;
                    Boneco.exibirBoneco(erros);
                    System.out.println("Palavra incorreta! Tente novamente.");
                }
            } else { // If input is a single letter
                char letra = input.charAt(0);
                boolean letraEncontrada = atualizarLetrasDescobertas(letra, palavra_minusculas, letrasDescobertas);

                if (!letraEncontrada) {
                    erros++;
                    Boneco.exibirBoneco(erros);
                } else {
                    estadoPalavra = new StringBuilder(new String(letrasDescobertas));
                }
            }

            mostraEstado(estadoPalavra, erros);

            if (verificarVitoria(estadoPalavra, palavra_minusculas, advinhador, palavra)) {
                jogoAtivo = false;
            } else if (verificarDerrota(erros, palavra)) {
                jogoAtivo = false;
            }
        }
        fimDeJogo(multiplayer);
    }

    private void mostraEstado(StringBuilder estadoPalavra, int erros) {
        System.out.println("Palavra: " + estadoPalavra.toString());
        System.out.println("Erros: " + erros);
    }

    private char[] inicializarLetrasDescobertas(String palavra) {
        char[] letrasDescobertas = new char[palavra.length()];
        for (int i = 0; i < letrasDescobertas.length; i++) {
            if (palavra.charAt(i) == ' ') {
                letrasDescobertas[i] = ' ';
            } else {
                letrasDescobertas[i] = '_';
            }
        }
        return letrasDescobertas;
    }

    private boolean atualizarLetrasDescobertas(char letra, String palavra_minusculas, char[] letrasDescobertas) {
        boolean letraEncontrada = false;
        for (int i = 0; i < palavra_minusculas.length(); i++) {
            if (palavra_minusculas.charAt(i) == letra) {
                letrasDescobertas[i] = letra;
                letraEncontrada = true;
            }
        }
        return letraEncontrada;
    }

    private boolean verificarVitoria(StringBuilder estadoPalavra, String palavra_minusculas, int indiceJogador, String palavra) {
        if (estadoPalavra.toString().equals(palavra_minusculas)) {
            System.out.println("Parabéns, " + jogador.getNome(indiceJogador) + "! Você adivinhou a palavra: " + palavra);
            pausa(1000);
            return true;
        }
        return false;
    }

    private boolean verificarDerrota(int erros, String palavra) {
        if (erros >= 6) {
            System.out.println("Você perdeu! A palavra era: " + palavra);
            pausa(2000);
            return true;
        }
        return false;
    }

    private void fimDeJogo(boolean multiplayer) {
        limpaTela();
        System.out.println("Fim do jogo! Obrigado por jogar.");
        pausa(1000);
        System.out.println("Deseja jogar novamente? (s/n) ou voltar pro menu? (m)");
        String resposta = scanner.nextLine().toLowerCase();

        switch (resposta) {
            case "m":
                break;
            case "s":
                iniciarJogo(multiplayer);
                break;
            case "n":
                System.out.println("Obrigado por jogar! Até a próxima.");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Saindo do jogo.");
                scanner.close();
                System.exit(0);
                break;
        }
    }
}