
import java.util.Random; // Importa a classe Random para gerar números aleatórios
import java.util.Scanner; // Importa a classe Scanner para ler entradas do usuário

public class Forca extends Menu {
    public Scanner scanner; // Scanner compartilhado com o Menu para economia de recursos/menos dor de cabeça.
    public Jogador jogador = new Jogador(); // Cria uma instância da classe Jogador.
    public String nome; // Variável que vai ser usada pra passa o nome do(s) jogador(es).
 // Objeto Random que vai ser usado pra escolher uma palavra aleatória (singleplayer) ou pra escolher o picker e o guesser (multiplayer).
    private final Random random = new Random();

    // Construtor que recebe o Scanner do Menu
    public Forca(Scanner scanner) {
        this.scanner = scanner; // Usa o Scanner passado pelo Menu
        this.jogador = new Jogador(); // Inicializa o objeto Jogador
    }

    // Inicia o jogo, pedindo a palavra e chamando o método de adivinhação
    public void iniciarJogo(boolean multiplayer) {
    /*Método que inicia a parte de adivinhar a palavra precisa de dois parâmetro:
    * A palavra escolhida e se é multiplayer ou não.
    * Então, no caso do parâmetro palavra, é a saída do método escolha palavra.
    * O segundo parâmetro, multiplayer, indica se a partida é contra um amigo ou contra o computador.
    * o método advinharPalavra recebe esses dois parâmetros e inicia a lógica de adivinhação.
    */  
    
    adivinharPalavra(escolhaPalavra(multiplayer), multiplayer); // Pega a palavra e inicia a lógica de adivinhação
    }

    // Método para escolher a palavra do jogo, dependendo se é multiplayer ou singleplayer.
    public String escolhaPalavra(boolean multiplayer) {
        String palavra; // Variável para armazenar a palavra escolhida
        if (multiplayer == true) { // Se for multiplayer
            for (int i = 0; i < jogador.numeroJogadores; i++) { // Para cada jogador (nesse caso 2, mas pode ser escalonado para mais)
                System.out.print("Digite o nome do jogador " + (i + 1) + ": "); // Pede o nome de cada jogador
                nome = scanner.nextLine(); // Lê o nome dele
                jogador.setNome(nome, i); // Salva o nome no array
            }

            jogador.setWordPicker(random.nextInt(jogador.numeroJogadores)); // Sorteia quem escolhe a palavra (número entre 0 e 1)
            // Define quem será o adivinhador
            switch (jogador.getWordpicker()) {
                case 1:
                    jogador.setGuesser(0); // Se o picker é o jogador 1, o guesser é o 0.
                    break;
                default:
                    jogador.setGuesser(1); // Caso contrário, o guesser é o 1.
                    break;
            }
        //pede pro picker escolher a palavra.
            System.out.print(jogador.getNome(jogador.getWordpicker()) + ", Escolha uma palavra para " + jogador.getNome(jogador.getGuesser()) + " adivinhar: "); // Pede a palavra
            palavra = scanner.nextLine(); // Lê a palavra
        // Limita o tamanho da palavra pra até 20 caracteres. Enquanto ela não for <20, pede pra digitar de novo.
            while (palavra.length() > 20) { 
                System.out.print(palavra + " É grande demais. Digite uma palavra menor: ");
                palavra = scanner.nextLine();
            }
        } else { // Se for singleplayer
            jogador.setNome("Computador", 0); // Define o nome do jogador 1 como computador
            System.out.println("A palavra será escolhida pelo computador.");
            // Lista de palavras possíveis, que serão escolhidas aleatoriamente.
            String palavraaleatoria[] = {"Java", "Ramon", "Análise de Sistemas", "São José", "IFSC", "Palhoça"}; 
            palavra = palavraaleatoria[random.nextInt(palavraaleatoria.length)]; // Escolhe aleatoriamente uma palavra da lista
            System.out.print("Digite seu nome: "); // pede o nome do jogador.
            nome = scanner.nextLine(); // Lê o nome do jogador
            jogador.setNome(nome, 1); // Salva o nome
            jogador.setGuesser(1); // O jogador será o adivinhador
        }
        limpaTela(); // Limpa a tela antes de começar
        return palavra; // Retorna a palavra escolhida
    }

