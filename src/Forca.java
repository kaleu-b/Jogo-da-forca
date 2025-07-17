import java.util.Scanner;
import java.util.Random;

public class Forca extends Menu {
    public Scanner scanner; // passa o scanner da classe Menu pra classe Forca, pra não precisar criar mais um objeto
    public Jogador jogador = new Jogador();
    public String nome;

    public Forca(Scanner scanner) {
        this.scanner = scanner;
        this.jogador = new Jogador();
    }

    public void iniciarJogo(boolean multiplayer) {
        String palavra = escolhaPalavra(multiplayer);
        adivinharPalavra(palavra, multiplayer);
    }


    public String escolhaPalavra(boolean multiplayer) {
        String palavra = "";
        if (multiplayer == true) {
            for (int i = 0; i < jogador.numeroJogadores; i++) {
                System.out.print("Digite o nome do jogador " + (i + 1) + ": ");
                nome = scanner.nextLine();
                jogador.setNome(nome, i);
            }
            Random random = new Random();
            int vezJogador = random.nextInt(jogador.numeroJogadores);
            System.out.println(jogador.getNome(vezJogador) + ", Escolha uma palavra para o amigo adivinhar:");
            palavra = scanner.nextLine();
           // System.out.println("Palavra escolhida: " + palavra);
        } else {
            jogador.setNome("Computador", 0);
            System.out.println("A palavra será escolhida pelo computador.");
            palavra = "exemplo";
            System.out.print("Digite seu nome: ");
            nome = scanner.nextLine();
            jogador.setNome(nome, 1);
            //System.out.println("Palavra escolhida: " + palavra);
        }
        return palavra;
    }

    public void adivinharPalavra(String palavra, boolean multiplayer) {
        char[] letrasDescobertas = inicializarLetrasDescobertas(palavra);
        int erros = 0;
        String palavra_minusculas = palavra.toLowerCase();
        StringBuilder estadoPalavra = new StringBuilder(new String(letrasDescobertas));
        System.out.println("Estado atual: " + estadoPalavra.toString());
        System.out.println("Erros: " + erros);
        boolean jogoAtivo = true;
        int indiceJogador = jogador.numeroJogadores > 1 ? 1 : 0;

        while (jogoAtivo) {
            char letra = lerLetra(indiceJogador);
            boolean letraEncontrada = atualizarLetrasDescobertas(letra, palavra_minusculas, letrasDescobertas);

            if (!letraEncontrada) {
                erros++;
                Boneco.exibirBoneco(erros);
            } else {
                estadoPalavra = new StringBuilder(new String(letrasDescobertas));
            }

            System.out.println("Estado atual: " + estadoPalavra.toString());
            System.out.println("Erros: " + erros);

            if (verificarVitoria(estadoPalavra, palavra_minusculas, indiceJogador, palavra)) {
                jogoAtivo = false;
            } else if (verificarDerrota(erros, palavra)) {
                jogoAtivo = false;
            }
        }
        fimDeJogo(multiplayer);
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

    private char lerLetra(int indiceJogador) {
        System.out.print(jogador.getNome(indiceJogador) + ", Digite uma letra: ");
        return scanner.nextLine().toLowerCase().charAt(0);
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
             Forca.pausa(1000);
            return true;
        }
        return false;
    }
    private boolean verificarDerrota(int erros, String palavra) {
        if (erros >= 6) {
            System.out.println("Você perdeu! A palavra era: " + palavra);
            Forca.pausa(1000);
            return true;
        }
        return false;
    }

    private void fimDeJogo(boolean multiplayer) {
        System.out.println("Fim do jogo! Obrigado por jogar.");
        Forca.pausa(1000);
        System.out.println("Deseja jogar novamente? (s/n) ou voltar pro menu? (m)");
        String resposta = scanner.nextLine().toLowerCase();
      
        switch (resposta) {
            case "m":
            //não precisa fazer nada. o jogo roda no loop do menu.
            break;            
            case "s":
                iniciarJogo(multiplayer); // Reinicia o jogo
                break;
            case "n":
                System.out.println("Obrigado por jogar! Até a próxima.");
                scanner.close(); // Fecha o scanner para liberar recursos
                System.exit(0); // Encerra o programa
                break;
            default:
                System.out.println("Opção inválida. Saindo do jogo.");
                scanner.close();
                System.exit(0);
                break;
        }
    }

}