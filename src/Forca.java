import java.util.Scanner;
import java.util.Random;

public class Forca {
    // criação do scanner para entrada de dados.
    public Scanner scanner = new Scanner(System.in);
     public Jogador jogador = new Jogador();
    
     public void iniciarJogo(boolean multiplayer) {    
        Jogadores(); // inicia o jogo pedindo os nomes dos jogadores
        escolhaPalavra(multiplayer);
    
    }

    public void Jogadores(){
      //  Scanner scanner = new Scanner(System.in); // Cria um scanner para ler a entrada do usuário

        // loop que pede o nome dos jogadores 
        for (int i = 0; i < jogador.numeroJogadores; i++) {
        
            System.out.print("Digite o nome do jogador " + (i + 1) + ": ");
            String nome = scanner.nextLine(); // Read player name from input
            jogador.setNome(nome, i); // Set the player's name in the class-level Jogador instance
        
        }

        // Mostra os nomes dos jogadores em loop. função de debug meramente
        
        for (int i = 0; i < jogador.numeroJogadores; i++) {
        
            System.out.println("Jogador " + (i + 1) + ": " + jogador.getNome(i));
            System.out.println(jogador.getNome(i)); // Display player points
        
        }
    }

    public void escolhaPalavra(boolean multiplayer) {
        
    //    Scanner scanner = new Scanner(System.in);
        String palavra; // variável para armazenar a palavra escolhida pelo jogador ou pela máquina
        
        if (multiplayer == true) {  // se o jogo for multiplayer...
            
            Random random = new Random();
            int vezJogador = random.nextInt(jogador.numeroJogadores); // Escolhe aleatoriamente o jogador que vai escolher a palavra
            System.out.println(jogador.getNome(vezJogador) + ", Escolha uma palavra para o amigo adivinhar:");
            palavra = scanner.nextLine(); // lê uma palavra do usuário
        
        } else {  // Modo singleplayer - palavra escolhida pelo computador
        
            System.out.println("A palavra será escolhida pelo computador.");
            palavra = "exemplo"; // Placeholder, vou adicionar mais palavras depois.
        
        }
    }

    public void adivinharPalavra(String palavra) {
        int erros = 0; // Initialize error count
        String palavra_minusculas = palavra.toLowerCase(); // Convert the word to lowercase for consistency
        StringBuilder estadoPalavra = new StringBuilder("_".repeat(palavra.length())); //
    }

}