    // Método principal de adivinhação da palavra
    public void adivinharPalavra(String palavra, boolean multiplayer) {
        char[] letrasDescobertas = inicializarLetrasDescobertas(palavra); // Inicializa o array de letras descobertas
        int erros = 0; // Contador de erros
        String palavra_minusculas = palavra.toLowerCase(); // Palavra escolhida em minúsculas para facilitar a comparação
        StringBuilder estadoPalavra = new StringBuilder(new String(letrasDescobertas)); // Estado atual da palavra
        mostraEstado(estadoPalavra, erros); // Mostra o estado da palavra
        boolean jogoAtivo = true; // Flag para controlar o loop do jogo
        int advinhador = jogador.getGuesser(); // Índice do jogador que vai adivinhar

        while (jogoAtivo) { // Loop principal do jogo
            System.out.print(jogador.getNome(advinhador) + ", Digite uma letra ou a palavra completa: "); // Pede uma letra ou palavra
            String input = scanner.nextLine().toLowerCase(); // Lê a entrada e converte para minúsculas
            // se a entrada for vazia, pede pro jogadore digitar mais uma vez.
            while (input.isEmpty()) { 
                System.out.print("Erro: vazio. Digite novamente: "); 
                input = scanner.nextLine().toLowerCase();
            }
        // Se o tamanho do input tiver um tamanho maior que 1, vamos considerar que é uma tentativa de advinhar a palavra inteira.
            if (input.length() > 1) { 
                if (input.equals(palavra_minusculas)) { // Se acertou a palavra inteira
                    estadoPalavra = new StringBuilder(palavra); // Revela a palavra
                    mostraEstado(estadoPalavra, erros); // Mostra o estado final
                    // Mensagem de vitória
                    System.out.println("Parabéns, " + jogador.getNome(advinhador) + "! Você adivinhou a palavra: " + palavra); 
                    pausa(2000); //pausa para dar tempo do jogador ler.
                    jogoAtivo = false; // Encerra o jogo
                    break;
                } else { // Se errou a palavra
                    erros++; // Conta erro
                    Boneco.exibirBoneco(erros); // Mostra o boneco
                    System.out.println("Palavra incorreta! Tente novamente."); // Mensagem de erro
                }
            } else { // Se for uma letra (tamanho do input  = 1)
                char letra = input.charAt(0); // Pega a letra (garantimos que a entrada seja transformada em um char antes de passar pro método)
            // condição que determina se uma letra foi ou não encontrada.
                boolean letraEncontrada = atualizarLetrasDescobertas(letra, palavra_minusculas, letrasDescobertas); 
            // se não foi:
                if (!letraEncontrada) { // Se não encontrou a letra
                    erros++; // Conta +1 erro
                    Boneco.exibirBoneco(erros); // Mostra o boneco
                } else { //se foi encontrada:
                    Boneco.exibirBoneco(erros); // Mostra o boneco mesmo se a letra foi encontrada, para manter uma consistência
                    estadoPalavra = new StringBuilder(new String(letrasDescobertas)); // Atualiza o estado da palavra
                }
            }

            mostraEstado(estadoPalavra, erros); // Mostra o estado após a jogada
            // essa condição encerra o jogo em dois casos.
            // o  1°: se o jogador advinhou a palavra inteira.
            // o 2° : se o jogador atingiu o limite de erros (6).
            // se uma dessas duas condições forem verdadeiras, o jogo é encerrado.
            // no caso, o loop é interrompido e o método fimDeJogo é chamado.
            if (verificarVitoria(estadoPalavra, palavra_minusculas, advinhador, palavra)) { 
                jogoAtivo = false;
            } else if (verificarDerrota(erros, palavra)) { // Verifica derrota
                jogoAtivo = false;
            }
        }
        fimDeJogo(multiplayer); // Chama o fim de jogo
    }

    // Mostra o estado atual da palavra e o número de erros
    private void mostraEstado(StringBuilder estadoPalavra, int erros) {
        System.out.println("Palavra: " + estadoPalavra.toString()); // Mostra a palavra com letras descobertas
        System.out.println("Erros: " + erros); // Mostra o número de erros
    }

