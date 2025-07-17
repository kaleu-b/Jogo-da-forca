public class Jogador {
    String[] nome = new String[2]; // Array to hold player names
    //int[] pontos = new int[2]; // Array to hold player scores
    int numeroJogadores = 2; // Number of players in the game
    //int[] vidaJogadores = new int[2]; // Array to hold players' lives

    /*
     * métodos e variáveis desativadas por comentários são funcionalidades que podem ser implementadas no futuro.
     * rodadas, pontuação e vidas dos jogadores.
     * infelizmente por questões de tempo e complexidade, não irei implementar essas funcionalidades agora.
     * mas estão aqui para referência futura.
     * não faço idéia se vou realmente implementar tudo isso uma hora, mas é uma possibilidade.
     * se vc estiver lendo isso e for implementar, será necessário criar uma lógica para gerenciar as rodadas, pontos dos jogadores, vidas dos jogadores, etc.
     * isso pode ser feito com um loop que controla as rodadas, atualiza os pontos, etc.
     * eu tenho mais ou menos um esboço mental pra isso e tenho escrito em papel. mas implementar isso na prática e em lógica de programação......
     */


    // esse método iniciaria os jogadores com 6 vidas e 0 pontos.
    // mas como não implementei a lógica de rodadas, pontos e vidas, então..... é.........

    //public void jogador() {
        // inicializa os jogadores sem pontuação e com 6 vidas.
       /*  for (int i = 0; i < numeroJogadores; i++) {
            pontos[i] = 0; // Default score is 0
            vidaJogadores[i] = 6; // Default lives for each player
        }
    }*/




    public void setNome(String nome, int idJogador) {
        this.nome[idJogador] = nome; // define o nome do jogador baseado no ID [0 ou 1] (terá que ser alteradp caso quantidades maiores de jogadores sejam implementadas)  
    }

    public String getNome(int idJogador) {
        return this.nome[idJogador]; // retorna o nome do jogador baseado no ID [0 ou 1] (terá que ser alteradp caso quantidades maiores de jogadores sejam implementadas)
    }

    // muda a quantidade pontos. não foi inplementado!

    /*public void setPontos(int pontos, int idJogador) {
        this.pontos[idJogador] = pontos; // Set the player's score based on their ID
    }
    
    // retorna a quantidade de pontos do jogador. não foi inplementado!
    
    public int getPontos(int idJogador) {
        return this.pontos[idJogador]; // Get the player's score based on their ID
    }

    // muda a quantidade de vidas do jogador. não foi inplementado!

    public void setVidaJogadores(int vida, int idJogador) {
        this.vidaJogadores[idJogador] = vida; // Set the player's lives based on their ID
    }*/
}

