import java.util.Scanner;

public class Menu extends Forca { // classe Menu que estende a classe Forca, que é a classe principal do jogo da Forca.
    public static void main(String[] args) {
        verMenu(); // chama o método menu para iniciar o menu do jogo.
    }

    public static void verMenu() { // método que exibe o menu do jogo da Forca.
        char opcao;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("---------------Bem-vindo ao jogo da Forca!---------------");
            pausa(1000);
            System.out.println("---------------MENU---------------");
            System.out.println("1 - Jogar"); // opção que inicia o jogo
            System.out.println("2 - Instruções");  // opção que encerra o jogo
            System.out.println("3 - Créditos"); // opção que mostra algumas instruções do jogo
            System.out.println("4 - Sair"); //créditos do jogo
            System.out.println("---------------------------------");
            System.out.print("Escolha uma opção:");
            
            opcao = input.next().charAt(0); 
            
            switch (opcao) {
                case '1':
                        Forca jogo = new Forca(); // cria um objeto da classe Forca
                        jogo.iniciarJogo(); // chama o método iniciarJogo da classe Forca,
                        //System.exit(opcao); // encerra o programa após iniciar o jogo.
                        //método de chamada do jogo. por enquanto não tem nada.
                    break; 
                case '2':
                    System.out.println("Instruções do jogo da Forca:");
                    System.out.println("1. O objetivo é adivinhar a(s) palavra(s) secreta.");
                    System.out.println("2. Você tem 7 tentativas.");
                    System.out.println("3. A cada erro, uma parte do boneco é desenhada.");
                    System.out.println("4. Se o boneco for completado, você perde.");
                    pausa(5000); 
                    break;
                case '3':
                    System.out.println("Créditos:");
                    System.out.println("Desenvolvido por Kaléu Borges");
                    pausa(2000); // pausa de 2 segundos pra dar tempo do usuário ler os créditos.
                    break;
                case '4':
                    System.out.println("Saindo do jogo...");
                    pausa(1500); // pausa de 1.5 segundos pra dar tempo do usuário ler a mensagem.
                    System.exit(0); // encerra o programa
                    break;
                default:
                    System.out.println("Opção inválida!");
                    System.out.flush();
                    break;
            }

        } while (opcao != '4'); // 4 é a opção de sair do jogo, então o loop continua até que o usuário escolha essa opção.
        input.close();
    }
     
    public static void pausa(int tempoPausa) {// pausa o jogo por um tempo para dar tempo do usuário ler as mensagens e deixar o jogo mais fluido. 
        try {
            Thread.sleep(tempoPausa); // pausa o programa por um tempo especificado em milissegundos. 1000 milisegundos = 1 segundo, e por aí vai.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}