    // Inicializa o array de letras descobertas com '_' ou espaço.
    // verifica cada caractere da palavra, e se for uma letra, preenche o array com '_'.
    // se for um espaço, preenche com espaço ' '.
    // é feito assim pros espaços entre as letras não aparecerem como '_', mas sim como espaços mesmo.
    // ex: palavra = "Pão de queijo" -> ___ __ ______
    // se não trocássemos os 
    private char[] inicializarLetrasDescobertas(String palavra) {
        char[] letrasDescobertas = new char[palavra.length()]; // Cria o array
        for (int i = 0; i < letrasDescobertas.length; i++) { // Para cada letra
            if (palavra.charAt(i) == ' ') { // Se for espaço
                letrasDescobertas[i] = ' '; // Revela espaço
            } else {
                letrasDescobertas[i] = '_'; // Caso contrário, esconde
            }
        }
        return letrasDescobertas; // Retorna o array
    }

    // Atualiza o array de letras descobertas com a letra digitada
    private boolean atualizarLetrasDescobertas(char letra, String palavra_minusculas, char[] letrasDescobertas) {
        boolean letraEncontrada = false; // Flag para saber se encontrou ou não uma letra.
        //se sim:
        //pra cada letra da palavra, se a letra digitada for igual a letra da palavra, revela a letra no array.
        // ex: palavra = "java", o jogador digita 'a', o array fica: _a_a.
        for (int i = 0; i < palavra_minusculas.length(); i++) { // Para cada letra
            if (palavra_minusculas.charAt(i) == letra) { // Se encontrou
                letrasDescobertas[i] = letra; // Revela a letra (troca o '_' pela letra)
                letraEncontrada = true; // muda o valor da flag para true, indicando que a letra foi encontrada
            }
        }
        return letraEncontrada; // Retorna se encontrou ou não
    }

    // Verifica se o jogador venceu
    private boolean verificarVitoria(StringBuilder estadoPalavra, String palavra_minusculas, int indiceJogador, String palavra) {
        if (estadoPalavra.toString().equals(palavra_minusculas)) { // Se todas as letras foram descobertas
            System.out.println("Parabéns, " + jogador.getNome(indiceJogador) + "! Você adivinhou a palavra: " + palavra); // Mensagem de vitória
            pausa(2000); // Pausa para leitura
            return true; // Retorna vitória
        }
        return false; // Não venceu ainda
    }

    // Verifica se o jogador perdeu
    private boolean verificarDerrota(int erros, String palavra) {
        if (erros >= 6) { // Se atingiu o limite de erros
            System.out.println("Você perdeu! A palavra era: " + palavra); // Mensagem de derrota
            pausa(2000); // Pausa para leitura
            return true; // Retorna derrota
        }
        return false; // Não perdeu ainda
    }

    // Finaliza o jogo e pergunta se o jogador quer jogar novamente
    private void fimDeJogo(boolean multiplayer) {
        limpaTela(); // Limpa a tela
        System.out.println("Fim do jogo! Obrigado por jogar."); // Mensagem final
        pausa(1000); // Pausa
        char resposta;
do {
    System.out.print("Deseja jogar novamente? (s/n) ou voltar pro menu? (m) "); // Pergunta ao jogador se ele quer voltar pro menu, jogar de novo, ou sair
    String input = scanner.nextLine().toLowerCase();
    while (input.isEmpty()) { //se a entrada for vazia, pede pro jogador digitar de novo até não ser mais vazia.
        System.out.print("Entrada vazia. Digite novamente: ");
        input = scanner.nextLine().toLowerCase();
    }
    resposta = input.charAt(0); // Lê a resposta
    switch (resposta) { // Trata a resposta
            case 'm':
            verMenu(); //volta pro menu. 
            break; 
            case 's':
                iniciarJogo(multiplayer); // Reinicia o jogo (é tipo uma revanche)
                break;
            case 'n':
                System.out.println("Obrigado por jogar! Até a próxima."); // Mensagem de despedida
                scanner.close(); // Fecha o scanner
                System.exit(0); // Encerra o programa
                break;
            default:
                System.out.println("Opção inválida. Digite novamente."); // Mensagem de erro
                break;
        }
    
    }while (resposta != 'n' && resposta != 'm'); //continua enquanto a resposta não for 'n' e 'm'
        
}
}