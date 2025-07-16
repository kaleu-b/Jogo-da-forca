public class Jogador {
    String[] nome = new String[2]; // Array to hold player names
    int[] pontos = new int[2]; // Array to hold player scores
    int numeroJogadores = 2; // Number of players in the game
    int[] vidaJogadores = new int[2]; // Array to hold players' lives

    public void jogador() {
        // inicializa os jogadores sem pontuação e com 6 vidas.
        for (int i = 0; i < numeroJogadores; i++) {
            pontos[i] = 0; // Default score is 0
            vidaJogadores[i] = 6; // Default lives for each player
        }
    }

    public void setNome(String nome, int idJogador) {
        this.nome[idJogador] = nome; // Set the player's name based on their ID    
    }

    public String getNome(int idJogador) {
        return this.nome[idJogador]; // Get the player's name based on their ID
    }

    public void setPontos(int pontos, int idJogador) {
        this.pontos[idJogador] = pontos; // Set the player's score based on their ID
    }

    public int getPontos(int idJogador) {
        return this.pontos[idJogador]; // Get the player's score based on their ID
    }

    public void setVidaJogadores(int vida, int idJogador) {
        this.vidaJogadores[idJogador] = vida; // Set the player's lives based on their ID
    }
}

